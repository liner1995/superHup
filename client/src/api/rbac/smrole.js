import request from '@/utils/request'

// 分页查询系统菜单
export function listSmRole(data) {
  return request({
    url: '/rbac/selectAllSmRoleAsPage',
    method: 'post',
    data
  })
}

// 查询所有角色作为穿梭框数据
export function listSmRoleAsTransfer() {
  return request({
    url: '/rbac/selectAllSmRoleAsTransfer',
    method: 'post'
  })
}

// 新增系统菜单
export function addSmRole(data) {
  return request({
    url: '/rbac/insertSmRole',
    method: 'post',
    data
  })
}

// 更新系统菜单
export function updateSmRole(data) {
  return request({
    url: '/rbac/updateSmRole',
    method: 'post',
    data
  })
}

// 根据主键集合删除系统菜单
export function deleteSmRoleByPks(data) {
  return request({
    url: '/rbac/deleteSmRoleByPks',
    method: 'post',
    data
  })
}