import request from '@/utils/request'

// 根据用户ID查询所有用户绑定角色
export function listUserRoleByUserid(data) {
  return request({
    url: '/rbac/selectAllUserRoleByUserid',
    method: 'post',
    data
  })
}

// 插入用户绑定角色
export function addUserRole(data) {
  return request({
    url: '/rbac/insertUserRole',
    method: 'post',
    data
  })
}