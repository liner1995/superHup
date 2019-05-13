<template>
  <div>
    <!--基础列表界面-->
    <Table :columns="columns" 
      :select="select" 
      :top_btn="top_btn" 
      :act_btn="act_btn" 
      :replace="replace"
      @onButtonClick="onButtonClick">
    </Table>
    <!--新增修改界面-->
    <Producequotecontent 
      :replace="replace"
      :visiable="content.visiable" 
      :title="content.title"
      :action="content.action"
      :updateRow="content.updateRow"
      @close="closeContent">
    </Producequotecontent>
    <!--查询模板界面-->
    <ProductQueryDialog
      :visiable="queryDialog.visiable"
      @close="closeQueryDialog"
      @refreshList="queryListData">
    </ProductQueryDialog>
  </div>
</template>

<script>
import Table from '@/components/viewcomp/Table'
import Producequotecontent from '@/views/produce/quote/content'
import ProductQueryDialog from '@/views/salemage/product/queryDialog'
import { listProduceQuote, updateProduceQuote, overPassProduceQuote } from '@/api/produce/quote'

export default {
  name: "producequote",
  components: { Table, Producequotecontent, ProductQueryDialog },
  data() {
    return {
      content: {
        visiable: false, // 内容界面显示控制
        title: '', // 标题
        action: null, // 执行Action
        updateRow: null // 更新时带过来的temp
      },
      queryDialog: {
        visiable: false
      },
      columns: [
        {prop: 'pkProduceQuoteH', label: '主键', width: '10', primary: true},
        {prop: 'pkCustomer', label: '客户信息', width: '120', show: false},
        {prop: 'customerCode', label: '客户编码', width: '120'},
        {prop: 'pkPsndoc', label: '负责人', width: '120', show: false},
        {prop: 'pkProduct', label: '产品信息', width: '120', show: false},
        {prop: 'productCode', label: '产品编码', width: '120'},
        {prop: 'productName', label: '产品名称', width: '120'},
        {prop: 'productModel', label: '产品型号', width: '120'},
        {prop: 'productOrderNumber', label: '订单数量', width: '120'},
        {prop: 'unitPriceOut', label: '外协工序单价', width: '120', show: false},
        {prop: 'unitTotalOut', label: '外协订单合计', width: '120', show: false},
        {prop: 'billState', label: '单据状态', width: '120'},
        {prop: 'creator', label: '创建人', width: '120', show: false},
        {prop: 'creatorShow', label: '创建人', width: '120'},
        {prop: 'creationtime', label: '创建时间', width: '180'},
        {prop: 'dr', label: '删除标志', width: '120', show: false},
        {prop: 'ts', label: '时间戳', width: '120', show: false}
      ],
      select: {
        'select': listProduceQuote
      },
      top_btn: [
        {name: '查询',code: 'QUERYDIALOG',action: listProduceQuote},
        {name: '批量通过',code: 'BATCHOVERQUOTE',action: updateProduceQuote}
      ],
      act_btn: [
        {name: '报价',code: 'DOQUOTE', when: {'billState': [0]}, action: updateProduceQuote},
        {name: '完成报价', code: 'OVERQUOTE', when: {'billState': [0]}, action: updateProduceQuote},
        {name: '查看',code: 'CHECK', when: {'billState': [1]}, action: updateProduceQuote}
      ],
      replace: [
        {name: 'billState',replace: {0:"自由态",1:"报价完成"}}
      ]
    }
  },
  methods: {
    onButtonClick(param) { // 监控页面中所有的按钮点击事件
      if (param.btnCode === 'DOQUOTE') {
        this.openContent('生产报价', param.btnAction)
        this.content.updateRow = param.bindData
      }
      if (param.btnCode === 'CHECK') {
        this.openContent('生产报价', param.btnAction)
        this.content.updateRow = param.bindData
      }
      // 查询界面
      if (param.btnCode === 'QUERYDIALOG') {
        this.openQueryDialog()
      }
      if (param.btnCode === 'OVERQUOTE') {
        let updateParam = { pkProduceQuoteH: param.bindData.pkProduceQuoteH, pkProduct: param.bindData.pkProduct,taskId: param.bindData.taskId, billState: 1 }
        overPassProduceQuote(updateParam).then((response) => {
          this.$message({ message: '操作成功', type: 'success' })
          param.bindData.billState = '报价完成'
        })
      }
    },
    queryListData(queryParam) { // 刷新界面数据
      if (queryParam) {
        let queryDialogParam ={
          productCode: queryParam.productCode,
          pkPsndoc: queryParam.pkPsndoc,
          customShortName: queryParam.customShortName,
          billState: queryParam.billState,
          creationdateBegin: queryParam.creationdateBegin,
          creationdateEnd: queryParam.creationdateEnd
        }
        let newSelect = {select: this.select.select, queryParam: queryDialogParam }
        this.select = newSelect
      } else {
        let newSelect = {select: this.select.select, queryParam: {dr: 0} }
        this.select = newSelect
      }
    },
    // 打开查询模板
    openQueryDialog() {
      this.queryDialog.visiable = true
    },
    // 关闭查询模板
    closeQueryDialog() {
      this.queryDialog.visiable = false
    },
    openContent(title, action) { // 打开内容界面Dialog
      this.content.visiable = true
      this.content.title = title
      this.content.action = action
    },
    closeContent(tempData) { // 关闭内容界面Dialog
      this.content.visiable = false
    }
  }
}
</script>

<style>

</style>
