<template>
  <router-link to="/" class="hyptxt">Login</router-link> |
  <router-link to="/register" class="hyptxt">Register</router-link>
  <section class="container h-100">
    <div
      class="row h-100 justify-content-center align-items-center"
      align="center"
      justify="center"
    >
      <div class="col wrapper-box">
        <img
          class="mx-auto d-block"
          src="../assets/logo-new-color-site.png"
          alt="DemoMail logo"
          title="DemoMail logo"
        />
        <p class="sign-in">Sign in</p>
        <div>
          <form class="form form-newaccount" id="loginForm">
            <div class="form-group">
              <label for="inputUser" style="margin: 27px">E-mail</label>
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
              <label for="inputPassword" style="margin: 15px">Password</label>
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
            <input
              type="button"
              v-on:click="login()"
              class="s-button"
              id="submitLogin"
              value="Login"
            />
            <transition name="fade">
              <span class="error-block error-message" v-show="error != null">{{
                error
              }}</span>
            </transition>
            <hr />
            <router-link to="/register" class="hyptxt"
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
        password: "",
        folders: ["sports", "hi"]
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
      if (response.data.success == true) {
        this.username = response.data.ans;
        this.$router.push({
          name: "user",
          params: {
            username: this.username,
            emailAdd: this.email,
            folderslist: this.folders
          }
        });
      } else {
        alert("You entered wrong email or password");
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

.sign-in {
  text-align: center;
  font-family: "Roboto", sans-serif;
  font-weight: bolder;
  font-size: 30px;
  color: #00cc66;
}
.s-button {
  display: inline-block;
  width: 300px;
  height: 50px;
  background-color: black;
  padding: 0.35em 1.2em;
  border: 0.1em solid #ffffff;
  font-size: 20px;
  margin: 10px;
  border-radius: 0.12em;
  box-sizing: border-box;
  text-decoration: none;
  font-family: "Roboto", sans-serif;
  font-weight: 100;
  color: #ffffff;
  text-align: center;
  transition: all 0.2s;
}
.s-button:hover {
  color: black;
  background-color: #00cc66;
}

.fade-enter,
.fade-leave-active {
  opacity: 0;
}
.hyptxt {
  color: #000;
}
.hyptxt:hover {
  color: #00cc66;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 300ms;
}
</style>
