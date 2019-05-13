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
    <Productcontent 
      :replace="replace"
      :visiable="content.visiable" 
      :title="content.title"
      :action="content.action"
      :updateRow="content.updateRow"
      @close="closeContent"
      @refreshList="queryListData">
    </Productcontent>
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
import Productcontent from '@/views/salemage/product/content'
import ProductQueryDialog from '@/views/salemage/product/queryDialog'
import { listProduct, addProduct, deleteProductByPks, updateProduct, commitProduct } from '@/api/salemage/product'

export default {
  name: "saleproduct",
  components: { Table, Productcontent, ProductQueryDialog },
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
        {prop: 'pkProduct', label: '主键', width: '10', primary: true},
        {prop: 'name', label: '产品名称', width: '120'},
        {prop: 'code', label: '产品编码', width: '120'},
        {prop: 'model', label: '产品型号', width: '120'},
        {prop: 'pkPsndoc', label: '负责人', width: '120', show: false},
        {prop: 'purchaseContacts', label: '采购联系人', width: '120', show: false},
        {prop: 'skillContacts', label: '技术联系人', width: '120', show: false},
        {prop: 'smt', label: 'SMT焊点', width: '120', show: false},
        {prop: 'dip', label: 'DIP焊点', width: '120', show: false},
        {prop: 'isinstal', label: '组装', width: '120', show: false},
        {prop: 'instalMin', label: '组装时间', width: '120', show: false},
        {prop: 'istest', label: '测试', width: '120', show: false},
        {prop: 'testMin', label: '测试时间', width: '120', show: false},
        {prop: 'isaging', label: '老化', width: '120', show: false},
        {prop: 'agingMin', label: '老化时间', width: '120', show: false},
        {prop: 'exeStandard', label: '执行标准', width: '120', show: false},
        {prop: 'steelMeshSource', label: '钢网来源', width: '120', show: false},
        {prop: 'defense', label: '三防', width: '120', show: false},
        {prop: 'defenseArea', label: '三防时间', width: '120', show: false},
        {prop: 'sealTechnics', label: '焊接工艺', width: '120', show: false},
        {prop: 'materialSource', label: '物料来源', width: '120', show: false},
        {prop: 'ispcb', label: 'PCB', width: '120'},
        {prop: 'pcbLong', label: '长（cm）', width: '120', show: false},
        {prop: 'pcbWide', label: '宽（cm）', width: '120', show: false},
        {prop: 'pcbThick', label: '板子厚度', width: '120', show: false},
        {prop: 'pcbResistColor', label: '阻焊颜色', width: '120', show: false},
        {prop: 'pcbCharColor', label: '字符颜色', width: '120', show: false},
        {prop: 'pcbResistCover', label: '阻焊覆盖', width: '120', show: false},
        {prop: 'pcbPadSpary', label: '焊盘喷镀', width: '120', show: false},
        {prop: 'pcbCopperThick', label: '铜厚', width: '120', show: false},
        {prop: 'pkCustomer', label: '客户主键', width: '120', show: false},
        {prop: 'customerName', label: '客户名称', width: '120'},
        {prop: 'orderNumber', label: '订单数量', width: '120'},
        {prop: 'bomFilename', label: 'BOM文件名称', width: '120', show: false},
        {prop: 'bomFilefullname', label: 'BOM文件全路径名称', width: '120', show: false},
        {prop: 'pcbFilename', label: 'PCB文件名称', width: '120', show: false},
        {prop: 'pcbFilefullname', label: 'PCB文件全路径名称', width: '120', show: false},
        {prop: 'craftFilename', label: '工艺文件名称', width: '120', show: false},
        {prop: 'craftFilefullname', label: '工艺文件全路径名称', width: '120', show: false},
        {prop: 'ismakeTool', label: '是否制具', width: '120'},
        {prop: 'makeSteelMesh', label: '钢网制具', width: '120', show: false},
        {prop: 'makeDip', label: 'DIP制具', width: '120', show: false},
        {prop: 'makeTest', label: '测试制具', width: '120', show: false},
        {prop: 'makeDefense', label: '三防制具', width: '120', show: false},
        {prop: 'makeAging', label: '老化制具', width: '120', show: false},
        {prop: 'billState', label: '单据状态', width: '140'},
        {prop: 'creator', label: '创建人', width: '120', show: false},
        {prop: 'creatorShow', label: '创建人', width: '120'},
        {prop: 'creationtime', label: '创建时间', width: '200'},
        {prop: 'modifier', label: '最后修改人', width: '120', show: false},
        {prop: 'modifierShow', label: '最后修改人', width: '120', show: false},
        {prop: 'modifiedtime', label: '最后修改时间', width: '180', show: false},
        {prop: 'dr', label: '删除标志', width: '120', show: false},
        {prop: 'ts', label: '时间戳', width: '120', show: false}
      ],
      select: {
        'select': listProduct,
        'queryParam': {dr: 0}
      },
      top_btn: [
        {name: '查询',code: 'QUERYDIALOG',action: listProduct},
        {name: '录入',code: 'ADD',action: addProduct}
      ],
      act_btn: [
        { name: '送审工艺部',code: 'COMMIT',when: {'billState': [0]},action: commitProduct },
        { name: '修改',code: 'UPDATE',when: {'billState': [0]},action: updateProduct },
        { name: '删除',code: 'DELETE',when: {'billState': [0]},action: deleteProductByPks }
      ],
      replace: [
        {name: 'billState',replace: { 0: "自由态",1: "工艺审核态",2: "工艺审核完成态"}}
      ]
    }
  },
  methods: {
    onButtonClick(param) { // 监控页面中所有的按钮点击事件
      if (param.btnCode === 'ADD') {
        this.openContent('录入', param.btnAction)
      }
      if (param.btnCode === 'UPDATE') {
        this.openContent('修改', param.btnAction)
        this.content.updateRow = param.bindData
      }
      // 查询界面
      if (param.btnCode === 'QUERYDIALOG') {
        this.openQueryDialog()
      }
      // 送审工艺部
      if (param.btnCode === 'COMMIT') {
        debugger
        let userid = this.$store.getters.userid
        let commitParam = { pkCustomer: param.bindData.pkCustomer, pkProduct: param.bindData.pkProduct, pkPsndoc: param.bindData.pkPsndoc, userid: userid }
        commitProduct(commitParam).then((response) => {
          this.$message({ message: '送审工艺部门成功!', type: 'success' })
          param.bindData.billState = '工艺审核态'
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
