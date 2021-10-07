import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/bootstrap-vue'
import './plugins/moment'
import axios from 'axios'
import jquery from 'jquery'

Vue.config.productionTip = false
Vue.prototype.$axios = axios;
Vue.prototype.$jquery = jquery;

if(process.env.NODE_ENV =='development'){
    // Vue.prototype.$axios.defaults.baseURL = 'http://finallap.online';

}

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
