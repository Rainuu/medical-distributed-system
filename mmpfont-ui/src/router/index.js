import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
    {
        path: '/SupplierMaintenance',
        name: 'SupplierMaintenance',
        component: () => import('../views/psasod/SupplierMaintenance.vue')
    },
    {
        path: '/PurchaseLnbound',
        name: 'PurchaseLnbound',
        component: () => import('../views/psasod/PurchaseLnbound.vue')
    },
    {
        path: '/InboundAudit',
        name: 'InboundAudit',
        component: () => import('../views/psasod/InboundAudit.vue')
    },
    {
        path: '/InventoryLnquiry',
        name: 'InventoryLnquiry',
        component: () => import('../views/psasod/InventoryLnquiry.vue')
    },
    {
        path: '/na',
        name: 'na',
        component: () => import(/* webpackChunkName: "about" */ '../views/System/NotificationAnnouncements.vue')
    },

    {
        path: '/olm',
        name: 'olm',
        component: () => import(/* webpackChunkName: "about" */ '../views/System/OperationalLogManagement.vue')
    },

    {
        path: '/lotlm',
        name: 'lotlm',
        component: () => import(/* webpackChunkName: "about" */ '../views/System/LogOnToLogManagement.vue')
    },{
        path: '/cem',
        name: 'cem',
        component: () => import(/* webpackChunkName: "about" */ '../views/System/CheckExpenseManagement.vue')
    },{
        path: '/rfs',
        name: 'rfs',
        component: () => import(/* webpackChunkName: "about" */ '../views/System/RegisteredFeeSettings.vue')
    },{
        path: '/dm',
        name: 'dm',
        component: () => import(/* webpackChunkName: "about" */ '../views/System/DictionaryManagement.vue')
    }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
