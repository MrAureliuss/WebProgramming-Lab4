<template>
  <div id="app" class="app">
    <transition name="fade" mode="out-in">
      <router-view/>
    </transition>
  </div>
</template>

<script>
    export default {
        name: "App",
        components: {},
        methods: {},
        mounted() {
        }
    };
</script>

<style lang="scss">
  @import url("https://fonts.googleapis.com/css?family=Lato|Rubik&display=swap");
  @media (max-height: 666px) and (max-width: 374px) {
    html {
      height: 100vh;
    }
    body {
      height: 100vh;
    }
    #app {
      border-radius: 0;
      box-shadow: none;
    }
  }
  @media (max-width: 600px) {
    #app {
      width: 100%;
    }
  }
  * {
    padding: 0;
    margin: 0;
  }
  html {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100%;
    font-family: "Lato", Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  body {
    background: linear-gradient(to top left, #ffcc99 0%, #9966ff 100%);
    display: flex;
    justify-content: center;
    width: 100%;
    border-radius: 20px;
  }
  #app {
    mix-blend-mode: hard-light;
    background-color: rgba(235, 235, 235, 0.8);
    min-height: 300px;
    width: 600px;
    padding: 6px;
    border-radius: 15px;
  }
  .fade-enter-active, .fade-leave-active {
    transition: opacity .45s
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
    opacity: 0
  }
  .active {
    color: #2a95ec;
  }
  .icon-button {
    background: none;
    border: none;
    font-size: 1.4em;
  }
  .row {
    display: inline-flex;
    align-content: center;
    flex-wrap: wrap;
  }
  .column {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .around {
    justify-content: space-around;
  }
  .between {
    width: 100%;
    justify-content: space-between;
  }
  .link {
    cursor: pointer;
    height: 15px;
    text-decoration: none;
    font-size: 16px;
    font-weight: 600;
    background: none;
    letter-spacing: 0.6px;
    border: none;
    color: #2a95ec;
  }
  ion-icon {
    font-size: 24px;
  }
  #link-home ion-icon {
    position: relative;
    top: -2px;
  }
  input[type="text"],
  input[type="email"],
  input[type="password"] {
    text-align: center;
    height: 32px;
    border: none;
    border-bottom: 1px solid #2c3e5056;
  }
  .time {
    align-self: flex-start;
    justify-self: flex-start;
  }
</style>
