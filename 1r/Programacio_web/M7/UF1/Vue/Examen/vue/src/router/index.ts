import type { RouteRecordRaw} from "vue-router";

const routes:Array<RouteRecordRaw> = [
    {
      path:'/Ex2',
      name:'Ex2',
      component:()=> import('@/components/ex2.vue')
    },
    {
        path:'/Ex3',
        name:'Ex3',
        component:()=> import('@/components/ex3.vue')
    },
    {
        path:'/Ex5',
        name:'Ex5',
        component:()=> import('@/components/ex5.vue')
    },
];
export default routes;