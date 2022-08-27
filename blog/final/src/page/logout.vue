<template>
</template>

<script>
	export default{
		name:'logout',
		data(){
			return{
				token:'',
				id:'',
			}
		},
		methods:{
			logout(){
				// console.log(this.GLOBAL.token,'-----------是否存在------------',JSON.stringify(this.params))
				
				
				const x = confirm('是否退出登录')
				
				if(x){
					console.log('退出')
					this.$axios({
							url:'http://localhost:8080/api/user/logout',
							method:'post',
							headers: {
								'access-token':this.token,
								'access-id':this.id
							}
						}
					).then(
						Response=>{
							console.log('退出账号成功',Response.data)
							this.$globalEventBus.$emit('logout')
						},
						Error=>{
							console.log('失败了',Error.message)
						}
					)
				}else{
					console.log('不退出')
					this.$globalEventBus.$emit('refresh')
				}
				
				
			},
		},
		mounted(){
			this.token = sessionStorage.getItem('token')
			this.id = sessionStorage.getItem('id')
			this.logout()
		},
	}
</script>

<style>
</style>