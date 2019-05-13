<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-form :inline="true" ref="dataForm" :model="temp" :rules="rules" label-position="left" label-width="120px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="temp.name"></el-input>
      </el-form-item>
      <el-form-item label="编码" prop="code">
        <el-input v-model="temp.code" :disabled="true"></el-input>
      </el-form-item>

      <el-form-item label="性别" prop="sex">
        <el-select v-model="temp.sex" placeholder="请选择">
          <el-option
            v-for="item in sexOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      
      <el-form-item label="公司名称" prop="corpname">
        <el-input v-model="temp.corpname"></el-input>
      </el-form-item>
      <el-form-item label="公司地址" prop="corpaddress">
        <el-input v-model="temp.corpaddress"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="tel">
        <el-input v-model="temp.tel"></el-input>
      </el-form-item>

      <el-form-item label="所属行业" prop="trade">
        <el-select v-model="temp.trade" placeholder="请选择">
          <el-option
            v-for="item in tradeOption"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="描述" prop="memo">
        <el-input v-model="temp.memo"></el-input>
      </el-form-item>
      <el-form-item label="创建人" prop="creatorShow">
        <el-input v-model="temp.creatorShow" :disabled="true"></el-input>
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
import { listTradeAsRef } from '@/api/refdoc/trade'

export default {
  name: 'Customercontent',
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
        pkCustomer: undefined,
        name: '',
        code: 'KH' + randomNum(100*1000),
        sex: '',
        corpname: '',
        corpaddress: '',
        tel: '',
        trade: '',
        memo: '',
        creator: null,
        creatorShow: '',
        creationtime: parseTime(new Date()),
        modifier: null,
        modifiedtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
      // 页面填写数据规则校验
      rules: {
        name: [
          {required: true, message: '客户名称不能为空', trigger: 'blur' }
        ],
        tel: [
          {required: true, message: '联系电话不能为空', trigger: 'blur' }
        ]
      },
      sexOption: [
        { value: '男', label: '男' },
        { value: '女', label: '女' }
      ],
      // 所属行业参照
      tradeOption: []
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
  created() {
    listTradeAsRef().then((response) => {
      if (response.object) {
        this.tradeOption = response.object
      }
    })
  },
  methods: {
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          const actTemp = this.temp
          if (this.action) {
            initReplaceUpdateData(actTemp, this.replace, this.userid)
            this.action(actTemp).then((response) => {
              if (response.primary) {
                actTemp.pkCustomer = response.primary
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
        } else {
          this.$message.warning('表单验证未通过!')
          return false
        }
      })
    },
    restTemp() { // 刷新本界面的数据
      this.temp = {
        pkCustomer: undefined,
        name: '',
        code: 'KH' + randomNum(100*1000),
        sex: '',
        corpname: '',
        corpaddress: '',
        tel: '',
        trade: '',
        memo: '',
        creator: null,
        creatorShow: '',
        creationtime: parseTime(new Date()),
        modifier: null,
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
