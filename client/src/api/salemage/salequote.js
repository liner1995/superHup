import request from '@/utils/request'

// 分页查询所有销售报价信息
export function listSaleQuote(data) {
    return request({
        url: '/sale/selectAllSaleQuoteAsPage',
        method: 'post',
        data
    })
}

// 新增销售报价信息
export function addSaleQuote(data) {
    return request({
        url: '/sale/insertSaleQuote',
        method: 'post',
        data
    })
}

// 根据主键集合删除销售报价信息
export function deleteSaleQuoteByPks(data) {
    return request({
        url: '/sale/deleteSaleQuoteByPks',
        method: 'post',
        data
    })
}

// 更新销售报价信息
export function updateSaleQuote(data) {
    return request({
        url: '/sale/updateSaleQuote',
        method: 'post',
        data
    })
}

// 完成销售报价
export function overProccessSaleQupte(data) {
    return request({
        url: '/quoteprocess/overProccessSaleQupte',
        method: 'post',
        data
    })
}