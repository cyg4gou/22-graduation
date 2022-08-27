<template>
	<form class="form-horizontal">
	  <div class="form-group">
	    <label for="userName" class="col-sm-2 control-label" >账号</label>
	    <div class="col-sm-10">
	      <input type="text" 
				class="form-control" 
				id="userName" 
				placeholder="Account" 
				ref="account"
				v-model="registryForm.userName">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="nickName" class="col-sm-2 control-label" >用户名称</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="nickName" placeholder="nickName" v-model="registryForm.nickName">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="password" class="col-sm-2 control-label" >密码</label>
	    <div class="col-sm-10">
	      <input type="password" 
				class="form-control" 
				id="password" 
				placeholder="password" 
				v-model="registryForm.password">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="rePassword" class="col-sm-2 control-label" >确认密码</label>
	    <div class="col-sm-10">
	      <input type="password" class="form-control" id="rePassword" placeholder="rePassword" v-model="registryForm.rePassword">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button v-on:click="registry" class="btn btn-default">注册</button>
	    </div>
	  </div>
	</form>
</template>

<script>
	export default{
		name:'registry',
		data(){
			return{
				registryForm:{
					userName:'',
					nickName:'',
					password:'',
					rePassword:'',
				},
			}
		},
		methods:{
			registry(){
				
				if(this.checkValid()){
					console.log('请输入账号密码、并保证两次密码一致')
					return false
				}
				
				this.$axios.post(
					'http://localhost:8080/api/user/registry',
					this.registryForm
				).then(
					Response=>{
						console.log('注册成功',Response.data)
						
						if(Response.data.code === 10004){
							alert('账户已经被注册了')
							this.focus()
							return false
						}
						
						this.$globalEventBus.$emit('registry',this.registryForm.userName)
					},
					Error=>{
						console.log('注册失败了',Error.message)
					}
				)
			},
			checkValid(){
				if(this.userName === '' || this.password === '' || this.rePassword !== this.password){
					return true
				}
				return false
			},
			focus() {
				this.$refs.account.focus()
			},
		},
		mounted(){
			this.focus()
		},
	}
</script>

<style>
</style>