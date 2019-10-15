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
	<div class="header">
		<h2 class="header2">Welcome ${loggedInUser}</h2>
	</div>
	<div class="process">
		<input type="text" placeholder="Choose Date" class="week-picker"
			id="datechosser" /> <input type="reset" value="Clear Efforts" /> <span
			id="startDate"></span><span id="endDate"></span> <input type="button"
			value="Last Week Activities" id="lastweek" />
		<h4>Hours Total :</h4>
		<h3>Log out</h3>
	</div>
	<table class="table">
		<tr>
			<th>Project</th>
			<th>Common Process</th>
			<th>Work Request</th>
			<th>Activity</th>
			<th>Work Unit</th>
			<th>Mon</th>
			<th>Tue</th>
			<th>Wed</th>
			<th>Thu</th>
			<th>Fri</th>
			<th>Sat</th>
			<th>Sun</th>
		</tr>
		<c:forEach var="emp" items="${model}">
			<tr class="table_data">
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
	<h4 class="addrow">Add New / Edit Row</h4>
	<div class="addrow_form">
		<form:form id="timeentryform" method="post" action="save"
			modelAttribute="timeEntryBean">
			<div class="form_outer_fields">
				<div class="form_inner_fields">
						<form:select path="projectname" id="projectname"
							name="projectname">
							<form:option value="NONE" label="Select" />
							<form:options items="${projName}" />
						</form:select>
						<form:select path="processName" id="processname">
							<form:option value="NONE" label="Select" />
							<form:options items="${prosName}" />
						</form:select>
						<form:select path="wrkReqName" class="selectclass" id="wrkreqname"
							name="requestName">
							<form:option value="NONE" label="Select" />
							<form:options items="${reqName}" />
						</form:select>
						<form:select path="activity" class="selectclass" id="activity"
							name="activityName">
							<form:option value="NONE" label="Select" />
							<form:options items="${activityName}" />
						</form:select>
						<form:select path="wrkUnit" class="selectclass" id="wrkunit"
							name="wkunitName" placeholder="WorkUnit">
							<form:option value="NONE" label="Select" />
						</form:select>
				</div>
				<div class="form_inner_fields">
						<form:input type="number" name="monEffort" path="monEffort"
							min="0" max="24" placeholder="Monday Efforts" />
						<form:input type="number" name="monEffort" path="tueEffort"
							min="0" max="24" placeholder="Tuesday Efforts" />
						<form:input type="number" name="monEffort" path="wedEffort"
							min="0" max="24" placeholder="Wednesday Efforts" />
						<form:input type="number" name="monEffort" path="thuEffort"
							min="0" max="24" placeholder="Thursday Efforts" />
						<form:input type="number" name="monEffort" path="friEffort"
							min="0" max="24" placeholder="Friday Efforts" />
						<form:input type="number" name="monEffort" path="satEffort"
							min="0" max="24" placeholder="Saturday Efforts" />
						<form:input type="number" name="monEffort" path="sunEffort"
							min="0" max="24" placeholder="Sunday Efforts" />
				</div>
				<div class="form_inner_fields">
					<input type="submit" value="save" id="save_but" name="save" /> <input
						type="submit" value="Submit" class="button" />
				</div>
			</div>
		</form:form>
	</div>
	<div class="footer">
		<p>Copy right @ Tops Market</p>
	</div>
</body>
</html>