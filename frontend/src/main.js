import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import BootstrapVue from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import {store} from "./store";

Vue.use(BootstrapVue);

export const eventBus = new Vue();

new Vue({
  router,
  store,
  created() {
    store.dispatch("LOGIN_FROM_STORAGE");
  },
  render: h => h(App)
}).$mount("#app");
