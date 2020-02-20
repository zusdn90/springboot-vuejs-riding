module.exports = {
  publicPath: process.env.NODE_ENV === "production" ? "/dist/" : "/",
  assetsDir: 'static',
  outputDir: "../server/public/dist", // 빌드 시 저장될 디렉토리 지정
  runtimeCompiler: undefined,
  productionSourceMap: undefined,
  parallel: undefined,
  css: undefined,
  transpileDependencies: [/node_modules[/\\\\]vuetify[/\\\\]/],
  chainWebpack: config => {
    config.plugin("fork-ts-checker").tap(args => {
      args[0].memoryLimit = 4096
      return args
    })
  }
}
