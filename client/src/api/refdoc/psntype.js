import request from '@/utils/request'

// 分页查询人员类别
export function listPsntype(data) {
    return request({
        url: '/refdoc/selectAllPsntypeAsPage',
        method: 'post',
        data
    })
}

// 分页查询人员类别
export function listPsntypeAsRef(data) {
    return request({
        url: '/refdoc/selectAllPsntypeAsRef',
        method: 'post',
        data
    })
}

// 插入人员类别
export function addPsntype(data) {
    return request({
        url: '/refdoc/insertPsntype',
        method: 'post',
        data
    })
}

// 根据主键集合删除人员类别
export function deletePsntypeByPks(data) {
    return request({
        url: '/refdoc/deletePsntypeByPks',
        method: 'post',
        data
    })
}

// 更新人员类别
export function updatePsntype(data) {
    return request({
        url: '/refdoc/updatePsntype',
        method: 'post',
        data
    })
}