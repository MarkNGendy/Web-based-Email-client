<template>
  <button class="tablink" onclick="openCity('Oslo', this, 'orange')">Previous Page</button>
  <button class="tablink" onclick="openCity('Paris', this, 'green')">Delete</button>
  <button class="tablink" onclick="openCity('Tokyo', this, 'blue')">Next Page</button>
  <div class="draft">
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
      emails: [{
        subject: "sub",
        sender: "can",
        reciever: "ssss"
      }, {
        subject: "lomdsad",
        sender: "aheads",
        reciever: "ssss"
      }, {
        subject: "smarkub",
        sender: "caabdooon",
        reciever: "ssminaaass"
      }]
    };
  },
  methods: {
    testHello() {
      this.Hello = "not me";
    }
  }
};
</script>

<style scoped>
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
.draft {
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
