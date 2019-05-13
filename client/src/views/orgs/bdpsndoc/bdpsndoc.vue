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
    <OrgsBdPsndocContent 
      :replace="replace"
      :visiable="content.visiable" 
      :title="content.title"
      :action="content.action"
      :updateRow="content.updateRow"
      @close="closeContent">
    </OrgsBdPsndocContent>
  </div>
</template>

<script>
import Table from '@/components/viewcomp/Table'
import OrgsBdPsndocContent from '@/views/orgs/bdpsndoc/content'
import { listBdPsndoc, addBdPsndoc, deleteBdPsndocByPks, updateBdPsndoc } from '@/api/orgs/bdpsndoc'

export default {
  name: "salecustomer",
  components: { Table, OrgsBdPsndocContent },
  data() {
    return {
      content: {
        visiable: false, // 内容界面显示控制
        title: '', // 标题
        action: null, // 执行Action
        updateRow: null // 更新时带过来的temp
      },
      columns: [
        {prop: 'pkPsndoc', label: '主键', width: '10', primary: true},
        {prop: 'psnname', label: '人员姓名', width: '120', search: true},
        {prop: 'psncode', label: '人员编码', width: '120'},
        {prop: 'pkCorp', label: '所属公司', width: '120'},
        {prop: 'pkDeptdoc', label: '所属部门', width: '120'},
        {prop: 'pkPsntype', label: '人员类别', width: '120', show: false},
        {prop: 'sealdate', label: '是否封存', width: '120'},
        {prop: 'directleader', label: '直属领导', width: '120', show: false},
        {prop: 'def1', label: '预留字段1', width: '120', show: false},
        {prop: 'def2', label: '预留字段2', width: '120', show: false},
        {prop: 'def3', label: '预留字段3', width: '120', show: false},
        {prop: 'def4', label: '预留字段4', width: '120', show: false},
        {prop: 'def5', label: '预留字段5', width: '180', show: false},
        {prop: 'creator', label: '创建人', width: '120'},
        {prop: 'creationtime', label: '创建时间', width: '180'},
        {prop: 'dr', label: '删除标志', width: '120', show: false},
        {prop: 'ts', label: '时间戳', width: '120', show: false}
      ],
      select: {
        'select': listBdPsndoc
      },
      top_btn: [
        {name: '录入',code: 'ADD',action: addBdPsndoc}
      ],
      act_btn: [
        {name: '修改',code: 'UPDATE',action: updateBdPsndoc},
        {name: '删除',code: 'DELETE',action: deleteBdPsndocByPks}
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
