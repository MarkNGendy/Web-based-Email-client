<template>
  <div class="dashboard">
    <Sidebar />
    <div class="content">
      <router-view />
    </div>
  </div>
</template>

<script>
import Sidebar from "../components/Sidebar";
export default {
  name: "home",
  components: {
    Sidebar
  },
  data() {
    return {
      title: ""
    };
  },
  methods: {
    created() {
      this.title = this.$route.params.username;
    }
  }
};
</script>

<style lang="scss" scoped>
body {
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
}

.dashboard {
  display: grid;
  grid-template-columns: 1fr 5fr;
  background-color: teal;
  height: 100vh;
  width: 100vw;
}

.content {
  background-color: white;
  border-radius: 10px;
  margin: 6px 6px 6px 0px;
}
</style>
