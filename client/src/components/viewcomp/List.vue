<template>
  <el-table :data="listData" border fit @selection-change="selsChange" @row-click="rowClick">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column 
      v-for="(column , index) in columns" 
      v-if="column.primary === true ? false : true"
      :key="index"
      :prop="column.prop"
      :label="column.label"
      :width="column.width">
    </el-table-column>
    <el-table-column label="操作栏">
      <template slot-scope="scope">
        <el-button v-for="(btn, index) in actBtns"
          v-if="checkBtnIsShow(btn, scope.row).show"
          :key="index"
          @click="buttonFunction(btn.name, btn.code, btn.action, scope.row)">
          {{btn.name}}
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: "List",
    props: ["replace"],
    computed: {
      ...mapGetters([
        'listData', 'columns', 'actBtns'
      ])
    },
    data() {
      return {
        sels: []// 选中的列表
      }
    },
    methods: {
      // 校验当前按钮是否显示
      checkBtnIsShow(btn, row) {
        let defaultShow = true // 默认显示
        if (btn && row) {
          let btnWhen = btn.when
          if (btnWhen) {
            defaultShow = false // 如果有when属性默认按钮为不显示
            for (let key in btnWhen) {
              let caseValueArr = btnWhen[key] // 条件值数组
              let rowDataVal = this.initRealValueByReplace(row[key])
              if (caseValueArr) {
                for (let caseIndex in caseValueArr) {
                  if (caseValueArr[caseIndex] == rowDataVal) {
                    defaultShow = true
                  }
                }
              }
            }
          }
        }
        return {show: defaultShow}
      },
      // 将显示的值还原为真实的值
      initRealValueByReplace(showValue) {
        let replace = this.replace
        if (showValue && replace) {
          for (let index in replace) {
            let porp = replace[index].name
            let repVal = replace[index].replace
            for (let key in repVal) {
              let rpValue = repVal[key]
              if (showValue === rpValue) {
                showValue = key
                return showValue
              }
            }
          }
        }
        return showValue;
      },
      buttonFunction(name, code, action, row) {
        let param = {'btnName': name, 'btnCode': code, 'btnAction': action, 'bindData': row}
        this.$emit('onButtonClick', param)
      },
      rowClick(row, event, column) { // 列表中的行被点击后
        let param = {'btnName': 'rowClick', 'btnCode': 'rowClick', 'bindData': row}
        this.$emit('onButtonClick', param)
      },
      selsChange(sels) {// 更新选中列表的值
        this.sels = sels
      }
    }
}
</script>

<style scoped>

</style>