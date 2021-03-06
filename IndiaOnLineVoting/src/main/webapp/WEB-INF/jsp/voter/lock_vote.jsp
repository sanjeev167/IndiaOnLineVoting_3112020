	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Lock [ <span style="color: red;">OR Unlock</span> ] your	Online-Voting before link is deactivated</h1> 
		<ol class="breadcrumb">
			<li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Lock your Online-Voting</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class="row ">
			<!-- /.col -->
			<div class="col-md-5" style="float: left; display: block">
				<!-- Profile Image -->
				<div class="box box-primary" style="height: 485px;">
					<div class="box-body box-profile">
						<img class="profile-user-img img-responsive img-circle"	src="<%request.getContextPath();%>/resources/assets/ec/img/lock.png">
						<p class="login-box-msg">
							<c:out value="${voteLockinigDTO.name}" />
							<br> <br> <label>Voter-Id:--</label>&nbsp;&nbsp;
							<c:out value="${voteLockinigDTO.voterId}"/>
							<br> <label>Aadhar-Id:--</label>&nbsp;&nbsp;
							<c:out value="${voteLockinigDTO.aadharId}" />
							<br> <label>Vote-Lock-Status:--</label>&nbsp;&nbsp;
							<label style="color: red;"><c:out value="${voteLockinigDTO.lockStatus}" /></label>
						</p>



						<c:set var="lockStatus" scope="session"
							value="${voteLockinigDTO.lockStatus}" />
						<div class="form-group has-feedback text-center">

							<c:if test="${ lockStatus ne 'Locked'}">
								<input type="radio" name=lockStatus value="1">&nbsp;&nbsp;<label>Lock
									for online voting.</label>
							</c:if>
							<c:if test="${ lockStatus eq 'Locked'}">
								<input type="radio" name=lockStatus value="0">&nbsp;&nbsp;<label>Unlock
									for offline voting.</label>
							</c:if></br>
							<span id="lockStatus_err" style="color: red; font-weight: bold;"></span>
							
						</div>
						<div class="form-group has-feedback" style="width: 100%">
							<div class="input-group">
								<input type="text" class="form-control" name="secret"
									id="secretId" placeholder="Your Online-Voting Account-Secret">
								<div class="input-group-addon" style="background-color: blue;"
									id="secretVerifyBgId">
									<i><a href="#" id="secretVerifyId" style="color: yellow;"><strong>Verify</strong></a></i>
								</div>
							</div>
							<!-- /.input group -->
							<span id="secret_err" style="color: red; font-weight: bold;"></span>
						</div>
						<!-- /.form group -->

						<div class="form-group has-feedback" style="width: 100%">
							<div class="input-group">
								<input type="text" class="form-control" name="mobileOtp"
									id="mobileOtpId" placeholder="Supply OTP received at mobile" data-inputmask="'mask': '9999'" data-mask>
								<div class="input-group-addon" style="background-color: grey;"
									id="mobileOtpVerifyBgId">
									<i><a href="#" id="mobileOtpVerify" style="color: yellow;"><strong>Verify</strong></a></i>
								</div>
							</div>
							<!-- /.input group -->
							<span id="mobileOtp_err" style="color: red; font-weight: bold;"></span>
						</div>
						<!-- /.form group -->

						<div class="form-group has-feedback" style="width: 100%">
							<div class="input-group">
								<input type="text" class="form-control" name="mailOtp"
									id="mailOtpId" placeholder="Supply OTP received at mail" 
												data-inputmask="'mask': '9999'" data-mask>
								<div class="input-group-addon" style="background-color: grey;"
									id="mailOtpVerifyBgId">
									<i><a href="#" id="mailOtpVerify" style="color: yellow;"><strong>Verify</strong></a></i>
								</div>
							</div>
							<!-- /.input group -->
							<span id="mailOtp_err" style="color: red; font-weight: bold;"></span>
						</div>
						<!-- /.form group -->
						<!-- /.col -->
						<div class="form-group has-feedback" style="display: none" id="submitButtonId">
							<button class="btn btn-primary btn-block btn-flat" style="width: 100%" id="submitId">Submit</button>
						</div>
						<!-- /.col -->

					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
			<!-- /.col -->
			<div class="col-md-5"
				style="float: left; height: auto; display: none;" id="lockedVoteDetailsId">
				<!-- Profile Image -->
				<div class="box box-primary">
					<div class="box-body box-profile">			
						<img class="profile-user-img img-responsive img-circle"
							src="<%request.getContextPath();%>/resources/assets/ec/img/lock.png">
						<p class="login-box-msg"><c:out value="${voteLockinigDTO.name}" /></p>
						<p class="login-box-msg">
							<label>Voter-Id:--</label>&nbsp;<c:out value="${voteLockinigDTO.voterId}"/>
							<br> <label>Aadhar-Id:--</label>&nbsp;<c:out value="${voteLockinigDTO.aadharId}" /><br>
							<label>Vote-Lock-Status:--</label>&nbsp;&nbsp;
							<label style="color: red;" id="currentVotingStatusId"></label><br>
							<label>Ip-Address-Used:--</label>&nbsp; <label id="ipAddId"></label><br> <label>Lock-Status-Changed on:</label>
							<label id="dateId"></label>
						
						</p>


						<div class="form-group has-feedback text-left"
							style="padding: 10px;">
							<ul>
								
								<li>Now, you are able to cast your vote in <strong
									style="color: red;">all upcoming Assembly and Loksabha
										voting </strong>online only
								</li>
								<li>Vote-Locking status can be changed in future within a
									given time schedule announced by EC.</li>
								<li>Your name will not come in the list of EVM-Voting at
									your polling booth.</li>
							
							</ul>

						</div>


					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>

	</section>
	<!-- /.content -->




<!-- jQuery 3 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"
		type="text/javascript"></script>


<!-- Page specific  javascript -->
<script>

$(function(){ 
	//lockStatus secretId submitButtonId secretVerifyId secretVerifyBgId mobileOtpId mobileOtpVerifyBgId 
	//mobileOtpVerify mailOtpId mailOtpVerifyBgId mailOtpVerify


	//Disable those links which are not required right now.
	$("#mobileOtpVerify").css("pointer-events","none");
	$("#mailOtpVerify").css("pointer-events","none");

	//Disable those input box which are not required right now.
	$("#mobileOtpId").prop("disabled", true);
	$("#mailOtpId").prop("disabled", true);


	var baseUrl="/pvt/voter";


	//############################################//
	//####### Voter Identification is started #######//
	//############################################//


	$("#secretVerifyId").click(function() {			
	    event.preventDefault();	
	   	
		var secret = $("#secretId").val();	
		//Ajax call Started	
		$.ajax({
			type : 'POST',
			url : baseUrl + "/verifyVotingSecret?secret="+secret,		
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				//alert("Response is coming");
				$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
				if(response.status){
					$("#votingSecretSuccessMsgId").html(response.statusMsg);
					$("#votingSecretSuccessModalId").modal('show');
				}else{
					//alert("Form has an error = "+response.statusMsg);
					$("#secret_err").html(response.statusMsg);
				}
			},
			error : function(jqXHR, exception) {			
				formatErrorMessage(jqXHR, exception);
			}
		});
		///Ajax call ends
			
	});

	//Do on click of ok button of modal
	$("#votingSecretVerificationOverId").click(function() {		
			//Secret Verification              
			$('#secretId').prop("disabled",true);
			$('#secretVerifyId').css('pointer-events', 'none');
			$('#secretVerifyId').text("Verified");
			$('#secretVerifyBgId').css('background-color', 'green');			
			
			//Mobile OTP			
			$('#mobileOtpVerifyBgId').css('background-color', 'blue');
			$('#mobileOtpId').prop("disabled",false);
			$("#mobileOtpVerify").css('pointer-events', 'auto');						
			$('#votingSecretSuccessModalId').modal('hide');
			//Clean Error msg
			$("#votingSecretSuccessMsgId").html("");
			$("#secret_err").html("");
						         
	});

	$("#mobileOtpVerify").click(function() {			
	    event.preventDefault();		   	
		var mobileOtp = $("#mobileOtpId").val();	
		//Ajax call Started	
		$.ajax({
			type : 'POST',
			url : baseUrl + "/verifyMobileOtp?mobileOtp="+mobileOtp,		
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				//alert("Response is coming");
				$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
				if(response.status){
					$("#mobileOtpSuccessMsgId").html(response.statusMsg);
					$("#mobileOtpSuccessModalId").modal('show');
				}else{
					//alert("Form has an error = "+response.statusMsg);
					$("#mobileOtp_err").html(response.statusMsg);
				}
			},
			error : function(jqXHR, exception) {			
				formatErrorMessage(jqXHR, exception);
			}
		});
		///Ajax call ends
			
	});

	//Do on click of ok button of modal
	$("#mobileOtpVerificationOverId").click(function() {			
			//Mobile Verification              
			$('#mobileOtpId').prop("disabled",true);
			$('#mobileOtpVerifyId').css('pointer-events', 'none');
			$('#mobileOtpVerify').text("Verified");
			$('#mobileOtpVerifyBgId').css('background-color', 'green');			
			
			//Mail OTP			
			$('#mailOtpVerifyBgId').css('background-color', 'blue');
			$('#mailOtpId').prop("disabled",false);
			$("#mailOtpVerify").css('pointer-events', 'auto');						
			$('#mobileOtpSuccessModalId').modal('hide');
			//Clean Error msg
			$("#mobileOtpSuccessMsgId").html("");
			$('#mobileOtp_err').html("");
						         
	});


	$("#mailOtpVerify").click(function() {			
	    event.preventDefault();	
	   	
		var mailOtp = $("#mailOtpId").val();	
		//Ajax call Started	
		$.ajax({
			type : 'POST',
			url : baseUrl + "/verifyMailOtp?mailOtp="+mailOtp,		
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				//alert("Response is coming");
				$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
				if(response.status){					
					
					$("#mailOtpSuccessMsgId").html(response.statusMsg);
					$("#mailOtpSuccessModalId").modal('show');
				}else{
					//alert("Form has an error = "+response.statusMsg);
					$("#mailOtp_err").html(response.statusMsg);
				}
			},
			error : function(jqXHR, exception) {			
				formatErrorMessage(jqXHR, exception);
			}
		});
		///Ajax call ends
			
	});

	//Do on click of ok button of modal
	$("#mailOtpVerificationOverId").click(function() {			
			//Mail Verification              
			$('#mailOtpId').prop("disabled",true);
			$('#mailOtpVerifyId').css('pointer-events', 'none');
			$('#mailOtpVerify').text("Verified");
			$('#mailOtpVerifyBgId').css('background-color', 'green');			
									
			$('#mailOtpSuccessModalId').modal('hide');
			//Clean Error msg
			$("#mailOtpSuccessMsgId").html("");
			$('#mailOtp_err').html("");
			$("#submitButtonId").attr("style","display:block; !Important");
			//lockStatus_err
						         
	});

//Lock vote
	$('#submitId').click(function(){     
     var lockStatus = $("input[name='lockStatus']:checked").val(); 
     var voterId="<c:out value="${voteLockinigDTO.voterId}" />";      
     var aadharId="<c:out value="${voteLockinigDTO.aadharId}" />"; 
     var onlineVoterAccountId="<c:out value="${voteLockinigDTO.onlineVoterAccountId}" />"; 
    
     var jsonData={
          "voterId":voterId,
          "aadharId":aadharId,
          "lockStatus":lockStatus,
          "onlineVoterAccountId":onlineVoterAccountId
    	     } ;
       
     if(lockStatus=="0" || lockStatus=="1"){
    	 $("#lockStatus_err").html("");
    	//Ajax call Started	
 		$.ajax({
 			type : 'POST',
 			url : baseUrl + "/lockOrUnlockVote",	
 			data:JSON.stringify(jsonData),	
 			beforeSend : function(xhr) {
 				xhr.setRequestHeader("Accept", "application/json");
 				xhr.setRequestHeader("Content-Type", "application/json");
 			},
 			success : function(response) {
 				//alert("Response is coming");
 				$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
 				if(response.status){					
 					$("#currentVotingStatusId").html(response.formObject.lockStatus);
 					$("#ipAddId").html(response.formObject.reqIpAdd);
 					$("#dateId").html(response.formObject.dateOfLocking);
 					$("#lockedVoteDetailsId").attr("style","display: block; !Important"); 					
 				}else{
 					alert("Form has an error = "+response.statusMsg); 					
 				}
 			},
 			error : function(jqXHR, exception) {			
 				formatErrorMessage(jqXHR, exception);
 			}
 		});
 		///Ajax call ends
    	
    	 
    	 

    	 
      }
     else{$("#lockStatus_err").html("Select your option");}
      
	 
     
	});//End of submitId
	

});//Top function

</script>

<!-- modal for  -->
	<div class="modal fade " id="mailOtpSuccessModalId">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Mail OTP Verification</b> 
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="mailOtpSuccessMsgId" style="color:green; font-weight:bold;">
						
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="mailOtpVerificationOverId"
						>Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



<!-- modal for RegistrationSuccessModalId -->
	<div class="modal fade " id="mobileOtpSuccessModalId">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Mobile OTP Verification</b> 
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="mobileOtpSuccessMsgId" style="color:green; font-weight:bold;">
						
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="mobileOtpVerificationOverId"
						>Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


<!-- modal for RegistrationSuccessModalId -->
	<div class="modal fade " id="votingSecretSuccessModalId">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Voting-Secret Verification</b> 
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="votingSecretSuccessMsgId" style="color:green; font-weight:bold;">
						
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="votingSecretVerificationOverId"
						>Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

