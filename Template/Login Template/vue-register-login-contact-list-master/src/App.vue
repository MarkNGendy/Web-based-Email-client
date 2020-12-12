<template>
  <div id="app" class="h-100">
    <header v-if="authenticated">
        <nav class="navbar">
          <div class="container">
          <div class="navbar-brand">
            <router-link to="/secure">
              <img src="./assets/logo-new-color-site.png">
            </router-link>
          </div>
          <div class="navbar-end">
            <router-link to="/secure" class="navbar-item btn btn-sm btn-primary">Contacts</router-link>
            <router-link to="/add" class="navbar-item btn btn-sm btn-primary">Add contact</router-link>
            <router-link to="/login" class="btn btn-sm btn-secondary" v-on:click.native="logout()" replace>Logout</router-link>
          </div>
        </div>
        </nav>
    </header>
    <transition name="page">
      <router-view @authenticated="setAuthenticated" />
      <!-- <router-view/> -->
    </transition>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {
        authenticated: false,
        mockAccount: {
          username: 'compacto',
          password: 'compacto'
        }
      }
    },
    mounted() {
      if (!this.authenticated) {
        this.$router.replace({name: 'register'})
      }
    },
    methods: {
      setAuthenticated(status) {
        this.authenticated = status;
      },
      logout() {
        this.authenticated = false;
      }
    }
  }
</script>

<style lang="scss">
  @import './assets/scss/variables.scss';
  @import './node_modules/bootstrap/scss/bootstrap.scss';
  @import './assets/scss/form.scss';
  @import './assets/scss/main.scss';

  .page-enter,
  .page-leave-active {
    opacity: 0;
  }

  .page-enter-active,
  .page-leave-active {
    transition: opacity 300ms;
  }
</style>
