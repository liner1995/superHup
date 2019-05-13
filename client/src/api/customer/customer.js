import request from '@/utils/request'

// 分页查询所有客户信息
export function listCustomer(data) {
    return request({
        url: '/customer/selectAllCustomerAsPage',
        method: 'post',
        data
    })
}

// 查询所有客户作为参照
export function listCustomerAsRef() {
  return request({
      url: '/customer/selectAllCustomerAsRef',
      method: 'post'
  })
}

// 新增客户信息
export function addCustomer(data) {
    return request({
        url: '/customer/insertCustomer',
        method: 'post',
        data
    })
}

// 根据主键集合删除客户信息
export function deleteCustomerByPks(data) {
    return request({
        url: '/customer/deleteCustomerByPks',
        method: 'post',
        data
    })
}

// 更新客户信息
export function updateCustomer(data) {
    return request({
        url: '/customer/updateCustomer',
        method: 'post',
        data
    })
}

// 查询客户基本信息集合
export function selectCustomInfo(data) {
    return request({
        url: '/customer/selectCustomInfo',
        method: 'post',
        data
    })
}

// 增加或修改收货信息
export function saveTakeOver(data) {
    return request({
        url: '/customer/saveTakeOver',
        method: 'post',
        data
    })
}

// 增加或修改收货信息
export function saveInvoice(data) {
    return request({
        url: '/customer/saveInvoice',
        method: 'post',
        data
    })
}

// 保存或修改客户人员信息
export function saveCustomdoc(data) {
    return request({
        url: '/customer/saveCustomdoc',
        method: 'post',
        data
    })
}

// 根据客户查询客户设置的默认联系人
export function selectCustomdocDefaultPsn(data) {
    return request({
        url: '/customer/selectCustomdocDefaultPsn',
        method: 'post',
        data
    })
}

// 查询所有客户人员信息档案作为参照
export function selectAllCustomdocAsRef(data) {
    return request({
        url: '/customer/selectAllCustomdocAsRef',
        method: 'post',
        data
    })
}