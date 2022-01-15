<template lang="html">
    <div class="home column">
        <div v-if="!isAuthenticated">
            <Login/>
        </div>
        <div v-else>
            <Main/>
        </div>
    </div>
</template>

<script>
    import {store} from "../store";
    import Login from "../templates/Login.vue";
    import Main from "../templates/Main.vue";
    export default {
        name: "Home",
        components: {Login, Main},
        methods: {
            logout() {
                store.dispatch("LOGOUT");
            }
        },
        computed: {
            isAuthenticated() {
                return store.getters.IS_AUTHENTICATED;
            }
        },
    };
</script>

<style scoped>
    .home {
        height: 100%;
    }
</style>