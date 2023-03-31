import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
Vue.use(VueRouter)

const routes = [
    // 公共模块 (common)
    {
        path: '/',
        name: 'login',
        redirect: "/login"
    },
    {
        path: '/login',
        name: 'login',
        component: ()=>import('../views/common/Login')
    },
    {
    path: '/index',
    name: 'index',
    component:()=>import('../views/Index'),
    children: [
        // 系统管理(System)——(10)——梁梦磊
        {   //科室管理
            path: '/system/dept',
            name: 'dept',
            component:()=>import('../views/system/dept')
        },
        {   // 用户管理
            path: '/system/user',
            name: 'user',
            component:()=> import('../views/system/User')
        },
            // 角色管理 & 菜单管理
        {   //字典管理
            path: '/system/dict',
            name: 'dict',
            component: () => import('../views/system/type'),
        },
        {
            path:"/dict/data/:dictId",
            name:'dictData',
            component:()=>import('../views/system/data')
        },
        {   // 通知公告
            path: '/system/notice',
            name: 'notice',
            component: () => import('../views/system/notice')
        },
        {   // 登录日志管理
            path: '/system/log_login',
            name: 'logLogin',
            component: () => import('../views/system/logLogin')
        },
        {   // 操作日志管理
            path: '/system/log_opt',
            name: 'logOpt',
            component: () => import('../views/system/logOpt')
        },
        {   // 检查费用管理
            path: '/system/ins_fee',
            name: 'insFee',
            component: () => import('../views/system/checkItem')
        },
        {   // 挂号费用设置
            path: '/system/reg_fee',
            name: 'regFee',
            component: () => import('../views/system/registeredItem')
        },
        {
          path:'/system/role',
            name: 'role',
            component: ()=>import('../views/system/Role')
        },
        {
            path: '/system/menu',
            name:'menu',
            component: () => import('../views/system/Menu')
        },
        {
          path: '/system/updatePassword/:userName',
          name: 'updatePassword',
          component:() => import('../views/system/UpdatePassword')
        },
        {
          path: '/system/personCenter',
          name: 'personCenter',
            component:()=>import('../views/system/PersonCenter')
        },
        // 药品进销存 (stock)——(6)——何泽明
        {   // 生产厂家维护
            path: '/stock/producter',
            name: 'producter',
            component: () => import('../views/stock/producter')
        },
        {   // 药品信息维护
            path: '/stock/medicinal',
            name: 'medicinal',
            component: () => import('../views/stock/medicinal')
        },
        {   // 供应商维护
            path: '/stock/provider',
            name: 'provider',
            component: () => import('../views/stock/provider'),
        },
        {   // 采购入库
            path: '/stock/purchase',
            name: 'purchase',
            component: () => import('../views/stock/purchase')
        },
        {   // 入库审核
            path: '/stock/examine',
            name: 'examine',
            component: () => import('../views/stock/examine')
        },
        {   // 库存查询
            path: '/stock/inventory',
            name: 'inventory',
            component: () => import('../views/stock/inventory')
        },
        // 收费管理(charge)——(5)——方号
        {   // 处方收费
            path: '/charge/docharge',
            name: 'docharge',
            component: () => import('../views/charge/docharge')
        },
        {   // 收费查询(收费列表)
            path: '/charge/chargelist',
            name: 'chargelist',
            component: () => import('../views/charge/chargelist')
        },
        {   // 处方退费
            path: '/charge/backfee',
            name: '/backfee',
            component: () => import('../views/charge/backfee')
        },
        {   // 退费查询
            path: '/charge/backfeelist',
            name: 'backfeelist',
            component: () => import('../views/charge/backfeelist')
        },
        {   // 处方发药
            path: '/charge/dispensing',
            name: 'dispensing',
            component: () => import('../views/charge/dispensing')
        },
        // 检查管理(check)——(3)——牛牧原
        {   // 新开检查
            path:'/check/docheck',
            name: 'docheck',
            component: () =>import('../views/check/docheck')
        },
        {   // 检查结果录入
            path:'/check/checkresult',
            name: 'checkresult',
            component: () =>import('../views/check/checkresult')
        },
        {   // 检查结果查询
            path:'/check/checklist',
            name: 'checklist',
            component: () =>import('../views/check/checklist')
        },
        // 看病就诊(doctor)——(6)——刘鸿飞
        {   // 门诊挂号
            path: '/doctor/registered',
            name: 'registered',
            component: () => import('../views/doctor/registered')
        },
        {   // 挂号列表
            path: '/doctor/registeredlist',
            name: 'registeredlist',
            component:()=>import('../views/doctor/registeredlist')
        },
        {   // 新开就诊
            path: '/doctor/newcare',
            name: 'newcare',
            component: () => import('../views/doctor/newcare')
        },
        {   // 我的排班
            path: '/doctor/myscheduling',
            name: 'myscheduling',
            component: () => import('../views/doctor/myscheduling')
        },
        {   // 医生排班
            path: '/doctor/scheduling',
            name: 'scheduling',
            component: () => import('../views/doctor/scheduling')
        },
        {   // 患者库
            path: '/doctor/patient',
            name:'patient',
            component:() =>import('../views/doctor/patient')
        },
        // 数据统计(statistics)——(4)——王喜龙
        {   //
            path: '/statistics/NewlyOpenedClinic',
            name: 'NewlyOpenedClinic',
            component: () =>import('../views/statistics/NewlyOpenedClinic')
        },
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
