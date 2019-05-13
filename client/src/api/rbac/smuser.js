import request from '@/utils/request'

// 分页查询系统菜单
export function listSmUser(data) {
  return request({
    url: '/rbac/selectAllSmUserAsPage',
    method: 'post',
    data
  })
}

// 新增系统菜单
export function addSmuser(data) {
  return request({
    url: '/rbac/insertSmUser',
    method: 'post',
    data
  })
}

// 更新系统菜单
export function updateSmUser(data) {
  return request({
    url: '/rbac/updateSmUser',
    method: 'post',
    data
  })
}

// 根据主键集合删除系统菜单
export function deleteSmUserByPks(data) {
  return request({
    url: '/rbac/deleteSmUserByPks',
    method: 'post',
    data
  })
}