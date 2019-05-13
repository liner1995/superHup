const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar, // 用户头像
  name: state => state.user.name,
  userid: state => state.user.userid,
  pkCorp: state => state.user.pkCorp,
  unitname: state => state.user.unitname,
  roles: state => state.user.roles,

  columns: state => state.viewComp.columns, // 列表字段
  listData: state => state.viewComp.listData, // 列表数据
  topBtns: state => state.viewComp.topBtns, // 页面顶部按钮
  actBtns: state => state.viewComp.actBtns // 操作栏按钮
}
export default getters
