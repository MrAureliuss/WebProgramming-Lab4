<template>
    <b-container fluid class="text-center justify-content-center" v-if="show" id="form">
        <b-form id="controls" @submit="onSubmit" class="form">
            <b-form-group>
                <p class="h5 text-center">X</p>
                <b-form-radio-group class="inline text-center"
                                    v-model="selectedX"
                                    :options="optionsX"
                                    :state="stateX"
                                    name="radioX"
                                    required>
                    <b-form-invalid-feedback :state="stateX"></b-form-invalid-feedback>
                    <b-form-valid-feedback :state="stateX"></b-form-valid-feedback>
                </b-form-radio-group>
            </b-form-group>
            <b-row class="text-center">
                <b-col></b-col>

                <b-form-group :invalid-feedback="invalidFeedbackY"
                              :valid-feedback="validFeedbackY"
                              :state="stateY">
                    <b-form-input
                            id="y-value"
                            v-model="selectedY"
                            type="number"
                            name="inputY"
                            required
                            placeholder="Enter Y"
                            :state="stateY"
                    ></b-form-input>
                </b-form-group>
                <b-col></b-col>
            </b-row>

            <b-form-group>
                <p class="h5 text-center">R</p>
                <b-form-radio-group class="text-center justify-content-center"
                                    v-model="selectedR"
                                    :options="optionsR"
                                    :state="stateR"
                                    name="radioR"
                                    required
                                    @change.native="changeR"
                >
                    <b-form-invalid-feedback :state="stateR"></b-form-invalid-feedback>
                    <b-form-valid-feedback :state="stateR"></b-form-valid-feedback>
                </b-form-radio-group>
            </b-form-group>
            <b-row class="text-center">
                <b-col>
                    <b-button type="submit" variant="primary" size="sm">Submit</b-button>
                </b-col>
            </b-row>
        </b-form>
    </b-container>
</template>

<script>
    import {eventBus} from "../main";
    import {store} from "../store";

    export default {
        data() {
            return {
                selectedX: null,
                selectedR: null,
                selectedY: null,
                optionsX: [
                    {text: '-3', value: '-3'},
                    {text: '-2', value: '-2'},
                    {text: '-1', value: '-1'},
                    {text: '0', value: '0'},
                    {text: '1', value: '1'},
                    {text: '2', value: '2'},
                    {text: '3', value: '3'},
                    {text: '4', value: '4'},
                    {text: '5', value: '5'}
                ],
                optionsR: [
                    {text: '1', value: '1'},
                    {text: '2', value: '2'},
                    {text: '3', value: '3'},
                    {text: '4', value: '4'},
                    {text: '5', value: '5'}
                ],
                show: true
            }
        },
        computed: {
            stateX() {
                return Boolean(this.selectedX);
            },
            stateR() {
                return Boolean(this.selectedR);
            },
            stateY() {
                return Boolean(this.selectedY) && +this.selectedY >= -3 && +this.selectedY <= 5;
            },
            invalidFeedbackY() {
                if (this.selectedY === null) {
                    return ''
                } else if (Boolean(this.selectedY) && (+this.selectedY < -3 || +this.selectedY > 5)) {
                    return 'Enter number in range of -3..5'
                } else {
                    return 'Please enter a number'
                }
            },
            validFeedbackY() {
                return ''
            },
        },
        name: "Form",
        methods: {
            onSubmit(evt) {
                evt.preventDefault();
                if (this.stateR && this.stateX && this.stateY) {
                    let entry = {
                        x: +this.selectedX,
                        y: +this.selectedY.replace(",", "."),
                        r: +this.selectedR,
                    };
                    entry.username = store.getters.USERNAME;
                    this.$emit("addentry", entry);
                }
            },
            changeR() {
                eventBus.$emit("radiusChanged", 20 * this.selectedR);
            },
        }
    };
</script>

<style scoped>
    #form {
        display: inline-flex;
        flex-direction: row;
        justify-content: space-evenly;
    }
    .form {
        display: inline-flex;
        flex-direction: column;
        justify-content: space-evenly;
        height: 100%;
        margin: 0 auto;
        padding-bottom: 10px;
    }
</style>