import request from '@/utils/request'

// 分页查询所有加工工艺信息
export function listTecaudit(data) {
  data.billState = 1
  return request({
      url: '/technics/selectAllTechniceAsPage',
      method: 'post',
      data
  })
}

// 更新加工工艺信息
export function updateTecaudit(data) {
  return request({
      url: '/sale/updateProduct',
      method: 'post',
      data
  })
}

// 工艺审核通过
export function auditPassTecaudit(data) {
  return request({
      url: '/quoteprocess/auditPassTecaudit',
      method: 'post',
      data
  })
}