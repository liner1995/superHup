<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="temp.roleName"></el-input>
      </el-form-item>
      <el-form-item label="角色编码" prop="roleCode">
        <el-input v-model="temp.roleCode" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="角色类型" prop="roleType">
        <el-input v-model="temp.roleType"></el-input>
      </el-form-item>
      <el-form-item label="角色描述" prop="roleMemo">
        <el-input type="textarea" v-model="temp.roleMemo"></el-input>
      </el-form-item>
      <el-form-item label="创建人" prop="creator">
        <el-input v-model="temp.creator" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="创建时间" prop="creationtime">
        <el-input v-model="temp.creationtime" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="修改人" prop="modifier">
        <el-input v-model="temp.modifier" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="最后修改时间" prop="modifiedtime">
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

export default {
  name: 'Smrolecontent',
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
        pkRole: undefined,
        roleName: '',
        roleCode: 'ROLE' + randomNum(100*1000),
        roleMemo: '',
        roleType: '',
        pkOrg: '',
        pkGroup: '',
        creator: '',
        creationtime: parseTime(new Date()),
        modifier: '',
        modifiedtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      }
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
            actTemp.pkRole = response.primary
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
        pkRole: undefined,
        roleName: '',
        roleCode: 'ROLE' + randomNum(100*1000),
        roleMemo: '',
        roleType: '',
        pkOrg: '',
        pkGroup: '',
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
