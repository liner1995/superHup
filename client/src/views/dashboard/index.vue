<template>
  <div class="dashboard-container">
    <el-row>
      <panel-group 
        :todoUserTaskLength="todoUserTaskLength" 
        :claimUserTaskLength="claimUserTaskLength" 
        :overUserTaskLength="overUserTaskLength"
        @handleSetMsgView="handleSetMsgView"/>
    </el-row>
    <el-row>
      <el-tabs type="border-card">
        <el-tab-pane :label="msgType">
          <el-table :data="showTaskMsgList">
            <el-table-column prop="taskId" v-if="false"></el-table-column>
            <el-table-column prop="processInstanceId" v-if="false"></el-table-column>
            <el-table-column prop="taskName" label="任务名称"></el-table-column>
            <el-table-column label="操作栏" width="420">
              <template slot-scope="scope">
                <el-button @click="findFlowImage(scope.row)">查看流程</el-button>
                <el-button v-if="msgType!='任务认领'" @click="findBillPageByFlow(scope.row)">查看单据</el-button>
                <el-button v-if="msgType=='任务认领'" @click="userClaimTaskHandle(scope.row)">认领</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-row>

    <!--流程图Dialog-->
    <FlowDialog
      :title="flowImageData.title"
      :visiable="flowImageData.visiable"
      :flowPrice="flowImageData.flowPrice"
      @close="closeFlowImageDialog()"></FlowDialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PanelGroup from './components/PanelGroup'
import FlowDialog from './components/FlowDialog'
import { listUserTask, userClaimTask, findFlowImage } from '@/api/flowprocess/flowprocess'

export default {
  name: 'Dashboard',
  components: { PanelGroup, FlowDialog },
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  data() {
    return {
      msgType: '待办事项',
      flowImageData: {
        visiable: false,
        flowPrice: '',
        title: '流程图'
      },
      showTaskMsgList: [],
      // 待办任务
      todoUserTaskList: [],
      todoUserTaskLength: 0,
      // 待认领任务
      claimUserTaskList: [],
      claimUserTaskLength: 0,
      // 已办事项
      overUserTaskList: [],
      overUserTaskLength: 0
    }
  },
  created() {
    this.initUserTaskList()
  },
  methods: {
    handleSetMsgView(type) {
      if (type === '待办事项') {
        this.showTaskMsgList = this.todoUserTaskList
      } else if (type === '任务认领') {
        this.showTaskMsgList = this.claimUserTaskList
      } else if (type === '已办事项') {
        this.showTaskMsgList = this.overUserTaskList
      } else {
        this.showTaskMsgList = []
      }
      this.msgType = type
    },
    // 查询用户待办/待认领任务列表
    initUserTaskList() {
      this.restTemp()
      let queryTaskListParam = { userid: this.$store.getters.userid }
      listUserTask(queryTaskListParam).then((response) => {
        if (response.success && response.object) {
          let userTaskList = response.object
          for (let index in userTaskList) {
            let userTaskOne = userTaskList[index]
            if (userTaskOne) {
              // 待办任务
              if (userTaskOne.taskType === 'todo') {
                this.todoUserTaskList.push(userTaskOne)
              }
              // 待认领任务
              if (userTaskOne.taskType === 'claim') {
                this.claimUserTaskList.push(userTaskOne)
              }
              // 已办事项
              if (userTaskOne.taskType === 'over') {
                this.overUserTaskList.push(userTaskOne)
              }
            }
          }
          // 统计首页信息数量
          // 待办任务
          this.todoUserTaskLength = this.todoUserTaskList == null ? 0 : this.todoUserTaskList.length
          // 待认领任务
          this.claimUserTaskLength = this.claimUserTaskList == null ? 0 : this.claimUserTaskList.length
          // 已办任务
          this.overUserTaskLength = this.overUserTaskList == null ? 0 : this.overUserTaskList.length
        } else {
          this.$message({ message: `操作失败:${response.message}`, type: 'error' })
        }
        this.showTaskMsgList = this.todoUserTaskList
        this.handleSetMsgView('待办事项')
      })
    },
    // 认领任务
    userClaimTaskHandle(row) {
      let claimParam = {
        userid: this.$store.getters.userid,
        taskId: row.taskId,
        taskName: row.taskName,
        processInstanceId: row.processInstanceId
      }
      this.$confirm('是否确定认领该任务?', '提示', {
      }).then(() => {
        userClaimTask(claimParam).then((response) => {
          if (response.success) {
            this.initUserTaskList()
            this.$message({ type: 'success', message: '认领成功' })
          } else {
            this.$message({ message: `操作失败:${response.message}`, type: 'error' })
          }
        })
      }).catch(() => {
        this.$message({ type: 'info', message: '已取消认领' })
      })
    },
    // 查看流程
    findFlowImage(row) {
      findFlowImage(row).then((response) => {
        let url = window.URL.createObjectURL(response.data)
        if (url) {
          this.flowImageData.flowPrice = url
          this.flowImageData.visiable = true
        } else {
          this.flowImageData.flowPrice = ''
          
        }
      }).catch(err => {
        this.$message({ message: '图片加载失败!', type: 'error' })
      })
    },
    // 关闭流程图Dialog
    closeFlowImageDialog() {
      this.flowImageData.visiable = false
    },
    // 通过任务信息查看具体单据
    findBillPageByFlow(row) {
      let taskName = row.taskName
      if (taskName) {
         let nameSplit = taskName.split('_')
         if (nameSplit && nameSplit.length > 0) {
           let showTaskName = nameSplit[0]
           // 采购报价单据
           if (showTaskName === '采购报价') {
            this.$router.push({path: 'MENU63907/MENU38889'})
           }
           // 工艺审核单据
           if (showTaskName === '工艺审核') {
             this.$router.push({path: 'MENU3252/MENU32036'})
           }
           // 生产报价单据
           if (showTaskName === '生产报价') {
             this.$router.push({path: 'MENU61719/MENU35235'})
           }
           // 销售报价单据
           if (showTaskName === '销售报价') {
             this.$router.push({path: 'MENU36962/MENU25210'})
           }
         }
      }
      
    },
    restTemp() {
      // 流程图
      this.flowImageData.flowPrice = ''
      this.showTaskMsgList = []
      // 待办任务
      this.todoUserTaskList = []
      // 待认领任务
      this.claimUserTaskList = []
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
