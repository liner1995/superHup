import axios from 'axios'

// 根据文件名称下载文件
export function downloadProductFile(data) {
  return axios({
    url: process.env.BASE_API + '/sale/downloadProductFile',
    method: 'post',
    responseType: 'blob',
    data
  })
}