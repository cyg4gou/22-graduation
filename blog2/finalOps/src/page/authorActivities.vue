<template>
	<div>
		<table class="table table-bordered ">
			<tr>
				<td>活动标题</td>
				<td>发布人</td>
				<td>发布时间</td>
				<td>观看人数</td>
			</tr>
			<tr v-for="activity in activities"
				v-bind:key='activity.id'
				ref="oneActivity">
				<td v-on:click="showActivityBodyById(activity.id,$event)"
					v-bind:activityId='activity.id'>
					<button class="btn btn-primary">
						{{activity.title}} </button></td>
				<td >{{activity.authorNickName}}</td>
				<td>{{activity.createTime}}</td>
				<td>{{activity.viewCount}}</td>
			</tr>
		</table>
	</div>
</template>

<script>
	import Vue from 'vue'
	
	export default {
		name: 'authorActivities',
		data(){
			return{
				activities:[],
				// 0 未登录 1 登录
				token:'',
			}
		},
		methods:{
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
							console.log('1.viewActivityBody被-------作者所有活动------调用---------->')
							this.$globalEventBus.$emit('viewActivityBody',Response.data.data)
						},
						Error=>{
							console.log('失败了',Error.message)
						}
					)
				}
			}
		},
		mounted() {
			this.token = sessionStorage.getItem('token')
			this.activities = this.$route.params.activities
		},
	}
</script>

<style>
</style>