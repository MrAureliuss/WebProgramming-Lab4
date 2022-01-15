import Vue from "vue"
import VueRouter from "vue-router"
import Login from "../templates/Login"
import About from "../templates/About";
import Register from "../templates/Register";
import Home from "../templates/Home";
import Main from "../templates/Main";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: Home
  },
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
    path: "/main",
    name: "main",
    component: Main
  },
  {
    path: '*', redirect: '/'
  }
];

const router = new VueRouter({routes});

export default router;
