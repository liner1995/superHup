<template>
    <div>
        <div v-if="search_columns && search_columns.length > 0" style="float: left">
            <el-input placeholder="请输入搜索内容" v-model="search_val" class="input-with-select" style="width: 400px;" clearable @clear="clear">
                <el-select v-model="select_val" slot="prepend" placeholder="请选择" value="" style="width: 150px;">
                    <el-option :label="column.label" :value="column.prop" :key="index" v-for="(column , index) in search_columns"></el-option>
                </el-select>
                <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
            </el-input>
        </div>
        <el-button-group style="margin-left: 1rem;" :key="index" v-for="(btn, index) in topBtns">
            <el-button type="primary" @click="clickBtn(btn.name, btn.code, btn.action)">{{btn.name}}</el-button>
        </el-button-group>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: "Top",
  props: ["search_columns"],
  computed: {
    ...mapGetters([
      'topBtns'
    ])
  },
  data () {
    return {
      search_val: '',
      select_val: ''
    }
  },
  methods: {
    search () {
      let paramProp = {'selprop': this.select_val, 'selval': this.search_val}
      // 触发查询逻辑
      this.$emit('getSearchData', paramProp)
    },
    clickBtn(name, code, action) {
      let param = {'btnName': name, 'btnCode': code, 'btnAction': action}
      this.$emit('onButtonClick', param)
    },
    clear(){
      this.search()
    }
  }
}
</script>

<style scoped>

</style>