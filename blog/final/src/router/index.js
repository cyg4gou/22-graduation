import VueRouter from 'vue-router'

import registry from '../page/registry'
import login from '../page/login'
import logout from '../page/logout'
import Activties from '../page/Activties'
import publish from '../page/publish.vue'
import authorActivities from '../page/authorActivities'
import activityBody from '../page/activityBody.vue'
import alterPassword from '../page/alterPassword'

export default new VueRouter({
	routes:[
		{
			name:'registry',
			path:'/registry',
			component:registry
		},
		{
			name:'login',
			path:'/login',
			component:login
		},
		{
			name:'logout',
			path:'/logout',
			component:logout
		},
		{
			name:'Activties',
			path:'/Activties',
			component:Activties
		},
		{
			name:'publish',
			path:'/publish',
			component:publish
		},
		{
			name:'authorActivities',
			path:'/authorActivities',
			component:authorActivities
		},
		{
			name:'activityBody',
			path:'/activityBody',
			component:activityBody
		},{
			name:'alterPassword',
			path:'/alterPassword',
			component:alterPassword
		},
		
	]
})

