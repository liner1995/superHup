<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="80%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="130px">
      <!--产品基本信息-->
      <el-tabs type="border-card">
        <el-tab-pane label="基本信息">
          <el-form-item label="客户编码" prop="customerCode">
            <el-input v-model="temp.customerCode" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="产品编码" prop="productCode">
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
        </el-tab-pane>
      </el-tabs>

      <!--生产报价-->
      <br/>
      <el-tabs type="border-card">
        <el-tab-pane label="生产报价">
          <el-tag>工序报价</el-tag>
          <el-table :data="temp.billProduceQuoteBPoList">
            <el-table-column prop="pkProduceQuoteB" v-if="false"></el-table-column>
            <el-table-column prop="ismodel" v-if="false"></el-table-column>
            <el-table-column prop="type" label="工序类别" width="160"></el-table-column>
            <el-table-column prop="param" label="工序参数" width="160"></el-table-column>
            <el-table-column prop="unitPriceOut" label="外协工序单价" width="160"></el-table-column>
            <el-table-column prop="unitTotalPriceOut" label="外协工序总价" width="160"></el-table-column>
            <el-table-column prop="unitTotalOut" label="外协订单合计" width="160"></el-table-column>

            <el-table-column label="销售工序单价" width="180">
              <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.unitPriceSale"></el-input>
              </template>
            </el-table-column>

            <el-table-column prop="unitTotalPriceSale" label="销售工序总价" width="160"></el-table-column>

            <el-table-column label="销售订单合计" width="180">
              <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.unitTotalSale"></el-input>
              </template>
            </el-table-column>
          </el-table>

          <br/>
          <el-tag>制具报价</el-tag>
          <el-table :data="temp.modelQuoteBPoList">
            <el-table-column prop="pkProduceQuoteB" v-if="false"></el-table-column>
            <el-table-column prop="ismodel" v-if="false"></el-table-column>
            <el-table-column prop="type" label="工序类别" width="180"></el-table-column>
            <el-table-column prop="param" label="工时" width="180"></el-table-column>
            <el-table-column prop="unitPriceOut" label="数量" width="180"></el-table-column>
            <el-table-column prop="unitTotalPriceOut" label="总价" width="180"></el-table-column>

            <el-table-column label="销售制具报价" width="180">
              <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.unitTotalSale"></el-input>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>

      <!--采购报价-->
      <br/>
      <el-tabs type="border-card">
        <el-tab-pane label="采购报价">
          <el-form-item label="报价截止日期" prop="quoteEndDate">
            <el-date-picker v-model="temp.billPurchaseQuotePo.quoteEndDate" type="date" placeholder="选择日期" :disabled="true"></el-date-picker>
          </el-form-item>

          <el-form-item label="采购单价" prop="purchasePrice">
            <el-input v-model="temp.billPurchaseQuotePo.purchasePrice" :disabled="true"></el-input>
          </el-form-item>

          <el-form-item label="采购总价" prop="purchaseTotal">
            <el-input v-model="temp.billPurchaseQuotePo.purchaseTotal" :disabled="true"></el-input>
          </el-form-item>

          <el-form-item label="采购单价_销售" prop="purchasePriceSale">
            <el-input v-model="temp.billPurchaseQuotePo.purchasePriceSale"></el-input>
          </el-form-item>

          <el-form-item label="采购总价_销售" prop="purchaseTotalSale">
            <el-input v-model="temp.billPurchaseQuotePo.purchaseTotalSale"></el-input>
          </el-form-item>

          <br/>
          <el-col :span="7">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>采购文件</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="downloadFile(temp.billPurchaseQuotePo.fileName, temp.billPurchaseQuotePo.fileFullname)">下载采购文件</el-button>
              </div>
              <div class="text item">
                {{ temp.billPurchaseQuotePo.fileName }}
              </div>
            </el-card>
          </el-col>
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

          <el-form-item label="创建人" prop="creatorShow">
            <el-input v-model="temp.creatorShow" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="创建时间" prop="creationtime">
            <el-input v-model="temp.creationtime" :disabled="true"></el-input>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
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
import { updateBatchProduceQuoteB } from '@/api/produce/quote'
import { downloadProductFile } from '@/api/pub/pub'

export default {
  name: 'Salequotecontent',
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
        pkSaleQuote: undefined,
        // 基本信息
        pkCustomer: null,
        customerCode: '',
        pkProduct: null,
        productCode: '',
        productName: '',
        productModel: '',
        productOrderNumber: null,
        // 生产报价
        billProduceQuoteBPoList: [],
        // 采购报价
        billPurchaseQuotePo: {
          quoteEndDate: '',
          purchasePrice: null,
          purchaseTotal: null,
          purchasePriceSale: null,
          purchaseTotalSale: null,
          fileName: '',
          fileFullname: ''
        },
        creator: '',
        creatorShow: '',
        creationtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
      billStateOprion: [
        { value: 0, label: '自由态' },
        { value: 1, label: '采购报价完成' },
        { value: 2, label: '生产报价完成' },
        { value: 3, label: '销售报价' },
        { value: 4, label: '完成销售报价' }
      ]
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
      if (this.title === '销售报价' && this.updateRow) {
        this.restTemp()
        this.temp = this.updateRow
        if (!this.temp.billPurchaseQuotePo) {
          this.temp.billPurchaseQuotePo = {
            quoteEndDate: '',
            purchasePrice: null,
            purchaseTotal: null,
            purchasePriceSale: null,
            purchaseTotalSale: null,
            fileName: '',
            fileFullname: ''
          }
        }
      }
    }
  },
  methods: {
    downloadFile(fileName, fileFullName) {
      if (fileName && fileFullName) {
        let downLoadParma = {fileName: fileName, fileFullName: fileFullName}
        downloadProductFile(downLoadParma).then((response) => {
          debugger
          const blob = response.data
          const reader = new FileReader()
          reader.readAsDataURL(blob)
          reader.onload = (e) => {
            const a = document.createElement('a')
            if (fileName) {
              a.download = fileName
            } else {
              a.download = fileName
            }
            a.href = e.target.result
            document.body.appendChild(a)
            a.click()
            document.body.removeChild(a)
          }
        })
      }
    },
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      const billProduceQuoteBPoList = this.temp.billProduceQuoteBPoList
      // 这里只更新报价信息
      updateBatchProduceQuoteB(billProduceQuoteBPoList).then((response) => {
        if (response.success) {
            initReplaceAddData(billProduceQuoteBPoList, this.replace)
            this.$message({ message: '操作成功', type: 'success' })
          } else {
            this.$message({ message: `操作失败:${response.message}`, type: 'error' })
          }
      })
      this.$emit('close', billProduceQuoteBPoList)
    },
    restTemp() { // 刷新本界面的数据
      this.temp = {
        pkSaleQuote: undefined,
        // 基本信息
        pkCustomer: null,
        customerCode: '',
        pkProduct: null,
        productCode: '',
        productName: '',
        productModel: '',
        productOrderNumber: null,
        // 生产报价
        billProduceQuoteBPoList: [],
        // 采购报价
        billPurchaseQuotePo: {
          quoteEndDate: '',
          purchasePrice: null,
          purchaseTotal: null,
          purchasePriceSale: null,
          purchaseTotalSale: null,
          fileName: '',
          fileFullname: ''
        },
        creator: '',
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
