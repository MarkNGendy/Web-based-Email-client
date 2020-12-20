<template>
  <div class="contacts">
      <button class="addContact" @click="filter()">Add Contact</button>
      <select class="contactbox" v-model="contacts.userName">
    <option v-for="item in contacts" :key="item.userName">{{item.userName}}</option>
  </select>
  </div>
</template>

<script>
export default {
  data() {
    return {
      contacts:[{'userName':'mark' , 'email':'mark123'} , {'userName' : 'mario' , 'email' : 'mario@gmail'},]
    };
},
}
</script>


<style scoped>
.contactbox {
  border-width: 2px;
  float: middle ;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 18px;
  width: 30%;
  margin-right: 2px;
  margin-left: 2px;
  margin-top: 20px;
  margin-bottom: 2px;
  background-color: #DCE1E3;
  font-style: oblique;
}


.addContact {
  background-color: rgb(49, 167, 112);
  border-radius: 4px;
  border-width: 8px;
  color: white;
  float: right;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 17px;
  width: 15%;
  margin-right: 500px;
  margin-left: 2px;
  margin-top: 20px;
  margin-bottom: 2px;
}

</style>