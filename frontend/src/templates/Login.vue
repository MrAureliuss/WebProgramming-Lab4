<template>
<b-container fluid class="text-center justify-content-center">
  <b-row class="row justify-content-center">
    <b-navbar class="text-center justify-content-center">

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item disabled>Sign In</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-navbar-nav center>
            <b-nav-item to="/register"><span class="text-primary">Register</span>
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
          <p class="h4 text-center">Sign in</p>
        </b-col>
      </b-row>
      <b-row class="col text-center justify-content-center">
        <b-form-group
          id="input-group-1"
          label="Nickname:"
          label-for="text-nickname">
          <b-form-input
            id="text-nickname"
            v-model="form.nickname"
            type="text"
            autocomplete="off"
            required
            placeholder="Enter nickname"
          ></b-form-input>
        </b-form-group>
      </b-row>
      <b-row class="col text-center justify-content-center">
        <b-form-group @submit.stop.prevent
                      id="input-group-2"
                      label="Password:"
                      label-for="text-password"
        >
          <b-form-input
            type="password"
            id="text-password"
            v-model="form.password"
            autocomplete="off"
            required
            placeholder="Enter password">
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
        methods: {
            onSubmit(evt) {
                evt.preventDefault();
                let user = {
                    username: this.form.nickname,
                    password: this.form.password,
                    token: window.btoa(this.form.nickname + ':' + this.form.password)
                };
                this.$store.dispatch("LOGIN", user);
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
            }
        }
    }
</script>

<style scoped>
</style>
