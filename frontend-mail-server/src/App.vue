<template>
  <div id="nav">
    <router-link to="/">Login</router-link> |
    <router-link to="/register">Register</router-link>
  </div>
  <router-view />
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

@import "./assets/scss/variables.scss";
@import "./assets/scss/form.scss";
@import "./assets/scss/main.scss";
@import "./assets/css/font-awesome.min.css";

</style>
