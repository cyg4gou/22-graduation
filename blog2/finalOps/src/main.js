import Vue from 'vue'
import App from './App.vue'
import Axios from 'axios'
import VueRouter from 'vue-router'

import router from './router'

import global_ from './components/GlobalConst'//引用文件
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
Vue.prototype.GLOBAL = global_//挂载到Vue实例上面

Vue.config.productionTip = false
Vue.prototype.$axios = Axios
Vue.use(VueRouter)

new Vue({
	render: h => h(App),
	router: router,
	beforeCreate() {
		Vue.prototype.$globalEventBus = this
	},
}).$mount('#app')
