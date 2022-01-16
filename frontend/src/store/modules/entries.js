import api from "../../service/api.js";
import toast from "../../lib/toast.js";

export const state = {
    entries: [],
    r: 1
};
const getters = {
    ENTRIES: state => {
        return state.entries;
    }
};
const mutations = {
    ADD_ENTRY: (state, entry) => {
        state.entries.push(entry);
    },
    SET_ENTRIES: (state, payload) => {
        state.entries = payload;
    }
};
const actions = {
    GET_ENTRIES: async (context, username) => {
        api()
            .get("/point/getEntries", {params: {
                "username": username
                }})
            .then(res => {
                context.commit("SET_ENTRIES", res.data);
            })
            .catch(err => {
                toast.error("Could not load history: " + err.message);
            });
    },
    POST_ENTRY: async (context, payload) => {
        api()
            .post("/point/addEntry", JSON.stringify(payload))
            .then(res => {
                let entry = {
                    x: res.data.x,
                    y: res.data.y,
                    r: res.data.r,
                    result: res.data.result,
                    dotResult: res.data.result // FOR DOT COLOR
                };
                if (entry.result) {
                    toast.success("Correct!");
                } else {
                    toast.error("Wrong!");
                }

                context.commit("ADD_ENTRY", entry);
            })
            .catch(error => {
                if (error.response.status === 409) {
                    toast.error(error.response.data)
                } else {
                    toast.error("" +
                        "Couldn't register: " + error.message);
                }
            });
    }
};

export default {
    state,
    getters,
    mutations,
    actions,
};