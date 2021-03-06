
	<!-- Full Width Column -->
	
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>&nbsp;&nbsp;&nbsp;&nbsp;Election-Result [ <strong>EC Test Data</strong> ]</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Election-Result</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">

			<div class="row">
				<div class="col-md-3">

					<!-- Profile Image -->
					<div class="box box-primary">
						<div class="box-body box-profile" style="height:450px">
							<img class="profile-user-img img-responsive img-circle"
								src="<%request.getContextPath();%>/resources/assets/ec/img/result.jpg">

							<h3 class="profile-username text-center">
								<input type="radio" name="electionType" value="A" checked="checked" id="assemblyId"/>&nbsp;Assembly <input
									type="radio" name="electionType" value="P" id="loksabhaId" />&nbsp;Parliamentary
							</h3>


							<p class="text-muted text-center">
								Year &nbsp;&nbsp;&nbsp;2020
							</p>

															
											<div class="form-group">										                
										                <select class="form-control select2" style="width: 100%;" id="stateNameId">
										                  <option value="">-Select State-</option>							                  
										                </select>
										   </div>
										   <!-- /.form-group -->
															
											<div class="form-group">										               
										                <select class="form-control select2" style="width: 100%;" id="loksabhaNameId"  disabled="disabled">
										                  <option value="">-Select Loksabha-</option>							                  
										                </select>
										   </div>
										   <!-- /.form-group -->
																
											<div class="form-group">
										                
										                <select class="form-control select2" style="width: 100%;" id="assemblyNameIdId" disabled="disabled">
										                  <option value="">-Select Assembly-</option>							                  
										                </select>
										   </div>
										   <!-- /.form-group -->
								
                             <div style="display: none;" id="searchResultButtonId">
							<a href="#" class="btn btn-primary btn-block" id="showEcResultId"><b>Election-Result</b></a>
							</div>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->




				<div class="col-md-9">
					<!-- Default box -->
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">Election-Result:-- &nbsp;&nbsp;
							   <label>State: &nbsp;&nbsp;</label>[<strong id="stateResultId" style="color:red"></strong> ] &nbsp;&nbsp;
							   <label>Loksabha:</label> &nbsp;&nbsp;[<strong style="color:red" id="loksabhaResultId"></strong>]&nbsp;&nbsp;							   
							   <label>Assembly:</label> &nbsp;&nbsp;
							   [<strong id="assemblyResultId" style="color:red"></strong>]							  						
							</h3>	
										
						</div>	
						<!-- 
						<div class="box-header with-border">
							<h3 class="box-title"><strong>Total-Voters:</strong>&nbsp;[ 1234567 ]&nbsp;&nbsp; <strong>Votes Casted:</strong> &nbsp;[ 1234 ]&nbsp;&nbsp;<strong>Absent:</strong>&nbsp;[ 500 ]&nbsp;&nbsp; <strong>Total-NOTA:</strong>&nbsp;[ 231 ]</h3>	
										
						</div>	
						 -->					
						<div class="box-body">
										<table id="ecResultTableId" width="100%"
											 class="table table-striped table-bordered table-hover table-condensed dt-responsive data-table">
											<thead>
												<tr>
												    <th width="3%">#</th>
												    											   
													<th width="20%">Candidate</th>
													<th width="6%">Party</th>
													<th width="10%">Symbol</th>
													<th width="6%">Offline[V]</th>
													<th width="6%">Online[V]</th>													
													<th width="6%">Total</th>
													<th width="6%">Rank</th>	
												</tr>
											</thead>
											
										
										</table>
									</div>
									<!-- /.box-body -->		
						
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

		</section>
		<!-- /.content -->
	

	<!-- jQuery 3 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	

	<!-- page script -->
	<script>
		$(function() {
			loadGrid("2000","A","10","2","2");//these input has been dummy to get grid data blank at start
			loadStateCombo();

			$("#showEcResultId").click(function(){             
				var yearId="2020";
				var electionTypeId=$("input[name=electionType]:checked").val();
				var stateId=$("#stateNameId").val();
				var loksabhaId=$("#loksabhaNameId").val();
				var assemblyId=$("#assemblyNameIdId").val();
				
				 $("#stateResultId").html($("#stateNameId option:selected").text());
				 $("#loksabhaResultId").html($("#loksabhaNameId option:selected").text());
				 if($("#assemblyNameIdId option:selected").text()!="-Select-")
				 $("#assemblyResultId").html($("#assemblyNameIdId option:selected").text());

				var table = $('#ecResultTableId').DataTable();
				table.ajax.url( "/pub/vote/resultPaginated?yearId="+yearId+
                        "&electionTypeId="+electionTypeId+
                        "&stateId="+stateId+
                        "&loksabhaId="+loksabhaId+
                        "&assemblyId="+assemblyId).load();					
				});//Search



			//Radio button click	
			 $("#assemblyId").click(function(){	
				//Initialize combo
				 $("#stateNameId").val("");
				 $("#loksabhaNameId").val("");
				 $("#assemblyNameIdId").val("");
				 $("#searchResultButtonId").attr("style","display:none;!Important"); 
				 		     
			      $("#loksabhaNameId").attr("disabled", false);
			      $("#assemblyNameIdId").attr("disabled", false);
			   });
              //Radio button click
			   $("#loksabhaId").click(function(){
				 //Initialize combo
					 $("#stateNameId").val("");
					 $("#loksabhaNameId").val("");
					 $("#assemblyNameIdId").val("");
					 $("#searchResultButtonId").attr("style","display:none;!Important"); 

					 
				   $("#loksabhaNameId").attr("disabled", false);
				   $("#assemblyNameIdId").attr("disabled", true);
			   });

			   $("#stateNameId").change(function(){
				   $("#loksabhaNameId").attr("disabled", false);
				   $("#assemblyNameIdId").attr("disabled", true);
			   });

			   $("#loksabhaNameId").change(function(){			 
				   var radioValue=$("input[name=electionType]:checked").val();				  
				   if(radioValue=="A")		 
				        $("#assemblyNameIdId").attr("disabled", false);
				   else					   
					   $("#searchResultButtonId").attr("style","display:block;!Important");      
				   });
				   
		       $("#assemblyNameIdId").change(function(){			       
				   var radioValue=$("input[name=electionType]:checked").val();
				   $("#searchResultButtonId").attr("style","display:block;!Important");
				   
			   });
			
		});//top main function



		//## Code for loading grid ##	
		function loadGrid(yearId,electionTypeId,stateId,loksabhaId,assemblyId) {
			//alert(yearId);alert(electionTypeId);alert(stateId);alert(loksabhaId);alert(assemblyId);	
		
			t = $('#ecResultTableId').DataTable(
					{
						"responsive": true,
						"retrieve" : true,// used for refreshing
						"bAutoWidth" : true,
						//"scrollY" : '110vh',
						//"scrollCollapse" : true,
						"lengthMenu" : [ 5, 10, 15],
						"processing" : true,
						"serverSide" : true,
						"ordering" : true,
						"searching" : true,
						"aaSorting" : [ [ 6, "desc" ]],

						"oLanguage": {
						      "sZeroRecords": "<label style='color:red;'>No election has yet been conducted in this constituency.</label>"
						    },
						"ajax" : {							
							"url" : "/pub/vote/resultPaginated?yearId="+yearId+
							                                "&electionTypeId="+electionTypeId+
							                                "&stateId="+stateId+
							                                "&loksabhaId="+loksabhaId+
							                                "&assemblyId="+assemblyId,
							"type" : "POST",
						},

						"columns" : [
							{
								"searchable" : false,
								"orderable" : false,
								"targets" : 0,
								"render" : function(data, type, full, meta) {
									return meta.row + 1;// Will send row index
								}
							},
							{
								"data" : "candidateId",
								"name" : "candidateId",
								"title" : "Candidate"
							},
							{
								"data" : "votingPartyId",
								"name" : "votingPartyId",
								"title" : "Party"
							},							

							{
		                        "render": function (data, type, JsonResultRow, meta) {
			                       
		                            return '<img height="40" width="40" src="'+JsonResultRow.symbol+'">';
		                        },
		                        "searchable" : false,
		                        "title" : "Symbol",
		                        "searchable" : false,
								"orderable" : false,
		                        //"bVisible":false
		                    },	
		                    {
								"data" : "offlineVotes",
								"name" : "offlineVotes",
								"title" : "Offline[V]",
								"searchable" : false
							},	
							

							{
								"data" : "onlineVotes",
								"name" : "onlineVotes",
								"title" : "Online[V]",
								"searchable" : false
							},	

							{
								"data" : "totalVotes",
								"name" : "totalVotes",
								"title" : "Total",
								"searchable" : false
							},	

							{
								"data" : "rank",
								"name" : "rank",
								"title" : "Rank",
								"searchable" : false,
								"orderable" : false,
							}			 					
						]
					});

			
		}// End of loading grid	

		$('#stateNameId').on('change', function(e) {	
			//This will clean the loksabha combo 
			$('#loksabhaNameId').empty().append('<option  value="">-Select-</option>');	
			loadLoksabhaCombo($('#stateNameId').val(),"");
			$('#assemblyNameIdId').empty().append('<option  value="">-Select-</option>');	
			
		});

		$('#loksabhaNameId').on('change', function(e) {				
			//This will clean the assembly combo
			$('#assemblyNameIdId').empty().append('<option  value="">-Select-</option>');				
			loadAssemblyCombo($('#loksabhaNameId').val(),"");
		});



		function loadStateCombo(){	
			/* stop form from submitting normally */	
			method = 'GET';
			url = "/pvt/master/state" + "/list";	
			$
			.ajax({
				type : method,
				url : url,
				data : {},
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(response) {			
					if(response.status){
						var ele = document.getElementById('stateNameId');	       
				        for (var i = 0; i < response.comboList.length; i++) {		        	
				            // POPULATE SELECT ELEMENT WITH JSON.
				            ele.innerHTML = ele.innerHTML +
				                '<option value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
				        }
						
						//Do something if required			
					}else{
						showBusinessEerror(response.fieldErrMsgMap);
					}
				},
				error : function(jqXHR, exception) {			
					formatErrorMessage(jqXHR, exception);
				}
			});
			
		}

		function loadLoksabhaCombo(id,selectedId){	
			
			/* stop form from submitting normally */	
			method = 'GET';
			url = "/pvt/master/loksabha" + "/list?id=" + id;		
			$
			.ajax({
				type : method,
				url : url,
				data : {},
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(response) {			
					if(response.status){				
								
						var ele = document.getElementById('loksabhaNameId');	       
				        for (var i = 0; i < response.comboList.length; i++) {		        	
				            // POPULATE SELECT ELEMENT WITH JSON.
				        	if(selectedId == response.comboList[i]['id'])
				            ele.innerHTML = ele.innerHTML +
				                '<option selected value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
				        	else
				        		 ele.innerHTML = ele.innerHTML +
					                '<option value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
					        		
				        }
						
						//Do something if required			
					}else{
						
						showBusinessEerror(response.fieldErrMsgMap);
					}
				},
				error : function(jqXHR, exception) {			
					formatErrorMessage(jqXHR, exception);
				}
			});
			
			}
			

		function loadAssemblyCombo(id,selectedId){	
			
			/* stop form from submitting normally */	
			method = 'GET';
			url = "/pvt/master/assembly/" + "list?id=" + id;		
			$
			.ajax({
				type : method,
				url : url,
				data : {},
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(response) {			
					if(response.status){				
								//alert("Response is coming ");
						var ele = document.getElementById('assemblyNameIdId');	       
				        for (var i = 0; i < response.comboList.length; i++) {		        	
				            // POPULATE SELECT ELEMENT WITH JSON.
				        	if(selectedId == response.comboList[i]['id'])
				            ele.innerHTML = ele.innerHTML +
				                '<option selected value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
				        	else
				        		 ele.innerHTML = ele.innerHTML +
					                '<option value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
					        		
				        }
						
						//Do something if required			
					}else{
						
						showBusinessEerror(response.fieldErrMsgMap);
					}
				},
				error : function(jqXHR, exception) {			
					formatErrorMessage(jqXHR, exception);
				}
			});
			
			}
			
	</script>

