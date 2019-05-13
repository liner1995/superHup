import request from '@/utils/request'

// 用户登陆
export function login(userName, userPassword) {
  return request({
    url: '/systemController/login',
    method: 'post',
    data: {
      userName,
      userPassword
    }
  })
}

// 用户登出
export function logout(token) {
  return request({
    url: '/systemController/logout',
    method: 'get',
    params: { token }
  })
}

// 获取用户信息
export function getInfo(token) {
  return request({
    url: '/systemController/getInfo',
    method: 'get',
    params: { token }
  })
}


