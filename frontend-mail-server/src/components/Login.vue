<template>
  <router-link to="/">Login</router-link> |
  <router-link to="/register">Register</router-link>
  <section class="container h-100">
    <div class="row h-100 justify-content-center align-items-center"
    align="center"
    justify="center">
      <div class="col wrapper-box">
        <img
          class="mx-auto d-block"
          src="../assets/logo-new-color-site.png"
          alt="Compacto Records"
          title="Compacto Records"
        />
        <p class="wrapper-box__title text-center">Sign in</p>
        <div>
          <form class="form form-newaccount" id="loginForm">
            <div class="form-group">
              <label for="inputUser">E-mail</label>
              <input
                v-model="input.email"
                @focus="clearError()"
                id="inputUser"
                type="text"
                name="email"
                class="form-control"
                placeholder="Email"
              />
            </div>
            <div class="form-group">
              <label for="inputPassword">Password</label>
              <input
                v-model="input.password"
                @focus="clearError()"
                @keyup.enter="login"
                id="inputPassword"
                type="password"
                name="password"
                class="form-control"
                placeholder="Password"
              />
            </div>
            <div class="custom-control custom-checkbox pull-right">
              <input
                type="checkbox"
                class="custom-control-input"
                id="customCheck1"
              />
              <label class="custom-control-label" for="customCheck1"
                >Remember password</label
              >
            </div>
            <input
              type="button"
              v-on:click="login()"
              class="btn btn-default"
              id="submitLogin"
              value="Login"
            />
            <transition name="fade">
              <span class="error-block error-message" v-show="error != null">{{
                error
              }}</span>
            </transition>
            <hr />
            <router-link to="/register" class="text-link d-block text-right"
              >No account yet?</router-link
            >
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
export default {
  name: "Login",
  metaInfo: {
    title: "Sign in",
    titleTemplate: "%s | Compacto Records"
  },
  data() {
    return {
      input: {
        email: "",
        password: ""
      },
      error: ""
    };
  },
  methods: {
    async login() {
      this.email = this.input.email;
      this.password = this.input.password;
      const response = await axios.post("http://localhost:8095/signin/", {
        email: this.email,
        username: "",
        password: this.password
      });
      if(response.data.success == true) {
        this.username = response.data.ans;
        this.$router.push({ name: "user", params: { username: this.username } });
      } else {
        prompt("You entered wrong email or password");
        return this.error === "";
      }
    },
    clearError() {
      this.error = "";
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./styles/scss/variables.scss";
@import "./styles/scss/form.scss";
body {
  align-self: center;
}
.fade-enter,
.fade-leave-active {
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 300ms;
}
</style>
