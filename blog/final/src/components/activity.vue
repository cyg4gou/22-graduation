<template>
	<span>
		<td v-on:click="showActivityBodyById" class="title">{{activity.title}}</td>
		<td v-on:click="showActivityByAuthorId" class="authorNickName">{{activity.authorNickName}}</td>
		<td class="createTime">{{activity.createTime}}</td>
		<td class="viewCount">{{activity.viewCount}}</td>
	</span>
</template>

<script>
	export default {
		name: 'activity',
		data(){
			return{
				activity: {},
			}
		},
		props: ['item'],
		methods: {
			// 根据活动id展示活动体
			showActivityBodyById() {
				// if(this.token === '' || this.token === null){
				// 	this.$router.push({name:'login'})
				// 	console.log('先登录')
				// }else{
				// 	console.log('查看活动内容')
				// }
			},
			// 展示用户所发表的所有活动
			showActivityByAuthorId() {
				// if(this.token === '' || this.token === null){
				// 	this.$router.push({name:'login'})
				// 	console.log('先登录')
				// }else{
				// 	var id = sessionStorage.getItem('id')
				// 	var token = sessionStorage.getItem('token')
				// 	console.log('id----->',id,'token---->',token)
				// 	this.$axios(
				// 		{
				// 			url:'http://localhost:8080/api/user/view',
				// 			method:'get',
				// 			headers: {
				// 				'access-token':token,
				// 				'access-id':id
				// 			},
				// 			params:{
				// 				id
				// 			}
				// 		}
				// 	).then(
				// 		Response=>{
				// 			console.log('查看该作者所有活动',Response.data)
				// 			this.activities = Response.data.data.activityRespArray
				// 		},
				// 		Error=>{
				// 			console.log('失败了',Error.message)
				// 		}
				// 	)
			},
		},
		mounted() {
			this.activity = this.item
			console.log('props------>',this.activity)
		},
	}
</script>

<style>
	td {
		list-style: none;
		height: 36px;
		line-height: 36px;
		padding: 0 5px;
		border-bottom: 1px solid #ddd;
		align-items: center;
		align-content: center;
		align-self: center;
	}
	
	td:hover{
		background-color: #ddd;
	}
	
	td:hover button{
		display: block;
	}
	
	.title{
		width: 50%;
	}
	
	.authorNickName{
		width: 20%;
	}
	
	.createTime{
		width: 20%;
	}
	
	.viewCount{
		width: 10%;
	}
</style>
