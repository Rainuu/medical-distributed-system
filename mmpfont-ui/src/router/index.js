import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
Vue.use(VueRouter)

const routes = [
    {
    path: '/',
    name: 'index',
    component:()=>import('../views/Index'),
    children: [
        // 公共模块 (common)
        {
            path: '/login',
            name: 'login',
            component: ()=>import('../views/common/Login')
        },
        {
            path: '/about',
            name: 'about',
            component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
        },
        // 系统管理(System)——(10)——梁梦磊
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
            path: '/DepartmentManagement',
            name: 'DepartmentManagement',
            component:()=>import('../views/System/DepartmentManagement')
        },
        {
            path: '/lotlm',
            name: 'lotlm',
            component: () => import('../views/System/LogOnToLogManagement.vue')
        },
        // 药品进销存 (Purchase、Stock and Sales of Drugs | psasod)——(6)——何泽明
        {
            path: '/SupplierMaintenance',
            name: 'SupplierMaintenance',
            component: () => import('../views/psasod/SupplierMaintenance.vue'),
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
            component: () => import('../views/psasod/InventoryInquiry')
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
        },
        // 收费管理(Charge Management | ChargeMana)——(5)——方号
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
            path: '/PrescriptionRefund',
            name: '/PrescriptionRefund',
            component: () => import('../views/ChargeMana/PrescriptionRefund.vue')
        },
        {
            path: '/ri',
            name: 'ri',
            component: () => import('../views/ChargeMana/RefundInquiry.vue')
        },
        {
            path: '/PrescriptionDispensing',
            name: 'PrescriptionDispensing',
            component: () => import('../views/ChargeMana/PrescriptionDispensing.vue')
        },
        // 检查管理(Inspection Management | InspectionMana)——(3)——牛牧原
        {
            path:'/CheckResultQuery',
            name: 'CheckResultQuery',
            component: () =>import('../views/InspectionMana/CheckResultQuery')
        },
        {
            path:'/CheckResultEntry',
            name: 'CheckResultEntry',
            component: () =>import('../views/InspectionMana/CheckResultEntry')
        },
        {
            path:'/NewOpenInspection',
            name: 'NewOpenInspection',
            component: () =>import('../views/InspectionMana/NewOpenInspection')
        },
        // 看病就诊(See a doctor | SeeAdoctor)——(6)——刘鸿飞
        {
            path: '/PatientLib',
            name:'PatientLib',
            component:() =>import('../views/SeeAdoctor/Patientlib')
        },
        {
            path: '/register',
            name: 'register',
            component:()=>import('../views/SeeAdoctor/register')
        },
        {
            path: '/NewVisits',
            name: 'NewVisits',
            component: () => import('../views/SeeAdoctor/NewVisits.vue')
        },
        {
            path: '/or',
            name: 'or',
            component: () => import('../views/SeeAdoctor/OutpatientRegistration.vue')
        },
        {
            path: '/MyShiftSchedule',
            name: 'MyShiftSchedule',
            component: () => import('../views/SeeAdoctor/MyShiftSchedule.vue')
        },
        {
            path: '/DoctorScheduling',
            name: 'DoctorScheduling',
            component: () => import('../views/SeeAdoctor/DoctorScheduling.vue')
        },
        // 数据统计(statistics)——(4)——王喜龙
        {
            path: '/NewlyOpenedClinic',
            name: 'NewlyOpenedClinic',
            component: () =>import('../views/statistics/NewlyOpenedClinic')
        },
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
