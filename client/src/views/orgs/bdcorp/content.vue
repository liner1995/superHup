<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <el-form-item label="公司名称" prop="unitname">
        <el-input v-model="temp.unitname"></el-input>
      </el-form-item>
      <el-form-item label="公司简称" prop="unitshortname">
        <el-input v-model="temp.unitshortname"></el-input>
      </el-form-item>
      <el-form-item label="公司编码" prop="unitcode">
        <el-input v-model="temp.unitcode" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="联系电话1" prop="phone1">
        <el-input v-model="temp.phone1"></el-input>
      </el-form-item>
      <el-form-item label="联系电话2" prop="phone2">
        <el-input v-model="temp.phone2"></el-input>
      </el-form-item>
      <el-form-item label="联系电话3" prop="phone3">
        <el-input v-model="temp.phone3"></el-input>
      </el-form-item>
      <!--<el-form-item label="所属行业" prop="industry">
        <el-input v-model="temp.industry"></el-input>
      </el-form-item>-->

      <el-form-item label="是否封存" prop="isseal">
        <el-select v-model="temp.isseal" placeholder="请选择">
          <el-option
            v-for="item in yesOrNoOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="封存日期" prop="sealeddate">
        <el-input v-model="temp.sealeddate" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="WEB网址" prop="url">
        <el-input v-model="temp.url"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop="memo">
        <el-input v-model="temp.memo"></el-input>
      </el-form-item>
      <el-form-item label="创建人" prop="creator">
        <el-input v-model="temp.creator" :disabled="true"></el-input>
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
  name: 'OrgBdCorpContent',
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
        pkCorp: undefined,
        unitname: '',
        unitshortname: '',
        unitcode: 'CORP' + randomNum(100*100),
        phone1: '',
        phone2: '',
        phone3: '',
        industry: '',
        isseal: '0',
        sealeddate: '',
        url: '',
        memo: '',
        def1: '',
        def2: '',
        def3: '',
        def4: '',
        def5: '',
        creator: null,
        creationtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
      // 是或否
      yesOrNoOption: [
        { value: '0', label: '否' },
        { value: '1', label: '是' }
      ]
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
        debugger
        initReplaceUpdateData(actTemp, this.replace)
        this.action(actTemp).then((response) => {
          if (response.primary) {
            actTemp.pkCustomer = response.primary
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
        pkCorp: undefined,
        unitname: '',
        unitshortname: '',
        unitcode: 'CORP' + randomNum(100*100),
        phone1: '',
        phone2: '',
        phone3: '',
        industry: '',
        isseal: '0',
        sealeddate: '',
        url: '',
        memo: '',
        def1: '',
        def2: '',
        def3: '',
        def4: '',
        def5: '',
        creator: null,
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
