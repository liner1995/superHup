import request from '@/utils/request'
import axios from 'axios'

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

// 查询流程图
export function findFlowImage(data) {
    return axios({
        url: process.env.BASE_API + '/quoteprocess/processDiagram',
        method: 'post',
        responseType: 'blob',
        data
    })
}