<template>
  <section class="container h-100">
    <router-link to="/" class="hyptxt">Login</router-link> |
    <router-link to="/register" class="hyptxt">Register</router-link>
    <div
      class="row h-100 justify-content-center align-items-center"
      align="center"
      justify="center"
    >
      <div class="col wrapper-box">
        <img
          class="mx-auto d-block"
          src="../assets/logo-new-color-site.png"
          alt="DemoMail loco"
          title="DemoMail logo"
        />
        <p class="sign-in">Welcome</p>
        <div>
          <form class="form form-newaccount" id="passwordForm">
            <div class="form-group">
              <label for="" style="margin: 5px">Full Name</label>
              <input
                type="text"
                v-model="input.username"
                @focus="clearError('username')"
                @blur="username_check"
                :class="{ orange: error.username !== '' }"
                name="username"
                class="form-control"
                id="username"
              />
            </div>
            <div class="form-group">
              <label for="" style="margin: 18px">E-mail</label>
              <input
                type="text"
                v-model="input.email"
                @focus="clearError('Email')"
                @blur="email_check"
                :class="{ orange: error.username !== '' }"
                name="email"
                class="form-control"
                id="email"
              />
            </div>

            <div class="form-group">
              <label for="" style="margin: 6px">Password</label>
              <input
                type="password"
                v-model="input.password"
                :class="{
                  green: passValid === 3,
                  yellow: passValid === 2,
                  orange: passValid === 1
                }"
                name="password"
                id="password"
                autocomplete="off"
              />
            </div>
            <div class="form-group">
              <label for="" style="margin: 13px">Confirm</label>
              <input
                type="password"
                v-model="input.match_password"
                @focus="clearError('passwordMatch')"
                @blur="match_check"
                @keyup.enter="newUser"
                name="password2"
                id="password2"
                autocomplete="off"
              />
            </div>
            <div class="row">
              <div class="col-sm-12"></div>
              <div class="col-sm-12">
                <span class="d-block bullet-pass-rounded"
                  ><span></span> password must contains at least 6 characters, 1
                  number and 1 uppercase letter</span
                >
              </div>
            </div>
            <input
              type="button"
              v-on:click="newUser()"
              class="s-button"
              id="submit"
              data-loading-text="Cadastrando..."
              value="Register"
            />
            <transition-group name="fade" class="error-block d-block">
              <span
                class="error-message"
                :key="index + 'error'"
                v-for="(errorName, index) in error"
                v-show="errorName !== ''"
                >{{ errorName }}</span
              >
            </transition-group>
            <hr />
            <router-link to="/" class="hyptxt"
              >Already have an account?</router-link
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
    title: "Welcome",
    titleTemplate: "%s | Compacto Records"
  },
  data() {
    return {
      input: {
        email: "",
        username: "",
        password: "",
        match_password: ""
      },
      error: {
        password: "",
        passwordMatch: "",
        username: ""
      }
    };
  },
  methods: {
    match_check() {
      this.error.passwordMatch = "";
      if (this.input.password !== this.input.match_password)
        this.error.passwordMatch = "Passwords do not match";
      return this.error.passwordMatch !== "";
    },

    // login() {
    //   if (this.input.username != '' && this.input.password != '' && this.input.match_password != '') {
    //     if (this.input.username && this.input.password && this.input.match_password) {
    //       this.$emit('authenticated', true);
    //       this.$router.replace({ name: 'secure' });
    //     } else {
    //       this.error = 'E-mail ou senha incorretos'
    //       console.log("The username and / or password is incorrect");
    //     }
    //   } else {
    //     this.error = 'Digite um e-mail válido e uma senha'
    //     console.log("A username and password must be present");
    //   }
    // },

    clearError(field) {
      this.error[field] = "";
    },
    password_check() {
      this.error.password = "";
      if (!this.hasNumber)
        this.error.password = "Password does not contain a number";
      // if (!this.hasLowerCase) this.error.password = 'Senha não contém letra minuscula'
      if (!this.hasUpperCase)
        this.error.password = "Password does not contain uppercase letter";
      if (!this.minLength)
        this.error.password = "Password does not contain 6 characters";

      return this.error.password !== "";
    },
    username_check() {
      this.error.username = "";
      if (!this.validUsername(this.input.username))
        this.error.username = "Enter a valid username";
      if (this.input.username === "") this.error.username = "Enter a username";
      return this.error.username !== "";
    },
    email_check() {
      this.error.email = "";
      if (!this.validEmail(this.input.email))
        this.error.email = "Enter a valid e-mail address";
      if (this.input.email === "") this.error.email = "Enter an e-mail";
      return this.error.username !== "";
    },
    async newUser() {
      this.username = this.input.username;
      this.email = this.input.email;
      this.password = this.input.password;
      if (this.username_check()) return;
      if (this.password_check()) return;
      if (this.match_check()) return;
      const response = await axios.post("http://localhost:8095/signup/", {
        email: this.email,
        username: this.username,
        password: this.password
      });
      console.log(response);
      if (response.data.success == true) {
        this.$router.push({
          name: "user",
          params: { username: this.username }
        });
      } else {
        alert("This email already exists");
      }
      return this.error === "";
    },
    validUsername(username) {
      if (username != "" && username.includes(" ") == false) return true;
      return false;
    },
    validEmail(email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@(([[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    }
  },
  username_check() {
    this.error.username = "";
    if (!this.validEmail(this.input.username))
      this.error.username = "Enter a valid e-mail address";
    if (this.input.username === "") this.error.username = "Enter an e-mail";
    return this.error.username !== "";
  },
  validEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@(([[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  },
  computed: {
    hasNumber() {
      return /\d/.test(this.input.password);
    },
    hasLowerCase() {
      return /[a-z]/.test(this.input.password);
    },
    hasUpperCase() {
      return /[A-Z]/.test(this.input.password);
    },
    minLength() {
      return this.input.password.length > 5;
    },
    passValid() {
      let value = 0;
      if (this.hasNumber) value += 1;
      // if (this.hasLowerCase) value += 1
      if (this.hasUpperCase) value += 1;
      if (this.minLength) value += 1;
      return value;
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./styles/scss/variables.scss";
@import "./styles/scss/main.scss";
@import "./styles/scss/form.scss";
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
.hyptxt {
  color: #000;
}
.hyptxt:hover {
  color: #00cc66;
}
</style>
