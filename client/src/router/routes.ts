import { Role } from '@/types/enum'

// const title = process.env.COMPANY_NM
const appId = process.env.APP_ID

const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('@/views/Index.vue'),
    meta: {
      title: appId,
      authority: [Role.NONE]
    },
    children: [
      {
        path: '/intro',
        component: () => import('@/views/Intro.vue')
      },
      {
        path: '/login',
        component: () => import('@/views/login/UserLogin.vue')
      }
    ]
  },
  { path: '*', redirect: '/' }
]

export default routes
