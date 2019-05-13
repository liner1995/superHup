<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="9" :sm="9" :lg="5" class="card-panel-col">
      <div class="card-panel" @click="handleSetMsgView('待办事项')">
        <div class="card-panel-icon-wrapper icon-todo">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">待办事项</div>
        </div>
      </div>
    </el-col>
    <el-col :xs="9" :sm="9" :lg="5" class="card-panel-col">
      <div class="card-panel" @click="handleSetMsgView('任务认领')">
        <div class="card-panel-icon-wrapper icon-todo">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">任务认领</div>
        </div>
      </div>
    </el-col>
    <el-col :xs="9" :sm="9" :lg="5" class="card-panel-col">
      <div class="card-panel" @click="handleSetMsgView('已办事项')">
        <div class="card-panel-icon-wrapper icon-done">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">已办事项</div>
        </div>
      </div>
    </el-col>
    <el-col :xs="9" :sm="9" :lg="5" class="card-panel-col">
      <div class="card-panel" @click="handleSetMsgView('预警消息')">
        <div class="card-panel-icon-wrapper icon-warn">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">预警消息</div>
        </div>
      </div>
    </el-col>
    <el-col :xs="9" :sm="9" :lg="4" class="card-panel-col">
      <div class="card-panel" @click="handleSetMsgView('通知公告')">
        <div class="card-panel-icon-wrapper icon-notice">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">通知公告</div>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>

export default {
  methods: {
    handleSetMsgView(type) {
      this.$emit('handleSetMsgView', type)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.panel-group {
  margin-top: 18px;
  .card-panel-col{
    margin-bottom: 32px;
  }
  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-todo {
         background: #40c9c6;
      }
      .icon-done {
        background: #36a3f7;
      }
      .icon-warn {
        background: #f4516c;
      }
      .icon-notice {
        background: #34bfa3
      }
    }
    .icon-todo {
      color: #40c9c6;
    }
    .icon-done {
      color: #36a3f7;
    }
    .icon-warn {
      color: #f4516c;
    }
    .icon-notice {
      color: #34bfa3
    }
    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 48px;
    }
    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;
      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}
</style>
