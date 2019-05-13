<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable"
    @close="closeDialog"
    width="70%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <!--基本信息-->
      <el-tabs type="border-card">
        <el-tab-pane label="基本信息">
          <el-form-item label="客户编号" prop="customerCode">
            <el-input v-model="temp.customerCode" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="产品编号" prop="productCode">
            <el-input v-model="temp.productCode" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="产品名称" prop="productName">
            <el-input v-model="temp.productName" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="产品型号" prop="productModel">
            <el-input v-model="temp.productModel" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="订单数量" prop="productOrderNumber">
            <el-input v-model="temp.productOrderNumber" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="物料来源" prop="productMaterialSource">
            <el-select v-model="temp.productMaterialSource" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in materialSourceOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="负责人" prop="pkPsndoc">
            <el-select v-model="temp.pkPsndoc" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in psndocOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          
        </el-tab-pane>
      </el-tabs>

      <!--报价信息-->
      <br/>
      <el-tabs type="border-card">
        <el-tab-pane label="报价信息">
          <el-form-item label="报价截止日期" prop="quoteEndDate">
            <el-date-picker v-model="temp.quoteEndDate" type="date" placeholder="选择日期" @change="dateFormat()"></el-date-picker>
          </el-form-item>

          <el-form-item label="采购单价" prop="purchasePrice">
            <el-input v-model="temp.purchasePrice" @change="priceCount('single')"></el-input>
          </el-form-item>

          <el-form-item label="采购总价" prop="purchaseTotal">
            <el-input v-model="temp.purchaseTotal" @change="priceCount('sum')"></el-input>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      
      <!--单据信息-->
      <br/>
      <el-tabs type="border-card">
        <el-tab-pane label="单据信息">
          
          <el-form-item label="单据状态" prop="billState">
            <el-select v-model="temp.billState" placeholder="请选择" :disabled="true">
              <el-option
                v-for="item in billStateOprion"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="审批人" prop="approverShow">
            <el-input v-model="temp.approverShow" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="审批时间" prop="approvetime">
            <el-input v-model="temp.approvetime" :disabled="true"></el-input>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
    </el-form>


    <!--附件管理Controller-->
    <br/>
    <el-tabs type="border-card">
      <el-tab-pane label="附件上传">
        <el-col :span="8">
          <el-upload drag
            class="upload-demo"
            ref="BOMUpload"
            :action="uploadUrl"
            :file-list="PURFileList"
            :limit="1"
            :on-exceed="handleExceed"
            :on-change="PURFileChange"
            :auto-upload="false"
            :disabled="title == '查看采购报价' ? true : false">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">采购报价文件   <em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">
              1: 上传文件大小不得超过200M
            </div>
          </el-upload>
        </el-col>
      </el-tab-pane>
    </el-tabs>

    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="sureDialog">保 存</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { initReplaceUpdateData, initReplaceAddData, parseTime, randomNum } from '@/utils/viewCompUtil'
import { savePurchaseFile } from '@/api/purchase/quote'
import { listBdPsndocAsRef } from '@/api/orgs/bdpsndoc'

export default {
  name: 'Purchasequotecontent',
  props: ["replace", "title", "visiable", 'action', 'updateRow'],
  computed: {
    ...mapGetters([
      'listData', 'columns'
    ])
  },
  data() {
    return {
      contentVisiable: false, // dialog 显示框
      uploadUrl: process.env.BASE_API + '/', // 上传地址
      PURFileList: [],
      PURFile: null,
      temp: {
        pkPurchaseQuote: undefined,
        contractno: '',
        pkCustomer: null,
        customerCode: '',
        pkPsndoc: null,
        pkProduct: null,
        productCode: '',
        productName: '',
        productModel: '',
        productOrderNumber: null,
        productMaterialSource: '',
        quoteEndDate: '',
        purchasePrice: null,
        purchaseTotal: null,
        fileName: '',
        fileFullname: '',
        billState: 0,
        approver: '',
        approverShow: '',
        approvetime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
      materialSourceOption: [ // 物料来源
        { value: '0', label: '甲方提供' },
        { value: '1', label: '乙方提供' },
        { value: '2', label: '部分甲方提供' }
      ],
      billStateOprion: [
        { value: 0, label: '自由态' },
        { value: 1, label: '报价完成' }
      ],
      // 人员档案
      psndocOption: []
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
      if (this.updateRow) {
        this.restTemp()
        this.temp = this.updateRow
        if (this.updateRow.fileName) {
          let purfile = { name: this.updateRow.fileName }
          this.PURFileList = [ purfile ]
        }
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
    // 解析日期
    dateFormat() {
      if (this.temp.quoteEndDate) {
        this.temp.quoteEndDate = parseTime(this.temp.quoteEndDate, '{y}-{m}-{d}')
      }
    },
    priceCount(flag) {
      if (flag === 'single') {
        // 采购总价 = 采购单价 * 数量
        this.temp.purchaseTotal = this.temp.purchasePrice * this.temp.productOrderNumber
      }
      if (flag === 'sum') {
        // 采购单价 = 采购总价 / 数量
        this.temp.productOrderNumber = this.temp.purchaseTotal / this.temp.productOrderNumber
      }
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    PURFileChange(file, fileList) { // 记录文件变化
      this.PURFile = file
    },
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      let formData = new FormData()
      if (this.title === '采购报价' && this.PURFile == null) {
        this.$message.warning('上传文件不完整!请选择需要上传的文件。')
        return
      }
      formData.append(`purfile`, this.PURFile.raw)

      savePurchaseFile(formData).then((response) => {
        if (response.object) {
          this.temp.fileName = response.object.PURCHASE_FILE_NAME
          this.temp.fileFullname = response.object.PURCHASE_FILE_FULL_NAME

          debugger
          const actTemp = this.temp
          if (this.action) {
            initReplaceUpdateData(actTemp, this.replace)
            this.action(actTemp).then((response) => {
              if (response.primary) {
                actTemp.pkProvince = response.primary
              }
              if (response.success) {
                initReplaceAddData(actTemp, this.replace)
                this.$store.commit('HANDLE_LISTVIEW_DATA', actTemp)
                this.$message({ message: '操作成功', type: 'success' })
                this.$emit('close', actTemp)
              } else {
                this.$message({ message: `操作失败:${response.message}`, type: 'error' })
              }
            })
          }
        }
      })
    },
    restTemp() { // 刷新本界面的数据
      this.temp = {
        pkPurchaseQuote: undefined,
        contractno: '',
        pkCustomer: null,
        customerCode: '',
        pkPsndoc: null,
        pkProduct: null,
        productCode: '',
        productName: '',
        productModel: '',
        productOrderNumber: null,
        productMaterialSource: '',
        quoteEndDate: '',
        purchasePrice: null,
        purchaseTotal: null,
        fileName: '',
        fileFullname: '',
        billState: 0,
        approver: '',
        approverShow: '',
        approvetime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      }
      this.PURFileList = []
      this.PURFile = null
    }
  }
}
</script>

<style>

</style>
