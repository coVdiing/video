import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'
import {formatSecond} from './filter/filter'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false
Vue.prototype.$ajax=axios

Vue.filter('formatSecond', formatSecond)
Vue.use(ElementUI);
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

