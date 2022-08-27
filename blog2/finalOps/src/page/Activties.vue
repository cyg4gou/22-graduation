<template>
	<div>
		<table class="table table-striped table-hover">
			<tbody>
				<tr class="">
					<td>
						<input type="checkbox"
						ref="allCheckBox"
						v-model="checkAll"
						v-on:click="checkAllToDelete($event)"
					/></td>
					<td><strong>序号</strong></td>
					<td><strong>活动标题</strong></td>
					<td><strong>发布人</strong></td>
					<td><strong>发布时间</strong></td>
					<td><strong>观看人数</strong></td>
				</tr>
				<tr v-for="(activity,index) in activities"
					v-bind:key='activity.id'
					ref="oneActivity">
					<td><input type="checkbox"
						ref="checkBox"
						v-model="check[index]"
						v-on:click="checkToDelete(activity.id,index,$event)"
					/></td>
					<!-- <activity v-bind:item='activity'></activity> -->
					<td>{{index + 1 + (requestParam.currentPage - 1) * requestParam.pageSize}}</td>
					<td v-on:click="showActivityBodyById(activity.id,$event)"
						v-bind:activityId='activity.id'>
						<button class="btn btn-primary">
							{{activity.title}} </button></td>
					<td v-on:click="showActivityByAuthorId(activity.createBy,$event)"
						v-bind:createBy='activity.createBy'>
						<button class="btn btn-info">
							{{activity.authorNickName}}</button> </td>
					<td>{{activity.createTime}}</td>
					<td>{{activity.viewCount}}</td>
				</tr>
			</tbody>
		</table>
		<button
			class="btn btn-danger"
			v-on:click="deleteAll"
		>删除</button>
		<page></page>
	</div>
</template>

<script>
	import Vue from 'vue'
	// import activity from '../components/activity.vue'
	import page from '../components/page'
	
	export default {
		name: 'Activties',
		data(){
			return{
				activities:[],
				// 0 未登录 1 登录
				token:'',
				requestParam:{
					currentPage:1,
					pageSize:5,
					authorId:'',
				},
				responseParam:{
					currentPage:1,
					counts:1,
				},
				check:[false,false,false,false,false],
				checkAll:false,
				activitiesToDelete:{
					
				}
			}
		},
		components:{
			// activity:activity,
			page
		},
		methods:{
			// 初始化活动
			init(){
				this.$axios.post(
					'http://localhost:8081/api/activity/page',
					this.requestParam
				).then(
					Response=>{
						console.log(Response.data)
						this.activities = Response.data.data.list
						this.responseParam.currentPage = Response.data.data.currentPage
						this.responseParam.counts = Response.data.data.counts
						// console.log(this.responseParam)
						this.$globalEventBus.$emit('activityCounts',
							this.responseParam.counts,
							this.responseParam.currentPage,
							this.requestParam.pageSize)
					},
					Error=>{
						console.log(Error.message)
					}
				)
			},
			// 根据活动id展示活动体
			showActivityBodyById(activityId,event){
				if(this.token === '' || this.token === null){
					this.$router.push({name:'login'})
					console.log('先登录')
				}else{
					// console.log('activityId------>',activityId,'event------>',event)
					var id = sessionStorage.getItem('id')
					var token = sessionStorage.getItem('token')
					var url = 'http://localhost:8081/api/activity/viewContent/' + activityId 
					this.$axios({
						url:url,
						method:'get',
						headers: {
							'access-token':token,
							'access-id':id
						},
						params:{
							activityId:activityId,
						}
					}).then(
						Response=>{
							// console.log('1.viewActivityBody被****activity***调用了----------->')
							this.$globalEventBus.$emit('viewActivityBody',Response.data.data)
						},
						Error=>{
							console.log('失败了',Error.message)
						}
					)
				}
			},
			// 展示用户所发表的所有活动
			showActivityByAuthorId(userId,event){
				if(this.token === '' || this.token === null){
					this.$router.push({name:'login'})
					console.log('先登录')
				}else{
					this.requestParam.authorId = userId
					this.requestParam.currentPage = 1
					this.requestParam.pageSize = 5
					this.init()
				}
			},
			currentPage(currentPage){
				// console.log('activities---->',currentPage)
				this.requestParam.currentPage = currentPage
				this.init()
			},
			checkAllToDelete(event){
				this.checkAll = !this.checkAll
				this.check.forEach((item,index,arr) =>{
					this.check[index] = this.checkAll
					// console.log(this.check[index])
				})
				
			},
			checkToDelete(id,index,event){
				// console.log(id)
				this.check[index] = !this.check[index]
				// console.log(this.check)
			},
			deleteAll(){
				if(this.token === '' || this.token === null){
					this.$router.push({name:'login'})
					console.log('先登录')
				}else{
					const yes = confirm('确认删除吗？')
					var toDelete = []
					if(yes){
						this.check.forEach((item,index,arr) =>{
							if(item && this.activities[index] !== undefined){
								console.log(this.activities[index])
								toDelete.push(this.activities[index].id)
							}
						})
						// console.log(toDelete)
						var id = sessionStorage.getItem('id')
						var token = sessionStorage.getItem('token')
						var url = 'http://localhost:8081/api/activity/deleteAll'
						// toDelete = JSON.stringify(toDelete)
						toDelete = toDelete.join(',')
						console.log(toDelete)
						// Object.assign(this.activitiesToDelete,{activitiesToDelete:toDelete})
						this.$axios({
							url:url,
							method:'get',
							headers: {
								'access-token':token,
								'access-id':id
							},
							params:{
								activitiesToDelete:toDelete,
							}
						}).then(
							Response=>{
								console.log(Response.data)
								this.init()
								this.check = [false,false,false,false,false]
								this.checkAll = false
							},
							Error=>{
								console.log(Error.message)
							}
						)
					}
					
				}
				
			}
		},
		mounted() {
			// console.log('MyList挂载完毕')
			this.token = sessionStorage.getItem('token')
			this.init();
			this.$globalEventBus.$on('currentPage',this.currentPage)
		},
		// beforeDestroy() {
		// 	console.log('activities组件即将被销毁')
		// }
	}
</script>

<style scoped>
	.list {
		margin-left: 0px;
		border: 1px solid #ddd;
		border-radius: 2px;
		padding: 0px;
		align-items: center;
		align-content: center;
		align-self: center;
		width: 100%;
	}
	
</style>
