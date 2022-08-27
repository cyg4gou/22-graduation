<template>
	<!-- 
		单文件组件三大标签--放结构
	-->
	<li>
		<label>
			<input 
				type="checkbox" 
				v-bind:checked='it.check' 
				v-on:click='changeDone(it.id)'
			/>
			<span>{{it.name}}</span>
		</label>
		<button 
			class="btn btn-danger"
			v-on:click="deleteItem"
		>删除</button>
	</li>
</template>

<script>
	// 单文件组件三大标签--放数据、数据交互...
	export default {
		name: 'MyItem',
		props: ['it', 'change','deleteDone'],
		methods: {
			changeDone(id) {
				this.change(id)
			},
			deleteItem(){
				if(confirm("确认删除吗")){
					this.deleteDone(this.it.id)
				}
			},
		}

	}
</script>

<style scoped>
	/* 
		单文件组件三大标签--放样式
	*/
	/*item*/
	li {
		list-style: none;
		height: 36px;
		line-height: 36px;
		padding: 0 5px;
		border-bottom: 1px solid #ddd;
	}

	li label {
		float: left;
		cursor: pointer;
	}

	li label li input {
		vertical-align: middle;
		margin-right: 6px;
		position: relative;
		top: -1px;
	}

	li button {
		float: right;
		display: none;
		margin-top: 3px;
	}

	li:before {
		content: initial;
	}

	li:last-child {
		border-bottom: none;
	}
	
	li:hover{
		background-color: #ddd;
	}
	
	li:hover button{
		display: block;
	}
</style>
