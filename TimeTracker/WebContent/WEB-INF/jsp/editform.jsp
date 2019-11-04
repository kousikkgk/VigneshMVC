<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<spring:url value="/resources/js/home.js" var="homeJs" />
	<script src="${homeJs}"></script>
</head>
<body>
<div class="container">
		<div class="form-container">
<form id="timeentryform" method="post" action="/TimeTracker/viewdir">
			<c:forEach items="${timeBean}" var="TimeBean">     
   					<h2>${TimeBean.projectname}</h2>
			</c:forEach>
<c:forEach var="student" items="${listStudent}">

<div class="form_outer_container">

	<div class="form_inner_container1">
		<input type="text" name="dispId" value="${student.timeid}" disabled="disabled"/>
		<input type="hidden" name="id" value="${student.timeid}"/>		
		
		<div class="input-group">
			<label for="projectname">Project</label>	
			<select id="projectname" name="projectname" class="form-control">
				<option value=""></option>
				<option>${student.projectname}</option>
			</select>
		</div>
		
		<div class="input-group">
			<label for="processname">Process</label>
			<select id="processname" name="processname" class="form-control">
			<option value=""></option>
			<%-- <option>${student.processname}</option> --%>
		</select>
		</div>
		
		<div class="input-group">
			<label for="wrkreqname">Workrequest</label>
			<select id="wrkreqname" name="requestName" class="form-control">
				<option value=""></option>
				<%-- <option>${student.requestname}</option> --%>
			</select>	
		</div>
		
		<div class="input-group">
			<label for="activity">Activity</label>
			<select id="activity" name="activityName" class="form-control">
				<option value=""></option>
				<%-- <option>${student.activityname}</option> --%>
			</select>
		</div>
		
		<div class="input-group">
			<label for="wrkunit">Work Unit</label>
			<select id="wrkunit" name="wkunitName" class="form-control">
				<option value=""></option>
				<%-- <option>${student.workunitname}</option> --%>
			</select>	
		</div>			
		</div>
		
		<div class="form_inner_container2">
			<div class="input-group">
				<label for="mon">Mon Efforts</label>
				<input type="number" id="mon" class="form-control" name="mon" value="${student.mon}" min="0" max="24"/>
			</div>
			
			<div class="input-group">
				<label for="tue">Tue Efforts</label>
				<input type="number" id="tue" class="form-control" name="tue" value="${student.tue}" min="0" max="24"/>
			</div>
			
			<div class="input-group">
				<input type="number" class="form-control" name="wed" value="${student.wed}" min="0" max="24"/>
			</div>
			
			<div class="input-group">
				<input type="number" class="form-control" name="thu" value="${student.thu}" min="0" max="24"/>
			</div>
			
			<div class="input-group">
				<input type="number" class="form-control" name="fri" value="${student.fri}" min="0" max="24"/>
			</div>
			
			<div class="input-group">
				<input type="number" class="form-control" name="sat" value="${student.sat}" min="0" max="24"/>
			</div>
			
			<div class="input-group">
				<input type="number" class="form-control" name="sun" value="${student.sun}" min="0" max="24"/>
			</div>
		</div>
		<div class="form_inner_container3">
        	<input type="submit" value="Update" />	
		</div>
		</div>
</c:forEach>	
</form>
</div>
</div>
</body>
</html>