import request from '@/utils/request'

// 分页查询人员信息基本档案
export function listBdPsndoc(data) {
    return request({
        url: '/bdcorp/selectAllBdPsndocAsPage',
        method: 'post',
        data
    })
}

// 插入人员基本档案
export function addBdPsndoc(data) {
  return request({
      url: '/bdcorp/insertBdPsndoc',
      method: 'post',
      data
  })
}

// 根据主键集合删除人员信息
export function deleteBdPsndocByPks(data) {
    return request({
        url: '/bdcorp/deleteBdPsndocByPks',
        method: 'post',
        data
    })
}

// 根据主键更新人员信息
export function updateBdPsndoc(data) {
    return request({
        url: '/bdcorp/updateBdPsndoc',
        method: 'post',
        data
    })
}

// 查询所有人员信息档案作为参照
export function listBdPsndocAsRef(data) {
    return request({
        url: '/bdcorp/selectAllBdPsndocAsRef',
        method: 'post',
        data
    })
}