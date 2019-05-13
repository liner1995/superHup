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
    <Smusercontent 
      :replace="replace"
      :visiable="content.visiable" 
      :title="content.title"
      :action="content.action"
      :updateRow="content.updateRow"
      @close="closeContent">
    </Smusercontent>
    <!--绑定角色界面-->
    <BindRole 
      :visiable="bindRole.visiable" 
      :title="bindRole.title"
      :updateRow="bindRole.updateRow"
      @close="closeBindRole">
    </BindRole>
  </div>
</template>

<script>
import Table from '@/components/viewcomp/Table'
import Smusercontent from '@/views/rbac/smuser/content'
import BindRole from '@/views/rbac/smuser/bindRole'
import { listSmUser, addSmuser, updateSmUser, deleteSmUserByPks } from '@/api/rbac/smuser'

export default {
  name: "smuser",
  components: { Table, Smusercontent, BindRole },
  data() {
    return {
      content: {
        visiable: false, // 内容界面显示控制
        title: '', // 标题
        action: null, // 执行Action
        updateRow: null // 更新时带过来的temp
      },
      bindRole: {
        visiable: false, 
        title: '角色绑定', 
        updateRow: null 
      },
      columns: [
        {prop: 'userid', label: '主键', width: '10', primary: true},
        {prop: 'userName', label: '用户名称', width: '120', search: true},
        {prop: 'userCode', label: '用户编码', width: '120'},
        {prop: 'userPassword', label: '用户密码', width: '120', show: false},
        {prop: 'memo', label: '用户描述', width: '120', show: false},
        {prop: 'email', label: 'email地址', width: '120', show: false},
        {prop: 'phone', label: '联系电话', width: '120', show: false},
        {prop: 'islocked', label: '是否锁定', width: '120'},
        {prop: 'enablestate', label: '是否启用', width: '120'},
        {prop: 'pkPsndoc', label: '关联人员', width: '120', show: false},
        {prop: 'lockNum', label: '加密锁号', width: '120', show: false},
        {prop: 'randomNum', label: '盐值', width: '120', show: false},
        {prop: 'creator', label: '创建人', width: '120'},
        {prop: 'creationtime', label: '创建时间', width: '180'},
        {prop: 'modifier', label: '修改人', width: '120', show: false},
        {prop: 'modifiedtime', label: '最后修改时间', width: '180', show: false},
        {prop: 'dr', label: '删除标志', width: '120', show: false},
        {prop: 'ts', label: '时间戳', width: '120', show: false}
      ],
      select: {
        'select': listSmUser
      },
      top_btn: [
        {name: '录入',code: 'ADD',action: addSmuser}
      ],
      act_btn: [
        {name: '绑定角色',code: 'BINDROLE'},
        {name: '修改',code: 'UPDATE',action: updateSmUser},
        {name: '删除',code: 'DELETE',action: deleteSmUserByPks},
      ],
      replace: [
        {name: 'islocked',replace: {0:"否",1:"是"}},
        {name: 'enablestate',replace: {0:"启用",1:"未启用"}}
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
      if (param.btnCode === 'BINDROLE') {
        this.openBindRole()
        this.bindRole.updateRow = param.bindData
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
    // 打开绑定角色穿梭框
    openBindRole() {
      this.bindRole.visiable = true
    },
    // 关闭绑定角色穿梭框
    closeBindRole(tempData) {
      this.bindRole.visiable = false
    }
  }
}
</script>

<style>

</style>
