import request from '@/utils/request'

// 分页查询系统菜单
export function listSmBtn(data) {
  return request({
    url: '/rbac/selectAllBtnAsPage',
    method: 'post',
    data
  })
}

// 查询所有按钮作为参照
export function listSmBtnAsRef() {
  return request({
    url: '/rbac/selectAllSmBtnAsRef',
    method: 'post'
  })
}

// 根据菜单中绑定的按钮主键查询按钮信息
export function listSmBtnByBindBtn(data) {
  return request({
    url: '/rbac/selectSmBtnByBindBtn',
    method: 'post',
    data
  })
}

// 新增系统菜单
export function addSmBtn(data) {
  return request({
    url: '/rbac/insertSmBtn',
    method: 'post',
    data
  })
}

// 更新系统菜单
export function updateSmBtn(data) {
  return request({
    url: '/rbac/updateSmBtn',
    method: 'post',
    data
  })
}

// 根据主键集合删除系统菜单
export function deleteSmBtnByPks(data) {
  return request({
    url: '/rbac/deleteSmBtnByPks',
    method: 'post',
    data
  })
}