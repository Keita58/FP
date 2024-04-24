import type {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Ex1',
        component: () => import('@/components/ex1.vue')
    },
    {
        path: '/Ex2',
        name: 'Ex2',
        component: () => import('@/components/ex2.vue')
    },
    {
        path: '/Ex3',
        name: 'Ex3',
        component: () => import('@/components/ex3.vue')
    }
];
export default routes;