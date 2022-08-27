<template>
	<div>
		<MyHeader></MyHeader>
		<div class="row">
			<div class="col-xs-2">
				<div class="list-group">
					
					<router-link v-show="this.token === '' || this.token === null" 
							class="list-group-item" active-class="login"
						to="/login"><span>登录</span></router-link>
						<!-- to="{name:'login',params:{a:1}}">登录</router-link> -->

					<router-link v-show="this.token === '' || this.token === null" 
						class="list-group-item" 
						active-class="registry" to="/registry">
							<span>注册</span>
					</router-link>

					<router-link v-show="this.token !== '' && this.token !== null" 
						class="list-group-item" 
						active-class="logout" to="/logout">
							<span>退出登录</span>
					</router-link>

					<router-link class="list-group-item" 
						active-class="Activties" to="/Activties">
							<span>活动列表</span>
					</router-link>
					
					<router-link v-show="this.token !== '' && this.token !== null"
						class="list-group-item" active-class="alterPassword" to="/alterPassword">
							<span>更改密码</span>
					</router-link>
						
					<!-- <router-link v-show="1 === 2"
						class="list-group-item" active-class="authorActivities" to="/authorActivities">
							<span>该作者所有的活动</span>
					</router-link>	 -->
					
					<router-link v-show="1 === 2"
						class="list-group-item" active-class="activityBody" to="/activityBody">
							<span>活动全部信息</span>
					</router-link>

				</div>
			</div>
			<div class="col-xs-9" >
				<div>
					<div>
						<keep-alive include="activities">
							<router-view></router-view>
						</keep-alive>
					</div>
				</div>
			</div>
		</div>
		<MyFooter ref="refName"></MyFooter>

	</div>
</template>

<script>
	import Vue from 'vue'

	import MyHeader from './components/MyHeader.vue'
	import MyFooter from './components/MyFooter.vue'


	export default {
		name: 'App',
		data() {
			return {
				token: '',
				// account: '',
				nickName:'',
			}
		},
		components: {
			MyHeader: MyHeader,
			MyFooter: MyFooter,
		},
		methods: {
			// localMethod(data){
			//  console.log('自定义事件eventName被触发了,收到的数据是',data)
			// },
			login(token,nickName,id) {
				// this.GLOBAL.token = token
				// this.token = this.GLOBAL.token
				// console.log('登录成功,token为----------',token)
				sessionStorage.setItem('token',token)
				sessionStorage.setItem('nickName',nickName)
				sessionStorage.setItem('id',id)
				this.token = token
				this.nickName = nickName
				this.$globalEventBus.$emit('init')
				this.refresh()
			},
			logout() {
				this.token = ''
				this.nickName = ''
				this.authorId = ''
				sessionStorage.removeItem('token')
				sessionStorage.removeItem('nickName')
				sessionStorage.removeItem('id')
				this.$globalEventBus.$emit('init')
				this.refresh()
			},
			registry(account) {
				this.$router.push({
					name: 'login',
					params: {
						account: account,
					},
				})
			},
			// viewAuthor(activities,authorId){
			// 	this.$router.push({
			// 		name:'authorActivities',
			// 		params:{
			// 			activities:activities,
			// 		}
			// 	})
			// },
			viewActivityBody(activityAllInformation){
				// console.log('2.app中的viewActivityBody开始执行-------->')
				this.$router.push({
					name:'activityBody',
					params:{
						activityAllInformation:activityAllInformation,
					}
				})
			},
			updateActivity(data){
				// console.log(data)
				this.$router.push({
					name:'publish',
					params:{
						data:data,
					}
				})
			},
			refresh(){
				this.$router.push({
					name: 'Activties'
				})
			},
			toLogin(userId){
				this.$router.push({
					name:'login',
					params:{
						userId:userId,
					}
				})
			}
		},
		mounted() {
			this.token = sessionStorage.getItem('token')
			this.nickName = sessionStorage.getItem('nickName')
			this.$globalEventBus.$on('refresh',this.refresh)
			// this.$refs.refName.$on('eventName',this.localMethod)
			this.$globalEventBus.$on('login', this.login)
			this.$globalEventBus.$on('logout', this.logout)
			this.$globalEventBus.$on('registry', this.registry)	
			this.$globalEventBus.$on('refresh',this.refresh)		
			this.$globalEventBus.$on('updateActivity',this.updateActivity)
			// this.$globalEventBus.$on('viewAuthor',this.viewAuthor)
			this.$globalEventBus.$on('viewActivityBody',this.viewActivityBody)
			this.$globalEventBus.$on('toLonin',this.toLogin)
		}
	}
</script>

<style>
	/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */
	.span-active{
		color: orange;
		border-bottom: 1px solid red;
	}
</style>
