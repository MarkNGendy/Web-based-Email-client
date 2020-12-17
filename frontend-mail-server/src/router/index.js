import { createRouter, createWebHistory } from "vue-router";
import Login from "../components/Login.vue";
import Register from "../components/Register.vue";
import dashboard from '../views/Dashboard'



const routes = [
  {
    path: "/",
    name: "Login",
    component: Login
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
  {
    name: "user",
    path: '/user',
    component: dashboard,
    props:{ username:true },
    children: [
      {
        path: "/inbox",
        name: "inbox",
        component: () => import("../views/Inbox.vue")
      },
      {
        path: "/draft",
        name: "draft",
        component: () => import("../views/Draft.vue")
      },
      {
        path: "/trash",
        name: "trash",
        component: () => import("../views/Trash.vue")
      },
      {
        path: "/sent",
        name:"sent",
        component: () => import("../views/Sent.vue")
      },
      {
        path: "/user-folders",
        name:"user-folders",
        component: () => import("../views/UserFolders.vue")
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
