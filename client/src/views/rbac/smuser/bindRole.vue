<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <div style="text-align: center">
      <el-transfer
        style="text-align: left; display: inline-block"
        v-model="transfer.userHaveRole"
        filterable
        :titles="['角色列表', '用户拥有角色']"
        :format="{
          noChecked: '${total}',
          hasChecked: '${checked}/${total}'
        }"
        :data="transfer.userRoleData">
        <span slot-scope="{ option }">{{ option.label }}</span>
      </el-transfer>
    </div>
    
    <span slot="footer" class="dialog-footer" style="margin-left: 75%;">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="sureDialog">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { initReplaceUpdateData, initReplaceAddData, parseTime, randomNum } from '@/utils/viewCompUtil'
import { listSmRoleAsTransfer } from '@/api/rbac/smrole'
import { listUserRoleByUserid, addUserRole } from '@/api/rbac/smuserrole'

export default {
  name: 'BindRole',
  props: ["title", "visiable", 'updateRow'],
  computed: {
    ...mapGetters([
      'listData', 'columns'
    ])
  },
  data() {
    return {
      contentVisiable: false, // dialog 显示框
      transfer: {
        userRoleData: [],
        userHaveRole: []
      }
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
      this.restTemp()
      this.initPageData()
    }
  },
  methods: {
    // 根据用户初始信息
    initPageData() {
      // 初始所有角色数据
      listSmRoleAsTransfer().then((response) => {
        this.transfer.userRoleData = response.object
      })
      // 查询用户已有角色
      let queryParam = {userid: this.updateRow.userid}
      listUserRoleByUserid(queryParam).then((response) => {
        if (response.object) {
          this.transfer.userHaveRole = response.object.idArr
        }
      })
    },
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      const userid = this.updateRow.userid
      const userRoleArr = this.transfer.userHaveRole.toString()
      let saveParam = {userid: userid, userRoleArr: userRoleArr}
      addUserRole(saveParam).then((response) => {
        this.$message({ message: '操作成功', type: 'success' })
      })
      this.$emit('close', null)
    },
    restTemp() {
      this.transfer.userRoleData = []
      this.transfer.userHaveRole = []
    }
  }
}
</script>

<style>

</style>
