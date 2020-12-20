<template>
  <button class="tablink" onclick="openCity('Oslo', this, 'orange')">Previous Page</button>
  <button class="tablink" onclick="openCity('Paris', this, 'green')">Delete</button>
  <button class="tablink" onclick="openCity('Tokyo', this, 'blue')">Next Page</button>
  <input type="text" class="filterbox" placeholder="Enter filter value..">
  <select class="filterbox" name="sort-type" id="sort">
    <option value="Filter by subject">Filter by subject</option>
    <option value="Filter by sender">Filter by sender</option>
  </select>
  <button class="filter" onclick="openCity('Paris', this, 'green')">Filter</button>
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
  <div class="inbox">
    <table class="content-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Subject</th>
          <th>Sender</th>
          <th>Reciever</th>
          <th>Date</th>
        </tr>
      </thead>
      <tbody v-for="item in emails" :key="item.subject">
        <tr>
          <td><input type="checkbox" id="1"></td>
          <td>{{item.subject}}</td>
          <td>{{item.sender}}</td>
          <td>{{item.reciever}}</td>
          <td>{{item.date}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username:"",
      emailAdd: "",
      emails: []
    };
  },
  methods: {
  },
  created: async function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
    const response = await axios.post("http://localhost:8095/mails/", {
        listname: "Inbox",
        user: this.emailAdd,
    });
    this.emails = response.data;
  }
};
</script>

<style scoped>
.filterbox {
  border-width: 3px;
  float: left;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 12px;
  width: 17%;
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
  width: 20%;
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
  font-size: 0.9em;
  min-width: 40px;
  border-radius: 5px 5px 0 0;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  width: 1050px;
}

.content-table thead tr {
  background-color: #009879;
  color: #ffffff;
  text-align: left;
  font-weight: bold;
}

.content-table th,
.content-table td {
  padding: 5px 50px;
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
