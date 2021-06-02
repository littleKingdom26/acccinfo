import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/bootstrap-vue'
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$axios = axios;
if(process.env.NODE_ENV =='development'){
    Vue.prototype.$axios.defaults.baseURL = 'http://localhost:8080';

}

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
