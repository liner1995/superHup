import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: false,
    meta: { title: '首页', icon: 'example' },
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  // {
  //   path: '/rbac',
  //   component: Layout,
  //   redirect: '/rbac',
  //   name: 'RBAC',
  //   meta: { title: '权限管理', icon: 'example' },
  //   children: [
  //     {
  //       path: 'smmenu',
  //       name: 'Smmenu',
  //       component: () => import('@/views/rbac/smmenu/smmenu'),
  //       meta: { title: '菜单管理', icon: 'table' }
  //     },
  //     {
  //       path: 'smbtn',
  //       name: 'Smbtn',
  //       component: () => import('@/views/rbac/smbtn/smbtn'),
  //       meta: { title: '按钮管理', icon: 'table' }
  //     },
  //     {
  //       path: 'smrole',
  //       name: 'Smrole',
  //       component: () => import('@/views/rbac/smrole/smrole'),
  //       meta: { title: '角色管理', icon: 'table' }
  //     },
  //     {
  //       path: 'smuser',
  //       name: 'Smuser',
  //       component: () => import('@/views/rbac/smuser/smuser'),
  //       meta: { title: '用户管理', icon: 'table' }
  //     }
  //   ]
  // },

  // {
  //   path: '/orgs',
  //   component: Layout,
  //   redirect: '/orgs',
  //   name: 'ORGS',
  //   meta: { title: '组织架构管理', icon: 'example' },
  //   children: [
  //     {
  //       path: 'bdcorp',
  //       name: 'Bdcorp',
  //       component: () => import('@/views/orgs/bdcorp/bdcorp'),
  //       meta: { title: '公司管理', icon: 'table' }
  //     },
  //     {
  //       path: 'bddept',
  //       name: 'Bddept',
  //       component: () => import('@/views/orgs/bddept/bddept'),
  //       meta: { title: '部门管理', icon: 'table' }
  //     },
  //     {
  //       path: 'bdpsndoc',
  //       name: 'Bdpsndoc',
  //       component: () => import('@/views/orgs/bdpsndoc/bdpsndoc'),
  //       meta: { title: '人员管理', icon: 'table' }
  //     }
  //   ]
  // },

  // {
  //   path: '/salemage',
  //   component: Layout,
  //   redirect: '/salemage',
  //   name: 'SALEMAGE',
  //   meta: { title: '销售管理', icon: 'example' },
  //   children: [
  //     {
  //       path: 'custom',
  //       name: 'Custom',
  //       component: () => import('@/views/salemage/customer/customer'),
  //       meta: { title: '客户管理', icon: 'table' }
  //     },
  //     {
  //       path: 'product',
  //       name: 'Product',
  //       component: () => import('@/views/salemage/product/product'),
  //       meta: { title: '产品管理', icon: 'table' }
  //     },
  //     {
  //       path: 'quote',
  //       name: 'Quote',
  //       component: () => import('@/views/salemage/quote/quote'),
  //       meta: { title: '销售报价', icon: 'table' }
  //     }
  //   ]
  // },

  // {
  //   path: '/technics',
  //   component: Layout,
  //   redirect: '/technics',
  //   name: 'TECHNICS',
  //   meta: { title: '工艺管理', icon: 'example' },
  //   children: [
  //     {
  //       path: 'tecaudit',
  //       name: 'Tecaudit',
  //       component: () => import('@/views/technics/tecaudit/tecaudit'),
  //       meta: { title: '产品工艺审核', icon: 'table' }
  //     }
  //   ]
  // },

  // {
  //   path: '/purchase',
  //   component: Layout,
  //   redirect: '/purchase',
  //   name: 'PURCHASE',
  //   meta: { title: '采购管理', icon: 'example' },
  //   children: [
  //     {
  //       path: 'quote',
  //       name: 'Quote',
  //       component: () => import('@/views/purchase/quote/quote'),
  //       meta: { title: '采购报价', icon: 'table' }
  //     }
  //   ]
  // },

  // {
  //   path: '/produce',
  //   component: Layout,
  //   redirect: '/produce',
  //   name: 'PRODUCE',
  //   meta: { title: '生产管理', icon: 'example' },
  //   children: [
  //     {
  //       path: 'produce',
  //       name: 'Produce',
  //       component: () => import('@/views/produce/quote/quote'),
  //       meta: { title: '生产报价', icon: 'table' }
  //     }
  //   ]
  // },

  // { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
