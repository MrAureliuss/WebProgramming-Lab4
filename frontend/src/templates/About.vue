<template>
  <b-container fluid class="text-center justify-content-center">
    <b-row class="row justify-content-center">
      <b-navbar class="text-center justify-content-center">

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item to="/login"><span class="text-primary">Sign In</span></b-nav-item>
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
              <b-nav-item to="/about" disabled>About
              </b-nav-item>
            </b-navbar-nav>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </b-row>
    <div class="col justify-content-center">
        <a href="https://vk.com/eriksimohyan" target="_blank"><img src="../assets/avatar.png"></a>
        <p></p>
        <p class="text-center">Вариант: 15123</p>
        <p class="text-center">Выполнил: Симонян Эрик Самвелович</p>
        <p class="text-center">Группа: P3215</p>
        <p></p>
        <a href="https://github.com/MrAureliuss" target="_blank"><img id="github_logo" src="../assets/githublogo.png"></a>
        <p></p>
    </div>
  </b-container>
</template>

<script>
    export default {
        name: "About"
    }
</script>

<style scoped>
#github_logo:hover {
  -webkit-transform: scale(1.1);
  -ms-transform: scale(1.1);
  transform: scale(1.1);
  transition-delay: 0.1s;
}
</style>
