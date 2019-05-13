<template>
  <div class="dashboard-container">
    <el-row>
      <panel-group @handleSetMsgView="handleSetMsgView"/>
    </el-row>
    <el-row>
      <el-tabs type="border-card">
        <el-tab-pane :label="msgType">
          <el-table :data="showTaskMsgList">
            <el-table-column prop="taskId" v-if="false"></el-table-column>
            <el-table-column prop="processInstanceId" v-if="false"></el-table-column>
            <el-table-column prop="taskName" label="任务名称"></el-table-column>
            <el-table-column label="操作栏" width="180">
              <template slot-scope="scope">
                <el-button v-if="msgType=='任务认领'" @click="userClaimTaskHandle(scope.row)">认领</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PanelGroup from './components/PanelGroup'
import { listUserTask, userClaimTask } from '@/api/flowprocess/flowprocess'

export default {
  name: 'Dashboard',
  components: { PanelGroup },
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  data() {
    return {
      msgType: '待办事项',
      showTaskMsgList: [],
      // 待办任务
      todoUserTaskList: [],
      // 待认领任务
      claimUserTaskList: []
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
            }
          }
        } else {
          this.$message({ message: `操作失败:${response.message}`, type: 'error' })
        }
        this.showTaskMsgList = this.todoUserTaskList
        this.handleSetMsgView('待办事项')
      })
    },
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
    restTemp() {
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
