<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">	
    <title>Login Page</title>
</head>
<body>
<div class="header">
  <img alt="logo" src="/resources/img/logo.png" />
  <h1>Tops Time Tracking System</h1>
</div>
    <%-- <form:form id="loginForm" method="get" action="login" modelAttribute="loginBean">
 			<p class="sign" align="center">Sign in</p>
            <form:label path="username">Enter your user-name</form:label>
            <form:input id="username" name="username" path="username" class="un" placeholder="Employee Id"/><br>
            <form:label path="username">Please enter your password</form:label>
            <form:password id="password" name="password" path="password" class="pass" placeholder="Password"/><br>
            <input type="submit" value="Submit" class="button" />
        </form:form> --%>
        <form id="loginForm" method="post" action="login">
        	<p class="sign" align="center">Sign in</p>
        	<input type="text" id="username" name="username" class="un" placeholder="Employee Id"/><br>
        	<input type="password" id="password" name="password" class="pass" placeholder="Password"/><br>
        	<input type="submit" value="Submit" class="button" />
        </form>
</body>
</html>

