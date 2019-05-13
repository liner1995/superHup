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
    <Salequotecontent 
      :replace="replace"
      :visiable="content.visiable" 
      :title="content.title"
      :action="content.action"
      :updateRow="content.updateRow"
      @close="closeContent">
    </Salequotecontent>
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
import Salequotecontent from '@/views/salemage/quote/content'
import ProductQueryDialog from '@/views/salemage/product/queryDialog'
import { listSaleQuote, updateSaleQuote, overProccessSaleQupte } from '@/api/salemage/salequote'

export default {
  name: "salequote",
  components: { Table, Salequotecontent, ProductQueryDialog },
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
        {prop: 'pkSaleQuote', label: '主键', width: '10', primary: true},
        {prop: 'pkCustomer', label: '客户信息', width: '120', show: false},
        {prop: 'customerCode', label: '客户编号', width: '120'},
        {prop: 'pkProduct', label: '产品信息', width: '120', show: false},
        {prop: 'productCode', label: '产品编号', width: '120'},
        {prop: 'productName', label: '产品名称', width: '120'},
        {prop: 'productModel', label: '产品型号', width: '120', show: false},
        {prop: 'productOrderNumber', label: '订单数量', width: '120', show: false},
        {prop: 'billState', label: '单据状态', width: '120'},
        {prop: 'creator', label: '创建人', width: '120', show: false},
        {prop: 'creatorShow', label: '创建人', width: '120'},
        {prop: 'creationtime', label: '创建时间', width: '180'},
        {prop: 'dr', label: '删除标志', width: '120', show: false},
        {prop: 'ts', label: '时间戳', width: '120', show: false}
      ],
      select: {
        'select': listSaleQuote
      },
      top_btn: [
        {name: '查询',code: 'QUERYDIALOG',action: listSaleQuote},
        {name: '批量通过',code: 'BATCHOVERQUOTE', when: {'billState': [3]},action: updateSaleQuote}
      ],
      act_btn: [
        {name: '报价',code: 'DOQUOTE', when: {'billState': [3]},action: updateSaleQuote},
        {name: '完成报价',code: 'OVERQUOTE', when: {'billState': [3]},action: overProccessSaleQupte},
        {name: '查看',code: 'CHECK', when: {'billState': [0,1,2,4]},action: updateSaleQuote}
      ],
      replace: [
        {name: 'billState',replace: {0:"自由态",1:"采购报价完成",2:"生产报价完成",3:"销售报价",4:"完成销售报价"}}
      ]
    }
  },
  methods: {
    onButtonClick(param) { // 监控页面中所有的按钮点击事件
      if (param.btnCode === 'BATCHOVERQUOTE') {
      }
      if (param.btnCode === 'DOQUOTE') {
        this.openContent('销售报价', param.btnAction)
        this.content.updateRow = param.bindData
      }
      // 查询界面
      if (param.btnCode === 'QUERYDIALOG') {
        this.openQueryDialog()
      }
      if (param.btnCode === 'OVERQUOTE') {
        let updateParam = { pkSaleQuote: param.bindData.pkSaleQuote, taskId: param.bindData.taskId, billState: 4 }
        overProccessSaleQupte(updateParam).then((response) =>{
          this.$message({ message: '操作成功', type: 'success' })
          param.bindData.billState = '完成销售报价'
        })
      }
      if (param.btnCode === 'CHECK') {
        this.openContent('销售报价', param.btnAction)
        this.content.updateRow = param.bindData
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
