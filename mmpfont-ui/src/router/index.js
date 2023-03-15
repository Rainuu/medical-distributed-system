import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    children: [
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
            component: () => import('../views/System/NotificationAnnouncements.vue')
        },
        {
            path: '/olm',
            name: 'olm',
            component: () => import('../views/System/OperationalLogManagement.vue')
        },
        {
            path: '/lotlm',
            name: 'lotlm',
            component: () => import('../views/System/LogOnToLogManagement.vue')
        },
        {
            path: '/cem',
            name: 'cem',
            component: () => import('../views/System/CheckExpenseManagement.vue')
        },
        {
            path: '/rfs',
            name: 'rfs',
            component: () => import('../views/System/RegisteredFeeSettings.vue')
        },
        {
            path: '/dm',
            name: 'dm',
            component: () => import('../views/System/DictionaryManagement.vue')
        },
        {
            path: '/pc',
            name: 'pc',
            component: () => import('../views/ChargeMana/PrescriptionCharges.vue')
        },
        {
            path: '/pc2',
            name: 'pc2',
            component: () => import('../views/ChargeMana/PrescriptionCharges2.vue')
        },
        {
            path: '/ChargeInquiry',
            name: 'ChargeInquiry',
            component: () => import('../views/ChargeMana/ChargeInquiry.vue')
        },
        {
            path: '/ManufacturerMaintenance',
            name: 'ManufacturerMaintenance',
            component: () => import('../views/psasod/ManufacturerMaintenance.vue')
        },
        {
            path: '/DrugLnformationMaintenance',
            name: 'DrugLnformationMaintenance',
            component: () => import('../views/psasod/DrugLnformationMaintenance.vue')
        }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: ()=>import('../views/common/Login')
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
