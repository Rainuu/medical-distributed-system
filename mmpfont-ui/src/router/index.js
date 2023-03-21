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
        {
            path: '/SupplierMaintenance',
            name: 'SupplierMaintenance',
            component: () => import('../views/psasod/SupplierMaintenance.vue'),
            meta: {
                title: "供应商维护"
            }
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
            path: '/PrescriptionRefund',
            name: '/PrescriptionRefund',
            component: () => import('../views/ChargeMana/PrescriptionRefund.vue')
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
        {
            path:'/CheckResultQuery',
            name: 'CheckResultQuery',
            component: () =>import('../views/statistics/CheckResultQuery')
        },
        {
            path:'/CheckResultEntry',
            name: 'CheckResultEntry',
            component: () =>import('../views/statistics/CheckResultEntry')
        },
        {
            path:'/NewOpenInspection',
            name: 'NewOpenInspection',
            component: () =>import('../views/statistics/NewOpenInspection')
        },
        {path: '/PatientLib',
        name:'PatientLib',
            component:() =>import('../views/SeeAdoctor/Patientlib')
        },
        {
            path: '/register',
            name: 'register',
            component:()=>import('../views/SeeAdoctor/register')
        },
        {
            path: '/DepartmentManagement',
            name: 'DepartmentManagement',
            component:()=>import('../views/System/DepartmentManagement')
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
            component: () => import('../views/psasod/InventoryInquiry')
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
            path: '/ManufacturerMaintenance',
            name: 'ManufacturerMaintenance',
            component: () => import('../views/psasod/ManufacturerMaintenance.vue')
        },
        {
            path: '/DrugLnformationMaintenance',
            name: 'DrugLnformationMaintenance',
            component: () => import('../views/psasod/DrugLnformationMaintenance.vue')
        },
        {
            path: '/PrescriptionDispensing',
            name: 'PrescriptionDispensing',
            component: () => import('../views/ChargeMana/PrescriptionDispensing.vue')
        },
        {
            path: '/NewVisits',
            name: 'NewVisits',
            component: () => import('../views/SeeAdoctor/NewVisits.vue')
        },

        {
            path: '/ri',
            name: 'ri',
            component: () => import('../views/ChargeMana/RefundInquiry.vue')
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
