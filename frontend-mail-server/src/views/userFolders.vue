<template>
  <button class="tablink3" @click="addFolder()">Add Folder</button>
  <button class="tablink3" @click="renameFolder()">Rename Folder</button>
  <button class="tablink3" @click="deleteFolder()">Delete Folder</button>
  <div>
    <button class="tablink5" @click="prevPage()">Previous Page</button>
    <button class="tablink5" @click="deleteMails()">Delete</button>
    <button class="tablink5" @click="nextPage()">Next Page</button>
  </div>
  <div>
    <select
      @change="onChange($event)"
      class="selector"
      id="folderslist"
      name="folders"
    >
      <option v-bind:value="item" v-for="item in folderslist" :key="item">{{
        item
      }}</option>
    </select>
  </div>
  <input
    type="text"
    class="filterbox"
    placeholder="Enter filter value.."
    id="filter-val"
  />
  <select class="filterbox" name="sort-type" id="filter">
    <option value="SUBJECT">Filter by subject</option>
    <option value="SENDER">Filter by sender</option>
  </select>
  <button class="filter" @click="filter()">Filter</button>
  <select class="filterbox" name="sort-type" id="sort">
    <option value="SUBJECT">Sort by subject</option>
    <option value="SENDER">Sort by sender</option>
    <option value="RECEIVERS">Sort by recievers</option>
    <option value="TIME">Sort by date</option>
    <option value="BODY">Sort by body</option>
    <option value="ATTACHMENTS">Sort by attachments</option>
    <option value="IMPORTANCE">Sort by importance</option>
  </select>
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
  <select id="search-cat" class="filterbox" name="sort-type">
    <option value="SUBJECT">Search in subjects</option>
    <option value="SENDER">Search in senders</option>
    <option value="RECEIVERS">Search in receivers</option>
    <option value="BODY">Search in body</option>
    <option value="WHOLE">Whole search</option>
  </select>
  <button class="filter" @click="search()">Search</button>
  <button class="filter" @click="gotoHome()">Home</button>
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
          <td>
            <input
              v-bind:id="item.id"
              v-bind:value="JSON.stringify(item)"
              v-on:click="addMail($event)"
              type="checkbox"
            />{{ item.id }}
          </td>
          <td>
            <router-link
              :to="{
                name: 'view-email',
                params: {
                  username: username,
                  emailAdd: emailAdd,
                  id: item.id,
                  emails: JSON.stringify(allMails),
                  email: JSON.stringify(item)
                }
              }"
            >
              {{ item.subject }}</router-link
            >
          </td>
          <td>{{ item.sender }}</td>
          <td>
            <ul>
              {{
                item.receivers
              }}
            </ul>
          </td>
          <td>{{ item.date }}</td>
          <td>{{ item.importance }}</td>
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
      deletedMails: [],
      currfolder: "",
      folderslist: []
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
          if (this.deletedMails[i].id !== JSON.parse(value).id) {
            tempArr.push(this.deletedMails[i]);
          }
        }
        this.deletedMails = tempArr;
      }
    },
    async onChange(event) {
      var folderName = event.target.value;
      this.currfolder = folderName;
      var response = await axios.post("http://localhost:8095/mails/", {
        listname: "UserFolders/" + folderName,
        user: this.emailAdd
      });
      this.allMails = response.data;
      this.isFiltered = false;
      this.currIndex = 1;
      this.paginate(this.allMails);
    },
    async moveMails() {
      var destination = prompt("Enter the destination folder name");
      var response = await axios.post("http://localhost:8095/move/user/folders/", {
        mails: this.deletedMails,
        user: this.emailAdd,
        source: "UserFolders/" + this.currfolder,
        target: "UserFolders/" + destination
      });
      response = await axios.post("http://localhost:8095/mails/", {
        listname: "UserFolders/" + this.currfolder,
        user: this.emailAdd
      });
      this.allMails = response.data;
      this.isFiltered = false;
      this.currIndex = 1;
      this.paginate(this.allMails);
    },
    async deleteMails() {
      var response = await axios.post("http://localhost:8095/delete/Trash/", {
        mails: this.deletedMails,
        currFolder: "UserFolders/" + this.currfolder,
        user: this.emailAdd
      });
      response = await axios.post("http://localhost:8095/mails/", {
        listname: "UserFolders/" + this.currfolder,
        user: this.emailAdd
      });
      this.allMails = response.data;
      this.isFiltered = false;
      this.currIndex = 1;
      this.paginate(this.allMails);
    },
    async nextPage() {
      if (this.currIndex < this.allMails.length / 5) {
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
      var left = (this.currIndex - 1) * (mailsList.length - 1);
      var right = (this.currIndex - 1) * (mailsList.length - 1) + 4;
      var i = left;
      var list = [];
      while (i <= right && i < mailsList.length) {
        list[counter] = mailsList[i];
        counter++;
        i++;
      }
      this.emails = list;
    },
    async filter() {
      var sel = document.getElementById("filter");
      var field = sel.value;
      var value = document.getElementById("filter-val");
      var criteria = value.value;
      const response = await axios.post("http://localhost:8095/filter/", {
        list: this.allMails,
        field: field,
        criteria: criteria
      });
      this.filteredList = response.data;
      this.currIndex = 1;
      this.isFiltered = true;
      this.paginate(this.filteredList);
    },
    async sort() {
      var requestList;
      if (this.isFiltered == true) {
        requestList = this.filteredList;
      } else {
        requestList = this.allMails;
      }
      var sel = document.getElementById("sort");
      var field = sel.value;
      var value = document.getElementById("sort-type");
      var criteria = value.value;
      const response = await axios.post("http://localhost:8095/sort/", {
        list: requestList,
        field: field,
        criteria: criteria
      });
      this.filteredList = response.data;
      this.currIndex = 1;
      this.isFiltered = true;
      this.paginate(this.filteredList);
    },
    async addFolder() {
      var name = prompt("Enter folder name");
      await axios.post("http://localhost:8095/addFolder/", {
        listname: name,
        newName: "",
        user: this.emailAdd
      });
      this.loadCurrFolderMails();
    },
    async renameFolder() {
      var name = this.currfolder;
      var newName = prompt("Enter folder new name");
      await axios.post("http://localhost:8095/renameFolder/", {
        listname: name,
        newName: newName,
        user: this.emailAdd
      });
      this.loadCurrFolderMails();
    },
    async deleteFolder() {
      var name = this.currfolder;
      await axios.post("http://localhost:8095/deleteFolder/", {
        listname: name,
        newName: "",
        user: this.emailAdd
      });
      this.loadCurrFolderMails();
    },
    async search() {
      var sel = document.getElementById("search-cat");
      var field = sel.value;
      var value = document.getElementById("search-val");
      var criteria = value.value;
      const response = await axios.post("http://localhost:8095/search/", {
        list: this.allMails,
        field: field,
        criteria: criteria
      });
      this.filteredList = response.data;
      this.currIndex = 1;
      this.isFiltered = true;
      this.paginate(this.filteredList);
    },
    gotoHome() {
      var sel = document.getElementById("check");
      console.log(sel.value);
      this.$router.push({
        name: "user",
        params: { username: this.username, emailAdd: this.email }
      });
    },
    async loadCurrFolderMails() {
      var response = await axios.post(
        "http://localhost:8095/folders/?emailAdd=" + this.emailAdd
      );
      this.folderslist = response.data;
      this.currfolder = this.folderslist[0];
      response = await axios.post("http://localhost:8095/mails/", {
        listname: "UserFolders/" + this.currfolder,
        user: this.emailAdd
      });
      this.allMails = response.data;
      this.isFiltered = false;
      this.currIndex = 1;
      this.paginate(this.allMails);
    }
  },
  created: async function() {
    this.username = this.$route.params.username;
    this.emailAdd = this.$route.params.emailAdd;
    var response = await axios.post(
      "http://localhost:8095/folders/?emailAdd=" + this.emailAdd
    );
    this.folderslist = response.data;
    if (this.folderslist.length != 0) {
      this.currfolder = this.folderslist[0];
      response = await axios.post("http://localhost:8095/mails/", {
        listname: "UserFolders/" + this.currfolder,
        user: this.emailAdd
      });
      this.allMails = response.data;
      this.isFiltered = false;
      this.currIndex = 1;
      this.paginate(this.allMails);
    }
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
.tablink3 {
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
.tablink5 {
  background-color: #555;
  color: white;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 17px;
  width: 33.33333%;
}
.tablink3:hover {
  background-color: #777;
}
.tablink5:hover {
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
.selector {
  text-align: center;
  padding: 5px;
  width: 100%;
}
</style>
