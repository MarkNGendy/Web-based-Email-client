<template>
  <div class="container">
    <div class="row">
      <div class="col-25">
        <label for="fname">Name</label>
      </div>
      <div class="col-75">
        <input type="text" id="name" placeholder="Enter new name..">
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="fname">New email</label>
      </div>
      <div class="col-75">
        <input type="text" id="email">
      <button @click="updateEmails()" class="left-col">Add new email</button>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="fname">New Emails</label>
      </div>
      <div class="col-75">
      <ul id="recievers" name="emails">
          <option v-for="item in emails" :key="item">{{item}}</option>
      </ul>
      </div>
    </div>
    <div class="row">
      <button class="submit" @click="send()">Save</button>
    </div>
</div>
</template>

<script>
// import axios from "axios"
export default {
  data() {
    return {
      id: "",
      emails: [],
      name: "",
      username:"",
      emailAdd:"",
      pass: ""
    }
  },
  methods: {
    updateEmails() {
      var addedItem = document.getElementById("email");
      if(this.emails.includes(addedItem.value)) {
        alert("email already added");
        document.getElementById("email").value = "";
      } else{
        this.emails.push(addedItem.value);
      }
    },
  },
  created: async function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
    this.id = this.$route.params.id;
    console.log(this.id)
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

.left-col {
  float: center;
  margin-top: 3px;
  background-color: #4CAF50;
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
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>