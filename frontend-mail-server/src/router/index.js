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
    props: true,
    children: [
      {
        path: "/compose-email",
        name: "compose-email",
        component: () => import("../views/ComposeEmail.vue"),
        props: true
      },
      {
        path: "/contacts",
        name: "contacts",
        component: () => import("../views/Contacts.vue"),
        props: true
      },
      {
        path: "/inbox",
        name: "inbox",
        component: () => import("../views/Inbox.vue"),
        props: true
      },
      {
        path: "/draft",
        name: "draft",
        component: () => import("../views/Draft.vue"),
        props: true
      },
      {
        path: "/trash",
        name: "trash",
        component: () => import("../views/Trash.vue"),
        props: true
      },
      {
        path: "/sent",
        name:"sent",
        component: () => import("../views/Sent.vue"),
        props: true
      },
      {
        path: "/user-folders",
        name:"user-folders",
        component: () => import("../views/UserFolders.vue"),
        props: true
      },
      {
        path: "/view-email",
        name:"view-email",
        component: () => import("../views/ViewEmail.vue"),
        props: true
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
