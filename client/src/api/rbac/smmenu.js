import request from '@/utils/request'

// 分页查询系统菜单
export function listSmMenu(data) {
  return request({
    url: '/rbac/selectAllSmMenuAsPage',
    method: 'post',
    data
  })
}

// 查询系统菜单为参照、树形结构数据
export function listSmMenuAsMenu() {
  return request({
    url: '/rbac/selectAllSmMenuAsMenu',
    method: 'post'
  })
}

// 新增系统菜单
export function addSmMenu(data) {
  return request({
    url: '/rbac/insertSmMenu',
    method: 'post',
    data
  })
}

// 更新系统菜单
export function updateSmMenu(data) {
  return request({
    url: '/rbac/updateSmMenu',
    method: 'post',
    data
  })
}

// 根据主键集合删除系统菜单
export function deleteSmMenuByPks(data) {
  return request({
    url: '/rbac/deleteSmMenuByPks',
    method: 'post',
    data
  })
}