import axios from "axios";

export default (includeAuth = true) => {

    let request = {
        baseURL: "http://localhost:31360/api",
        withCredentials: false,
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
        }
    };
    if (includeAuth) {
        let currentUserString = window.localStorage.currentUser;
        if (localStorage.getItem("currentUser") !== null) {
            let currentUser = currentUserString ? JSON.parse(currentUserString) : "";
            if (currentUser !== null) {
                request.headers.Authorization = "Basic " + currentUser.token;
            }
        }
    }
    return axios.create(request);
};