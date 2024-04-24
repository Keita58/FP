import "bootstrap/dist/css/bootstrap.css";
import { createApp } from 'vue'
import App from './App.vue'
import router from "../src/router/router"

createApp(App).use(router).mount('#app')
