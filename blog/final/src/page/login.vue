<template>
	<div>

		<el-radio-group v-model="labelPosition" size="small">
			<el-radio-button label="left">左对齐</el-radio-button>
			<el-radio-button label="right">右对齐</el-radio-button>
			<el-radio-button label="top">顶部对齐</el-radio-button>
		</el-radio-group>

		<div style="margin: 20px;"></div>
		<el-form :label-position="labelPosition" label-width="80px" :model="loginForm">
			<el-form-item label="账号">
				<el-input v-model="loginForm.userName" 
							ref="account" 
							placeholder="Account" 
							clearable></el-input>
			</el-form-item>
			<el-form-item label="密码">
				<el-input v-model="loginForm.password" 
							ref="password" 
							placeholder="password" 
							show-password clearable>
				</el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="login">登录</el-button>
			</el-form-item>
		</el-form>

		<!-- <form class="form-horizontal">
		  <div class="form-group">
			<label for="userName" class="col-sm-2 control-label" >账号</label>
			<div class="col-sm-10">
			  <input type="text" 
					class="form-control" 
					id="userName" 
					placeholder="Account" 
					ref="account"
					v-model="loginForm.userName">
			</div>
		  </div>
		  <div class="form-group">
			<label for="password" class="col-sm-2 control-label" >密码</label>
			<div class="col-sm-10">
			  <input type="password" 
					class="form-control" 
					id="password" 
					placeholder="password" 
					ref="password"
					v-model="loginForm.password">
			</div>
		  </div>
		  <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			  <div class="checkbox">
				<label>
				  <input type="checkbox"> 记住账号密码
				</label>
			  </div>
			</div>
		  </div>
		  <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			  <button v-on:click="login" class="btn btn-default">登录</button>
			</div>
		  </div>
		</form> -->
	</div>
</template>

<script>
	export default {
		name: 'login',
		props: ['account'],
		data() {
			return {
				labelPosition: 'right',
				formLabelAlign: {
					name: '',
					region: '',
					type: ''
				},
				loginForm: {
					userName: '',
					password: '',
				},
			}
		},
		methods: {
			valid() {
				if (this.loginForm.userName === '' || this.loginForm.password === '') {
					alert('请输入账号和密码')
					return true
				}
			},
			login() {
				if (this.valid()) {
					return
				}

				// console.log(JSON.stringify(this.loginForm))
				this.$axios.post(
					'http://localhost:8080/api/user/login',
					this.loginForm
				).then(
					Response => {
						// console.log('成功了',Response.data.data.token)
						// console.log('传递token参数------',data.data.token)
						if(Response.data.code === 10002){
							this.password === ''
							this.getFouc()
							alert(Response.data.message)
							return false
						}
						this.$globalEventBus.$emit('login', 
							Response.data.data.token, 
							Response.data.data.nickName,
							Response.data.data.id)
					},
					Error => {
						console.log('失败了', Error.message)
					}
				)
			},
			getFouc() {
				if (this.loginForm.userName === '' || this.loginForm.userName === undefined) {
					this.$refs.account.focus()
				} else {
					this.$refs.password.focus()
				}

			}
		},
		mounted() {
			this.loginForm.userName = this.$route.params.account
			this.getFouc()
		},
	}
</script>

<style>
</style>
