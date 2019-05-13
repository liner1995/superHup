<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <el-form-item label="用户名称" prop="userName">
        <el-input v-model="temp.userName"></el-input>
      </el-form-item>
      <el-form-item label="用户编码" prop="userCode">
        <el-input v-model="temp.userCode" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="用户密码" prop="userPassword">
        <el-input v-model="temp.userPassword" type="password"></el-input>
      </el-form-item>

      <el-form-item label="关联人员" prop="pkPsndoc">
        <el-select v-model="temp.pkPsndoc" placeholder="请选择">
          <el-option
            v-for="item in psndocOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="Email" prop="email">
        <el-input v-model="temp.email"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="temp.phone"></el-input>
      </el-form-item>

      <el-form-item label="是否锁定" prop="islocked">
        <el-select v-model="temp.islocked" placeholder="请选择">
          <el-option
            v-for="item in yesOrNoOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否启用" prop="enablestate">
        <el-select v-model="temp.enablestate" placeholder="请选择">
          <el-option
            v-for="item in validOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="加密锁号" prop="lockNum">
        <el-input v-model="temp.lockNum"></el-input>
      </el-form-item>
      <el-form-item label="用户描述" prop="memo">
        <el-input type="textarea" :rows="2" v-model="temp.memo"></el-input>
      </el-form-item>
      <el-form-item label="创建人" prop="creator">
        <el-input v-model="temp.creator" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="创建时间" prop="creationtime">
        <el-input v-model="temp.creationtime" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="创建人" prop="modifier">
        <el-input v-model="temp.modifier" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="创建时间" prop="modifiedtime">
        <el-input v-model="temp.modifiedtime" :disabled="true"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="sureDialog">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { initReplaceUpdateData, initReplaceAddData, parseTime, randomNum } from '@/utils/viewCompUtil'
import { listBdPsndocAsRef } from '@/api/orgs/bdpsndoc'

export default {
  name: 'Smusercontent',
  props: ["replace", "title", "visiable", 'action', 'updateRow'],
  computed: {
    ...mapGetters([
      'listData', 'columns'
    ])
  },
  data() {
    return {
      contentVisiable: false, // dialog 显示框
      temp: {
        userid: undefined,
        userName: '',
        userCode: 'USER' + randomNum(100*1000),
        userPassword: '',
        memo: '',
        email: '',
        phone: '',
        islocked: '0',
        enablestate: '0',
        pkPsndoc: '',
        lockNum: '',
        randomNum: '',
        creator: '',
        creationtime: parseTime(new Date()),
        modifier: '',
        modifiedtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
      yesOrNoOption: [
        { value: '0', label: '否' },
        { value: '1', label: '是' }
      ],
      validOption: [
        { value: '0', label: '启用' },
        { value: '1', label: '未启用' }
      ],
      // 人员信息参照
      psndocOption: []
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
      if (this.title === '录入') {
        this.restTemp()
      }
      if (this.title === '修改' && this.updateRow) {
        this.temp = this.updateRow
      }
    }
  },
  created() {
    // 初始人员信息参照
    listBdPsndocAsRef().then((response) => {
      this.psndocOption = response.object
    })
  },
  methods: {
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      const actTemp = this.temp
      if (this.action) {
        initReplaceUpdateData(actTemp, this.replace)
        this.action(actTemp).then((response) => {
          if (response.primary) {
            actTemp.userid = response.primary
          }
          if (response.success) {
            initReplaceAddData(actTemp, this.replace)
            this.$store.commit('HANDLE_LISTVIEW_DATA', actTemp)
            this.$message({ message: '操作成功', type: 'success' })
          } else {
            this.$message({ message: `操作失败:${response.message}`, type: 'error' })
          }
        })
      }
      this.$emit('close', actTemp)
    },
    restTemp() { // 刷新本界面的数据
      this.temp = {
        userid: undefined,
        userName: '',
        userCode: 'USER' + randomNum(100*1000),
        userPassword: '',
        memo: '',
        email: '',
        phone: '',
        islocked: '0',
        enablestate: '0',
        pkPsndoc: '',
        lockNum: '',
        randomNum: '',
        creator: '',
        creationtime: parseTime(new Date()),
        modifier: '',
        modifiedtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      }
    }
  }
}
</script>

<style>

</style>
