import api from "../../service/api.js";
import router from "../../router";
import toast from "../../lib/toast.js";
import {store} from "../../store";

const state = {
    username: "",
    password: "",
    isAuthenticated: false
};
const getters = {
    USERNAME: () => {
        return state.username;
    },
    PASSWORD: () => {
        return state.password;
    },
    IS_AUTHENTICATED: () => {
        return state.isAuthenticated;
    }
};
const mutations = {
    LOGIN_USER: (state, payload) => {
        let {username, password, token} = payload;
        state.username = username;
        state.password = password;
        state.isAuthenticated = true;
        window.localStorage.currentUser = JSON.stringify({
            username,
            token
        });
        router.push("/main");
    },
    LOGOUT_USER: state => {
        window.localStorage.currentUser = null;
        state.username = "";
        state.password = "";
        state.isAuthenticated = false;
        toast.info("Logged out!");
    },
    SET_USER: (state, payload) => {
        state.username = payload;
    },
    SET_PASSWORD: (state, payload) => {
        state.password = payload;
    }
};
const actions = {
    REGISTER: async (context, payload) => {
        api()
            .post("/auth/sign_up", JSON.stringify(payload))
            .then(response => {
                if (response.status === 201) {
                    toast.success("Successfully registered!");
                    toast.info("Now you should log in");
                    router.push("/account/login");
                }
            })
            .catch(error => {
                if (error.response.status === 409) {
                    toast.error("Register denied. User with same username exist!")
                } else {
                    toast.error("" +
                        "Couldn't register: " + error.message);
                }
            });
    },
    LOGIN: async (context, payload) => {
        let {token} = payload;
        api()
            .post("/auth/sign_in", JSON.stringify(payload), {
                headers: {
                    'Authorization': 'Basic ' + token,
                },
                //JSON.stringify(payload))
            })
            .then(response => {
                if (response.status === 200) {
                    toast.success("Successfully logged in!");
                    context.commit("LOGIN_USER", payload);
                }
            })
            .catch((error) => {
                if (error.response.status === 401) {
                    toast.error("Bad login or password!");
                } else {
                    toast.error("Wrong Credentials. Try Again!");
                }
            });
    },
    LOGOUT: async context => {
        context.commit("LOGOUT_USER");
        router.push("/account/login");
    },
    LOGIN_FROM_STORAGE: async () => {
        if (localStorage.getItem("currentUser") !== null) {
            let user = JSON.parse(window.localStorage.currentUser);
            if (user) {
                store.dispatch("LOGIN", user);
            }
        }
    }
};
export default {
    state,
    getters,
    mutations,
    actions
};