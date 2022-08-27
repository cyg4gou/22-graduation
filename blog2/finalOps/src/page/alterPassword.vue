<template>
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="password" class="col-sm-2 control-label" >原密码</label>
	    <div class="col-sm-10">
	      <input type="password" 
	  				class="form-control" 
	  				id="oldPassword" 
	  				placeholder="oldPassword" 
	  				ref="oldPassword"
					v-on:keydown.9="checkOldPassword"
	  				v-model="userUpdatePasswordReq.oldPassword">
					<!-- v-on:keyup.enter='focusNewPassword' -->
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="password" class="col-sm-2 control-label" >新密码</label>
	    <div class="col-sm-10">
	      <input type="password" 
				class="form-control" 
				id="newPassword" 
				placeholder="newPassword" 
				ref="newPassword"
				v-model="userUpdatePasswordReq.newPassword">
	    </div>
	  </div>

	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button v-on:click="changePassword" class="btn btn-default">确认更改</button>
	    </div>
	  </div>
	</form>
</template>

<script>
	export default{
		name:'alterPassword',
		data(){
			return{
				userUpdatePasswordReq : {
					oldPassword:'',
					newPassword:'',
				},
				canUpdate : false,
			}
		},
		methods:{
			changePassword(){
				if(this.canUpdate){
					var id = sessionStorage.getItem('id')
					var token = sessionStorage.getItem('token')
					this.$axios({
						url:'http://localhost:8080/api/user/updatePassword',
						method:'post',
						headers:{
							'access-token':token,
							'access-id':id
						},
						data : this.userUpdatePasswordReq,
						
					}).then(
						Response=>{
							console.log(Response.data)
							if(Response.data){
								const userId = sessionStorage.getItem('id')
								this.$globalEventBus.$emit('logout')
								this.$globalEventBus.$emit('toLogin',userId)
							}
						},
						Error=>{
							console.log(Error.message)
						}
					)
				}else{
					alert('旧密码不正确')
					this.userUpdatePasswordReq.newPassword = ''
					this.userUpdatePasswordReq.oldPassword = ''
					this.$refs.oldPassword.focus()
				}
			},
			checkOldPassword(event){
				if(oldPassword === ''){
					console.log('请输入原密码')
					return false
				}
				var id = sessionStorage.getItem('id')
				var token = sessionStorage.getItem('token')
				this.$axios({
					url:'http://localhost:8081/api/user/checkOldPassword',
					method:'post',
					headers:{
						'access-token':token,
						'access-id':id
					},
					data : this.userUpdatePasswordReq,
					
				}).then(
					Response=>{
						console.log('请求通过----->',Response.data)
						if(Response.data.data){
							this.canUpdate = Response.data
						}else{
							this.userUpdatePasswordReq.oldPassword = ''
							alert('密码不正确')
							this.$refs.oldPassword.focus()
							return false
						}
					},
					Error=>{
						console.log(Error.message)
					}
				)
			},
			focusNewPassword(){
				this.$refs.newPassword.focus()
			}
		},
		mounted(){
			this.$refs.oldPassword.focus()
		},
		
	}
</script>

<style>
</style>