import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Login',
        component: () => import('@/views/Login.vue'),
        meta: {
            title: '登录'
        }
    },
    {
        path: '/login',
        redirect: '/'
    },
    {
        path: '/home',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
        meta: {
            title: '首页'
        }
    },
    {
        path: '/wxjsz',
        name: 'Wxjsz',
        component: () => import('@/views/Wxjsz.vue'),
        meta: {
            title: '危险驾驶罪'
        }
    },
    {
        path: '/zscqqq',
        name: 'Zscqqq',
        component: () => import('@/views/Zscqqq.vue'),
        meta: {
            title: '知识产权侵权'
        }
    },
    {
        path: '/xykzp',
        name: 'Xykzp',
        component: () => import('@/views/Xykzp.vue'),
        meta: {
            title: '信用卡诈骗'
        }
    },
    {
        path: '/shfz',
        name: 'Shfz',
        component: () => import('@/views/Shfz.vue'),
        meta: {
            title: '涉黑犯罪'
        }
    },
    {
        path: '/wcnfz',
        name: 'Wcnfz',
        component: () => import('@/views/Wcnfz.vue'),
        meta: {
            title: '未成年犯罪'
        }
    },

    {
        path: '/lhjf',
        name: 'Lhjf',
        component: () => import('@/views/Lhjf.vue'),
        meta: {
            title: '离婚纠纷'
        }
    },
    {
        path: '/ladsj',
        name: 'Ladsj',
        component: () => import('@/views/Ladsj.vue'),
        meta: {
            title: '立案大数据'
        }
    },
    {
        path: '/splcjd',
        name: 'Splcjd',
        component: () => import('@/views/Splcjd.vue'),
        meta: {
            title: '审判流程监督'
        }
    },
    {
        path: '/dxwlzp',
        name: 'Dxwlzp',
        component: () => import('@/views/Dxwlzp.vue'),
        meta: {
            title: '诈骗'
        }
    },
    {
        path: '/syjfaj',
        name: 'Syjfaj',
        component: () => import('@/views/Syjfaj.vue'),
        meta: {
            title: '赡养纠纷'
        }
    },
    {
        path: '/ajList',
        name: 'AjList',
        component: () => import('@/views/AjList.vue'),
        meta: {
            title: '案件详情'
        }
    },
    {
        path: '/bjAjList',
        name: 'BjAjList',
        component: () => import('@/views/BjAjList.vue'),
        meta: {
            title: '案件详情'
        }
    }
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

export default router
