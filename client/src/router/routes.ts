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
    }
    // Nested Routes
    // children: [
    //   {
    //     path: '/intro',
    //     component: () => import('@/views/Intro.vue')
    //   },
    //   {
    //     path: '/login',
    //     component: () => import('@/views/login/UserLogin.vue')
    //   }
    // ]
  },
  { path: '/login',
    name: 'login',
    component: () => import('@/views/login/UserLogin.vue')
  },
  { path: '/signUp',
    name: 'signUp',
    component: () => import('@/views/signUp/SignUp.vue')
  },
  { path: '*', redirect: '/' }
]

export default routes
