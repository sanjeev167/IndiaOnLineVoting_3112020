<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

	<div class="modal fade " id="showVoterIdForOffline">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"><b style="color: red;">Offline-Voting</b> Identification </h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
				
				
				       <div class="form-group has-feedback" style="width: 100%">
									     <div class="input-group" >								
											<input type="text" class="form-control" name="voteVoterId" id="voteVoterId" placeholder="13 digit Voter-Id"
												data-inputmask="'mask': '99-99-99-999-9999'" data-mask>
												<div class="input-group-addon" style="background-color: blue;" id="voteVoterIdVerifyBgId">
										            <i><a href="#" id="voteVoterIdVerify" style="color: yellow;"><strong id="voteVoterIdButtonId">Verify</strong></a></i>
									            </div>
										</div>
										<!-- /.input group -->
										<span id="voteVoterId_err" style="color:red; font-weight:bold;"></span>
					</div>
				    <!-- /.form group -->
				    <div class="form-group has-feedback" style="width: 100%">
									     <div class="input-group" >								
											<input type="text" class="form-control" name="secret" id="secretId" placeholder="Voting Secret">
												<div class="input-group-addon" style="background-color: grey;" id="secretBgColorId">
										            <i><a href="#" id="secretVerifyId" style="color: yellow;"><strong id="secretButtonId">Verify</strong></a></i>
									            </div>
										</div>
										<!-- /.input group -->
										<span id="secret_err" style="color:red; font-weight:bold;"></span>
					</div>
				    <!-- /.form group -->
				    
				    <div class="form-group" style="width: 100%">
									     <div class="input-group" >	<label>Election:</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;							
											<input type="radio"  name="ecType" value="A" id="ecTypeId" checked="checked">	&nbsp;&nbsp; Assembly
											<input type="radio" name="ecType" value="L" id="ecTypeId">	&nbsp;&nbsp; Loksabha											
										</div>										
					</div>
				    <!-- /.form group -->
				    
					<div class="form-group" style="width: 100%; text-align: center;">
									<label>Year : 2020	</label>								
					</div>
				    <!-- /.form group -->
				    

					<p>In actual, it'll be <span style="font-weight: bold; color: red;">exercised through an EVM</span> </p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="voteVoterVerificationSubmitId">Submit
						Identity Proof</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
	<div class="modal fade " id="showVoterIdForOnline">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"><b style="color: red;">Online-Voting</b> Identification </h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					  <div class="form-group has-feedback" style="width: 100%">
									     <div class="input-group" >								
											<input type="text" class="form-control" name="onlineVoteVoterId" id="onlineVoteVoterId" placeholder="13 digit Voter-Id"
												data-inputmask="'mask': '99-99-99-999-9999'" data-mask>
												<div class="input-group-addon" style="background-color: blue;" id="onlineVoteIdVerifyBgId">
										            <i><a href="#" id="onlineVoteVoterIdVerify" style="color: yellow;"><strong id="onlineVoteVoterIdButtonId">Verify</strong></a></i>
									            </div>
										</div>
										<!-- /.input group -->
										<span id="onlineVoteVoterId_err" style="color:red; font-weight:bold;"></span>
					</div>
				    <!-- /.form group -->
				    <div class="form-group has-feedback" style="width: 100%">
									     <div class="input-group" >								
											<input type="text" class="form-control" name="secretOnline" id="secretOnlineId" placeholder="Voting Secret">
												<div class="input-group-addon" style="background-color: grey;" id="secretOnlineBgColorId">
										            <i><a href="#" id="secretOnlineVerifyId" style="color: yellow;"><strong id="secretOnlineButtonId">Verify</strong></a></i>
									            </div>
										</div>
										<!-- /.input group -->
										<span id="secretOnline_err" style="color:red; font-weight:bold;"></span>
					</div>
				    <!-- /.form group -->
				    <div class="form-group has-feedback" style="width: 100%">
									     <div class="input-group" >								
											<input type="text" class="form-control" name="votingOtp" id="votingOtpId" placeholder="Voting OTP" data-inputmask="'mask': '9999'" data-mask>
												<div class="input-group-addon" style="background-color: grey;" id="votingOtpBgColorId">
										            <i><a href="#" id="votingOtpVerifyId" style="color: yellow;"><strong id="votingOtpButtonId">Verify</strong></a></i>
									            </div> 
										</div>
										<!-- /.input group -->
										<span id="votingOtp_err" style="color:red; font-weight:bold;"></span>
					</div>
				    <!-- /.form group -->
				    
				    <div class="form-group" style="width: 100%">
									     <div class="input-group" >	<label>Election:</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;							
											<input type="radio"  name="ecTypeOnline" value="A" checked="checked">	&nbsp;&nbsp; Assembly
											<input type="radio" name="ecTypeOnline" value="L" >	&nbsp;&nbsp; Loksabha											
										</div>										
					</div>
				    <!-- /.form group -->
				    
				    
					<div class="form-group" style="width: 100%; text-align: center;">
									<label>Year : 2020	</label>								
					</div>
				    <!-- /.form group -->
				    

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="onlineVotingVerificationSubmitId">Submit
						Identity Proof</button>
				</div>
					
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<div class="modal fade " id="athenticationRequiredId">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"><b style="color: red;">Authentication</b> Required </h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<b>First authenticate [<span style="color:red;">Login</span>] yourself.</b>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">OK</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
<header class="main-header">
	<nav class="navbar navbar-static-top">
		<div class="container" style="margin-left: 2%; padding-left: 2px;">
			<div class="navbar-header">
				<img
					src="<%request.getContextPath();%>/resources/assets/ec/img/logo.png"
					height="10%;" width="68%">
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
				<ul class="nav navbar-nav" style=" font-weight: bold">
					<li><a href="http://localhost:8080/"><i class="fa fa-home"></i></a></li>
					<li><a href="/pvt/voter/db"><i class="fa fa-dashboard"></i>&nbsp;Voter</a></li>
					<li><a href="/pvt/adm/db"><i class="fa fa-dashboard"></i>&nbsp;Adm</a></li>
					<li><a href="/pub/vote/result">EC-Result</a></li>
					<li><a href="#" id="offineLinkId">Vote-Offline</a></li>
					<li><a href="/pvt/vote/onlineV" style="color:yellow;">Vote-Online</a></li>
					
					<li><a href="/pvt/voter/lock_vote"><i class="fa fa-lock" style="color:yellow"></i> - Online-Vote</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
			<!-- Navbar Right Menu -->
			<div class="navbar-custom-menu pull-right">
				<ul class="nav navbar-nav">
					<li><a href="/pub/voter/login">Login</a></li>
					<li><a href="/pub/hm/reg/">Reg</a></li>					
				</ul>
			</div>
			<!-- /.navbar-custom-menu -->
		</div>
		<!-- /.container-fluid -->
	</nav>


</header>

<!-- jQuery 3 -->
<script
	src="<%request.getContextPath();%>/resources/assets/bower_components/jquery/dist/jquery.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {

		//Code for opening online voting verification
       var canOnlineBalletBeOpened="<c:out value="${canBeOpened}"/> ";
        if(canOnlineBalletBeOpened==1)
        	$('#showVoterIdForOnline').modal('show');
        $("#showVoterIdForOnline").on("hidden.bs.modal", function () {
        	window.open("/",_self);
        });
   ////////////////////////////////////
		
		//Initialize Maskin
		$('[data-mask]').inputmask();
		//Disable anchor links
		$('#secretVerifyId').css('pointer-events', 'none');
		$('#secretOnlineVerifyId').css('pointer-events', 'none');
		$('#votingOtpVerifyId').css('pointer-events', 'none');
		

		//Disable submit button
		$("#voteVoterVerificationSubmitId").attr("disabled",true);
		$("#onlineVotingVerificationSubmitId").attr("disabled",true);
		
				
		$("#offineLinkId").click(function() {
			$('#showVoterIdForOffline').modal('show');				
		});
		
		$("#onlineLinkId").click(function() {			
			$('#showVoterIdForOnline').modal('show');			
		});

	});

//Offline	
//Voter Id verification
	$("#voteVoterIdVerify").click(function() {			
	    event.preventDefault();		
		var voteVoterId = $("#voteVoterId").val();	
			///Ajax call Started	
				$.ajax({
					type : 'POST',
					url : "/pub/hm/voter/voterIdVerification?voteVoterId="+voteVoterId,		
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Accept", "application/json");
						xhr.setRequestHeader("Content-Type", "application/json");
					},
					success : function(response) {
						//alert("Response is coming");
						$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
						if(response.status){
							//Do something if required	
							$("#voteVoterId_err").html("");//Clean previous error msg if any
							
							//if Voter id is verified . Turn its background green and show action response.               
							$('#voteVoterIdVerifyBgId').css('background-color', 'green'); 
							$('#voteVoterIdButtonId').text("Verified");
							//Disable voterId Verification once verified               
							$('#voteVoterId').prop("disabled",true);
							$('#voteVoterIdVerify').css('pointer-events', 'none');
							//Enable anchor tag                
							$('#secretVerifyId').css('pointer-events', 'auto');											
							//Change background of aadhar verification on voter id verifcation success
							$('#secretBgColorId').css('background-color', 'blue');												
						}else{
							//alert("Form has an error = "+response.statusMsg);
							$("#voteVoterId_err").html(response.statusMsg);
						}
					},
					error : function(jqXHR, exception) {			
						formatErrorMessage(jqXHR, exception);
					}
				});
			///Ajax call ends
	});
//Voter Id verification ends

//Secret Id verification
	$("#secretVerifyId").click(function() {			
	    event.preventDefault();		
		var secretId = $("#secretId").val();	
			///Ajax call Started	
				$.ajax({
					type : 'POST',
					url : "/pub/hm/voter/secretIdVerification?secretId="+secretId,		
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Accept", "application/json");
						xhr.setRequestHeader("Content-Type", "application/json");
					},
					success : function(response) {
						//alert("Response is coming");
						$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
						if(response.status){
							//Do something if required	
							$("#secret_err").html("");//Clean previous error msg if any							
							//if Voter id is verified . Turn its background green and show action response.               
							$('#secretBgColorId').css('background-color', 'green'); 
							//Disable voterId Verification once verified               
							$('#secretId').prop("disabled",true);
							$('#secretVerifyId').css('pointer-events', 'none');
							$('#secretButtonId').text("Verified"); 
							  
							$("#voteVoterVerificationSubmitId").attr("disabled",false);     
																			
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
//Voter Id verification ends

//Now go for offline ballet paper

$("#voteVoterVerificationSubmitId").click(function(){
	//var secretId = $("#secretId").val();
	var voterId = $("#voteVoterId").val();
	var electionType = $("input[name='ecType']:checked").val();
	window.location.replace("/pub/vote/offline?voterId="+voterId+"&electionType="+electionType);
	
	
});

//####################  Online	Voter identification ###################//
//Voter Id verification
	$("#onlineVoteVoterIdVerify").click(function() {			
	    event.preventDefault();		
		var onlineVoteVoterId = $("#onlineVoteVoterId").val();	
			///Ajax call Started	
				$.ajax({
					type : 'POST',
					url : "/pvt/online/voter/voterIdVerification?voteVoterId="+onlineVoteVoterId,		
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Accept", "application/json");
						xhr.setRequestHeader("Content-Type", "application/json");
					},
					success : function(response) {
						//alert("Response is coming");						
						if(response.status){
							//Do something if required	
							$("#onlineVoteVoterId_err").html("");//Clean previous error msg if any
							
							//if Voter id is verified . Turn its background green and show action response.               
							$('#onlineVoteIdVerifyBgId').css('background-color', 'green'); 
							$('#onlineVoteVoterIdButtonId').text("Verified");
							//Disable voterId Verification once verified               
							$('#onlineVoteVoterId').prop("disabled",true);
							$('#onlineVoteVoterIdVerify').css('pointer-events', 'none');
							//Enable anchor tag                
							$('#secretOnlineVerifyId').css('pointer-events', 'auto');											
							//Change background of aadhar verification on voter id verifcation success
							$('#secretOnlineBgColorId').css('background-color', 'blue');												
						}else{
							//alert("Form has an error = "+response.statusMsg);
							$("#onlineVoteVoterId_err").html(response.statusMsg);
						}
					},
					error : function(jqXHR, exception) {			
						formatErrorMessage(jqXHR, exception);
					}
				});
			///Ajax call ends
	});
//Voter Id verification ends

//Secret Id verification
	$("#secretOnlineVerifyId").click(function() {			
	    event.preventDefault();		
		var secretId = $("#secretOnlineId").val();	
		var onlineVoteVoterId = $("#onlineVoteVoterId").val();
		
			///Ajax call Started	
				$.ajax({
					type : 'POST',
					url : "/pvt/online/voter/secretIdVerification?secretId="+secretId+"&voterId="+onlineVoteVoterId,		
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Accept", "application/json");
						xhr.setRequestHeader("Content-Type", "application/json");
					},
					success : function(response) {
						//alert("Response is coming");
						
						if(response.status){
							//Do something if required	
							$("#secretOnline_err").html("");//Clean previous error msg if any							
							//if Voter id is verified . Turn its background green and show action response.               
							$('#secretOnlineBgColorId').css('background-color', 'green'); 
							//Disable voterId Verification once verified               
							$('#secretOnlineId').prop("disabled",true);
							$('#secretOnlineVerifyId').css('pointer-events', 'none');
							$('#secretOnlineButtonId').text("Verified");
							//Now enable mobile otp link and change background color							
							$('#votingOtpVerifyId').css('pointer-events', 'auto');
							$('#votingOtpBgColorId').css('background-color', 'blue'); 
														
						}else{
							//alert("Form has an error = "+response.statusMsg);
							$("#secretOnline_err").html(response.statusMsg);
						}
					},
					error : function(jqXHR, exception) {			
						formatErrorMessage(jqXHR, exception);
					}
				});
			///Ajax call ends
	});
//Voter Id verification ends

//OTP Id verification
	$("#votingOtpVerifyId").click(function() {			
	    event.preventDefault();	 
		var mobileOtp = $("#votingOtpId").val();	
			///Ajax call Started	
				$.ajax({
					type : 'POST',  
					url : "/pvt/online/voter/mobileOtpVerification?mobileOtp="+mobileOtp,		
					beforeSend : function(xhr) {
						xhr.setRequestHeader("Accept", "application/json");
						xhr.setRequestHeader("Content-Type", "application/json");
					},
					success : function(response) {
						//alert("Response is coming");
						
						if(response.status){
							//Do something if required
							$("#votingOtp_err").html("");//Clean previous error msg if any							
							//if Voter id is verified . Turn its background green and show action response.               
							$('#votingOtpBgColorId').css('background-color', 'green'); 
							//Disable voterId Verification once verified               
							$('#votingOtpId').prop("disabled",true);
							$('#votingOtpVerifyId').css('pointer-events', 'none');
							$('#votingOtpButtonId').text("Verified"); 
							  
							$("#onlineVotingVerificationSubmitId").attr("disabled",false);     
																			
						}else{
							//alert("Form has an error = "+response.statusMsg);
							$("#votingOtp_err").html(response.statusMsg);
						}
					},
					error : function(jqXHR, exception) {			
						formatErrorMessage(jqXHR, exception);
					}
				});
			///Ajax call ends
	});
//Voter Id verification ends

//Now go for online ballet paper

$("#onlineVotingVerificationSubmitId").click(function(){	
	var voterId = $("#onlineVoteVoterId").val();
	var electionType = $("input[name='ecTypeOnline']:checked").val();
	window.location.replace("/pvt/vote/online?voterId="+voterId+"&electionType="+electionType);
	
	
});
	
</script>