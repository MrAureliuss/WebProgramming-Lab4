<template>
    <div class="main">
        <b-navbar class="text-center justify-content-center">

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item disabled>{{$store.getters.USERNAME}}</b-nav-item>
                </b-navbar-nav>

                <b-navbar-nav class="ml-auto">
                    <b-navbar-nav center>
                        <b-nav-item @click="showHistory = !showHistory"
                                    :class="[{ active: showHistory}]"><span class="text-primary">Table</span>
                        </b-nav-item>
                    </b-navbar-nav>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <b-navbar-nav right>
                        <b-nav-item @click="logout"><span class="text-danger">Exit</span>
                        </b-nav-item>
                    </b-navbar-nav>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
        <div class="row">
            <keep-alive>
                <component :is="currentTabPlane" :entries="entries" @addentry="addEntry"
                           class="row justify-content-center"
                           style="padding-bottom: 15px; padding-top: 10px; margin: 0 auto;"></component>
            </keep-alive>
            <keep-alive>
                <component :is="currentTabForm" :entries="entries" @addentry="addEntry"
                           class="text-center justify-content-center"></component>
            </keep-alive>
        </div>
        <keep-alive>
            <component :is="currentTabTable" :entries="entries" @addentry="addEntry"
                       class="text-center justify-content-center"></component>
        </keep-alive>
    </div>
</template>

<script>
    import {store} from "../store";
    import CoordPlane from "../components/CoordPlane.vue";
    import Form from "../components/Form.vue";
    import Table from "../components/Table.vue";
    import toast from "../lib/toast.js";
    export default {
        name: "Main",
        components: {CoordPlane, Form, Table},
        data() {
            return {
                showHistory: false
            };
        },
        computed: {
            currentTabPlane() {
                return this.showHistory ? "" : "CoordPlane";
            },
            currentTabTable() {
                return this.showHistory ? "Table" : "";
            },
            currentTabForm() {
                return this.showHistory ? "" : "Form";
            },
            entries() {
                return this.$store.getters.ENTRIES;
            }
        },
        methods: {
            addEntry(entry) {
                this.$store.dispatch("POST_ENTRY", entry);
            },
            getEntries() {
                this.$store.dispatch("GET_ENTRIES", store.getters.USERNAME);
            },
            logout() {
                store.dispatch("LOGOUT");
            }
        },
        beforeRouteEnter(to, from, next) {
            next(vue => {
                if (vue.$store.getters.IS_AUTHENTICATED) {
                    vue.getEntries();
                    next();
                } else {
                    next("/login");
                    toast.info("You must login");
                }
            });
        }
    };
</script>

<style lang="css">
</style>