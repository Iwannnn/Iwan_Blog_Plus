import Vue from 'vue'
import App from './App'
import Cookies from 'js-cookie'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import APlayer from '@moefe/vue-aplayer';

Vue.prototype.msgSuccess = function (msg) {
	this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
	this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
	this.$message.info(msg);
}


/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
	size: Cookies.get('size') || 'medium' // set element-ui default size
})

Vue.use(APlayer, {
	defaultCover: 'https://github.com/u3u.png',
	productionTip: true,
});

Vue.config.productionTip = false

new Vue({
	el: '#app',
	router,
	render: h => h(App)
})
