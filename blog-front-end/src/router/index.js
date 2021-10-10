import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout'
Vue.use(Router)

export const constantRoutes = [
	{
		path: "/",
		component: Layout,
		redirect: 'index',
		children: [
			{
				path: "/index",
				component: resolve => require(["@/views/index"], resolve),
				hidden: true
			},
		]
	}
]

export default new Router({
	mode: 'history', // 去掉url中的#
	scrollBehavior: () => ({ y: 0 }),
	routes: constantRoutes
})
