import {createRouter, createWebHistory, type Router} from "vue-router";
import routes from './index';

const router:Router = createRouter(
    {history:createWebHistory(import.meta.env.BASE_URL),
        routes,});

router.beforeEach(
    (to,from,next):void =>{
        const a:string = to.name ? to.name.toString():'sense valor';
        const b:string = from.name ? from.name.toString():'sense valor';
        console.log('Navigating to ' + a + " from " + b)
        next();
    }
);

router.afterEach((to,from,next):void =>{
    const a:string = to.name ? to.name.toString():'sense valor';
    const b:string = from.name ? from.name.toString():'sense valor';
    console.log('Navigated to ' + a + " from " + b)
});

export default router;
