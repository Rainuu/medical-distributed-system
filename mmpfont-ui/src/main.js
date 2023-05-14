import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/icons/index'
import './assets/css/global.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// 刘鸿飞加的全局
import {formatDict, formatDict2, resetForm} from './utils/DictFormat'
// Axios
import ax from "axios";

Vue.config.productionTip = false
const axios = ax.create({
  baseURL: "http://localhost:8090"
});
Vue.prototype.$axios = axios;
Vue.prototype.formatDict = formatDict;

// 刘鸿飞
Vue.prototype.formatDict2 = formatDict2;
Vue.prototype.resetForm = resetForm;


Vue.use(mavonEditor)

//设置axios请求的拦截器
axios.interceptors.request.use(config => {
  //从localStorage获取令牌信息
  var token = sessionStorage.getItem("token");
  //判断是否获取token 只要token有值则返回true
  if (token) {
    //把token信息放入请求头种
    config.headers.token = token;
  }
  return config;
})
//返回相应
axios.interceptors.response.use(response => {
  if (response.data.code === 200) {

    return response;
  } else if (response.data.code === 503) {
    this.$route.path('/login')
  } else {
    return response;
  }
}, error => {

  return Promise.reject(error)
})

//设置路由守卫 to:目标路由   from:从哪个路由   next:放行到目标路由  跳转到指定路径
router.beforeEach(((to, from, next) => {
  var path = to.path;
  if (path === "/" || path === "/login") {
    return next();
  }

  var token = sessionStorage.getItem("token");
  if (token) {
    return next();
  }
  return next("/");
}))

new Vue({
  el: '#app',
  router,
  render: h => h(App)
}).$mount('#app')
