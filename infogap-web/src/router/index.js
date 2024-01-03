import { createRouter, createWebHistory } from 'vue-router'

const Index = () => import('@/views/index/Index.vue')
const NotFound = () => import('@/views/error/NotFound.vue')

const InformationGap = () => import('@/views/information_gap/InformationGap.vue')

const CategoryNews = () => import('@/views/news/CategoryNews.vue')

const SearchNews = () => import('@/views/news/SearchNews.vue')

const routes = [
  {
    path: '/',
    component: Index,
    meta: {
      title: '早知道 - 每日信息差分享平台'
    }
  },
  {
    path: '/information_gap/:date',
    component: InformationGap,
    meta: {
      title: '每日信息 - 早知道'
    }
  },
  {
    path: '/search',
    component: SearchNews,
    meta: {
      title: '新闻搜索 - 早知道'
    }
  },
  {
    path: '/:category',
    component: CategoryNews,
    meta: {
      title: '新闻分区 - 早知道'
    }
  },
  {
    path: '/:pathMatch(.*)',
    component: NotFound
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

export default router