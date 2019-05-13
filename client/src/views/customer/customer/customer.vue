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
    <Customercontent 
      :replace="replace"
      :visiable="content.visiable" 
      :title="content.title"
      :action="content.action"
      :updateRow="content.updateRow"
      @close="closeContent">
    </Customercontent>
  </div>
</template>

<script>
import Table from '@/components/viewcomp/Table'
import Customercontent from '@/views/customer/customer/content'
import { listCustomer, addCustomer, deleteCustomerByPks, updateCustomer } from '@/api/customer/customer'

export default {
  name: "salecustomer",
  components: { Table, Customercontent },
  data() {
    return {
      content: {
        visiable: false, // 内容界面显示控制
        title: '', // 标题
        action: null, // 执行Action
        updateRow: null // 更新时带过来的temp
      },
      columns: [
        {prop: 'pkCustomer', label: '主键', width: '10', primary: true},
        {prop: 'name', label: '客户名称', width: '120', search: true},
        {prop: 'code', label: '客户编码', width: '120'},
        {prop: 'sex', label: '客户性别', width: '120'},
        {prop: 'corpname', label: '企业名称', width: '120'},
        {prop: 'corpaddress', label: '企业地址', width: '120', show: false},
        {prop: 'tel', label: '联系电话', width: '120'},
        {prop: 'trade', label: '所属行业', width: '120', show: false},
        {prop: 'memo', label: '描述', width: '120', show: false},
        {prop: 'creator', label: '创建人', width: '120', show: false},
        {prop: 'creatorShow', label: '创建人', width: '120'},
        {prop: 'creationtime', label: '创建时间', width: '180'},
        {prop: 'modifier', label: '最后修改人', width: '120', show: false},
        {prop: 'modifiedtime', label: '最后修改时间', width: '180', show: false},
        {prop: 'dr', label: '删除标志', width: '120', show: false},
        {prop: 'ts', label: '时间戳', width: '120', show: false}
      ],
      select: {
        'select': listCustomer
      },
      top_btn: [
        {name: '录入',code: 'ADD',action: addCustomer}
      ],
      act_btn: [
        {name: '修改',code: 'UPDATE',action: updateCustomer},
        {name: '删除',code: 'DELETE',action: deleteCustomerByPks}
      ],
      replace: [
        {name: 'valid',replace: {0:"启用",1:"禁用"}}
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
