$(document).ready(function() {
	// Date Jquery
	$(function() {
		var startDate;
		var endDate;
		var passstDate;
		var passendDate;
		var selectCurrentWeek = function() {
			window.setTimeout(function() {
				$('#datechooser').find('.ui-datepicker-current-day a').addClass('ui-state-active')}, 1);
		}
		$('#datechooser').datepicker({
			showOn:"button",
            buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
            buttonImageOnly: true,
			showOtherMonths : true,
			selectOtherMonths : true,
			firstDay : 1,
			onSelect : function(dateText, inst) {
				var date = $(this).datepicker('getDate');
				startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate()- date.getDay() + 1);
				endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate()- date.getDay() + 7);
				var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
				$('#startDate').val($.datepicker.formatDate(dateFormat,startDate, inst.settings));
				$('#endDate').val($.datepicker.formatDate(dateFormat,endDate, inst.settings));
				var stdate=$('#startDate').val();
				$('#datechooser').val(stdate);
				$('#disp-date').text(stdate);
				dataDisp(stdate);	
				functionSelectAjaxCall('project',"","","",'#projectname');
				selectCurrentWeek();
			},
			beforeShowDay : function(date) {
				var cssClass = '';
				if (date >= startDate && date <= endDate)
					cssClass = 'ui-datepicker-current-day';
				return [ true, cssClass ];
				},
				onChangeMonthYear : function(year, month, inst) {
					selectCurrentWeek();
					}
				});

//		$('.week-picker .ui-datepicker-calendar tr').live('mousemove',
//				function() {
//					$(this).find('td a').addClass('ui-state-hover');
//				});
//		$('.week-picker .ui-datepicker-calendar tr').live('mouseleave',
//				function() {
//					$(this).find('td a').removeClass('ui-state-hover');
//				});
	});
	
	function dataDisp(selDate){
		var stdate=$('#startDate').val();
		//getPreviousMonday();
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "dateview",
			data : {
				stweek : selDate
				},
			success : function(response) {
				
				//Display Dynamic Html table 
				dispHtmlTable(response);
				
				//adding class to the selected tr tag
				$('table').find('tr').click(function(){
					if ($("tr").hasClass("selected")){
						$(".selected").removeClass('selected');
					}else{
						$(this).addClass('selected');
					}
		    	});
				var selWeek = new Date(selDate);
//				var lastMon = new Date(lastMonDate);
				var today=new Date();
				
				$('#editrow').unbind().click(function(){
//					if(selWeek.getTime()<today.getTime()-6 || lastMon.getTime()<today.getTime())
//					{
//						alert("true");
//					}
//					else{
						if ($("tr").hasClass("selected")){
							var timeid=$('.selected').attr('id');
							var r = confirm("Are you want to edit the record!");
							if (r == true) {
								actionAjaxCall("editrow",timeid);
								$("#toggle-container").show();
							}
						}
						else{alert("Select atleast one row in the table.");}
//					}
				});
				
				//unbind()-->trigger events ontime
				$('#deleterow').unbind().click(function(){
//						if(selWeek.getTime()<today.getTime() || lastMon.getTime()<today.getTime())
//					{
//						alert("true");
//					}
//					else{
						if ($("tr").hasClass("selected")){	
							var timeid=$('.selected').attr('id');
							var r = confirm("Are you want to Delete the record!");
							if (r == true) {
								actionAjaxCall("deleterow",timeid);
							} 
						}else{alert("Select atleast one row in the table.");}
//					}
				});
			    
			},
			error : function(error) {
				alert("ERROR: "+error);
			}
		});
	}

	
	
	
	
	
	
		function getPreviousMonday()
	{
		//var today = new Date("Thu Oct 26 2019 21:43:27 GMT+0530 (India Standard Time)");
		var today = new Date();
		var prvMon = new Date(today.getFullYear(), today.getMonth(),today.getDate() - today.getDay() - 6);
		var dd=prvMon.getDate();
		var mm=prvMon.getMonth() + 1;
		var yyyy=prvMon.getFullYear();
		var prevMonday=mm+"/"+dd+"/"+yyyy;
		 alert(prevMonday);
	    return prevMonday;
	}
	function dispHtmlTable(response){
		var resLen=response.toString().length;
		alert(resLen);
		var vectorLayersList = $('.table-container');
		$("#entry-table").remove();
		var content = '<table id="entry-table">';
	    content += '<thead><tr>';
	    content += '<th>Time Id</th>';
	    content += '<th colspan="2">Project</th>';
	    content += '<th colspan="2">Process</th>';
	    content += '<th colspan="2">Request</th>';
	    content += '<th colspan="2">Activity</th>';
	    content += '<th colspan="2">Workunit</th>';
	    content += '<th colspan="2">Mon</th>';
	    content += '<th colspan="2">Tue</th>';
	    content += '<th colspan="2">Wed</th>';
	    content += '<th colspan="2">Thu</th>';
	    content += '<th colspan="2">Fri</th>';
	    content += '<th colspan="2">Sat</th>';
	    content += '<th colspan="2">Sun</th>';
	    content += '</tr></thead><tbody>';
		if(resLen==0){
			
    		var msg="No data to display.";
	    	content += '<tr>'; 
	    	content += '<td>' + msg + '</td>';
	        content += '</tr>';
	    }
	    
	    $.each(response, function () {
	    	content += '<tr id="' + this.timeid + '">'; 
	        content += '<td id="timeid">' + this.timeid + '</td>';
	        content += '<td>' + this.projectname + '</td>';
	        content += '<td>' + this.processname + '</td>';
	        content += '<td>' + this.requestname + '</td>';
	        content += '<td>' + this.activity + '</td>';
	        content += '<td>' + this.workunit + '</td>';
	        content += '<td>' + this.mon + '</td>';
	        content += '<td>' + this.tue + '</td>';
	        content += '<td>' + this.wed + '</td>';
	        content += '<td>' + this.thu + '</td>';
	        content += '<td>' + this.fri + '</td>';
	        content += '<td>' + this.sat + '</td>';
	        content += '<td>' + this.sun + '</td>';
	        content += '</tr>';
	        });
	    content += '</tbody></table>';

    // append the table once
    vectorLayersList.append(content);

	}
	function actionAjaxCall(url,timeid){
//		alert("function call");
		
		$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		data:{
			timeiddata : timeid
		},
		success : function(response) {
			if(url!="deleterow"){
				for (var i = 0; i < response.length; i++) {
					$('#timeidtext').val(response[i].timeid);
					$('#timeidhide').val(response[i].timeid);	
					$('#projectname').find("option:gt(0)").remove();
					$('#projectname').append('<option>' + response[i].projectname + '</option>');
//						$('#projectname')
//					    .find('option')
//					    .remove()
//					    .end()
//					    .append('<option value="selval1" selected="selected">'+"Select Project" +'</option>')
//					    .append('<option value="selval2">'+ response[i].projectname +'</option>')
//					    .val('selval1')
//					    .val('selval2');
					$('#processname').find("option").remove();
					$('#processname').append('<option>' + response[i].processname + '</option>');
					$('#wrkreqname').find("option").remove();
					$('#wrkreqname').append('<option>' + response[i].requestname + '</option>');
					$('#activity').find("option").remove();
					$('#activity').append('<option>' + response[i].activity + '</option>');
					$('#wrkunit').find("option").remove();
					$('#wrkunit').append('<option>' + response[i].workunit + '</option>');
					$('#mon').val(response[i].mon);
					$('#tue').val(response[i].tue);
					$('#wed').val(response[i].wed);
					$('#thu').val(response[i].thu);
					$('#fri').val(response[i].fri);
					$('#sat').val(response[i].sat);
					$('#sun').val(response[i].mon);
					
				}
			}else{
				dispHtmlTable(response);
				$('table').find('tr').click(function(){
			    	   $(".selected").removeClass('selected');
			    	   $(this).addClass('selected');
			    	});
			}
		},	
		error : function(error) {
			alert("ERROR: "+error);
		}
	});
	}
	$('#lastweek').unbind().click(function(){
		alert("inside lastweek");
		alert(getPreviousMonday());
		dataDisp(getPreviousMonday());	
		functionSelectAjaxCall('project',"","","",'#projectname');
	});
	
	//show form
	$(".addrow").unbind().click(function() {
		if ($('#startDate').val().length === 0) {
			alert("Please choose date");
		} else {
			$("#toggle-container").toggle();
		}
	});
	
	/*-----------------------------------------------------*/
	
	/*function for Selectbox Ajax Call*/
	
	function functionSelectAjaxCall(urlVal,data1,data2,data3,nxtSelboxId){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : urlVal,
			data : {
				get_member1 : data1, get_member2 : data2,get_member3 : data3
			},
			success : function(response) {
				$(nxtSelboxId).find("option:gt(0)").remove();
				$.each(response, function (i, item) {
	   			    $(nxtSelboxId).append($('<option>', { 
	   			        text : item.name 
	   			    }));
	   			});  
				
			},
			error : function(error) {
				alert(error);
			}
		});
	}
	
	/*Select Boxes Action*/
	
	// Project Name Ajax
	$("#projectname").change(function(e) {
		var projectSel = $("#projectname option:selected").text();
		functionSelectAjaxCall('process',projectSel,"","",'#processname');			
	});
	
	// Process Name from Ajax
	$("#processname").change(function(e) {
		var processSel = $("#processname option:selected").text();
		var stDate=$('#startDate').val();
		var endDate= $('#endDate').val();
		functionSelectAjaxCall('request',processSel,stDate,endDate,'#wrkreqname');
	});
	
	//	Activity Name from Ajax
	$("#wrkreqname").change(function(e) {
		var wrkreqSel = $("#wrkreqname option:selected").text();
		var stDate=$('#startDate').val();
		var endDate= $('#endDate').val();
		functionSelectAjaxCall('activity',wrkreqSel,stDate,endDate,'#activity');
	});
	
	//	Workunit Name from Ajax
	$("#activity").change(function(e) {
		var activitySel = $("#activity option:selected").text();
		var processSel = $("#processname option:selected").text();
		functionSelectAjaxCall('wrkunit',activitySel,processSel,"",'#wrkunit');
	});
	
});