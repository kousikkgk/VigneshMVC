<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--------------------------------------------------------------------------------------------->
<spring:url value="/resources/css/home.css" var="homeCss" />
<spring:url value="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" var="calCss"/>
<spring:url value="https://fonts.googleapis.com/css?family=Nunito:400,100" var="fontCss"/>
<!----------------------------------------------------------------------------------------------------->
<spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" var="jqueryJs" />
<spring:url value="/resources/js/home.js" var="homeJs" />
<spring:url value="https://code.jquery.com/jquery-1.10.2.js" var="caljqueryJs"/>	
<spring:url value="https://code.jquery.com/ui/1.10.4/jquery-ui.js" var="calUiJs"/>
<!------------------------------------------------------------------------------------------------------>
<link href="${homeCss}" rel="stylesheet" />
<link href="${calCss}" rel="stylesheet" />
<link href="${fontCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
<script src="${homeJs}"></script>
<script src="${caljqueryJs}"></script>
<script src="${calUiJs}"></script>
<!------------------------------------------------------------------------------------------------------>
<title>Welcome</title>
</head>
<body>
		<h4 class="header2">Welcome ${loggedInUser}</h4>
	<fieldset>
		<table>
			<tr>
				<td class="table"><input type="button" value="Add Row" id="addrow"/></td>
				<td class="table"><input type="text" placeholder="Choose Date" class="week-picker" id="datechosser"></td>
				<span id="startDate"></span><span id="endDate"></span>
				<td class="table"><input type="reset" value="Clear Efforts" /></td>
				<td class="table"><input type="button" value="Last Week Activities" id="lastweek"/></td>
				<td class="table">Hours Total :</td>
				<td id="hourstotal">0</td>
			</tr>
		</table>
		<table border="1">
			<tr style="font-size: 13">
				<td><h4 class="header4">Project</h4></td>
				<td><h4 class="header4">Common Process</h4></td>
				<td><h4 class="header4">Work Request</h4></td>
				<td><h4 class="header4">Activity</h4></td>
				<td><h4 class="header4">Work Unit</h4></td>
				<td><h4 class="header4">Mon</h4></td>
				<td><h4 class="header4">Tue</h4></td>
				<td><h4 class="header4">Wed</h4></td>
				<td><h4 class="header4">Thu</h4></td>
				<td><h4 class="header4">Fri</h4></td>
				<td><h4 class="header4">Sat</h4></td>
				<td><h4 class="header4">Sun</h4></td>
			</tr>
			<c:forEach var="emp" items="${model}">
				<tr style="font-size: 10">
					<td>${emp.projectname}</td>
					<td>${emp.processname}</td>
					<td>${emp.requestname}</td>
					<td>${emp.activityname}</td>
					<td>${emp.workunitname}</td>
					<td>${emp.mon}</td>
					<td>${emp.tue}</td>
					<td>${emp.wed}</td>
					<td>${emp.thu}</td>
					<td>${emp.fri}</td>
					<td>${emp.sat}</td>
					<td>${emp.sun}</td>
				</tr>
			</c:forEach>
</table>	
		<%-- <table>
		<c:forEach var="entry" items="${model}">
			<tr>
				<th><c:out value="${(entry.key)}" /></th>
			</tr>
			<tr>
				<td><c:out value="${entry.value}"/></td>
			</tr>
			</c:forEach>
		</table> --%>
	</fieldset>
	<%-- <table>
	<c:forEach var="entry" items="${model}">
                <tr>
                	<th><c:out value="${(entry.key)}"/></th>
                </tr>	
                <tr>
                	<td><c:out value="${entry.value}"/></td> 
                </tr>
     </c:forEach> 
     </table> --%>
	<%-- <fieldset>
		<table class="tableclass">
			<tr>
				<td><h4 class="header4">Project</h4></td>
				<td><h4 class="header4">Common Process</h4></td>
				<td><h4 class="header4">Work Request</h4></td>
				<td><h4 class="header4">Activity</h4></td>
				<td><h4 class="header4">Work Unit</h4></td>
				<td><h4 class="header4">Mon</h4></td>
				<td><h4 class="header4">Tue</h4></td>
				<td><h4 class="header4">Wed</h4></td>
				<td><h4 class="header4">Thu</h4></td>
				<td><h4 class="header4">Fri</h4></td>
				<td><h4 class="header4">Sat</h4></td>
				<td><h4 class="header4">Sun</h4></td>
			</tr>
			
			</table>
			<table class="emp_list" style="background:#F6F6F6;">
			<c:forEach var="emp" items="${model}">   
			<tr>
				<td>${emp.projectname}</td>
				<td>${emp.processname}</td>
				<td>${emp.requestname}</td>
				<td>${emp.activityname}</td>
				<td>${emp.workunitname}</td>
				<td>${emp.mon}</td>
				<td>${emp.tue}</td>
				<td>${emp.wed}</td>
				<td>${emp.thu}</td>
				<td>${emp.fri}</td>
				<td>${emp.sat}</td>
				<td>${emp.sun}</td>
			</tr>
			</c:forEach> 
			
		</table> 
	</fieldset> --%>
	<form:form id="timeentryform" method="post" action="save" modelAttribute="timeEntryBean">
	<div class="inline" id="process">
	<fieldset>
				<legend class="section">Project Name</legend>
				<form:select path="projectname" id="projectname" name="projectname">
					<form:option value="NONE" label="Select" />
					<form:options items="${projName}" />
				</form:select>
				<br>
				<legend class="section">Process Name</legend>
				<form:select path="processName" id="processname">
					<form:option value="NONE" label="Select" />
					<form:options items="${prosName}" />
				</form:select>
				<br>
				<legend class="section">Request Name</legend>
				<form:select path="wrkReqName" class="selectclass" id="wrkreqname"
					name="requestName">
					<form:option value="NONE" label="Select" />
					<form:options items="${reqName}" />
				</form:select>
				<br>
				<legend class="section">Activity</legend>
				<form:select path="activity" class="selectclass" id="activity" name="activityName">
					<form:option value="NONE" label="Select" />
					<form:options items="${activityName}" />
				</form:select>
				<br>
				<legend class="section">Work Unit</legend>
				<form:select path="wrkUnit" class="selectclass" id="wrkunit" name="wkunitName" placeholder="WorkUnit">
					<form:option value="NONE" label="Select" />
				</form:select>
			</fieldset>
	</div>
	<div class="inline" id="time">
	<fieldset>
			<form:input type="number" name="monEffort" path="monEffort" min="0" max="24"
			placeholder="Monday Efforts" /><br>
			<form:input type="number" name="monEffort" path="tueEffort" min="0" max="24"
			placeholder="Tuesday Efforts" /><br>
			<form:input type="number" name="monEffort" path="wedEffort" min="0" max="24"
			placeholder="Wednesday Efforts" /><br>
			<form:input type="number" name="monEffort" path="thuEffort" min="0" max="24"
			placeholder="Thursday Efforts" /><br>
			<form:input type="number" name="monEffort" path="friEffort" min="0" max="24"
			placeholder="Friday Efforts" /><br>
			<form:input type="number" name="monEffort" path="satEffort" min="0" max="24"
			placeholder="Saturday Efforts" /><br>
			<form:input type="number" name="monEffort" path="sunEffort" min="0" max="24"
			placeholder="Sunday Efforts" /><br>
	</fieldset>
	</div>
	<div class="form-buttons">
		<!-- <input type="submit" value="save" id="save_but" name="save"/> -->
		<input type="submit" value="Submit" class="button" />
	</div>
	</form:form>
</body>
</html>