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
    // Nested Routes
    children: [
      {
        path: '/intro',
        component: () => import('@/views/Intro.vue')
      },
      {
        path: '/login',
        component: () => import('@/views/login/UserLogin.vue')
      },
      { path: '/signUp',
        name: 'signUp',
        component: () => import('@/views/signUp/SignUp.vue')
      },
      { path: '/signUpSteps',
        name: 'signUpSteps',
        component: () => import('@/views/signUp/SignUpSteps.vue')
      },
      { path: '/notice',
        name: 'notice',
        component: () => import('@/views/notice/noticeBoardList.vue')
      },
      { path: '/social',
        name: 'social',
        component: () => import('@/views/login/social/Login.vue')
      }
    ]
  },
  { path: '*', redirect: '/' }
]

export default routes
