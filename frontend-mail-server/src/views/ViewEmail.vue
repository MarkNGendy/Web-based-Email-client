<template>
  <div class="container">
  <form action="action_page.php">
    <div class="row">
      <div class="col-25">
        <label for="fname">From</label>
      </div>
      <div class="col-75">
        <ul id="sender">
          <dt>{{ sender }}</dt>
        </ul>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="fname">To</label>
      </div>
      <div class="col-75">
        <ul id="recievers" v-for="item in recievers" :key="item">
          <dt>{{ item }}</dt>
        </ul>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="lname">Subject</label>
      </div>
      <div class="col-75">
        <label id="subject" name="subject">{{subject}}</label>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="subject">Body</label>
      </div>
      <div class="col-75">
        <p id="body" name="body">{{ body }}</p>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="country">Attachments</label>
      </div>
      <div class="col-75">
        <input type="file" multiple>
      </div>
    </div>
    <div class="row">
      <button class="submit" @click="gotoHome()">Home</button>
    </div>
  </form>
</div>
</template>

<script>
import axios from "axios";
export default {
  name: "view-email",
  data() {
    return {
      emails:[],
      id: "",
      receiver: [],
      subject: "",
      body: "",
      sender: "",
    }
  },
  methods: {
    gotoHome() {
      this.$router.push({ name: "user", params: { username: this.username, emailAdd:this.email} });
    }
  },
  created: async function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
    this.id = this.$route.params.id;
    this.emails = JSON.parse(this.$route.params.emails);
    console.log(this.emails)
    const response = await axios.post("http://localhost:8095/read/", {
        list: this.emails,
        ID: this.id
    });
    console.log(response);
    this.recievers = response.data.receivers;
    this.subject = response.data.subject;
    this.body = response.data.body;
    this.sender = response.data.sender;
  }
}
</script>

<style scoped>
/* Style inputs, select elements and textareas */
input[type=text], select, textarea, input[type=file]{
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
  background-color: #4CAF50;
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
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>