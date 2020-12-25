<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style lang="scss" scoped>
@import "./components/styles/scss/variables.scss";
@import "./components/styles/scss/main.scss";
#app {
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body {
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
}
</style>
