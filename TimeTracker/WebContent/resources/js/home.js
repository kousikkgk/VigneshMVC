$(document).ready(function() {
			// Date Jquery
			$(function() {
				var startDate;
				var endDate;
				var passstDate;
				var passendDate;
				var selectCurrentWeek = function() {
					window.setTimeout(function() {
						$('.week-picker').find('.ui-datepicker-current-day a')
								.addClass('ui-state-active')
					}, 1);
				}
				$('.week-picker').datepicker(
						{
							showOtherMonths : true,
							selectOtherMonths : true,
							firstDay : 1,
							onSelect : function(dateText, inst) {
								var date = $(this).datepicker('getDate');
								startDate = new Date(date.getFullYear(), date
										.getMonth(), date.getDate()
										- date.getDay() + 1);
								endDate = new Date(date.getFullYear(), date
										.getMonth(), date.getDate()
										- date.getDay() + 7);
								var dateFormat = inst.settings.dateFormat
										|| $.datepicker._defaults.dateFormat;
								/*$('#startDate').text(
										$.datepicker.formatDate(dateFormat,
												startDate, inst.settings));
								$('#endDate').text(
										$.datepicker.formatDate(dateFormat,
												endDate, inst.settings));*/
								
								$('#startDate').val(
										$.datepicker.formatDate(dateFormat,
												startDate, inst.settings));
								$('#endDate').val(
										$.datepicker.formatDate(dateFormat,
												endDate, inst.settings));
								$.ajax({
									type : "POST",
									dataType : "json",
									url : "view",
									data : {
										stweek : $('#startDate').val()
									},
									success : function(response) {
										alert("SUCCESS: "+response);
										//console.log(response);
									},
									error : function(error) {
										alert("ERROR: "+error);
									}
								});
								
								
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

//				$('.week-picker .ui-datepicker-calendar tr').live('mousemove',
//						function() {
//							$(this).find('td a').addClass('ui-state-hover');
//						});
//				$('.week-picker .ui-datepicker-calendar tr').live('mouseleave',
//						function() {
//							$(this).find('td a').removeClass('ui-state-hover');
//						});
			});
		/*************************************************************************/
			//show form
			$(".addrow").click(function() {
				if ($('#startDate').val().length === 0) {
					alert("Please choose date");
				} else {
//					$('#timeentryform').reset();
					$('#timeentryform').trigger("reset"); 
					$(".addrow_form").toggle();
					$.ajax({
						type : "POST",
						dataType : "json",
						url : "project",
						data : {
						},
						success : function(response) {
							alert("Success: "+response);
							$.each(response, function(i, item) {
								$('#projectname').append($('<option>', {
									text : item.name
								}));
							});
						},
						error : function(error) {
							alert("ERROR="+error);
						}
					});
				}
				//$('#timeentryform').reset();
			});
			/*
			 * // Project Name Ajax var proName;
			 * $("#datechosser").click(function(e) { $.ajax({ type : "POST",
			 * dataType : "json", url : "project", data : { //get_member :
			 * proName }, success : function(response) { // some action here
			 * $.each(response, function (i, item) { //alert(item);
			 * $('#projectname').append($('<option>', { // value: item.id, text :
			 * item.name })); }); }, error : function(error) { alert(error); }
			 * }); });
			 */
			
			// Project Name Ajax
			var proName;
			$("#projectname").change(function(e) {
				// e.preventDefault();
				proName = $("#projectname option:selected").text();
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "process",
					data : {
						get_member : proName
					},
					success : function(response) {
						// some action here
						$.each(response, function (i, item) {
			   				//alert(item);
			   			    $('#processname').append($('<option>', { 
			   			       // value: item.id,
			   			        text : item.name 
			   			    }));
			   			});  
					},
					error : function(error) {
						alert(error);
					}
				});
			});
			//Process Name from AJAX
			var prosName;
			$("#processname").change(function(e) {
				
				prosName = $("#processname option:selected").text();
				var stDate=$('#startDate').val();
				var endDate= $('#endDate').val();

				$.ajax({
					type : "POST",
					dataType : "json",
					url : "request",
					data : {
						procsName : prosName,stDate : stDate, endDate : endDate
					},
					success : function(response) {
						//some action here
						//alert("Response: "+response);
						$.each(response, function (i, item) {
			   				//alert(item);
			   			    $('#wrkreqname').append($('<option>', { 
			   			       // value: item.id,
			   			        text : item.name 
			   			    }));
			   			});  
					},
					error : function(error) {
						alert("Error: "+error);
					}
				});
			});
			//WrkRequest Name from AJAX
			var reqName;
			$("#wrkreqname").change(function(e) {
				//e.preventDefault();
				//alert("HI");
				reqName = $("#wrkreqname option:selected").text();
				var stDate=$('#startDate').val();
				var endDate= $('#endDate').val();
				
				//alert(prosName+""+stDate+""+endDate);
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "activity",
					data : {
						reqName : reqName,stDate : stDate, endDate : endDate
					},
					success : function(response) {
						$.each(response, function (i, item) {
			   				//alert(item);
			   			    $('#activity').append($('<option>', { 
			   			       // value: item.id,
			   			        text : item.name 
			   			    }));
			   			});  
					},
					error : function(error) {
						alert(error);
					}
				});
			});
			//Activity rom AJAX
			var activity;
			var prosName;
			$("#activity").change(function(e) {
				activity = $("#activity option:selected").text();
				prosName = $("#processname option:selected").text();
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "wrkunit",
					data : {
						activity : activity, process : prosName
					},
					success : function(response) {
						$.each(response, function (i, item) {
			   				//alert(item);
			   			    $('#wrkunit').append($('<option>', { 
			   			       // value: item.id,
			   			        text : item.name 
			   			    }));
			   			});  
						/*var html = '';
		                  var len = response.length;
		                  for(var i=0; i<len; i++){
		                       html += '<option value=' +i+ '">' + response[i].name + '</option>';
		                   }
		                  alert(html);*/
					},
					error : function(error) {
						alert(error);
					}
				});
			});

		});