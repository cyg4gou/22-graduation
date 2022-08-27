const {
	defineConfig
} = require('@vue/cli-service')

module.exports = defineConfig({
	transpileDependencies: true,
	lintOnSave: false,
	devServer: {
		port:8081,
		proxy: {
			'/api': {
				target: 'http://localhost:8089/',
				changeOrigin: true,
				ws: true,
				pathRewrite: {
					'^/api': ''
				},
			}
		}
	}
})
