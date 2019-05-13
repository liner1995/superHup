<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <el-form-item label="名称" prop="tradename">
        <el-input v-model="temp.tradename"></el-input>
      </el-form-item>
      <el-form-item label="编码" prop="tradecode">
        <el-input v-model="temp.tradecode" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="创建人" prop="creatorShow">
        <el-input v-model="temp.creatorShow" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="创建时间" prop="creationtime">
        <el-input v-model="temp.creationtime" :disabled="true"></el-input>
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
  name: 'RefdocTrade',
  props: ["replace", "title", "visiable", 'action', 'updateRow'],
  computed: {
    ...mapGetters([
      'listData', 'columns', 'name', 'userid'
    ])
  },
  data() {
    return {
      contentVisiable: false, // dialog 显示框
      temp: {
        pkTrade: undefined,
        tradename: '',
        tradecode: 'TRADE' + randomNum(100*1000),
        creator: null,
        creatorShow: '',
        creationtime: parseTime(new Date()),
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
        this.temp.creatorShow = this.name
      }
      if (this.title === '修改' && this.updateRow) {
        this.temp = this.updateRow
        this.temp.modifier = this.name
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
        initReplaceUpdateData(actTemp, this.replace, this.userid)
        this.action(actTemp).then((response) => {
          if (response.primary) {
            actTemp.pkTrade = response.primary
          }
          if (response.success) {
            initReplaceAddData(actTemp, this.replace, this.name)
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
        pkTrade: undefined,
        tradename: '',
        tradecode: 'PT' + randomNum(100*1000),
        creator: null,
        creatorShow: '',
        creationtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      }
    }
  }
}
</script>

<style>

</style>
