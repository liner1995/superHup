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
    <Smrolecontent 
      :replace="replace"
      :visiable="content.visiable" 
      :title="content.title"
      :action="content.action"
      :updateRow="content.updateRow"
      @close="closeContent">
    </Smrolecontent>
    <!--绑定权限界面-->
    <BindPower
      :visiable="bindPower.visiable"
      :title="bindPower.title"
      :updateRow="bindPower.updateRow"
      @close="closeBindPower">
    </BindPower>
  </div>
</template>

<script>
import Table from '@/components/viewcomp/Table'
import Smrolecontent from '@/views/rbac/smrole/content'
import BindPower from '@/views/rbac/smrole/bindPower'
import { listSmRole, addSmRole, updateSmRole, deleteSmRoleByPks } from '@/api/rbac/smrole'

export default {
  name: "smrole",
  components: { Table, Smrolecontent, BindPower },
  data() {
    return {
      content: {
        visiable: false, // 内容界面显示控制
        title: '', // 标题
        action: null, // 执行Action
        updateRow: null // 更新时带过来的temp
      },
      bindPower: { // 绑定权限界面属性
        visiable: false,
        title: '权限设置',
        updateRow: null
      },
      columns: [
        {prop: 'pkRole', label: '主键', width: '10', primary: true},
        {prop: 'roleName', label: '角色名称', width: '120', search: true},
        {prop: 'roleCode', label: '角色编码', width: '120'},
        {prop: 'roleMemo', label: '角色描述', width: '120', show: false},
        {prop: 'roleType', label: '角色类型', width: '120'},
        {prop: 'pkOrg', label: '所属公司', width: '120', show: false},
        {prop: 'pkGroup', label: '所属集团', width: '120', show: false},
        {prop: 'creator', label: '创建人', width: '120'},
        {prop: 'creationtime', label: '创建时间', width: '180'},
        {prop: 'modifier', label: '修改人', width: '120', show: false},
        {prop: 'modifiedtime', label: '最后修改时间', width: '180', show: false},
        {prop: 'dr', label: '删除标志', width: '120', show: false},
        {prop: 'ts', label: '时间戳', width: '120', show: false}
      ],
      select: {
        'select': listSmRole
      },
      top_btn: [
        {name: '录入',code: 'ADD',action: addSmRole}
      ],
      act_btn: [
        {name: '设置权限',code: 'BINDPOWER'},
        {name: '修改',code: 'UPDATE',action: updateSmRole},
        {name: '删除',code: 'DELETE',action: deleteSmRoleByPks}
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
      if (param.btnCode === 'BINDPOWER') {
        this.openBindPower()
        this.bindPower.updateRow = param.bindData
      }
    },
    openContent(title, action) { // 打开内容界面Dialog
      this.content.visiable = true
      this.content.title = title
      this.content.action = action
    },
    closeContent(tempData) { // 关闭内容界面Dialog
      this.content.visiable = false
    },
    // 打开绑定权限Dialog
    openBindPower() {
      this.bindPower.visiable = true
    },
    // 关闭绑定权限Dialog
    closeBindPower(tempData) {
      this.bindPower.visiable = false
    }
  }
}
</script>

<style>

</style>
