<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <el-form :inline="true" ref="dataForm" :model="temp" label-position="left" label-width="120px">
      <el-form-item label="菜单名称" prop="menuName">
        <el-input v-model="temp.menuName"></el-input>
      </el-form-item>
      <el-form-item label="菜单编码" prop="menuCode">
        <el-input v-model="temp.menuCode" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="URL" prop="url">
        <el-input v-model="temp.url"></el-input>
      </el-form-item>
      <el-form-item label="图标" prop="icno">
        <el-input v-model="temp.icno"></el-input>
      </el-form-item>

      <el-form-item label="父级菜单" prop="parentId">
        <el-select v-model="temp.parentId" filterable placeholder="请选择" width="20px">
          <el-option
            v-for="item in menuOption"
            :key="item.pkMenu"
            :label="item.menuName"
            :value="item.pkMenu">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="排序" prop="sort">
        <el-input-number v-model="temp.sort" :min="1" :max="99"></el-input-number>
      </el-form-item>

      <el-form-item label="绑定按钮" prop="bindBtnArr">
        <el-select v-model="bindBtnArr" multiple placeholder="请选择">
          <el-option
            v-for="item in btnOption"
            :key="item.pkBtn"
            :label="item.btnName"
            :value="item.pkBtn">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="创建人" prop="creator">
        <el-input v-model="temp.creator" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="创建时间" prop="creationtime">
        <el-input v-model="temp.creationtime" :disabled="true"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="sureDialog">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { initReplaceUpdateData, initReplaceAddData, parseTime, randomNum, initElSelectManyShowData, initElSelectManySaveData } from '@/utils/viewCompUtil'
import { listSmMenuAsMenu } from '@/api/rbac/smmenu'
import { listSmBtnAsRef } from '@/api/rbac/smbtn'

export default {
  name: 'Smmenucontent',
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
        pkMenu: undefined,
        menuName: '',
        menuCode: 'MENU' + randomNum(100*1000),
        icno: '',
        parentId: null,
        url: '',
        sort: null,
        bindBtn: '',
        creator: '',
        creationtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      },
      bindBtnArr: [], // 多选按钮控件绑定值
      menuOption: [],
      btnOption: []
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
      if (this.title === '录入') {
        this.restTemp()
        // 初始菜单参照数据
        listSmMenuAsMenu().then((response) => {
          this.menuOption = response.object.refData
        })
      }
      if (this.title === '修改' && this.updateRow) {
        this.temp = this.updateRow
        this.bindBtnArr = initElSelectManyShowData(this.temp.bindBtn)
      }
    }
  },
  created() {
    // 初始菜单参照数据
    listSmMenuAsMenu().then((response) => {
      this.menuOption = response.object.refData
    })
    // 初始所有按钮参照
    listSmBtnAsRef().then((response) => {
      this.btnOption = response.object
    })
  },
  methods: {
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    },
    sureDialog() { // 确定保存回调Dialog
      const actTemp = this.temp
      actTemp.bindBtn = initElSelectManySaveData(this.bindBtnArr)
      if (this.action) {
        initReplaceUpdateData(actTemp, this.replace)
        this.action(actTemp).then((response) => {
          if (response.primary) {
            actTemp.pkMenu = response.primary
          }
          if (response.success) {
            initReplaceAddData(actTemp, this.replace)
            this.$store.commit('HANDLE_LISTVIEW_DATA', actTemp)
            this.$message({ message: '操作成功', type: 'success' })
          } else {
            this.$message({ message: `操作失败:${response.message}`, type: 'error' })
          }
        })
      }
      this.$emit('close', actTemp)
    },
    restTemp() { // 刷新本界面的数据
      this.temp = {
        pkMenu: undefined,
        menuName: '',
        menuCode: 'MENU' + randomNum(100*1000),
        icno: '',
        parentId: null,
        url: '',
        sort: null,
        bindBtn: '',
        creator: '',
        creationtime: parseTime(new Date()),
        dr: 0,
        ts: parseTime(new Date())
      }
      this.bindBtnArr = []
    }
  }
}
</script>

<style>

</style>
