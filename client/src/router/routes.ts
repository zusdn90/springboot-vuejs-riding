const appId = process.env.APP_ID

const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('@/views/Index.vue'),
    meta: {
      title: appId
    },
    children: [
      {
        path: '/intro',
        component: () => import('@/views/Intro.vue')
      },
      {
        path: '/login',
        component: () => import('@/views/login/Login.vue')
      }
    ]
  },
  { path: '*', redirect: '/' }
]

export default routes
