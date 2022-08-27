<template>
	<form class="form-horizontal">

		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">活动标题</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" placeholder="title" ref="title"
					v-model="activity.title">
			</div>
		</div>

		<div class="form-group">
			<label for="expireTime" class="col-sm-2 control-label">活动过期时间</label>
			<div class="col-sm-10">
				<input type="date" class="form-control time" id="expireTime" placeholder="expireTime" ref="expireTime"
					v-model="activity.expireTime">
			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label">活动内容</label>
			<!-- <div class="col-sm-10">
				<input type="text" class="form-control" id="body" placeholder="body" ref="body"
					v-on:keyup.enter="publish" v-model="activity.body">
			</div> -->
			<div class="col-sm-10">

				<div id="editor" class="editor">
					<span ref='body' v-html='body'></span>
				</div>
				<!-- <div class="wang-editor"> -->
				<!-- <div id="editorToolBar" 
					class="editor-tool-bar" 
					title="富文本的头部"></div> -->
				<!-- <div id="editorContent" -->
				<!-- class="editor-content" -->
				<!-- title="富文本的编辑区域" -->
				<!-- ></div> -->
			</div>
		</div>
		</div>



		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button v-on:click="publish" class="btn btn-default">发表</button>
			</div>
		</div>
	</form>
</template>

<script>
	import Editor from 'wangeditor'
	import moment from "moment"

	var a = 0

	export default {
		name: 'publish',
		data() {
			return {
				body:'',
				activity: {
					title: '',
					body: '',
					expireTime: '',
					id: '',
					activityId:'',
					token: '',
				},
				// editor1: null,
				// editor2: null,
				// editorContent: '',
				// msg: '活动内容', //富文本编辑器中默认初始化的内容
			}
		},
		methods: {
			checkValid() {
				if (this.activity.title === '' ||
					this.activity.body === '' ||
					this.activity.expireTime === '') {
					return true
				}
				return false
			},
			publish() {
				this.activity.body = a
				if (this.checkValid()) {
					console.log('请输入正确内容')
					return false
				}

				this.activity.expireTime += ' 00:00:00.883Z'

				var id = sessionStorage.getItem('id')
				var token = sessionStorage.getItem('token')
				console.log(this.activity,'<<<<<<<<<<<<<<<<<<')
				this.$axios({
						url: 'http://localhost:8080/api/activity/publish',
						method: 'post',
						headers: {
							'access-token': token,
							'access-id': id
						},
						data: this.activity,
					}

				).then(
					Response => {
						console.log('请求活动列表成功', Response.data)
						this.$globalEventBus.$emit('refresh')
					},
					Error => {
						console.log('失败了', Error.message)
					}
				)
			},
			
			//创建富文本编辑器
			createEditor() {
				//一、将头部和编辑的区域不分开的富文本编辑器
				this.editor1 = new Editor('#editor');
				// console.log('0------------------->', this.editor1.config)
				// this.editor1.config.menus = [
				// 	'head', // 标题
				// 	'bold', // 粗体
				// 	'fontSize', // 字号
				// 	'fontName', // 字体
				// 	'italic', // 斜体
				// 	'underline', // 下划线
				// 	'strikeThrough', // 删除线
				// 	'foreColor', // 文字颜色
				// 	'backColor', // 背景颜色
				// 	'link', // 插入链接
				// 	'list', // 列表
				// 	'justify', // 对齐方式
				// 	'quote', // 引用
				// 	'emoticon', // 表情
				// 	'image', // 插入图片
				// 	'table', // 表格
				// 	'video', // 插入视频
				// 	'code', // 插入代码
				// 	'undo', // 撤销
				// 	'redo' // 重复
				// ]; //编辑器头部的操作功能菜单
				// this.editor1.config.onchangeTimeout = 1000; //编辑内容改变的函数的时间等待
				
				this.editor1.config.onchange = function(html) {
					// console.log(html);
					a = html
					// console.log(a)
				} //编辑内容改变的函数
				// this.editor1.config.zIndex = 100000; //同时改变头部和编辑区的z-index
				// this.editor1.config.pasteFilterStyle = false; //去除粘贴文本时自带 的样式
				// this.editor1.config.pasteIgnoreImg = true; //去除粘贴时自带的图片
				// this.editor1.config.pasteTextHandle = function(content) { //粘贴文本时的函数
				// 	console.log(content); //打印粘贴的文本
				// } //粘贴文本时的函数
				// this.editor1.config.onblur = function(html) { //编辑器失去焦点
				// 	console.log('onblur');
				// 	console.log(html); //编辑器中的内容
				// } //编辑器失去焦点
				// this.editor1.config.onfocus = function() { //编辑器获得焦点
				// 	console.log('onfocus');
				// } //编辑器获得焦点
				// this.editor1.config.colors = [
				// 	'#000000',
				// 	'#eeece0',
				// 	'#1c487f',
				// 	'#4d80bf',
				// 	'#c24f4a',
				// 	'#8baa4a',
				// 	'#7b5ba1',
				// 	'#46acc8',
				// 	'#f9963b',
				// 	'#ffffff'
				// ]; //自定义配置颜色（字体颜色、背景色）
				this.editor1.create();

				// 二、将头部和编辑的区域分开的富文本编辑器
				// this.editor2 = new Editor('#editorToolBar', '#editorContent');
				// this.editor2 = new Editor('#editorContent');
				// this.editor2.create();
				// this.editor2.$textElem.attr('contenteditable', true); //开启编辑功能
				// this.editor2.txt.html(this.msg); //设置文本中的内容
				// this.editor2.txt.clear(); //清除文本中的内容
			},
		},


		mounted() {
			this.activity.id = sessionStorage.getItem('id')
			this.activity.token = sessionStorage.getItem('token')
			this.$refs.title.focus()
			// this.activity.expireTime = new Date
			// console.log('this.activity.expireTime----->',new Date().format('yyyy-MM-dd'))
			// console.log(JSON.stringify(this.activity))
			// console.log(this.$route.params.data === undefined)
			// console.log(this.$route.params.data)
			const data = this.$route.params.data
			if(data !== undefined){
				this.activity.title = data.title
				this.activity.activityId = data.id
				this.body = data.body
				this.activity.expireTime = moment(data.expireTime.substring(0,10),"YYYY-MM-DD")._i;
				// console.log(this.activity.expireTime)
				// console.log(data.expireTime)
			}
			this.createEditor()

		}
	}
</script>

<style scoped>
	.wang-editor {
		width: 100%;
		height: 100%;

		p {
			background: #dcddcc;
			height: 30px;
			font-size: 24px;
		}

		.editor {
			width: 800px;
			height: 300px;
			margin: 50px auto;
			text-align: left;
		}

		.editor-tool-bar {
			width: 800px;
			margin: 0 auto;
			border: 1px solid #000;
			background: #ccc;
		}

		.editor-content {
			width: 800px;
			height: 300px;
			margin: 0 auto;
			border: 1px solid #000;
			text-align: left;
		}
	}
</style>
