<template>
  <el-dialog title="查询界面" 
    :visible.sync="dialogVisiable" 
    @close="closeDialog"
    width="40%">
    
    <el-form ref="queryTemp" label-position="left" :inline="true" :model="queryTemp" label-width="120px">
      <el-form-item label="产品编号">
        <el-input v-model="queryTemp.productCode"></el-input>
      </el-form-item>

      <el-form-item label="业务员">
        <el-select v-model="queryTemp.pkPsndoc" placeholder="请选择">
          <el-option
            v-for="item in psndocOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="客户简称">
        <el-input v-model="queryTemp.customShortName"></el-input>
      </el-form-item>

      <el-form-item label="单据状态">
        <el-select v-model="queryTemp.billState" placeholder="请选择">
          <el-option
            v-for="item in billStateOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="创建日期">
        <el-date-picker width="300px" v-model="queryTemp.creationdate" type="daterange" range-separator="~" start-placeholder="开始日期" end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="sureDialog">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { listBdPsndocAsRef } from '@/api/orgs/bdpsndoc'
import { parseTime } from '@/utils/viewCompUtil'

export default {
  name: 'ProductQueryDialog',
  props: ["visiable"],
  data() {
    return {
      dialogVisiable: false,
      queryTemp: {
        productCode: '',
        pkPsndoc: '',
        customShortName: '',
        billState: null,
        creationdate: ''
      },
      // 人员信息option
      psndocOption: [],
      // 单据状态Option
      billStateOption: [
        { value: 0, label: "自由态" },
        { value: 1, label: "工艺审核态" },
        { value: 2, label: "工艺审核完成态" }
      ]
    }
  },
  watch: {
    visiable() {
      this.dialogVisiable = this.visiable
    }
  },
  created() {
    listBdPsndocAsRef().then((response) => {
      this.psndocOption = response.object
    })
  },
  methods: {
    // 保存关闭
    sureDialog() {
      let creationdate = this.queryTemp.creationdate
      if (creationdate) {
        let creationdateBegin = parseTime(creationdate[0], '{y}-{m}-{d}')
        let creationdateEnd = parseTime(creationdate[1], '{y}-{m}-{d}')
        this.queryTemp.creationdateBegin = creationdateBegin + ' 00:00:00'
        this.queryTemp.creationdateEnd = creationdateEnd + ' 23:59:59'
      } else {
        this.queryTemp.creationdateBegin = ''
        this.queryTemp.creationdateEnd = ''
      }
      this.$emit('refreshList', this.queryTemp)
      this.$emit('close', null)
    },
    // 取消关闭
    closeDialog() {
      this.$emit('close', null)
    }
  }
}
</script>

<style>

</style>
