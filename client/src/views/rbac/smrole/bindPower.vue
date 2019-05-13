<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-row>
      <el-col :span="24">
        <el-card class="box-card" style="float: left;width: 48%;">
          <div slot="header" class="clearfix">
            <span>菜单</span>
          </div>
          <el-tree
            :data="menuTree.treeData"
            node-key="id"
            :default-checked-keys="menuTree.defaultCheckedMenus"
            @node-click="initBtnByMenu"
            show-checkbox
            ref="menuTree">
          </el-tree>
        </el-card>

        <el-card class="box-card" style="float: right;width: 48%;">
          <div slot="header" class="clearfix">
            <span>按钮</span>
          </div>
          <el-checkbox-group v-model="bindBtn.checkBtnList">
            <el-checkbox v-for="btnItem in bindBtn.btnList" :label="btnItem.pkBtn" :key="btnItem.pkBtn">
              {{btnItem.btnName}}
            </el-checkbox>
          </el-checkbox-group>
        </el-card>
      </el-col>
    </el-row>
    
    <span slot="footer" class="dialog-footer" style="margin-left: 75%;">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="sureDialog">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { initReplaceUpdateData, initReplaceAddData, parseTime, randomNum } from '@/utils/viewCompUtil'
import { listSmMenuAsMenu } from '@/api/rbac/smmenu'
import { listSmBtnByBindBtn } from '@/api/rbac/smbtn'
import { selectAllSmPermissionByPkRole, addSmPerm } from '@/api/rbac/smperm'

export default {
  name: 'BindPower',
  props: ["title", "visiable", 'updateRow'],
  computed: {
    ...mapGetters([
      'listData', 'columns'
    ])
  },
  data() {
    return {
      contentVisiable: false, // dialog 显示框
      menuTree: {
        refAllData: [],
        treeData: [],
        defaultCheckedMenus: []
      },
      bindBtn: {
        checkBtnList: [], // 选中的按钮框
        btnList: [] // 菜单下的所有按钮
      }
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
      this.restTemp()
      this.initPageData()
    }
  },
  methods: {
    // 根据角色初始信息
    initPageData() {
      // 初始菜单参照
      listSmMenuAsMenu().then((response) => {
        this.menuTree.treeData = response.object.treeData
        this.menuTree.refAllData = response.object.refData
      })

      // 初始角色已有权限
      let queryPermByRole = {pkRole: this.updateRow.pkRole}
      selectAllSmPermissionByPkRole(queryPermByRole).then((response) => {
        this.menuTree.defaultCheckedMenus = response.object.MENU_PERM_ALL
      })
    },
    // 根据菜单初始按钮
    initBtnByMenu(node) {
      let refAll = this.menuTree.refAllData
      if (node.id && refAll) {
        let bindBtnIds = ''
        for (let index in refAll) {
          if (refAll[index] && refAll[index].pkMenu === node.id) {
            bindBtnIds = refAll[index].bindBtn
            break
          }
        }
        // 根据菜单查询按钮
        if (bindBtnIds) { // 预判断一次，减少查询次数
          let param = {bindBtn: bindBtnIds}
          listSmBtnByBindBtn(param).then((response) => {
            this.bindBtn.btnList = response.object
          })
        } else {
          this.bindBtn.btnList = []
        }
      }
    },
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      const checkAll = this.$refs.menuTree.getCheckedKeys()// 获取全选的节点
      const checkHalf = this.$refs.menuTree.getHalfCheckedKeys()// 获取半选的节点,一般为父级菜单
      let bindPerm = {pkMenuArrAll: checkAll + '', pkMenuArrHalf: checkHalf + '', pkRole: this.updateRow.pkRole}
      addSmPerm(bindPerm).then((response) => {
        this.$message({ message: '操作成功', type: 'success' })
      })
      this.$emit('close', null)
    },
    restTemp() {
      this.menuTree.defaultCheckedMenus = []
      this.bindBtn.checkBtnList = []
      this.bindBtn.btnList = []
    }
  }
}
</script>

<style>

</style>
