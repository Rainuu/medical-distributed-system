import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

// Element-UI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

// Axios
import ax from "axios";
const axios = ax.create({
  baseURL:"http://localhost:8090"
});
Vue.prototype.$axios=axios;


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
