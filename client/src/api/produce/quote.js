import request from '@/utils/request'

// 分页查询所有生产保价信息
export function listProduceQuote(data) {
    return request({
        url: '/produce/selectAllProduceQuoteAsPage',
        method: 'post',
        data
    })
}

// 新增生产报价信息
export function addProduceQuote(data) {
    return request({
        url: '/produce/insertProduceQuote',
        method: 'post',
        data
    })
}

// 根据主键集合删除生产报价信息
export function deleteProduceQuoteByPks(data) {
    return request({
        url: '/produce/deleteProduceQuoteByPks',
        method: 'post',
        data
    })
}

// 更新生产报价信息
export function updateProduceQuote(data) {
    return request({
        url: '/produce/updateProduceQuote',
        method: 'post',
        data
    })
}

// 批量更新生成报价信息
export function updateBatchProduceQuoteB(data) {
    return request({
        url: '/produce/updateBatchProduceQuoteB',
        method: 'post',
        data
    })
}

// 完成生成报价
export function overPassProduceQuote(data) {
    return request({
        url: '/quoteprocess/overPassProduceQuote',
        method: 'post',
        data
    })
}