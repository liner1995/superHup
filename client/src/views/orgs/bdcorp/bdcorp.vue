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
    <OrgBdCorpContent 
      :replace="replace"
      :visiable="content.visiable" 
      :title="content.title"
      :action="content.action"
      :updateRow="content.updateRow"
      @close="closeContent">
    </OrgBdCorpContent>
  </div>
</template>

<script>
import Table from '@/components/viewcomp/Table'
import OrgBdCorpContent from '@/views/orgs/bdcorp/content'
import { listBdCorp, addBdCorp, deleteBdCorpByPks, updateBdCorp } from '@/api/orgs/bdcorp'

export default {
  name: "OrgsBdCorp",
  components: { Table, OrgBdCorpContent },
  data() {
    return {
      content: {
        visiable: false, // 内容界面显示控制
        title: '', // 标题
        action: null, // 执行Action
        updateRow: null // 更新时带过来的temp
      },
      columns: [
        {prop: 'pkCorp', label: '主键', width: '10', primary: true},
        {prop: 'unitname', label: '公司名称', width: '120', search: true},
        {prop: 'unitshortname', label: '公司简称', width: '120'},
        {prop: 'unitcode', label: '公司编码', width: '120'},
        {prop: 'phone1', label: '公司电话1', width: '120', show: false},
        {prop: 'phone2', label: '公司电话2', width: '120', show: false},
        {prop: 'phone3', label: '公司电话3', width: '120', show: false},
        {prop: 'industry', label: '所属行业', width: '120', show: false},
        {prop: 'isseal', label: '是否封存', width: '120', show: false},
        {prop: 'sealeddate', label: '封存日期', width: '120', show: false},
        {prop: 'url', label: 'WEB网址', width: '120'},
        {prop: 'memo', label: '描述', width: '120', show: false},
        {prop: 'def1', label: '预留字段1', width: '120', show: false},
        {prop: 'def2', label: '预留字段2', width: '120', show: false},
        {prop: 'def3', label: '预留字段3', width: '120', show: false},
        {prop: 'def4', label: '预留字段4', width: '120', show: false},
        {prop: 'def5', label: '预留字段5', width: '120', show: false},
        {prop: 'creator', label: '创建人', width: '120'},
        {prop: 'creationtime', label: '创建时间', width: '180'},
        {prop: 'dr', label: '删除标志', width: '120', show: false},
        {prop: 'ts', label: '时间戳', width: '120', show: false}
      ],
      select: {
        'select': listBdCorp
      },
      top_btn: [
        {name: '录入',code: 'ADD',action: addBdCorp}
      ],
      act_btn: [
        {name: '修改',code: 'UPDATE',action: updateBdCorp},
        {name: '删除',code: 'DELETE',action: deleteBdCorpByPks}
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
