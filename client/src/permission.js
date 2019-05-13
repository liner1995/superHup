import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth' // 验权
import Layout from './views/layout/Layout'

const _import = require('./router/_import_' + process.env.NODE_ENV) // 获取组件的方法
let addRourtersLoad = true
let getRouters = []

const whiteList = ['/login'] // 不重定向白名单
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    if (to.path === '/login') { // 如果当前页面是登陆也 直接通过
      next({ path: '/' })
      NProgress.done()
    } else {
      if (store.getters.roles.length === 0) {
        store.dispatch('GetInfo').then(res => { // 拉取用户信息
          // 根据拉取的用户信息组装router,动态加载菜单
          if (addRourtersLoad) {
            initAddRouters(res.object.perms)
            router.options.routes = router.options.routes.concat(getRouters)
            router.addRoutes(getRouters)
            next({ ...to, replace: true })
            addRourtersLoad = false
          } else {
            next()
          }
        }).catch((err) => {
          store.dispatch('FedLogOut').then(() => {
            Message.error(err.message)
            next({ path: '/' })
          })
        })
      } else {
        next()
      }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next(`/login?redirect=${to.path}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})

// 组装router数据
function initAddRouters(perms) {
  getRouters = filterAsyncRouter(perms)
}

// 递归操作
function filterAsyncRouter(asyncRouterMap) { // 遍历后台传来的路由字符串，转换为组件对象
  const accessedRouters = asyncRouterMap.filter(route => {
    if (route.component == '') { // Layout组件特殊处理
      route.component = Layout
      route.path = '/' + route.name
      if (route.name == '首页') {
        route.path = '/'
      }
    } else {
      route.component = _import(route.component)
    }
    if (route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children)
    }
    return true
  })

  return accessedRouters
}
