import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'
import {formatSecond} from './filter/filter'

Vue.config.productionTip = false
Vue.prototype.$ajax=axios

Vue.filter('formatSecond', formatSecond)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

