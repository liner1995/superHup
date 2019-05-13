import request from '@/utils/request'

// 分页查询所属行业
export function listTrade(data) {
    return request({
        url: '/refdoc/selectAllTradeAsPage',
        method: 'post',
        data
    })
}

// 查询所有所属行业作为参照
export function listTradeAsRef(data) {
    return request({
        url: '/refdoc/selectAllTradeAsRef',
        method: 'post',
        data
    })
}

// 插入所属行业
export function addTrade(data) {
    return request({
        url: '/refdoc/insertTrade',
        method: 'post',
        data
    })
}

// 根据主键集合删除所属行业
export function deleteTradeByPks(data) {
    return request({
        url: '/refdoc/deleteTradeByPks',
        method: 'post',
        data
    })
}

// 更新所属行业
export function updateTrade(data) {
    return request({
        url: '/refdoc/updateTrade',
        method: 'post',
        data
    })
}