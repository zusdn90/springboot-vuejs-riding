module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ? '/dist/' : '/',
    outputDir: '../server/public/dist'  //빌드 시 저장될 디렉토리 지정
};