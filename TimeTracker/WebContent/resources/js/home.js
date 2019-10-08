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
						//alert("1:"+response);
						 var html = '';
		                  var len = response.length;
		                  for(var i=0; i<len; i++){
		                       html += '<option value=' +i+ '">' + response[i].name + '</option>';
		                   }
		                  //alert(html);
		                  $('#processname').append(html);
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
						alert("Response: "+response);
						var html = '';
		                  var len = response.length;
		                  for(var i=0; i<len; i++){
		                       html += '<option value=' +i+ '">' + response[i].name + '</option>';
		                   }
		                  alert(html);
		                  $('#wrkreqname').append(html);
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
						var html = '';
		                  var len = response.length;
		                  for(var i=0; i<len; i++){
		                       html += '<option value=' +i+ '">' + response[i].name + '</option>';
		                   }
		                  alert(html);
		                  $('#activity').append(html);
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
						alert("Response "+response);
						var html = '';
		                  var len = response.length;
		                  for(var i=0; i<len; i++){
		                       html += '<option value=' +i+ '">' + response[i].name + '</option>';
		                   }
		                  alert(html);
		                  $('#wrkunit').append(html);
//						$.each(response, function(i, item) {
//							$('#workunit').append($('<option>', {
//								text : item
//							}));
//						});
					},
					error : function(error) {
						alert(error);
					}
				});
			});
			/*//form data from AJAX
			$("#save_but").click(function (e) {
				e.preventDefault();
				//var formData = $(this).closest('form').serializeArray();
				//formData.push({ name: this.name, value: this.value });
				//alert(formData);
				//var result=JSON.stringify($('form').serializeArray());
				var result = $("form").serialize();  //.serializeArray(); 
				var x = [];
				$.each(result, function(i, field) { 
                    x[i]=field.name + ":" + field.value; 
                }); 
				
				//alert(result);
				console.log("REsult:"+result);
				//var userJson = $('#timeentryform').serialize;
				//console.log("User JSon "+userJson);
				//alert(userJson);
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "save",
					data : {
						activity : result
		               
					},
					success : function(response) {
						//some action here
						alert("Response "+response);
						$.each(response, function(i, item) {
							//alert(item);
							$('#work_unit').append($('<option>', {
								text : item
							}));
						});
					},
					error : function(error) {
						alert(error);
					}
				});
			});*/
		});