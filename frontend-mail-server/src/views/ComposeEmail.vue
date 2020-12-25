<template>
  <div class="container">
    <div class="row">
      <div class="col-25">
        <label for="fname">Choose receivers</label>
      </div>
      <div class="col-75">
        <select id="users" name="users">
          <option v-bind:value="item" v-for="item in users" :key="item">{{
            item
          }}</option>
        </select>
        <button @click="updateReceivers()" class="left-col">
          Add to receivers
        </button>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="fname">To</label>
      </div>
      <div class="col-75">
        <ul id="recievers" name="emails">
          <option v-for="item in receivers" :key="item">{{ item }}</option>
        </ul>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="fname">Choose Importance</label>
      </div>
      <div class="col-75">
        <select class="right-col" id="importance" name="emails">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="lname">Subject</label>
      </div>
      <div class="col-75">
        <input
          type="text"
          id="subject"
          name="lastname"
          placeholder="Write subject of your email.."
        />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="subject">Body</label>
      </div>
      <div class="col-75">
        <textarea
          id="body"
          name="body"
          placeholder="Write something.."
          style="height:200px"
        ></textarea>
      </div>
    </div>
    <form @submit="send()" enctype="multipart/form-data">
      <div class="row">
        <div class="col-25">
          <label for="country">Attachments</label>
        </div>
        <div class="col-75">
          <input ref="files" type="file" multiple />
        </div>
      </div>
      <div class="row">
        <button class="submit" @click="send()">Send</button>
        <button class="submit" @click="saveDraft()">Save to drafts</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      users: [],
      receivers: [],
      body: "",
      importance: 0,
      username: "",
      emailAdd: "",
      pass: "",
      files: [],
      mail: {
        subject: "",
        body: "",
        receivers: [],
        sender: "",
        importance: ""
      }
    };
  },
  methods: {
    updateReceivers() {
      var addedItem = document.getElementById("users");
      this.receivers.push(addedItem.value);
      var newUsers = [];
      var i = 0;
      for (i = 0; i < this.users.length; i++) {
        if (!this.receivers.includes(this.users[i])) {
          newUsers.push(this.users[i]);
        }
      }
      this.users = newUsers;
    },
    async send() {
      this.files = this.$refs.files.files;
      var formData = new FormData();
      var sub = document.getElementById("subject");
      this.subject = sub.value;
      sub = document.getElementById("body");
      this.body = sub.value;
      sub = document.getElementById("importance");
      this.importance = sub.value;
      this.mail.subject = this.subject;
      this.mail.body = this.body;
      this.mail.receivers = this.receivers;
      this.mail.sender = this.emailAdd;
      this.mail.importance = this.importance;
      formData.append("mails", JSON.stringify(this.mail));
      formData.append("files", this.files[0]);

      try {
        const response = await axios({
          method: "post",
          url: "http://localhost:8095/composeEdit/",
          data: formData,
          headers: { "Content-Type": "multipart/form-data" }
        });
        alert(response.data.ans);
      } catch {
        console.log("test");
      }
    },
    async saveDraft() {
      var sub = document.getElementById("subject");
      this.subject = sub.value;
      sub = document.getElementById("body");
      this.body = sub.value;
      sub = document.getElementById("importance");
      this.importance = sub.value;
      const response = await axios.post("http://localhost:8095/saveDraft/", {
        subject: this.subject,
        body: this.body,
        sender: this.emailAdd,
        receivers: this.receivers,
        importance: this.importance
      });
      alert(response.data.ans);
    }
  },
  created: async function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
    console.log(this.emailAdd);
    const response = await axios.post("http://localhost:8095/read/users/", {
      email: this.emailAdd,
      password: "",
      username: this.username
    });
    var i = 0;
    for (i = 0; i < response.data.length; i++) {
      if (response.data[i].email != this.emailAdd) {
        this.users.push(response.data[i].email);
      }
    }
  }
};
</script>

<style scoped>
/* Style inputs, select elements and textareas */
input[type="text"],
select,
textarea,
input[type="file"] {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  resize: vertical;
}

/* Style the label to display next to the inputs */
label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

/* Style the submit button */
.submit {
  background-color: #4caf50;
  color: white;
  padding: 12px 20px;
  margin-right: 5px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

/* Style the container */
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

/* Floating column for labels: 25% width */
.col-25 {
  float: left;
  width: 25%;
  margin-top: 6px;
}

.left-col {
  float: center;
  margin-top: 3px;
  background-color: #4caf50;
  color: white;
  padding: 12px 20px;
  margin-right: 5px;
  border: none;
  border-radius: 4px;
  margin-bottom: 3px;
  cursor: pointer;
}

.right-col {
  align-items: center;
  font-size: 16px;
}
/* Floating column for inputs: 75% width */
.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25,
  .col-75,
  input[type="submit"] {
    width: 100%;
    margin-top: 0;
  }
}
</style>
