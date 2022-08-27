<template>
	<div class="text-center">
		<h1>老年人关怀系统Ops</h1>
		<p id="nickName">{{nickName}}</p>
	</div>
</template>

<script>
	export default {
		name: 'MyHeader',
		data(){
			return{
				nickName:'',
			}
		},
		methods:{
			login(){
				
			},
			registry(){
				
			},
			logout(){
				
			},
			init(){
				this.nickName = sessionStorage.getItem('nickName')
				console.log('header初始化成功,nickName----->',this.nickName)
			}
		},
		mounted(){
			this.$globalEventBus.$on('init',this.init)
		}
	}
</script>

<style scoped>
	.nickName{
		float: right;
		color: red;
		
	}
</style>
