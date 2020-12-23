<template>
  <div class="divname">
    <input
      class="name"
      type="text"
      id="name"
      name="name"
      placeholder="Write the name"
    />
  </div>
  <div class="divemail">
    <input
      class="email"
      type="text"
      id="email"
      name="email"
      placeholder="Write the email"
    />
  </div>
  <div>
    <button class="addemail" @click="addemail()">add another email</button>
  </div>
  <div>
    <button class="addcontact" @click="addcontact()">Add To My Contact</button>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      /*name: "",
      emails: [{}],*/
      username: "",
      emailAdd: "",
      Contact: { name: "", emails: [{}] }
    };
  },

  created: async function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
    console.log(this.emailAdd)
  },

  methods: {
    addemail() {
      var txt = document.getElementById("email").value;
      this.Contact.emails.push(txt);
      /*  var iterator = this.emails.values(); 
    console.log(iterator.next().value); 
    console.log(iterator.next().value); 
    console.log(iterator.next().value); 
    console.log(iterator.next().value);*/

      document.getElementById("email").value = "";
    },

    addcontact() {
      var txtname = document.getElementById("name").value;
      this.Contact.name = txtname;
      var txtemail = document.getElementById("email").value;
      this.Contact.emails.push(txtemail);

      const response = Axios.post("http://localhost:8095/addContact/", {
        contact: { userName: this.Contact.name, mails: this.Contact.emails },
        user: this.emailAdd,
        ind: 0
      });
      console.log(response);
      console.log(this.Contact.name);
      console.log(this.Contact.emails);

      document.getElementById("email").value = "";
      document.getElementById("name").value = "";
      this.Contact.emails = [];
      /*    var iterator = this.emails.values(); 
    console.log(iterator.next().value); 
    console.log(iterator.next().value); 
    console.log(iterator.next().value); 
    console.log(iterator.next().value);
    console.log(this.name);*/
    }
  }
};
</script>

<style scoped>
.name {
  text-align: center;
  width: 25%;
  height: 100%;
}
.email {
  text-align: center;
  width: 25%;
  height: 100%;
}
.divname {
  height: 40px;
  margin-top: 30px;
}
.divemail {
  height: 40px;
  margin-top: 30px;
}
.addemail {
  background-color: rgb(49, 167, 112);
  border-radius: 4px;
  border-width: 3px;
  color: white;
  text-align: center;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 17px;
  width: 25%;
  margin-right: 2px;
  margin-left: 2px;
  margin-top: 20px;
  margin-bottom: 2px;
}
.addcontact {
  background-color: rgb(49, 167, 112);
  border-radius: 4px;
  border-width: 3px;
  color: white;
  text-align: center;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 17px;
  width: 25%;
  margin-right: 2px;
  margin-left: 2px;
  margin-top: 60px;
  margin-bottom: 2px;
}
</style>
