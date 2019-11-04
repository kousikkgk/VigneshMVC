<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--------------------------------------------------------------------------------------------->
<spring:url value="/resources/css/home.css" var="homeCss" />
<spring:url
	value="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	var="calCss" />
<spring:url
	value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	var="iconCss" />
<spring:url
	value="https://fonts.googleapis.com/css?family=Noto+Serif|Nunito:400,600|Poppins&display=swap"
	var="fontCss" />
<!----------------------------------------------------------------------------------------------------->
<spring:url
	value="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"
	var="jqueryJs" />
<spring:url value="/resources/js/home.js" var="homeJs" />
<spring:url value="https://code.jquery.com/jquery-1.10.2.js"
	var="caljqueryJs" />
<spring:url value="https://code.jquery.com/ui/1.10.4/jquery-ui.js"
	var="calUiJs" />
<!------------------------------------------------------------------------------------------------------>
<link href="${homeCss}" rel="stylesheet" />
<link href="${calCss}" rel="stylesheet" />
<link href="${fontCss}" rel="stylesheet" />
<link href="${iconCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
<script src="${homeJs}"></script>
<script src="${caljqueryJs}"></script>
<script src="${calUiJs}"></script>
<!------------------------------------------------------------------------------------------------------>
<title>Welcome</title>
</head>
<body>
	<div class="header">

		<div class="header-logo">
			<h2 class="header2">${loggedInUser}</h2>
		</div>

		<div class="header-title">
			<h1>Tops Time Tracking System</h1>
		</div>

		<div class="header-logout">
			<a href="logout"><img src="/TimeTracker/resources/img/logout1.png" alt="logout" /></a>
		</div>

	</div>

	<div class="table-container">
  		<%-- <table class="entry-table">
    		<thead>
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
    		</thead>
			<c:forEach var="entries" items="${tabledisplay}">
				<tr class="table_data">
					<td width="50">${entries.projectname}</td>
					<td width="50">${entries.processname}</td>
					<td width="50">${entries.requestname}</td>
					<td width="50">${entries.activityname}</td>
					<td width="50">${entries.workunitname}</td>
					<td width="20">${entries.mon}</td>
					<td width="20">${entries.tue}</td>
					<td width="20">${entries.wed}</td>
					<td width="20">${entries.thu}</td>
					<td width="20">${entries.fri}</td>
					<td width="20">${entries.sat}</td>
					<td width="20">${entries.sun}</td>
				</tr>
			</c:forEach>
			</tbody>
  		</table> --%>
  	</div>
	<form id="timeentryform" method="post" action="save">	

		<div class="action-container">

			<div class="action-container-cte">
					Date : <input type="hidden" id="datechooser">
					<p id="disp-date"></p>
			</div>
			
			
			<div class="action-container-reset">
				<input type="reset" value="Clear Efforts" class="btn save" /> <span
					id="startDate"></span><span id="endDate"></span>
			</div>
			
			<div class="action-container-lastweek">
				<input type="button" value="Last Week Activities" id="lastweek"
					class="btn save" />
			</div>
			
			<div class="action-container-total">
				<h4>Hours Total :</h4>
			</div>

			<div class="action-container-edit">
				<span id="editrow"> <img src="/TimeTracker/resources/img/edit1.png" style="width:20px;"/></span>
			</div>
			
			<div class="action-container-delete">
				<span id="deleterow"> <img src="/TimeTracker/resources/img/delete1.png" style="width:20px;"/></span>	
			</div>
		</div>

		<div class="addrow">
			<h4>Add New / Edit Row</h4>
		</div>
		<div id="toggle-container">
		<div class="form_outer_container">
			<div class="form_inner_container1">
				<input type="hidden" id="timeidtext" name="timeidtext" disabled="disabled" /> 
				<input type="hidden" name="timeidhidden" id="timeidhide" value="0"/>
				
				<div class="floating-label">			
					<select id="projectname" name="projectName" class="floating-select" onclick="this.setAttribute('value', this.value);" value="">
						<option value=""></option>
					</select>
					<span class="highlight"></span>
					<label>Project</label> 
				</div>
				
				<div class="floating-label">
					<select id="processname" name="processName" class="floating-select" class="floating-select" onclick="this.setAttribute('value', this.value);" value="">
						<option value=""></option>
					</select>
					<span class="highlight"></span>
					<label>Process</label> 
				</div>

				<div class="floating-label">
					<select id="wrkreqname" name="requestName" class="floating-select" class="floating-select" onclick="this.setAttribute('value', this.value);" value="">
						<option value=""></option>
					</select>
					<span class="highlight"></span>
					<label>Workrequest</label> 
				</div>

				<div class="floating-label">
					 
					<select id="activity" name="activityName" class="floating-select" class="floating-select" onclick="this.setAttribute('value', this.value);" value="">
						<option value=""></option>
					</select>
					<span class="highlight"></span>
					<label>Activity</label>
				</div>

				<div class="floating-label">
					<select id="wrkunit" name="wrkunitName" class="floating-select" class="floating-select" onclick="this.setAttribute('value', this.value);" value="">
					</select>
					<span class="highlight"></span>
					<label>Work Unit</label>
				</div>
						
			</div>

					<div class="form_inner_container2">
						<div class="floating-label"> 
							<input type="number" id="mon" class="floating-input" name="monEffort" step="any" min="0" max="24" placeholder=" " value="0.0"/>
							<span class="highlight"></span>
							<label>Mon Efforts</label>
						</div>

						<div class="floating-label"> 
							<input type="number" id="tue" class="floating-input" name="tueEffort" step="any" min="0" max="24" placeholder=" " value="0.0"/>
							<span class="highlight"></span>
							<label>Tue Efforts</label>
						</div>

						<div class="floating-label"> 
							<input type="number" id="wed" class="floating-input" name="wedEffort" step="any"  min="0" max="24" placeholder=" " value="0.0"/>
							<span class="highlight"></span>
							<label>Wed Efforts</label>
						</div>

						<div class="floating-label"> 
							<input type="number" id="thu" class="floating-input" name="thuEffort"  step="any" min="0" max="24" placeholder=" " value="0.0"/>
							<span class="highlight"></span>
							<label>Thu Efforts</label>
						</div>

						<div class="floating-label"> 
							<input type="number" id="fri" class="floating-input" name="friEffort" step="any"  min="0" max="24" placeholder=" " value="0.0"/>
							<span class="highlight"></span>
							<label>Fri Efforts</label>
						</div>

						<div class="floating-label"> 
							<input type="number" id="sat" class="floating-input" name="satEffort"  step="any" min="0" max="24" placeholder=" " value="0.0"/>
							<span class="highlight"></span>
							<label>Sat Efforts</label>
						</div>

						<div class="floating-label"> 
							<input type="number" id="sun" class="floating-input" name="sunEffort"  step="any" min="0" max="24" placeholder=" " value="0.0"/>
							<span class="highlight"></span>
							<label>Sun Efforts</label>
						</div>
						
					</div>
					<div class="form_inner_container3">
						<input type="submit" value="Save" />
					</div>
				</div>
			</div>
	</form>
	
</body>
</html>