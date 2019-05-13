<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <el-form-item label="部门名称" prop="deptname">
        <el-input v-model="temp.deptname"></el-input>
      </el-form-item>
      <el-form-item label="部门简称" prop="deptshortname">
        <el-input v-model="temp.deptshortname"></el-input>
      </el-form-item>
      <el-form-item label="部门编码" prop="deptcode">
        <el-input v-model="temp.deptcode" :disabled="true"></el-input>
      </el-form-item>

      <el-form-item label="所属公司" prop="pkCorp">
        <el-select v-model="temp.pkCorp" placeholder="请选择">
          <el-option
            v-for="item in bdCorpOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="部门负责人" prop="pkPsndoc">
        <el-select v-model="temp.pkPsndoc" placeholder="请选择">
          <el-option
            v-for="item in bdPsndocOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否封存" prop="canceled">
        <el-select v-model="temp.canceled" placeholder="请选择">
          <el-option
            v-for="item in yesOrNoOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="封存日期" prop="canceldate">
        <el-input v-model="temp.canceldate" :disabled="true"></el-input>
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
import { listBdCorpAsRef } from '@/api/orgs/bdcorp'
import { listBdPsndocAsRef } from '@/api/orgs/bdpsndoc'

export default {
  name: 'OrgsBdDeptContent',
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
        pkDeptdoc: undefined,
        deptname: '',
        deptshortname: '',
        deptcode: 'DEPT' + randomNum(100*100),
        pkCorp: null,
        pkPsndoc: null,
        canceled: '0',
        canceldate: '',
        memo: '',
        def1: '',
        def2: '',
        def3: '',
        def4: '',
        def5: '',
        creator: '',
        creationtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
      // 是或否
      yesOrNoOption: [
        { value: '0', label: '否' },
        { value: '1', label: '是' }
      ],
      // 公司参照
      bdCorpOptions: [],
      // 人员参照
      bdPsndocOptions: []
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
    // 初始公司参照
    listBdCorpAsRef().then((response) => {
      if (response.object) {
        this.bdCorpOptions = response.object
      }
    })
    // 人员档案参照
    listBdPsndocAsRef().then((response) => {
      if (response.object) {
        this.bdPsndocOptions = response.object
      }
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
        pkDeptdoc: undefined,
        deptname: '',
        deptshortname: '',
        deptcode: 'DEPT' + randomNum(100*100),
        pkCorp: null,
        pkPsndoc: null,
        canceled: '0',
        canceldate: '',
        memo: '',
        def1: '',
        def2: '',
        def3: '',
        def4: '',
        def5: '',
        creator: '',
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
