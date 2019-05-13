<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="80%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
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
          <el-tag>工序报价</el-tag>
          <el-table :data="temp.billProduceQuoteBPoList">
            <el-table-column prop="pkProduceQuoteB" v-if="false"></el-table-column>
            <el-table-column prop="ismodel" v-if="false"></el-table-column>
            <el-table-column prop="type" label="工序类别" width="180"></el-table-column>
            <el-table-column prop="param" label="工序参数" width="180"></el-table-column>

            <el-table-column label="外协工序单价" width="180">
              <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.unitPriceOut" @change="gongxuChange(scope.row, '1')"></el-input>
              </template>
            </el-table-column>

            <el-table-column prop="unitTotalPriceOut" label="外协工序总价" width="180"></el-table-column>

            <el-table-column label="外协订单合计" width="180">
              <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.unitTotalOut" @change="gongxuChange(scope.row, '2')"></el-input>
              </template>
            </el-table-column>
          </el-table>

          <br/>
          <el-tag>制具报价</el-tag>
          <el-table :data="temp.modelQuoteBPoList">
            <el-table-column prop="pkProduceQuoteB" v-if="false"></el-table-column>
            <el-table-column prop="ismodel" v-if="false"></el-table-column>
            <el-table-column prop="type" label="工序类别" width="180"></el-table-column>

            <el-table-column label="工时" width="180">
              <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.param" @change="zhijuChange(scope.row)"></el-input>
              </template>
            </el-table-column>

            <el-table-column label="数量" width="180">
              <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.unitPriceOut" @change="zhijuChange(scope.row)"></el-input>
              </template>
            </el-table-column>

            <el-table-column prop="unitTotalPriceOut" label="总价" width="180"></el-table-column>
          </el-table>
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
import { initReplaceAddData, parseTime, randomNum } from '@/utils/viewCompUtil'
import { updateBatchProduceQuoteB } from '@/api/produce/quote'
import { listBdPsndocAsRef } from '@/api/orgs/bdpsndoc'

export default {
  name: 'Producequotecontent',
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
        pkProduceQuoteH: undefined,
        contractno: '',
        pkCustomer: null,
        customerCode: '',
        pkProduct: null,
        productCode: '',
        productName: '',
        productModel: '',
        productOrderNumber: '',
        unitPriceOut: null,
        unitTotalOut: null,
        billProduceQuoteBPoList: [],
        modelQuoteBPoList: [],
        billState: null,
        creator: '',
        creatorShow: '',
        creationtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
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
      if (this.title === '生产报价' && this.updateRow) {
        this.restTemp()
        this.temp = this.updateRow
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
    // 工序报价计算公式
    gongxuChange(row, type) {
      if ('1' === type) {
        // 外协工序总价 = 工序参数 * 外协工序单价
        row.unitTotalPriceOut = row.param * row.unitPriceOut
        // 外协订单合计 = 外协工序总价 * 订单数量
        row.unitTotalOut = row.unitTotalPriceOut * this.temp.productOrderNumber
      }
      if ('2' === type) {
        // 外协工序总价 = 外协订单合计 / 数量
        row.unitTotalPriceOut = row.unitTotalOut / this.temp.productOrderNumber
        // 外协工序单价 = 外协工序总价 / 工序参数
        row.unitPriceOut = row.unitTotalPriceOut / row.param
      }
    },
    // 制具报价计算公式
    zhijuChange(row) {
      // 总价 = 工时 * 数量
      row.unitTotalPriceOut = row.param * row.unitPriceOut
    },
    restTemp() { // 刷新本界面的数据
      this.temp = {
        pkProduceQuoteH: undefined,
        contractno: '',
        pkCustomer: null,
        customerCode: '',
        pkProduct: null,
        productCode: '',
        productName: '',
        productModel: '',
        productOrderNumber: '',
        unitPriceOut: null,
        unitTotalOut: null,
        billProduceQuoteBPoList: [],
        modelQuoteBPoList: [],
        billState: null,
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
