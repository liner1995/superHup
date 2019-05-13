import request from '@/utils/request'

// 根据用户查询用户代办/待认领任务列表
export function listUserTask(data) {
    return request({
        url: '/quoteprocess/selectTaskByUserId',
        method: 'post',
        data
    })
}

// 用户认领任务
export function userClaimTask(data) {
    return request({
        url: '/quoteprocess/claim',
        method: 'post',
        data
    })
}