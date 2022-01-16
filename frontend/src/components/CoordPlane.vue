<template>
    <div id="coords">
        <svg
                width="250"
                height="250"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="-125 -125 250 250"
                ref="plane"
                @click="graphClick"
        >
            <!-- axes -->

            <line x1="-125" x2="125" y1="0" y2="0"/>
            <line x1="0" x2="0" y1="-125" y2="125"/>

            <!-- arrows -->

            <polyline points="-5,-115 0,-125 5,-115"/>
            <polyline points="115,5 125,0 115,-5"/>

            <!-- marks -->

            <line x1="-20" x2="-20" y1="5" y2="-5"/>
            <line x1="-40" x2="-40" y1="5" y2="-5"/>
            <line x1="-60" x2="-60" y1="5" y2="-5"/>
            <line x1="-80" x2="-80" y1="5" y2="-5"/>
            <line x1="-100" x2="-100" y1="5" y2="-5"/>

            <line x1="20" x2="20" y1="5" y2="-5"/>
            <line x1="40" x2="40" y1="5" y2="-5"/>
            <line x1="60" x2="60" y1="5" y2="-5"/>
            <line x1="80" x2="80" y1="5" y2="-5"/>
            <line x1="100" x2="100" y1="5" y2="-5"/>

            <line y1="20" y2="20" x1="5" x2="-5"/>
            <line y1="40" y2="40" x1="5" x2="-5"/>
            <line y1="60" y2="60" x1="5" x2="-5"/>
            <line y1="80" y2="80" x1="5" x2="-5"/>
            <line y1="100" y2="100" x1="5" x2="-5"/>

            <line y1="-20" y2="-20" x1="5" x2="-5"/>
            <line y1="-40" y2="-40" x1="5" x2="-5"/>
            <line y1="-60" y2="-60" x1="5" x2="-5"/>
            <line y1="-80" y2="-80" x1="5" x2="-5"/>
            <line y1="-100" y2="-100" x1="5" x2="-5"/>

            <!-- circle -->
            <clipPath id="clip-circle">
                <polygon points="-125 -125 0 -125 0 0 -125 0"/>
            </clipPath>

            <circle
                    cx="0"
                    cy="0"
                    :r="radius/2"
                    fill="#2a95ec"
                    clip-path="url(#clip-circle)"
            />
            <!-- rectangle -->
            <rect :x="0" :y="0" :width="radius" :height="radius/2" fill="#2a95ec"/>
            <!-- triangle -->
            <polygon :points="-radius/2 + ' 0 0 0 0 ' + radius/2" fill="#2a95ec"/>
            <!-- dots -->
            <dot
                    v-for="entry in entries"
                    :dot="entry"
                    :key="entry.id"
                    :radius="radius / 20"
            />
        </svg>
    </div>
</template>

<script>
    import {eventBus} from "../main";
    import Dot from "./Dot.vue";
    import toast from "../lib/toast.js";
    import {store} from "../store";

    export default {
        name: "CoordPlane",
        props: {
            entries: {type: Array}
        },
        data() {
            return {
                radius: 0
            };
        },
        components: {dot: Dot},
        created() {
            eventBus.$on("radiusChanged", value => {
                this.radius = value;
            });
        },
        methods: {
            graphClick(click) {
                if (this.radius > 0) {
                    let plane = this.$refs.plane;
                    let width = 250;
                    let height = 250;
                    let coordX = click.pageX - plane.getBoundingClientRect().left;
                    let coordY = click.pageY - plane.getBoundingClientRect().top;
                    let relX = coordX - width / 2;
                    let relY = coordY - height / 2;
                    relY = -relY;
                    let x = (relX / 125) * 6.25;
                    let y = (relY / 125) * 6.25;
                    x = +(x + "").substring(0, 5);
                    y = +(y + "").substring(0, 5);
                    let entry = {x, y};
                    entry.r = this.radius / 20;
                    entry.username = store.getters.USERNAME;
                    this.$emit("addentry", entry);
                } else {
                    toast.error("Choose radius!");
                }
            }
        },
        mounted() {
        }
    };
</script>

<style scoped>
    svg {
        stroke: rgb(53, 53, 53);
        stroke-width: 2px;
    }
    circle {
        color: blue;
    }
</style>