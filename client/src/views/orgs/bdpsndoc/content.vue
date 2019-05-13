<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <el-form-item label="人员姓名" prop="psnname">
        <el-input v-model="temp.psnname"></el-input>
      </el-form-item>
      <el-form-item label="人员编码" prop="psncode">
        <el-input v-model="temp.psncode" :disabled="true"></el-input>
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

      <el-form-item label="所属部门" prop="pkDeptdoc">
        <el-select v-model="temp.pkDeptdoc" placeholder="请选择">
          <el-option
            v-for="item in bdDeptdocOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="人员类别" prop="pkPsntype">
        <el-select v-model="temp.pkPsntype" placeholder="请选择">
          <el-option
            v-for="item in psnTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否封存" prop="sealdate">
        <el-select v-model="temp.sealdate" placeholder="请选择">
          <el-option
            v-for="item in yesOrNoOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="直属领导" prop="directleader">
        <el-select v-model="temp.directleader" placeholder="请选择">
          <el-option
            v-for="item in bdPsndocOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
import { listBdDeptAsRef } from '@/api/orgs/bddept'
import { listBdPsndocAsRef } from '@/api/orgs/bdpsndoc'
import { listPsntypeAsRef } from '@/api/refdoc/psntype'

export default {
  name: 'OrgsBdPsndocContent',
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
        pkPsndoc: undefined,
        psnname: '',
        psncode: 'PSN' + randomNum(100*100),
        pkCorp: null,
        pkDeptdoc: null,
        pkPsntype: '',
        sealdate: '0',
        directleader: null,
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
      ],
      // 公司参照
      bdCorpOptions: [],
      // 部门参照
      bdDeptdocOptions: [],
      // 人员参照
      bdPsndocOptions: [],
      // 人员类别参照
      psnTypeOptions: []
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
    // 初始部门参照
    let quertDeptParam = { pkCorp: this.$store.getters.pkCorp}
    listBdDeptAsRef(quertDeptParam).then((response) => {
      if (response.object) {
        this.bdDeptdocOptions = response.object
      }
    })
    // 人员档案参照
    listBdPsndocAsRef().then((response) => {
      if (response.object) {
        this.bdPsndocOptions = response.object
      }
    })
    // 初始人员类别参照
    listPsntypeAsRef().then((response) => {
      if (response.object) {
        this.psnTypeOptions = response.object
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
        pkPsndoc: undefined,
        psnname: '',
        psncode: 'PSN' + randomNum(100*100),
        pkCorp: null,
        pkDeptdoc: null,
        pkPsntype: '',
        sealdate: '0',
        directleader: null,
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
