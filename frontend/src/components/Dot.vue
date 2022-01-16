<template>
    <circle :cx="dot.x * 20" :cy="-dot.y * 20" r="3" :fill="color"></circle>
</template>

<script>
    export default {
        name: "Dot",
        props: {
            dot: {type: Object},
            radius: {type: Number}
        },
        methods: {
        },
        computed: {
            color() {
                this.radius;    // FUCKING MAGIC
                let result = this.dot.dotResult;
                let green = "#4ced5c";
                let red = "#ff564a";
                if (result) {
                    return green
                } else {
                    return red;
                }
            }
        }
    };
</script>

<style></style>