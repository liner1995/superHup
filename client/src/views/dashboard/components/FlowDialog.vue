<template>
  <el-dialog :title="title" 
    :visible.sync="contentVisiable" 
    @close="closeDialog"
    width="60%">
    <img :src="flowPrice" class="image">
    <span slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">关 闭</el-button>
    </span>
  </el-dialog>
</template>

<script>

export default {
  name: 'FlowDialog',
  props: ["title", "visiable", "flowPrice"],

  data() {
    return {
      contentVisiable: false, // dialog 显示框
    }
  },
  watch: {
    visiable() {
      this.contentVisiable = this.visiable
    }
  },
  methods: {
    closeDialog() { // 回调关闭Dialog
      this.$emit('close', null)
    }
  }
}
</script>

<style>

</style>
