import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueRouter from 'vue-router';
import axios from 'axios'
import VueAxios from 'vue-axios'



createApp(App)
  .use(store)
  .use(router)
  .use(VueRouter)
  .use(VueAxios, axios)
  .mount("#app");
