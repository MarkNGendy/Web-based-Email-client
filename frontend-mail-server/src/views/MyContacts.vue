<template>
  <button class="tablink" @click="prevPage()">Previous Page</button>
  <router-link
    :to="{
      name: 'add-contact',
      params: { username: username, emailAdd: emailAdd }
    }"
    active-class="active"
    tag="button"
    exact
    class="tablink"
  >
    <div class="link-container">
      Add Contact
    </div>
  </router-link>

  <button class="tablink" @click="deleteMails()">Delete</button>
  <button class="tablink" @click="nextPage()">Next Page</button>
  <select class="filterbox" name="sort-type" id="sort-type">
    <option value="ASCENDING">Ascending</option>
    <option value="DESCENDING">Descending</option>
  </select>
  <button class="filter" @click="sort()">Sort</button>
  <input
    id="search-val"
    type="text"
    class="filterbox"
    placeholder="Enter search value.."
  />
  <button class="filter" @click="search()">Search</button>
  <button class="filter" @click="gotoHome()">Home</button>
  <div class="inbox">
    <table class="content-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Emails</th>
        </tr>
      </thead>
      <tbody v-for="item in emails" :key="item.subject">
        <tr>
          <td>
            <input
              v-bind:id="item.userName"
              v-bind:value="JSON.stringify(item)"
              v-on:click="addMail($event)"
              type="checkbox"
            />{{ item.ind }}
          </td>
          <td>
            <router-link
              :to="{
                name: 'edit-contact',
                params: {
                  username: username,
                  emailAdd: emailAdd,
                  id: item.id,
                  emails: JSON.stringify(allMails)
                }
              }"
            >
              {{ item.userName }}</router-link
            >
          </td>
          <td>
            <ul>
              {{
                item.mails
              }}
            </ul>
          </td>
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
      username: "",
      emailAdd: "",
      emails: [],
      allMails: [],
      currIndex: 1,
      filteredList: [],
      isFiltered: false,
      deletedMails: []
    };
  },
  methods: {
    addMail: function(event) {
      var id = event.currentTarget.id;
      var checkbox = document.getElementById(id);
      var value = checkbox.value;
      if (checkbox.checked == true) {
        this.deletedMails.push(JSON.parse(value));
      } else {
        var i = 0;
        var tempArr = [];
        for (i = 0; i < this.deletedMails.length; i++) {
          if (this.deletedMails[i].userName !== JSON.parse(value).userName) {
            tempArr.push(this.deletedMails[i]);
          }
        }
        this.deletedMails = tempArr;
      }
      console.log(this.deletedMails);
    },
    async deleteMails() {
      console.log(this.deletedMails);
      var response = await axios.post("http://localhost:8095/removeContact/", {
        contact: { userName: "", mails: [] },
        user: this.emailAdd,
        RContacts: this.deletedMails,
        ind: 0,
        editingMail: ""
      });
      response = await axios.post("http://localhost:8095/contact/", {
        email: this.emailAdd,
        password: "",
        username: this.username
      });
      console.log(response);
      this.allMails = response.data;
      this.isFiltered = false;
      this.currIndex = 1;
      this.paginate(this.allMails);
    },
    async nextPage() {
      if (this.currIndex < this.allMails.length / 10) {
        this.currIndex++;
      }
      if (this.isFiltered == true) {
        await this.paginate(this.filteredList);
      } else {
        await this.paginate(this.allMails);
      }
    },
    async prevPage() {
      if (this.currIndex != 1) {
        this.currIndex--;
      }
      if (this.isFiltered == true) {
        await this.paginate(this.filteredList);
      } else {
        await this.paginate(this.allMails);
      }
    },
    paginate(mailsList) {
      var counter = 0;
      var left = (this.currIndex - 1) * 10;
      var right = (this.currIndex - 1) * 10 + 9;
      var i = left;
      var list = [];
      while (i <= right && i < mailsList.length) {
        list[counter] = mailsList[i];
        counter++;
        i++;
      }
      this.emails = list;
    },
    async sort() {
      var requestList;
      if (this.isFiltered == true) {
        requestList = this.filteredList;
      } else {
        requestList = this.allMails;
      }
      var value = document.getElementById("sort-type");
      var criteria = value.value;
      const response = await axios.post(
        "http://localhost:8095/sort/contacts/",
        {
          contacts: requestList,
          criteria: criteria
        }
      );
      this.filteredList = response.data;
      this.currIndex = 1;
      this.isFiltered = true;
      this.paginate(this.filteredList);
    },
    async search() {
      var value = document.getElementById("search-val");
      var criteria = value.value;
      const response = await axios.post(
        "http://localhost:8095/search/contacts/",
        {
          contacts: this.allMails,
          criteria: criteria
        }
      );
      this.filteredList = response.data;
      this.currIndex = 1;
      this.isFiltered = true;
      this.paginate(this.filteredList);
    },
    gotoHome() {
      this.$router.push({
        name: "user",
        params: { username: this.username, emailAdd: this.email }
      });
    }
  },
  created: async function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
    const response = await axios.post("http://localhost:8095/contact/", {
      email: this.emailAdd,
      password: "",
      username: this.username
    });
    console.log(response.data);
    this.allMails = response.data;
    this.isFiltered = false;
    this.currIndex = 1;
    this.paginate(this.allMails);
  }
};
</script>

<style scoped>
.filterbox {
  border-width: 3px;
  float: center;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 12px;
  width: 16%;
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
  float: center;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 17px;
  width: 10%;
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
  width: 24%;
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
  font-size: 18px;
}

.content-table th,
.content-table td {
  padding: 1px 45px;
}

.content-table tbody tr {
  border-bottom: 1px solid #dddddd;
  font-size: 13px;
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
