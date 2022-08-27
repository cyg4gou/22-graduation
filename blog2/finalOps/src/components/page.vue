<template>
	<div>
		<input v-model="currentPage" type="hidden"/>
		<nav aria-label="...">
			<ul class="pagination" v-for="(page,index) of pages" v-bind:key="index">
				<!-- <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li> -->
				<!-- <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li> -->
				<li><button 
						class="btn" 
						ref="page" 
						v-on:click="toPage(page)">
						{{page}}</button></li>
			</ul>
		</nav>

		<!-- <div class="dropup">
		  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    Dropup
		    <span class="caret">{{this.pageSize}}</span>
		  </button>
		  <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
		    <li><a href="#">Action</a></li>
		    <li><a href="#">Another action</a></li>
		    <li><a href="#">Something else here</a></li>
		    <li role="separator" class="divider"></li>
		    <li><a href="#">Separated link</a></li>
		  </ul>
		</div> -->
	</div>
</template>

<script>
	export default {
		name: 'page',
		data() {
			return {
				currentPage: 1,
				pages : 1,
				counts : 1,
			}
		},
		methods: {
			getCounts(counts,currentPage,pageSize) {
				this.counts = counts
				// console.log('this.pages ======  ', counts,"-------",pageSize)
				this.pages = Math.ceil(counts / pageSize)
				this.currentPage = currentPage
				// console.log('pages---------->', this.pages)
			},
			toPage(toPage) {
				this.$refs.page[this.currentPage - 1].classList.remove('btn-primary')
				this.currentPage = toPage
				this.$globalEventBus.$emit('currentPage', toPage)
			}
		},
		mounted() {
			this.$globalEventBus.$on('activityCounts', this.getCounts)
		},
		beforeUpdate(){
			this.$refs.page[this.currentPage  - 1].classList.add('btn-primary')
			// console.log(this.$refs.page)
		},
	}
</script>

<style>
</style>
