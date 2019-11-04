<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,600|Poppins:400,500&display=swap" rel="stylesheet">
<title>Login Page</title>
</head>
<body>
	<div class="header">
		<div class="header-img">
			<img alt="logo" src="/TimeTracker/resources/img/logo.png" />
		</div>
		<div class="header-title">
			<h1>Tops Time Tracking System</h1>
		</div>
	</div>
	<form id="loginForm" method="post" action="login">
		<div class="outer-container">
			<div class="inner-container1">
				<img src="/TimeTracker/resources/img/timebg03.png" alt="timetracker">
			</div>
			<div class="inner-container2">
				<h2>Employee Login</h2>

				<div class="floating-label">
					<input type="text" id="username" name="username"
						class="floating-input" placeholder=" " /> <span class="highlight"></span>
					<label>Employee ID</label>
				</div>

				<div class="floating-label">
					<input type="password" id="password" name="password"
						class="floating-input" placeholder=" " /> <span class="highlight"></span>
					<label>Password</label>
				</div>

				<input type="submit" name="submit" value="Login"> 
				<a href="#" data-toggle="modal" data-target="#myModal">Change Password</a>
  <!--button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button-->

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Change Password</h4>
        </div>
        <div class="modal-body">
          <div class="floating-label">
			<input type="password" id="oldpassword" name="oldpassword" class="floating-input" placeholder=" " /> 
            <span class="highlight"></span>
			<label>Old Password</label>
		  </div>
          <div class="floating-label">
			<input type="password" id="newpassword" name="newpassword" class="floating-input" placeholder=" " /> 
            <span class="highlight"></span>
			<label>New Password</label>
		  </div>
          <div class="floating-label">
			<input type="password" id="confpassword" name="confpassword" class="floating-input" placeholder=" " /> 
            <span class="highlight"></span>
			<label>Confirm New Password</label>
		  </div>
		  <input type="submit" name="submit" value="Change Password"> 
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
			</div>
		</div>
	</form>
</body>
<%-- <body>
<div class="header">
	<div class="header-img">
  		<img alt="logo" src="/TimeTracker/resources/img/logo.png" />
  	</div>
  	<div class="header-title">
  		<h1>Tops Time Tracking System</h1>
	</div>
</div>
    <form:form id="loginForm" method="get" action="login" modelAttribute="loginBean">
 			<p class="sign" align="center">Sign in</p>
            <form:label path="username">Enter your user-name</form:label>
            <form:input id="username" name="username" path="username" class="un" placeholder="Employee Id"/><br>
            <form:label path="username">Please enter your password</form:label>
            <form:password id="password" name="password" path="password" class="pass" placeholder="Password"/><br>
            <input type="submit" value="Submit" class="button" />
        </form:form>
        <form id="loginForm" method="post" action="login">
        	<p class="sign" align="center">Sign in</p>
        	<input type="text" id="username" name="username" class="un" placeholder="Employee Id"/><br>
        	<input type="password" id="password" name="password" class="pass" placeholder="Password"/><br>
        	<input type="submit" value="Submit" class="button" />
        </form>
</body> --%>
</html>

