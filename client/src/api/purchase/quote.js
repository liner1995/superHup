import request from '@/utils/request'

// 分页查询所有采购保价信息
export function listPurchase(data) {
    return request({
        url: '/purchase/selectPurchaseQuoteAsPage',
        method: 'post',
        data
    })
}

// 新增采购报价信息
export function addPurchase(data) {
    return request({
        url: '/purchase/insertPurchaseQuote',
        method: 'post',
        data
    })
}

// 根据主键集合删除销售采购信息
export function deletePurchaseQuoteByPks(data) {
    return request({
        url: '/purchase/deletePurchaseQuoteByPks',
        method: 'post',
        data
    })
}

// 更新采购报价信息
export function updatePurchaseQuote(data) {
    return request({
        url: '/purchase/updatePurchaseQuote',
        method: 'post',
        data
    })
}

// 保存采购报价文件
export function savePurchaseFile(data) {
    return request({
        url: '/purchase/savePurchaseFile',
        method: 'post',
        data
    })
}

// 完成采购报价
export function overPassPurchaseQuote(data) {
    return request({
        url: '/quoteprocess/overPassPurchaseQuote',
        method: 'post',
        data
    })
}