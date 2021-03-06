
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- Content Wrapper. Contains page content -->
	
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>&nbsp;&nbsp;&nbsp;Register yourself for Online Voting.</h1>
			<ol class="breadcrumb">
				<li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Register yourself for Online Voting.</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">

			<div class="row ">
				<div class="col-md-4" style="height: auto;">
					<!-- Profile Image -->
					<div class="box box-primary" style="height: 445px">
						<div class="box-body box-profile">
							<img class="profile-user-img img-responsive img-circle"
								src="<%request.getContextPath();%>/resources/assets/ec/img/register.png">

							<h3 class="profile-username text-center" id="voterIdentificationHeaderId">Voter Identification</h3>
							<span style="color: red; font-weight: bold;">${message}</span>
							
								<div class="form-group has-feedback" style="width: 100%">
									<div class="input-group" >										
											
											<input type="text" class="form-control" name="voterId" id="voterId" placeholder="13 digit Voter-Id"
												data-inputmask="'mask': '99-99-99-999-9999'" data-mask>
												<div class="input-group-addon" style="background-color: blue;" id="voterVerifyBgId">
										            <i><a href="#" id="voterIdVerify" style="color: yellow;"><strong>Verify</strong></a></i>
									            </div>
										</div>
										<!-- /.input group -->
										<span id="voterId_err" style="color:red; font-weight:bold;"></span>
									</div>
									<!-- /.form group -->									
							
								
						
						
						
						<div class="form-group has-feedback" style="width: 100%">
							<div class="input-group" >
							 
								<input type="text" class="form-control"	placeholder="12 digit Aadhar-Id" name="aadharId" id="aadharId" 
								data-inputmask="'mask': '999-999-999-999'" data-mask>
								
								<div class="input-group-addon" style="background-color: grey;" id="aadharBgColorId">
									<i><a href="#" id="aadharIdVerify" style="color: yellow;"><strong>Verify</strong></a></i>
								</div>								
							</div>
							<!-- /.input group -->
							      <span id="aadharId_err" style="color:red; font-weight:bold;"></span>
							</div>
							<!-- /.form group -->
							
                         <strong style="color:red;">OR [ Use Finger-Impression ]</strong>
						<div class="form-group has-feedback" style="width: 100%">
							<div class="input-group">
							  		 <a href="#" id="scanImpressionId"><img class="profile-user-img  img-circle" width="30" height="50"
								src="<%request.getContextPath();%>/resources/assets/ec/img/thumb.jpg" alt="Scan"> &nbsp;&nbsp;<strong >Scan</strong></a> 
								&nbsp;
								<span style="display:none" id="scannedImpressionId"> <img class="profile-user-img  img-circle" width="30" height="50" style="background-color: green"
								src="<%request.getContextPath();%>/resources/assets/ec/img/dummy-thumb.png">&nbsp;&nbsp;<strong >Received</strong></span>
								 <div class="input-group-addon" style="background-color: grey;"
									id="impressionBgColorId">
									<i><a href="#" id="impressionVerifyId"
										style="color: yellow;" ><strong id="impressionVerifyButtonId">Verify</strong></a></i>
								</div>
								
							</div>
							  <!-- /.input group -->
							  <span id="otp_err" style="color:red; font-weight:bold;"></span>
						</div>
						<!-- /.form group -->
											
						<!-- /.col -->



						<div class="form-group has-feedback" style="width: 100%">
							<div class="input-group">
							    
								<input type="text" class="form-control" placeholder="4 digits Aadhar-OTP" data-inputmask="'mask': '9999'" data-mask
									id="otpId" name="otp" id="otpId">
								<div class="input-group-addon" style="background-color: grey;"
									id="otpButtonVerifyBgColorId">
									<i><a href="#" id="aadharOtpVerifyId"
										style="color: yellow;"><strong>Verify</strong></a></i>
								</div>
							</div>
							  <!-- /.input group -->
							  <span id="otp_err" style="color:red; font-weight:bold;"></span>
						</div>
						<!-- /.form group -->
						
						

						
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->


			</div>
				<!-- /.col -->
				<div class="col-md-4"
					style="float: left; width: 25%; height: auto; display: none"
					id="loginDetailsFormId">
					<!-- Profile Image -->
					<div class="box box-primary" style="height: 445px">
						<div class="box-body box-profile">
							<img class="profile-user-img img-responsive img-circle"
								src="<%request.getContextPath();%>/resources/assets/ec/img/register.png">
	
							<h3 class="profile-username text-center">Voter Login Details
							</h3>
	
	
							<span style="color: red; font-weight: bold;">${message}</span>
	
	  
							<div class="form-group has-feedback">
								<input type="text" class="form-control" name="name" id="nameId"
									placeholder="Full name" /> <span
									class="glyphicon glyphicon-user form-control-feedback"></span> <span 
									id="name_err" style="color:red; font-weight:bold;"></span>
							</div>
	
							<div class="form-group has-feedback">
								<input type="email" class="form-control" name="userLoginId"
									id="userLoginId" placeholder="Email" /> <span
									class="glyphicon glyphicon-envelope form-control-feedback"></span>
								<span id="userLoginId_err" style="color:red; font-weight:bold;"></span>
							</div>
	
	
	
							<div class="form-group has-feedback">
								<input type="password" class="form-control" name="password"
									id="passwordId" placeholder="Password" /> <span
									class="glyphicon glyphicon-lock form-control-feedback"></span> <span
									id="password_err" style="color:red; font-weight:bold;"></span>
							</div>
							
							<div class="form-group has-feedback">
								<input type="password" class="form-control" id="passwordConfId"
									name="passwordConf" placeholder="Retype password" /> <span
									class="glyphicon glyphicon-log-in form-control-feedback"></span>
								<span id="passwordConf_err" style="color:red; font-weight:bold;"></span>
							</div>
							<div class="row">
	
								<div class="col-xs-4 pull-right" style="display:block;" id="nextButtonId">
									<button class="btn btn-primary btn-block btn-flat"
										id="submitLoginDetailsId">Next</button>
								</div>
								<!-- /.col -->
							</div>
	
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
	
				<div class="col-md-4"
					style="float: right; width: 25%; height: auto; display: none"
					id="MobileAndSecurityDetailsId">
					<!-- Profile Image -->
					<div class="box box-primary" style="height: 445px">
						<div class="box-body box-profile">
							<img class="profile-user-img img-responsive img-circle"
								src="<%request.getContextPath();%>/resources/assets/ec/img/register.png">
	
							<h3 class="profile-username text-center">Mobile No. & Voting-Secret</h3>
							<span style="color: red; font-weight: bold;">${message}</span>
							
							
								<div class="form-group has-feedback">
								  <div class="input-group">
								    
									<input type="text" class="form-control" name="mobileNo" id="mobileNoId"
										placeholder="Mobile-No for communication" data-inputmask="'mask': '9999999999'" data-mask/> 
										<div class="input-group-addon" id="mobileVerificationButtonBgId" style="background-color: blue; ">
										<i><a href="#" id="mobileVerificationId"><strong id="mobileVerificationButtonId" style="color:yellow;">Verify</strong></a></i>									
									</div>
									</div>
									<span id="mobileNo_err" style="color:red; font-weight:bold;"></span>	
								</div>
								
								
								<div class="form-group has-feedback" style="display:none" id="otpInputId">
								  <div class="input-group">
								    
									<input type="text" class="form-control" name="mobileOtp" id="mobileOtpId"
										placeholder="Mobile OTP please" data-inputmask="'mask': '9999'" data-mask/> 
										<div class="input-group-addon" style="background-color: blue; " id="mobileOtpVerificationButtonBgId">
										<i><a href="#" id="mobileOtpVerifyId"><strong style="color:yellow" id="mobileOtpVerificationButtonId">Verify</strong></a></i>
									</div>
									</div>	
									<span id="mobileOtp_err" style="color:red; font-weight:bold;"></span>
								</div>
	
								<div class="form-group has-feedback">
									<input type="text" class="form-control" name="secret" disabled="disabled"
										id="regsecretId" placeholder="Online-Voting-Account-Secret" /> <span
										class="glyphicon glyphicon-envelope form-control-feedback"></span>
	
									<span id="secret_err" style="color:red; font-weight:bold;"></span>
								</div>
								<br>
								
								<div class="row">
									<div class="col-xs-8">
										<div class="checkbox icheck">
											<label style="margin-left: 22px;"> <input
												type="checkbox">&nbsp;&nbsp; I agree to the <a
												href="terms">terms</a>
											</label>
										</div>
									</div> 
									<!-- /.col -->
									<div class="col-xs-4" style="display:none;" id="registerButtonId">
										<button id="submitRegistrationDetailsId"
											class="btn btn-primary btn-block btn-flat">Register</button>
									</div>
									<!-- /.col -->
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



<!-- page script  -->
<script>
$(function() {
//Disable anchor links
$('#aadharIdVerify').css('pointer-events', 'none');
$('#aadharOtpVerifyId').css('pointer-events', 'none');
$("#scanImpressionId").css('pointer-events', 'none');
$("#impressionVerifyId").css('pointer-events', 'none');


var baseUrl="/pub/hm/reg";

//############################################//
//####### Voter Identification is started #######//
//############################################//


$("#voterIdVerify").click(function() {	
	
    event.preventDefault();		
	var voterId = $("#voterId").val();	
		///Ajax call Started	
			$.ajax({
				type : 'POST',
				url : baseUrl + "/voterIdVerification?voterId="+voterId,		
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(response) {
					//alert("Response is coming");
					$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
					if(response.status){
						//Do something if required	
						$("#voterId_err").html("");//Clean previous error msg if any
						
						//if Voter id is verified . Turn its background green and show action response.               
						$('#voterVerifyBgId').css('background-color', 'green');  

						$("#voterIdVerificationResponseModalId").html(response.statusMsg);            
						$('#showVoterIdVerificationResponse').modal('show');
						
					}else{
						//alert("Form has an error = "+response.statusMsg);
						$("#voterId_err").html(response.statusMsg);
					}
				},
				error : function(jqXHR, exception) {			
					formatErrorMessage(jqXHR, exception);
				}
			});
		///Ajax call ends
});

//Do on click of ok button of modal
$("#voterVerificationOverId").click(function() {
	    
		//Disable voterId Verification once verified               
		$('#voterId').prop("disabled",true);
		$('#voterIdVerify').css('pointer-events', 'none');
		//Enable anchor tag                
		$('#aadharIdVerify').css('pointer-events', 'auto');	
		$("#scanImpressionId").css('pointer-events', 'auto');			
		//Change background of aadhar verification on voter id verifcation success
		$('#aadharBgColorId').css('background-color', 'blue');
		
		
		$('#showVoterIdVerificationResponse').modal('hide');
		//Let him go for Aadhar verification. 
		$('#voterIdVerify').text("Verified");				         
});

//###### Start verifying aadhar id
$("#aadharIdVerify").click(function() {
		var aadharId = $("#aadharId").val();
		//Ajax call Started	
		$.ajax({
			type : 'POST',
			url : baseUrl + "/aadharIdVerification?aadharId="+aadharId,		
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				//alert("Response is coming");
				$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
				if(response.status){
					//Do something if required	
					$("#aadharId_err").html("");//Clean previous error msg if any	
							               
					//IfAadhar id verified . Turn background green                 
					$('#aadharBgColorId').css('background-color', 'green');
					//Show aadhar verification message response	
					$("#aadharVerificationResponseId").html(response.statusMsg)	;			
					$('#showAadharVerificationResponse').modal('show');
					
				}else{
					//alert("Form has an error = "+response.statusMsg);
					$("#aadharId_err").html(response.statusMsg);
				}
			},
			error : function(jqXHR, exception) {			
				formatErrorMessage(jqXHR, exception);
			}
		});
		///Ajax call ends

});

//Do on click of ok button of modal for aadhar response
$("#aadharVerificationOverId").click(function() {
		//Disable aadharId Verification once verified               
		$('#aadharId').prop("disabled",true);
		$('#aadharIdVerify').css('pointer-events', 'none');
		//Change background of aadhar verification on voter id verifcation success
		$('#otpButtonVerifyBgColorId').css('background-color', 'blue');
		//Enable anchor tag  
		$('#aadharOtpVerifyId').css('pointer-events', 'auto');
		//enable otp entry and submit button
		
		//On success of partial Aadhar verification
		$("#aadharVerificationResponseId").html("")	;
		$('#showAadharVerificationResponse').modal('hide');
		$('#aadharIdVerify').text("Verified");
		//Disable thumb impression link
		$("#scanImpressionId").css('pointer-events', 'none');
		

});

//##### Start verifying aadhar id
$("#aadharOtpVerifyId").click(function() {				
		var aadharOtp = $("#otpId").val();		
		
		///Ajax call Started	
		$.ajax({
			type : 'POST',
			url : baseUrl + "/aadharOTPVerification?aadharOtp="+aadharOtp,		
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				//alert("Response is coming");
				$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
				if(response.status){
					//Do something if required	
					$("#otp_err").html("");//Clean previous error msg if any			               
					//Final verification is over . Turn background green                 
					$('#otpButtonVerifyBgColorId').css('background-color', 'green');
					
					//Show aadhar verification message response	
					$("#aadharOtpVerificationResponseId").html(response.statusMsg)	;						
					$('#showVoterIdentificationFinalResponse').modal('show');
					
				}else{
					//alert("Form has an error = "+response.statusMsg);
					$("#otp_err").html(response.statusMsg);
				}
			},
			error : function(jqXHR, exception) {			
				formatErrorMessage(jqXHR, exception);
			}
		});
		///Ajax call ends
});


//Scan thum impression
$("#scanImpressionId").click(function(){
	   $("#scannedImpressionId").attr("style","display:block !important;");
	   $('#impressionBgColorId').css('background-color', 'blue');
	   $("#impressionVerifyId").css('pointer-events', 'auto');
	
});
//Verify thum impression
$("#impressionVerifyId").click(function(){
	 
	///Ajax call Started	
		$.ajax({
			type : 'POST',
			url : baseUrl + "/thumbImpressionVerification?impression="+"",		
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				//alert("Response is coming");
				$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
				if(response.status){
					//Do something if required	
					$("#aadharId").val(response.formObject);
					
							               
					//If Aadhar imression verified . Turn background green 
					$('#aadharBgColorId').css('background-color', 'green');                
					$('#impressionBgColorId').css('background-color', 'green');
					$('#aadharIdVerify').text("Verified");
					$('#impressionVerifyButtonId').text("Verified");

					$("#scanImpressionId").css('pointer-events', 'none');
					$("#impressionVerifyId").css('pointer-events', 'none');
					
					//Show aadhar verification message response	
					$("#aadharVerificationResponseId").html(response.statusMsg)	;					
					$('#showAadharVerificationResponse').modal('show');
					
					
				}else{
					//alert("Form has an error = "+response.statusMsg);
					
				}
			},
			error : function(jqXHR, exception) {			
				formatErrorMessage(jqXHR, exception);
			}
		});
		///Ajax call ends
	
});


//############################################//
//####### Voter Identification is over #######//
//############################################//			

//############################################//
//####### Go for Registration now #######//
//############################################//
$("#GoForRegistrationId").click(function() {
		//Disable aadharId Verification once verified               
		$('#otpId').prop("disabled",true);
		$('#aadharOtpVerifyId').css('pointer-events', 'none');
		//Close aadhar verification message response						
		$('#showVoterIdentificationFinalResponse').modal('hide');
		//Now open Login detail form
		$("#loginDetailsFormId").attr("style","display: block !important;");
		$('#aadharOtpVerifyId').text("Verified");
		
		$("#voterIdentificationHeaderId").css("color","green");

});

			

//Submit your login details
$("#submitLoginDetailsId").click(function(){
		//Clean Error msg
		$("#name_err").html("");
		$("#userLoginId_err").html("");
		$("#password_err").html("");
		$("#passwordConf_err").html("");
		
		var voterId=$("#voterId").val();
		
		var name=$("#nameId").val();
		var userLoginId=$("#userLoginId").val();
		var password=$("#passwordId").val();
		var passwordConf=$("#passwordConfId").val();
		var jsonData={
                 "name" :name,
                 "userLoginId" :userLoginId,
                 "password":password,
                 "passwordConf":passwordConf,
                 "voterId":voterId
				};
		///Ajax call Started	
		$.ajax({
			type : 'POST',
			url : "/pub/hm/login/",	
			data: JSON.stringify(jsonData),
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				//alert("Response is coming");
				$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
				if(response.status){
				$("#nameId").prop("disabled",true);
				$("#userLoginId").prop("disabled",true);
				$("#passwordId").prop("disabled",true);
				$("#passwordConfId").prop("disabled",true);	
				//Now open mobile and security key receiving page
				$('#MobileAndSecurityDetailsId').attr("style","display: block !important;");
				 $("#nextButtonId").attr("style","display: none !important;");
				 $("#nextButtonId").html("Login Details Accepted");				
				}else{
					//alert("Form has an error = "+response.statusMsg);
					$("#name_err").html(response.statusMsg);
					$("#name_err").html(response.fieldErrMsgMap.name);
					$("#userLoginId_err").html(response.fieldErrMsgMap.userLoginId);
					$("#password_err").html(response.fieldErrMsgMap.password);
					$("#passwordConf_err").html(response.fieldErrMsgMap.passwordConf);	
				}
			},
			error : function(jqXHR, exception) {			
				formatErrorMessage(jqXHR, exception);
			}
		});
		///Ajax call ends

});


//Go for final registration 

//Verify mobile
$("#mobileVerificationId").click(function(){

var mobileNo=$("#mobileNoId").val();
///Ajax call Started	
$.ajax({
	type : 'POST',
	url : baseUrl + "/mobileVerification?mobileNo="+mobileNo,		
	beforeSend : function(xhr) {
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	},
	success : function(response) {
		//alert("Response is coming");
		$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
		if(response.status){
			//Do something if required			
			$("#mobileVerificationResponseModalId").modal('show');
			$("#mobileVerificationResponseMsgId").html(response.statusMsg);
			//Open text box for mobile OTP verification
			$("#otpInputId").attr("style","display:block; !Important");
			
		}else{
			//alert("Form has an error = "+response.statusMsg);
			 $("#mobileNo_err").html(response.statusMsg);
			
		}
	},
	error : function(jqXHR, exception) {			
		formatErrorMessage(jqXHR, exception);
	}
});
///Ajax call ends
	
});

$("#mobileVerificationOverId").click(function(){
	$("#mobileVerificationResponseModalId").modal('hide');
	$("#mobileVerificationButtonId").text("Verified");
	$("#mobileVerificationButtonBgId").css('background-color', 'green');
	//Now disable mobile input box and link
	$("#mobileVerificationId").css('pointer-events', 'none');
	$("#mobileNoId").attr("disabled",true);	
});




//Verify mobile
$("#mobileOtpVerifyId").click(function(){
     var mobileOtp=$("#mobileOtpId").val();    
     //mobileOtp
     ///Ajax call Started	
$.ajax({
	type : 'POST',
	url : baseUrl + "/mobileOtpVerification?mobileOtp="+mobileOtp,		
	beforeSend : function(xhr) {
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	},
	success : function(response) {
		//alert("Response is coming");
		$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
		if(response.status){
			//Do something if required			
			$("#mobileOtpVerificationResponseModalId").modal('show');
			$("#mobileOtpResponseMsgId").html(response.statusMsg);
			//Disable otp input
			$("#otpInputId").prop("disabled",true);
			
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

$("#mobileOtpVerificationOverId").click(function(){
	$("#mobileOtpVerificationResponseModalId").modal('hide');
	$("#mobileOtpVerificationButtonId").text("Verified");
	$("#mobileOtpVerificationButtonBgId").css('background-color', 'green');
	//Now disable mobile input box and link
	$("#mobileOtpVerifyId").css('pointer-events', 'none');
	$("#mobileOtpId").attr("disabled",true);
	//Now enable input text box for secret and show register button	
    $("#regsecretId").attr("disabled",false);
    $("#registerButtonId").attr("style","display: block !important;");
	 
});




$("#submitRegistrationDetailsId").click(function(){
	
	var voterId=$("#voterId").val();
	var aadharId=$("#aadharId").val();
	var name=$("#nameId").val();
	var userLoginId=$("#userLoginId").val();
	var password=$("#passwordId").val();
	var passwordConf=$("#passwordConf").val();	
	var mobileNo=$("#mobileNoId").val();
	var secret=$("#regsecretId").val();

	var jsonObj={
              "voterId":voterId,
              "aadharId":aadharId,
              "name":name,
              "userLoginId":userLoginId,
              "password":password,
              "passwordConf":passwordConf,
              "mobileNo":mobileNo,
              "secret":secret
			};
	///Ajax call Started	
	$.ajax({
		type : 'POST',
		url : baseUrl + "/onlineRegisteration",
		data:JSON.stringify(jsonObj),	
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			//alert("Response is coming");
			$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
			if(response.status){
				//Do something if required	
				$("#RegistrationSuccessModalMsgId").html(response.statusMsg);		
				$("#RegistrationSuccessModalId").modal('show');
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
//############################################//
//####### Final Registration is over #######//
//############################################//

//Initialize Maskin
$('[data-mask]').inputmask();			

});//End of $(function()
		
</script>
				
				
<!-- modal for RegistrationSuccessModalId -->
	<div class="modal fade " id="RegistrationSuccessModalId">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Online-Voting-Registration</b> 
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="RegistrationSuccessModalMsgId" style="color:green; font-weight:bold;">
						
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="javascript:window.location.replace('/');"
						>Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->				
				
				

<!-- modal for mobileOtpVerificationResponseModalId -->
	<div class="modal fade " id="mobileOtpVerificationResponseModalId">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Mobile-OTP Verification</b> 
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="mobileOtpResponseMsgId">
						
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="mobileOtpVerificationOverId">Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



<!-- modal for Voter Id verification -->
	<div class="modal fade " id="mobileVerificationResponseModalId">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Mobile-Verification</b> 
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="mobileVerificationResponseMsgId">
						
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="mobileVerificationOverId">Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- modal for Voter Id verification -->
	<div class="modal fade " id="showVoterIdVerificationResponse">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Voter-Id Verification</b> 
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="voterIdVerificationResponseModalId">
						
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="voterVerificationOverId">Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- Modal for showing Aadhar verification -->
	<div class="modal fade " id="showAadharVerificationResponse">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Aadhar-Verification</b>
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="aadharVerificationResponseId">
						
					</p>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="aadharVerificationOverId">Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- Modal for final verification message -->
	<div class="modal fade " id="showVoterIdentificationFinalResponse">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Voter-Identity-Verification</b>
						
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<p id="aadharOtpVerificationResponseId">
						
					</p>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="GoForRegistrationId">Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

