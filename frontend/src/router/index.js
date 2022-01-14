import Vue from "vue"
import VueRouter from "vue-router"
import Login from "../templates/Login"
import About from "../templates/About";
import Register from "../templates/Register";

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "login",
    component: Login
  },
  {
    path: "/about",
    name: "about",
    component: About
  },
  {
    path: "/register",
    name: "register",
    component: Register
  },
  {
    path: '*', redirect: '/login'
  }
];

const router = new VueRouter({routes});

export default router;
