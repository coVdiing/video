import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'
import {formatSecond} from './filter/filter'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false
// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;
Vue.prototype.$ajax=axios

Vue.filter('formatSecond', formatSecond)
Vue.use(ElementUI);

// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.LoginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    return item.meta.loginRequire
  })) {
    let loginUser = Tool.getLoginUser();
    if (Tool.isEmpty(loginUser)) {
      next('/login');
    } else {
      next();
    }
  } else {
    next();
  }
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

