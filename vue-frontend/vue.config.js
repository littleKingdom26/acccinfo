module.exports = {
	outputDir: "../src/main/resources/static",
	indexPath: "../static/index.html",
	devServer: {
		proxy: "http://localhost:9090"
	},
	chainWebpack: config => {
		const svgRule = config.module.rule("svg");
		svgRule.uses.clear();
		svgRule.use("vue-svg-loader").loader("vue-svg-loader");

		config.module
			.rule('images')
			.use('url-loader')
			.tap(options => Object.assign({}, options, { name: 'vue_assets/img/[name].[hash:8].[ext]' }));
	},
	css: {
		extract: {
			filename: 'vue_assets/css/[name].[hash:8].css',
			chunkFilename: 'vue_assets/css/[name].[hash:8].css',
		},
	},
	configureWebpack: {
		output: {
			filename: 'vue_assets/js/[name].[hash:8].js',
			chunkFilename: 'vue_assets/js/[name].[hash:8].js',
		}
	}
};