const appId = process.env.APP_ID

const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('@/views/Index.vue'),
    meta: {
      title: appId,
      authority: ''
    },
    children: [
      {
        path: '/intro',
        component: () => import('@/views/Intro.vue')
      }
    ]
  },
  { path: '*', redirect: '/' }
]

export default routes
