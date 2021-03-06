<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<style>
.modal-content  {
    -webkit-border-radius: 0px !important;
    -moz-border-radius: 0px !important;
    border-radius: 0px !important; 
}
.example-modal .modal {
	position: relative;
	top: auto;
	bottom: auto;
	right: auto;
	left: auto;
	display: block;
	z-index: 1;
}

.example-modal .modal {
	background: transparent !important;
}
</style>
</head>
<body>
	<!-- Full Width Column -->

	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Cast Offline-Vote [EVM Voting Sample Test ]</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Cast your Offline-Vote</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-3">
				<!-- Profile Image -->
				<div class="box box-primary">
					<div class="box-body box-profile">
						<img class="profile-user-img img-responsive img-circle"
							src="<%request.getContextPath();%>/resources/assets/ec/img/offlineVote.jpg">
							
						
						
						<p class="text-muted text-center"><br> <label style="font-size: medium;">
							Election-Year:-- &nbsp;&nbsp;&nbsp;<c:out value="${electionDetailDTO.electionYear}"/></label><br>
							 <label style="font-size: medium;">Type:-- &nbsp;&nbsp;&nbsp; General-Election</label>
						
						<h4 class="profile-username text-center">
							<input type="radio" name="electionType" value="A"/>&nbsp;Assembly <input 
								type="radio" name="electionType" value="P"/>&nbsp;Parliamentary
						</h4>
					</p>
					
					     <label style="color:red;">Voter-Voting-Point</label>
						<ul class="list-group list-group-unbordered">
							<li class="list-group-item"><b>State</b> <span
								class="pull-right"><c:out value="${electionDetailDTO.stateName}"/></span></li>
							<li class="list-group-item"><b>Lok Sabha</b><span
								class="pull-right"><c:out value="${electionDetailDTO.loksabhaName}"/></span></li>	
							<li class="list-group-item"><b>Assembly</b><span
								class="pull-right"><c:out value="${electionDetailDTO.assemblyName}"/></span></li>
							
							<li class="list-group-item"><b>Polling-Booth</b><span
								class="pull-right"><c:out value="${electionDetailDTO.pollingBoothName}"/></span></li>
						</ul>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->

			</div>
			<!-- /.col -->
			<div class="col-md-6" style="margin-right: 0px; margin-left: 0px;">
				<!-- Default box -->
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Select your favorite candidate <br><br><label style="color:blue;">
						Vote will be accepted from <br><br>
						<c:out value="${votingTime}"></c:out>
						</label></h3>
					</div>

					<div class="box-body">
						<div style="text-align: center">
						  <label class="pull-left"><span style="color:red;">Loksabha:</span>   <c:out value="${electionDetailDTO.loksabhaName}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						                           <span style="color:red;">Assembly:</span>   <c:out value="${electionDetailDTO.assemblyName}"/>
						  </label> 
							
						</div>
						<div style="text-align: left">
						<label>NOTA </label> <input id="notaId" type="radio" name="vote" class="voteNota voteClass" value="0">&nbsp;&nbsp;<img
								class=" img-circle" width="60" height="60"
								src="<%request.getContextPath();%>/resources/assets/ec/img/nota.png">
						</div>
					</div>	
					<div class="box-body">
												
						<table id="offline" class="table table-striped table-bordered table-hover table-condensed dt-responsive data-table">
							<thead>
								<tr>
									<th>#</th>
									<th>ID</th>
									<th>Candidate</th>
									<th>Party</th>
									<th>Symbol</th>
									<th>Cast-Vote</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>#</th>
									<th>ID</th>
									<th>Candidate</th>
									<th>Party</th>
									<th>Symbol</th>
									<th>Cast-Vote</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
			
			
			
			
			
			
			<div class="col-md-3">

				<!-- Profile Image -->
				<div class="box box-primary">
					<div class="box-body box-profile">
						<img class="profile-user-img img-responsive img-circle"
							src="<%request.getContextPath();%>/resources/assets/dist/img/avatar.png">
						<hr>
						
						<h3 class="profile-username text-center"><c:out value="${voterDetailsDTO.name}"/></h3>
					
						<p class="text-muted text-center">
							Age [ Voter ] &nbsp;&nbsp;&nbsp;<c:out value="${voterDetailsDTO.age}"/>
							<br>Voter-Id:-- <c:out value="${voterDetailsDTO.voterId}"/>
						</p>
						<ul class="list-group list-group-unbordered">
							<li class="list-group-item"><b>Sex</b> <span
								class="pull-right"><c:out value="${voterDetailsDTO.sex}"/></span></li>
							<li class="list-group-item"><b>DOB</b><span
								class="pull-right"><c:out value="${voterDetailsDTO.dob}"/></span></li>
							<li class="list-group-item"><b>Father's Name</b><span
								class="pull-right"><c:out value="${voterDetailsDTO.fatherName}"/></span></li>
							<li class="list-group-item"><b>Address</b><span
								class="pull-right"><c:out value="${voterDetailsDTO.address}"/></span></li>
						</ul>
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


	<div class="modal fade " id="modal-default" >
		<div class="modal-dialog modal-sm " >
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Vote-Selection.</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p>Candidate: <span id="candidateId"></span></p>
					<p>Party: <span id="partyId"></span></p>
					<p>Symbol: <span id="symbolId"></span></p>
				
					<p style="font-weight:bold; color:red;">Attention:-- Can't change once casted vote.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="receiveVoteId">Vote</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

<div class="modal fade " id="voteCastSuccessId" >
		<div class="modal-dialog modal-sm " >
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Vote-Casted.</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p>Your vote has been received successfully. An acknowledgement has been sent at your registered mobile and mail.</p>
				</div>
				<div class="modal-footer">					
					<button type="button" class="btn btn-primary" onclick="javascript:window.open('/','_self')">Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
</div>
	<!-- /.modal -->


	<!-- jQuery 3 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	


	<!-- page script -->
<script>
var electionYear;var electionType;var voterId;var stateNameId;var assemblyNameId;var loksabhaNameId;var pollingBoothNameId;

var candidateSelectedId;
var votingPartyId;
$(function() {	
 electionYear="<c:out value="${electionDetailDTO.electionYear}"/>";	

electionType="<c:out value="${electionDetailDTO.electionType}"/>";	

$("input[name=electionType][value=" + electionType + "]").attr('checked', 'checked');

//Start preserving some values which will be used while submitting the form

voterId="<c:out value="${voterDetailsDTO.voterId}"/>";
stateNameId="<c:out value="${electionDetailDTO.stateNameId}"/>";
assemblyNameId="<c:out value="${electionDetailDTO.assemblyNameId}"/>";
loksabhaNameId="<c:out value="${electionDetailDTO.loksabhaNameId}"/>";
pollingBoothNameId="<c:out value="${electionDetailDTO.pollingBoothNameId}"/>";
loadGrid(voterId,electionType);
				
});//Top function
				
		//## Code for loading grid ##
	
		function loadGrid(voterId,electionType) {
			t = $('#offline').DataTable(
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
						"aaSorting" : [ [ 3, "asc" ]],
						"ajax" : {							
							"url" : "/pub/vote/offlineBalletPaperPaginated?voterId="+voterId+"&electionType="+electionType,
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
								"data" : "assemblyCandidateId",
								"name" : "assemblyCandidateId",
								"title" : "ID",
								"searchable" : false,
								"bVisible" : false, // used for hiding a column
							},							
							
							{
								"data" : "assemblyCandidateName",
								"name" : "assemblyCandidateName",
								"title" : "Candidate"
							},
							{
								"data" : "partyName",
								"name" : "partyName",
								"title" : "Party"
							},							

							{
		                        "render": function (data, type, JsonResultRow, meta) {
		                            return '<img height="50" width="50" src="'+JsonResultRow.symbolName+'">';
		                        },
		                        "searchable" : false,
		                        "title" : "Symbol"
		                    },							

							{
		                    	"render": function (data, type, JsonResultRow, meta) {			                    	
		                            return  '<a href="#" class="voteClass"><input type="radio" name="vote" value="'+JsonResultRow.partyNameId+"#"+JsonResultRow.assemblyCandidateId+'">&nbsp;&nbsp; <img	class="img-circle voteClass" width="50" height="50"	src="<%request.getContextPath();%>/resources/assets/ec/img/vote2.jpg"></a>';
		                        },
		                        "searchable" : false,
		                        "title" : "Cast-Vote"
							}
						]
					});

			
		}// End of loading grid	
		
		
		
		$("#offline").on("click", "input[type=radio]", function() {			
		   var row = $(this).closest("tr");	  
		   $("#candidateId").html("&nbsp;&nbsp;&nbsp;&nbsp;"+row.find('td:eq(1)').text());
		   $("#partyId").html("&nbsp;&nbsp;&nbsp;&nbsp;"+row.find('td:eq(2)').text());
		   var imgPath=row.find('td:eq(3)').find('img').attr('src');
		    candidateSelectedId = $("input[name='vote']:checked").val().split('#')[1];		   
		    votingPartyId=$("input[name='vote']:checked").val().split('#')[0];	   	   
		   $("#symbolId").html("&nbsp;&nbsp;&nbsp;&nbsp;"+'<img src="'+ imgPath +'" class="img-circle" width="50" height="50" />');	  
		   $('#modal-default').modal('show');		  
		});

		
		$(document).on("click",".voteNota",function() {	
			candidateSelectedId="0";					   
			var radioValue = $("input[name='vote']:checked").val();			
            if(radioValue=="0"){             
               $("#candidateId").html("&nbsp;&nbsp;&nbsp;&nbsp;"+"None");
     		   $("#partyId").html("&nbsp;&nbsp;&nbsp;&nbsp;"+"None");
     		   var imgPath="/resources/assets/ec/img/nota.png";     		  
     		   $("#symbolId").html("&nbsp;&nbsp;&nbsp;&nbsp;"+'<img src="'+ imgPath +'" class="img-circle" width="50" height="50" />');	
            }	
            $('#modal-default').modal('show');
            
		});

		//Accept Vote
		$("#receiveVoteId").click(function(){			
             $('#modal-default').modal('hide');           
			var jsonData={
					  "electionYear":electionYear,
					  "electionType":electionType,
                      "stateNameId":stateNameId,                      
                      "loksabhaNameId":loksabhaNameId,
                      "assemblyNameId":assemblyNameId,
                      "pollingBoothNameId":pollingBoothNameId,
                      "voterId":voterId,
                      "votingPartyId":votingPartyId,
                      "selectedCandidateIdForVoting": candidateSelectedId                                         
					}		    
				   ///Ajax call Started	
				 	$.ajax({
				 		type : 'POST',
				 		url : "/pub/vote/receiveVote",
				 		data:JSON.stringify(jsonData),	
				 		beforeSend : function(xhr) {
				 			xhr.setRequestHeader("Accept", "application/json");
				 			xhr.setRequestHeader("Content-Type", "application/json");
				 		},
				 		success : function(response) {
				 			//alert("Response is coming");
				 			$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
				 			if(response.status){				 				
				 				//Do something if required	
				 				$("#voteCastSuccessId").modal('show');
				 			}else{
				 				//alert("Form has an error = "+response.statusMsg);
				 	            $("#secret_err").html(response.fieldErrMsgMap.secret);		 	         
				 			}
				 		},
				 		error : function(jqXHR, exception) {			
				 			formatErrorMessage(jqXHR, exception);
				 		}
				 	});
				 	///Ajax call ends
		});

		
		
	</script>
</body>
</html>
