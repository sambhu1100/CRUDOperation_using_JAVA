<%@page import="DBConnection.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="Entities.User" %>
    <%@ page import="java.util.*" %>
    <%@ page import="DBOperation.GetAllUsers" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crud Operation</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-dark@4/dark.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
</head>
<body>
<div class="container-fluid">
<!-- nav bar -->
<div class="row bg-primary ">
<strong class="text-light py-2 fs-5 text-center">Crud Operation</strong>
</div>
<!-- Input box -->
<div class="row ">
<div class="col-md-4 pt-5 ">
  
      <form class="row justify-content-center needs-validation  border-end border-primary" action="FormValidation" method="post" id="Mainform" novalidate>

        <div class="col-md-8 pb-2">
          <label for="validationCustom01" class="form-label">Name</label>
          <input type="text" class="form-control" id="name" name="name" required>
          <div class="valid-feedback">
            Looks good!
          </div>
          <div class="invalid-feedback">
            Please enter valid name
          </div>
        </div>
        <div class="col-md-8 pb-2">
          <label for="validationCustom02" class="form-label">Username</label>
          <input type="text" class="form-control" id="username" name="username" required>
          <div class="valid-feedback">
            Looks good!
          </div>
          <div class="invalid-feedback">
            Please enter valid username
          </div>
        </div>
        <div class="col-md-8 pb-2">
          <label for="validationCustomUsername" class="form-label">Email</label>
          <div class="input-group has-validation">
            
            <input type="email" class="form-control" id="email" name="email" aria-describedby="inputGroupPrepend" required>
            <div class="invalid-feedback">
              Please enter valid email
            </div>
          </div>
        </div>
        <div class="col-md-8 pb-2">
          <label for="validationCustom03" class="form-label">Phone Number</label>
          <input type="text" class="form-control" id="phone" name="phone" required>
          <div class="invalid-feedback">
            Please valid number at lest 10 digit
          </div>
        </div>
           <input type="hidden" name="uid" id="uid"><!--for formaction change  --> 
        <div class="col-md-8 pb-2">
          <button class="btn btn-primary" type="submit" id="submit">Submit form</button>
        </div>
      
      </form>
      
     

</div>

<!-- List of user -->
<div class="col-sm-8 pt-5  ">
<table class="table table-striped table caption-top">
 <caption>List of users</caption>
<thead>
    <tr >
     <th scope="col">ID</th>
      <th scope="col">Name</th>
      <th scope="col">Username</th>
      <th scope="col" >Email</th>
      <th scope="col">Phone</th>
      <th scope="col">Action</th>
      
    </tr>
    
  </thead>
  <tbody>
<% 
       
	List<User> listofuser=new ArrayList<>();
    GetAllUsers getallusers=new GetAllUsers(DBConnection.getCon());
    listofuser= getallusers.getUser();
  //out.println(listofuser.get(1).getId());
  for(int i=0;i<listofuser.size();i++)
  {
  %>
    <tr id="Edit<%= listofuser.get(i).getId() %>">
      <td><%= listofuser.get(i).getId() %></td>
      <td><%= listofuser.get(i).getName() %></td>
      <td><%= listofuser.get(i).getUsername() %></td>
      <td><%= listofuser.get(i).getEmail() %></td>
      <td><%= listofuser.get(i).getPhone() %></td>
      <td>
	      <div class="d-flex justify-content-around">
	      	<button class="btn btn-primary btn-sm "name="edit_id" value="<%= listofuser.get(i).getId() %>" onclick="Edit(this)">Edit</button>
	        <a href="DeleteServlet?id=<%= listofuser.get(i).getId() %>" class="btn btn-danger btn-sm" >Delete</a>
	      </div>
      </td>
      
    </tr>
    <% } %>
  </tbody>
</table>
</div>

</div>
<%


String msg=(String)session.getAttribute("msg");
%>
<div class="d-none" id="msg"><%=msg%></div>
<%session.removeAttribute("msg"); %>
</body>

<script>
  (() => {
    'use strict'
  
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')
  
    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
      form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }
  
        form.classList.add('was-validated')
      }, false)
    })
  })()
</script>

<script>
function Edit(e){
	
	var id="#Edit"+e.value;
	//alert(id);
	
$(id).each(function () {
	
    var this_row = $(this);
    var Uid = $.trim(this_row.find('td:eq(0)').html());
    var name= $.trim(this_row.find('td:eq(1)').html());
    var uname= $.trim(this_row.find('td:eq(2)').html());
    var email = $.trim(this_row.find('td:eq(3)').html());
    var ph = $.trim(this_row.find('td:eq(4)').html());
    
    $("#name").val(name);
    $("#username").val(uname);
    $("#email").val(email);
    $("#phone").val(ph);
    $("#uid").val(Uid);
    $("#submit").text("Update Form");
    var ServletName=$("#Mainform").attr("action","EditServlet");
    
});
}
</script>
<script>

$(document).readyop(function(){
	debugger;
	var msg=$("#msg").text().trim();
	if(msg !="null" && msg !=""){
		Swal.fire({
			  position: 'center',
			  icon: 'success',
			  title: msg,
			  showConfirmButton: false,
			  timer: 1500
			})
	}
	if(msg =="Registertion Failed" ){
		Swal.fire({
			  position: 'center',
			  icon: 'error',
			  title: msg,
			  showConfirmButton: false,
			  timer: 1500
			})
	}
	if(msg =="delete successfully" ){
		Swal.fire({
			  position: 'center',
			  icon: 'success',
			  title: msg,
			  showConfirmButton: false,
			  timer: 1500
			})
	}
	if(msg =="Delete fail" ){
		Swal.fire({
			  position: 'center',
			  icon: 'error',
			  title: msg,
			  showConfirmButton: false,
			  timer: 1500
			})
	}
	if(msg =="Update Successfully" ){
		Swal.fire({
			  position: 'center',
			  icon: 'success',
			  title: msg,
			  showConfirmButton: false,
			  timer: 1500
			})
	}
	if(msg =="Delete fail" ){
		Swal.fire({
			  position: 'center',
			  icon: 'error',
			  title: msg,
			  showConfirmButton: false,
			  timer: 1500
			})
	}
	
})

</script>
</html>