<template>
  <b-container fluid class="text-center justify-content-center">
    <b-row class="col justify-content-center">
      <b-navbar class="text-center justify-content-center">

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item to="/account/login"><span class="text-primary">Sign In</span></b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto">
            <b-navbar-nav center>
              <b-nav-item to="/account/register" disabled>Register
              </b-nav-item>
            </b-navbar-nav>
          </b-navbar-nav>

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-navbar-nav right>
              <b-nav-item to="/about"><span class="text-primary">About</span>
              </b-nav-item>
            </b-navbar-nav>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </b-row>
    <b-row class="text-center justify-content-center">
      <b-form @submit="onSubmit" @reset="onReset" v-if="show" autocomplete="off">
        <b-row class="text-center">
          <b-col>
            <p class="h4 text-center">Register</p>
          </b-col>
        </b-row>
        <b-row class="col text-center justify-content-center">
          <b-form-group
            id="input-group-1"
            label="Nickname:"
            label-for="text-nickname"
            :invalid-feedback="invalidFeedbackN"
            :valid-feedback="validFeedbackN"
            :state="stateN"
            autocomplete="off">
            <b-form-input
              id="text-nickname"
              v-model="form.nickname"
              type="nickname"
              autocomplete="off"
              required
              placeholder="Enter nickname"
              :state="stateN" trim
            ></b-form-input>
          </b-form-group>
        </b-row>
        <b-row class="col text-center justify-content-center">
          <b-form-group @submit.stop.prevent
                        id="input-group-2"
                        label="Password:"
                        label-for="text-password"
                        :invalid-feedback="invalidFeedback"
                        :valid-feedback="validFeedback"
                        :state="state">
            <b-form-input
              type="password"
              id="text-password"
              v-model="form.password"
              required
              placeholder="Enter password"
              :state="state" trim>
            </b-form-input>
          </b-form-group>
        </b-row>
        <b-row class="text-center">
          <b-col>
            <b-button type="submit" variant="primary" size="sm">Submit</b-button>
          </b-col>
          <b-col>
            <b-button type="reset" variant="danger" size="sm">Reset</b-button>
          </b-col>
        </b-row>
      </b-form>
    </b-row>
  </b-container>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    nickname: '',
                    password: '',
                },
                show: true
            }
        },
        computed: {
            state() {
                return this.form.password.length >= 4
            },
            invalidFeedback() {
                if (this.form.password.length > 4) {
                    return ''
                } else if (this.form.password.length > 0) {
                    return 'Enter at least 4 characters'
                } else {
                    return ''
                }
            },
            validFeedback() {
                return ''
            },
            stateN() {
                return this.form.nickname.length >= 4
            },
            invalidFeedbackN() {
                if (this.form.nickname.length > 4) {
                    return ''
                } else if (this.form.nickname.length > 0) {
                    return 'Enter at least 4 characters'
                } else {
                    return ''
                }
            },
            validFeedbackN() {
                return ''
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault();
                let user = {
                    username: this.form.nickname,
                    password: this.form.password
                };
                if (this.form.nickname.length >= 4 && this.form.password.length >= 4) {
                    this.$store.dispatch("REGISTER", user);
                }
            },
            onReset(evt) {
                evt.preventDefault();
                // Reset our form values
                this.form.nickname = '';
                this.form.password = '';
                // Trick to reset/clear native browser form validation state
                this.show = false;
                this.$nextTick(() => {
                    this.show = true
                })
            },
            submit() {
                let user = {
                    username: this.username,
                    password: this.password
                };
                this.$store.dispatch("REGISTER", user);
            }
        }
    }
</script>

<style scoped>

</style>
