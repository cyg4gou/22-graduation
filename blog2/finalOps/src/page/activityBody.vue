<template>
	<div>

		<h4>活动标题</h4>
		<h2>{{title}}</h2>
		<hr/>
		<h4>活动内容</h4>
		<p v-html="content"></p>
		<hr/>
		<h4>观看人数</h4>
		<h2>{{viewCount}}</h2>
		<hr/>
		<h4>过期时间</h4>
		<h2>{{expireTime}}</h2>
		<hr/>
		<div class="right">
			<button class="btn btn-info"
					v-on:click="update">修改</button>
			<button class="btn btn-info"
					v-on:click="deleteActivity(data.id)">删除</button>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'activityBody',
		data() {
			return {
				data:'',
				author:'',
				title: '',
				content: '',
				viewCount: '',
				expireTime: '',
				userId:'',
			}
		},
		methods: {
			update(){
				// console.log(this.data)
				this.$globalEventBus.$emit('updateActivity',this.data)
			},
			deleteActivity(activityId){
				const yes = confirm('确认删除该活动吗？')
				
				if(yes){
					var id = sessionStorage.getItem('id')
					var token = sessionStorage.getItem('token')
					var url = 'http://localhost:8081/api/activity/delete/' + activityId 
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
							this.$globalEventBus.$emit('refresh')
						},
						Error=>{
							console.log('失败了',Error.message)
						}
					)
				}
			}
		},
		mounted() {
			const data = this.$route.params.activityAllInformation
			// console.log('3.acvitivyBody将展示数据--------------->', data)
			this.data = data
			this.title = data.title
			this.content = data.body
			this.viewCount = data.viewCount
			this.expireTime = data.expireTime
			this.author = data.author
			this.userId = sessionStorage.getItem('id')
		}
	}
</script>

<style>
</style>
