<template>
    <div class="sidebar">
        <div class="title">
            {{ username }}
        </div>
        <div class="menu-items">
            <router-link :to="{ name: 'compose-email', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    Compose Email
                </div>
            </router-link>
            <router-link :to="{name: 'add-contact', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    Add Contact
                </div>
            </router-link>
            <router-link :to="{name: 'my-contacts', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    My Contacts
                </div>
            </router-link>
            <router-link :to="{name: 'inbox', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    Inbox
                </div>
            </router-link>
            <router-link :to="{name: 'trash', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    Trash
                </div>
            </router-link>
            <router-link :to="{name: 'sent', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    Sent
                </div>
            </router-link>
            <router-link :to="{name: 'draft', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    Draft
                </div>
            </router-link>
            <router-link :to="{name: 'user-folders', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    UserFolders
                </div>
            </router-link>
            <router-link :to="{name: 'view-email', params: {username : username, 
            emailAdd: emailAdd}}" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    View Email
                </div>
            </router-link>
            <router-link to="/" active-class="active" tag="button" exact class="side-btn">
                <div class="link-container">
                    Logout
                </div>
            </router-link>
        </div>
    </div>
</template>

<script>
export default { 
  name:"Sidebar",
  data() {
    return {
      username: "",
      emailAdd: ""
    }
  },
  created: function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
  },
}
</script>
<style scoped>
.sidebar {
  margin: 5px;
}
.title {
    color: white;
    font-size: 23px;
    margin-top: 14px;
}

.menu-items {
    display: flex;
    flex-direction: column;
    margin-top: 3px;
    margin-left: 6px;
}

.menu-items > * {
    margin-top: 15px;
}

.side-btn {
    border: none;
    padding: 16px 0px;
    cursor: pointer;
    font-size: 16px;
    font-weight: 500;
    color: white;
    background-color: transparent;
}

.side-btn:focus {
    outline: none;
}

.side-btn.active {
    position: relative;
    background-color: white;
    color: teal;
    font-weight: 600;
    margin-left: 10px;
    border-radius: 30px 0 0 30px;
}

.side-btn.active::before {
    top: -30px;
}

.side-btn.active::after {
    bottom: -30px;
}

.side-btn.active::before, .side-btn.active::after {
    position: absolute;
    content: "";
    right: 0;
    height: 30px;
    width: 30px;
    background-color: white;
}

.side-btn.active .link-container::before {
    top: -60px;
}

.side-btn.active .link-container::after {
    bottom: -60px;
    z-index: 99;
}

.side-btn.active .link-container::before, .side-btn.active .link-container::after {
    position: absolute;
    content: "";
    right: 0px;
    height: 60px;
    width: 60px;
    border-radius: 50%;
    background-color: teal;
}
</style>