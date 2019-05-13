import request from '@/utils/request'

// 根据角色查询权限资源
export function selectAllSmPermissionByPkRole(data) {
  return request({
    url: '/rbac/selectAllSmPermissionByPkRole',
    method: 'post',
    data
  })
}

// 新增权限资源
export function addSmPerm(data) {
  return request({
    url: '/rbac/insertPermission',
    method: 'post',
    data
  })
}