import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueRouter from 'vue-router';


createApp(App)
  .use(store)
  .use(router)
  .use(VueRouter)
  .mount("#app");
