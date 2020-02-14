import Vue from 'vue'
import Router from 'vue-router'
import routes from '@/router/routes'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 네비게이션 가드 사용(전역 가드)
router.beforeEach(async (to, from, next) => {
  if (to.meta && to.meta.title) {
    document.title = to.meta.title
  }
  const { authority } = to.meta
  if (authority) {
    // 페이지 접근 권한에 따른 분기 처리
    // Vue.$log.error('error!');
  }
  next()
})

export default router
