<template>
    <b-container fluid class="col" style="padding-bottom: 15px">
        <b-table
                :sticky-header="true"
                responsive
                :items="entries"
                :fields="fields"
                style="margin: auto;"
                class="col text-center justify-content-center col-md"
        >
        </b-table>
    </b-container>
</template>

<script>
    export default {
        name: "Table",
        props: {
            entries: {type: Array}
        },
        data() {
            return {
                fields: [
                    'x',
                    'y',
                    'r',
                    'result'
                ],
                items: [
                    {x: 1, y: 0, r: 1, result: 2},
                    {x: 2, y: 0, r: 1, result: 2},
                    {x: 3, y: 0, r: 1, result: 2},
                    {x: 4, y: 0, r: 1, result: 2},
                    {x: 5, y: 0, r: 1, result: 2},
                    {x: 6, y: 0, r: 1, result: 2},
                    {x: 7, y: 0, r: 1, result: 2},
                    {x: 8, y: 0, r: 1, result: 2},
                    {x: 9, y: 0, r: 1, result: 2},
                    {x: 10, y: 0, r: 1, result: 2}
                ]
            }
        }
    };
</script>

<style lang="scss" scoped>
</style>