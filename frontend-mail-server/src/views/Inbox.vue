<template>
  <button class="tablink" @click="prevPage()">Previous Page</button>
  <button class="tablink" onclick="openCity('Paris', this, 'green')">Delete</button>
  <button class="tablink" @click="nextPage()">Next Page</button>
  <input type="text" class="filterbox" placeholder="Enter filter value.." id="filter-val">
  <select class="filterbox" name="sort-type" id="filter">
    <option value="SUBJECT">Filter by subject</option>
    <option value="SENDER">Filter by sender</option>
  </select>
  <button class="filter" @click="filter()">Filter</button>
  <select class="filterbox" name="sort-type" id="sort">
    <option value="sort by subject ascendingly">Sort by subject ascendingly</option>
    <option value="sort by subject descendingly">Sort by subject descendingly</option>
    <option value="sort by sender ascendingly">Sort by sender ascendingly</option>
    <option value="sort by sender descendingly">Sort by sender descendingly</option>
    <option value="sort by sender ascendingly">Sort by recievers ascendingly</option>
    <option value="sort by sender descendingly">Sort by recievers descendingly</option>
    <option value="sort by sender ascendingly">Sort by body ascendingly</option>
    <option value="sort by sender descendingly">Sort by body descendingly</option>
    <option value="sort by sender ascendingly">Sort by attachments ascendingly</option>
    <option value="sort by sender descendingly">Sort by attachments descendingly</option>
  </select>
  <button class="filter" onclick="openCity('Tokyo', this, 'blue')">Sort</button>
  <input type="text" class="filterbox" placeholder="Enter search value..">
  <select class="filterbox" name="sort-type" id="sort">
    <option value="Filter by subject">Search in subjects</option>
    <option value="Filter by subject">Search in senders</option>
    <option value="Filter by subject">Search in recievers</option>
    <option value="Filter by subject">Search in body</option>
    <option value="Filter by subject">Whole search</option>
  </select>
  <button class="filter" onclick="openCity('Paris', this, 'green')">Search</button>
  <button class="filter" @click="reload()">Home</button>
  <div class="inbox">
    <table class="content-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Subject</th>
          <th>Sender</th>
          <th>Receivers</th>
          <th>Date</th>
          <th>Importance</th>
        </tr>
      </thead>
      <tbody v-for="item in emails" :key="item.subject">
        <tr>
          <td><input type="checkbox" id="1"></td>
          <td><router-link :to="{name: 'view-email', params: {username: username,
          emailAdd: emailAdd, id:item.id, emails: JSON.stringify(allMails)}}">
          {{item.subject}}</router-link></td>
          <td>{{item.sender}}</td>
          <td>
            <ul>{{item.receiver}}
            </ul>
          </td>
          <td>{{item.date}}</td>
          <td>Priority</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      username:"",
      emailAdd: "",
      emails: [],
      allMails: [],
      currIndex: 1,
      filteredList: [],
    };
  },
  methods: {
    async nextPage() {
      if(this.currIndex < (this.allMails.length / 4)) {
        this.currIndex++; 
      }
      await this.paginate(this.allMails);
    },
    async prevPage() {
      if(this.currIndex != 1) {
        this.currIndex--; 
      }
      await this.paginate(this.allMails);
    },
    paginate(mailsList) {
      var counter = 0;
      var left = ((this.currIndex - 1) * (mailsList.length-1));
      var right = ((this.currIndex - 1) * (mailsList.length-1) + 3);
      var i = left;
      var list = [];
      while(i <= right && i< mailsList.length) {
        list[counter] = mailsList[i];
        counter++;
        i++;
      }
      this.emails = list;
    },
    async filter() {
      var sel = document.getElementById('filter');
      var field = sel.value;
      var value = document.getElementById('filter-val');
      var criteria = value.value;
      const response = await axios.post("http://localhost:8095/filter/", {
        list: this.allMails,
        field: field,
        criteria: criteria
    });
    this.filteredList = response.data;
    this.currIndex = 1;
    this.paginate(this.filteredList);
    },
    reload() {
      this.$router.push({ name: "user", params: { username: this.username, emailAdd:this.email} });
    },
  },
  created: async function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
    const response = await axios.post("http://localhost:8095/mails/", {
        listname: "Inbox",
        user: this.emailAdd,
    });
    this.allMails = response.data;
    this.currIndex = 1;
    this.paginate(this.allMails);
  }
}
</script>

<style scoped>
.filterbox {
  border-width: 3px;
  float: left;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 12px;
  width: 14%;
  margin-right: 2px;
  margin-left: 2px;
  margin-top: 2px;
  margin-bottom: 2px;
}
.filterboxcontent {
  color: white;
  display: none;
  text-align: center;
}
.filter {
  background-color: rgb(49, 167, 112);
  border-radius: 4px;
  border-width: 3px;
  color: white;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 17px;
  width: 7%;
  margin-right: 2px;
  margin-left: 2px;
  margin-top: 2px;
  margin-bottom: 2px;
}
.tablink {
  background-color: #555;
  color: white;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 17px;
  width: 33.3333%;
}
.tablink:hover {
  background-color: #777;
}
.tabcontent {
  color: white;
  display: none;
  padding: 50px;
  text-align: center;
}
.inbox {
  display: flex;
  justify-content: center;
  align-items: top;
  height: 100%;
  width: 100%;
  font-size: 40px;
  color: rgb(167, 167, 167);
  font-weight: 600;
}
* {
  font-family: sans-serif; /* Change your font family */
}

.content-table {
  border-collapse: collapse;
  margin: 0px 0;
  font-size: 13px;
  min-width: 40px;
  border-radius: 5px 5px 0 0;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  width: 1300px;
}

.content-table thead tr {
  background-color: #009879;
  color: #ffffff;
  text-align: center;
  font-weight: bold;
}

.content-table th,
.content-table td {
  padding: 1px 45px;
}

.content-table tbody tr {
  border-bottom: 1px solid #dddddd;
}

.content-table tbody tr:nth-of-type(even) {
  background-color: #f3f3f3;
}

.content-table tbody tr:last-of-type {
  border-bottom: 2px solid #009879;
}

.content-table tbody tr.active-row {
  font-weight: bold;
  color: #009879;
}

</style>
