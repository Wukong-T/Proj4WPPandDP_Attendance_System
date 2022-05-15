import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import MyServerHttp from '../src/plugins/http.js'
import * as echarts from 'echarts';

import 'element-ui/lib/theme-chalk/index.css';
import '../src/assets/css/reset.css'
import '../src/assets/css/style.css'
import 'element-ui/lib/theme-chalk/display.css';
import 'lib-flexible';

Vue.use(MyServerHttp);
Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts
window.echarts = require('echarts');
new Vue({
    
    el: '#app',
    components: {
        App
    },
    template: '<App/>',
    router,
    render: h => h(App)

}).$mount('#app')

/* eslint-disable no-new */ 
router.beforeEach((to, from, next) => { 

    if (to.matched.some(res => res.meta.requireAuth)) { // 验证是否需要登陆 
      
        if (sessionStorage.getItem('username')) { // 未登录则拦截登录后的界面
                next(); 
        } else { 
            next({ 
            path: '/login', // 未登录则跳转至login页面 
            query: {redirect: to.fullPath} // 登陆成功后回到当前页面，这里传值给login页面，to.fullPath为当前点击的页面 
            }); 
        } 
    } else { 
        next(); 
        } 
   }); 

