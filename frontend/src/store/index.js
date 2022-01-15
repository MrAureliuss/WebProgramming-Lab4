import Vue from "vue";
import Vuex from "vuex";
import user from "./modules/user";
import entries from "./modules/entries";

Vue.use(Vuex);

export const store = new Vuex.Store({
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    user,
    entries
  }
});
