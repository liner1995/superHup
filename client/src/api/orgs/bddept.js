import request from '@/utils/request'

// 分页查询部门基本档案
export function listBdDept(data) {
    return request({
        url: '/bdcorp/selectAllBdDeptdocAsPage',
        method: 'post',
        data
    })
}

// 根据公司查询所有部门作为参照
export function listBdDeptAsRef(data) {
    return request({
        url: '/bdcorp/selectAllBdDeptdocAsRef',
        method: 'post',
        data
    })
}

// 插入部门基本档案
export function addBdDept(data) {
  return request({
      url: '/bdcorp/insertBdDeptdoc',
      method: 'post',
      data
  })
}

// 根据主键集合删除部门档案
export function deleteBdDeptdocByPks(data) {
    return request({
        url: '/bdcorp/deleteBdDeptdocByPks',
        method: 'post',
        data
    })
}

// 根据主键更新部门档案
export function updateBdDeptdoc(data) {
    return request({
        url: '/bdcorp/updateBdDeptdoc',
        method: 'post',
        data
    })
}