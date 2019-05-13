import request from '@/utils/request'

// 分页查询公司基本档案
export function listBdCorp(data) {
    return request({
        url: '/bdcorp/selectAllBdCorpAsPage',
        method: 'post',
        data
    })
}

// 分页查询公司基本档案
export function listBdCorpAsRef() {
    return request({
        url: '/bdcorp/selectAllBdCorpAsRef',
        method: 'post'
    })
}

// 插入公司基本档案
export function addBdCorp(data) {
  return request({
      url: '/bdcorp/insertBdCorp',
      method: 'post',
      data
  })
}

// 根据主键集合删除公司档案
export function deleteBdCorpByPks(data) {
    return request({
        url: '/bdcorp/deleteBdCorpByPks',
        method: 'post',
        data
    })
}

// 根据主键更新公司档案
export function updateBdCorp(data) {
    return request({
        url: '/bdcorp/updateBdCorp',
        method: 'post',
        data
    })
}