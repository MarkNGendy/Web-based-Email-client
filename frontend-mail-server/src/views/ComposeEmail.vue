<template>
  <div class="container">
  <form action="action_page.php">
    <div class="row">
      <div class="col-25">
        <label for="fname">To</label>
      </div>
      <div class="col-75">
        <select id="reciever" name="emails" multiple>
          <option value="australia">7amada@gmail</option>
          <option value="canada">Ahmed@gmail.com</option>
          <option value="usa">Abdo@gmail.com</option>
          <option value="australia">7amada@gmail</option>
          <option value="canada">Ahmed@gmail.com</option>
          <option value="usa">Abdo@gmail.com</option>
          <option value="australia">7amada@gmail</option>
          <option value="canada">Ahmed@gmail.com</option>
          <option value="usa">Abdo@gmail.com</option>
        </select>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="lname">Subject</label>
      </div>
      <div class="col-75">
        <input type="text" id="lname" name="lastname" placeholder="Write subject of your email..">
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="subject">Body</label>
      </div>
      <div class="col-75">
        <textarea id="subject" name="subject" placeholder="Write something.." style="height:200px"></textarea>
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
      <input type="submit" value="Send">
    </div>
  </form>
</div>
</template>

<script>
export default {
  
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
input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
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