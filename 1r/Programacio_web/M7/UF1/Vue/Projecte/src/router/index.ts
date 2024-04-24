import type { RouteRecordRaw} from "vue-router";

const routes:Array<RouteRecordRaw> = [
  {
    path:'/',
    name:'Explicació',
    component:()=> import('@/components/explicacioJoc.vue')
  },
];
export default routes;