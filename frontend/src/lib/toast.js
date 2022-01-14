import Vue from "vue";
import VueToast from "vue-toast-notification";
import "vue-toast-notification/dist/index.css";

Vue.use(VueToast, {
    position: "top-left"
});

const toast = Vue.$toast;

export default toast;