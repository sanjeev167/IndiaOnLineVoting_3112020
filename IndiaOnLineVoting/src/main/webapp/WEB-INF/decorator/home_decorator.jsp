<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- <meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval()+1 %>;"> -->

<title>IndiaOnLineVoting</title>

<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/bower_components/Ionicons/css/ionicons.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/bower_components/bootstrap-daterangepicker/daterangepicker.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/plugins/iCheck/all.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
<!-- Bootstrap time Picker -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/plugins/timepicker/bootstrap-timepicker.min.css">
<!-- Select2 -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/bower_components/select2/dist/css/select2.min.css">



<!-- Data Tables -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">





<!-- Theme style -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/dist/css/skins/_all-skins.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->




<!-- Google Font sidebar-collapse-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

<style type="text/css">
.field-error {
	color: red;
	font-size: small;
}

.marquee {
	width: 100%;
	overflow: hidden;
	border: 1px solid #ccc;
	background: #ccc;
	color: green;
	font-weight: bold;
}
</style>
</head>

<body class="hold-transition skin-blue  sidebar-mini fluid">
	<div class="wrapper">
		<header class="main-header">
			<%@ include file="includes/headerH.jsp"%>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<%@ include file="includes/left_menuHome.jsp"%>
		</aside>
		<!-- Content Wrapper. Contains page content -->
		<div style="background-color: white; height: 40px; font-size: large;"
			class="marquee" data-duplicated='true' data-direction='right'
			data-pauseOnHover="true" id="marqueeId">No bulletin has been published today</div>
		<div class="content-wrapper">
			<sitemesh:write property="body" />
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="includes/footer.jsp"%>

		<!-- Control Sidebar -->
		<%@ include file="includes/rightMenu.jsp"%>
	</div>
	<!-- ./wrapper -->




	<!-- Select2 -->
	<script
		src="${contextPath}/resources/assets/bower_components/select2/dist/js/select2.full.min.js"></script>
	<!-- InputMask -->
	<script
		src="${contextPath}/resources/assets/plugins/input-mask/jquery.inputmask.js"></script>
	<script
		src="${contextPath}/resources/assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script
		src="${contextPath}/resources/assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<!-- date-range-picker -->
	<script
		src="${contextPath}/resources/assets/bower_components/moment/min/moment.min.js"></script>
	<script
		src="${contextPath}/resources/assets/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script
		src="${contextPath}/resources/assets/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="${contextPath}/resources/assets/bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
	<!-- bootstrap time picker -->
	<script
		src="${contextPath}/resources/assets/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<!-- SlimScroll -->
	<script
		src="${contextPath}/resources/assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- iCheck 1.0.1 -->
	<script
		src="${contextPath}/resources/assets/plugins/iCheck/icheck.min.js"></script>
	<!-- FastClick -->
	<script
		src="${contextPath}/resources/assets/bower_components/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="${contextPath}/resources/assets/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="${contextPath}/resources/assets/dist/js/demo.js"></script>




	<!-- DataTables -->
	<script
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
	<script
		src="${contextPath}/resources/assets/pageSpecific/js/jQueryGridFeature.js"></script>


	<!-- page script -->

	<script>
		//alert("Decorator");
		//This function is used to keep your selected link active and remain opened the tree
		$(function() {
			var url = window.location;

			// for sidebar menu entirely but not cover treeview
			$('ul.sidebar-menu a').filter(function() {
				return this.href == url;
			}).parent().addClass('active');

			// for treeview
			$('ul.treeview-menu a').filter(function() {
				return this.href == url;
			}).parentsUntil(".sidebar-menu > .treeview-menu")
					.addClass('active');
		});

		//This is required for make button click on left menu
		$(document).ready(function() {
			//This is required for loading tree via ajax
			$('[data-widget="tree"]').each(function() {
				$.fn.tree.call($(this));
			});
			//

		});

		//at bottom/footer of the page
		$(document).ready(function() {
			$(document).trigger("resize");

		});

		//this will handle an ajax call error when the session is expired.
		//var loginPageLink = '/admin/pub/login?expiredThroughAjax';//for testing only	
		var loginPageLink = '/admin/pub/login?expired';
		/*$(document).ajaxError(function (event, xhr) {		
		if(xhr.status=="440"){
		//alert("Some message...........");
		document.location.href = loginPageLink;
		}});*/

		//This will be used for formatting error
		function formatErrorMessage(jqXHR, exception) {
			//alert("Error Code coming  = "+jqXHR.status);
			if (jqXHR.status === 440) {
				document.location.href = loginPageLink;
			} else if (jqXHR.status === 0) {
				// return ('Not connected.\nPlease verify your network connection.');
				document.location.href = "/ce/403";
			} else if (jqXHR.status == 404) {
				return ('The requested page not found. [404]');
			} else if (jqXHR.status == 500) {
				return ('Internal Server Error [500].');
			} else if (exception === 'parsererror') {
				return ('Requested JSON parse failed.');
			} else if (exception === 'timeout') {
				return ('Time out error.');
			} else if (exception === 'abort') {
				return ('Ajax request aborted.');
			} else {
				return ('Uncaught Error.\n' + jqXHR.responseText);
			}
		}
	</script>

	<!-- Page script -->
	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()

			//Datemask dd/mm/yyyy
			$('#datemask').inputmask('dd/mm/yyyy', {
				'placeholder' : 'dd/mm/yyyy'
			})
			//Datemask2 mm/dd/yyyy
			$('#datemask2').inputmask('mm/dd/yyyy', {
				'placeholder' : 'mm/dd/yyyy'
			})
			//Money Euro
			$('[data-mask]').inputmask()

			//Date range picker
			$('#reservation').daterangepicker()
			//Date range picker with time picker
			$('#reservationtime').daterangepicker({
				timePicker : true,
				timePickerIncrement : 30,
				format : 'MM/DD/YYYY h:mm A'
			})
			//Date range as a button
			$('#daterange-btn').daterangepicker(
					{
						ranges : {
							'Today' : [ moment(), moment() ],
							'Yesterday' : [ moment().subtract(1, 'days'),
									moment().subtract(1, 'days') ],
							'Last 7 Days' : [ moment().subtract(6, 'days'),
									moment() ],
							'Last 30 Days' : [ moment().subtract(29, 'days'),
									moment() ],
							'This Month' : [ moment().startOf('month'),
									moment().endOf('month') ],
							'Last Month' : [
									moment().subtract(1, 'month').startOf(
											'month'),
									moment().subtract(1, 'month')
											.endOf('month') ]
						},
						startDate : moment().subtract(29, 'days'),
						endDate : moment()
					},
					function(start, end) {
						$('#daterange-btn span').html(
								start.format('MMMM D, YYYY') + ' - '
										+ end.format('MMMM D, YYYY'))
					})

			//Date picker
			$('#datepicker').datepicker({
				autoclose : true
			})

			//iCheck for checkbox and radio inputs
			$('input[type="checkbox"].minimal, input[type="radio"].minimal')
					.iCheck({
						checkboxClass : 'icheckbox_minimal-blue',
						radioClass : 'iradio_minimal-blue'
					})
			//Red color scheme for iCheck
			$(
					'input[type="checkbox"].minimal-red, input[type="radio"].minimal-red')
					.iCheck({
						checkboxClass : 'icheckbox_minimal-red',
						radioClass : 'iradio_minimal-red'
					})
			//Flat red color scheme for iCheck
			$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
					.iCheck({
						checkboxClass : 'icheckbox_flat-green',
						radioClass : 'iradio_flat-green'
					})

			//Colorpicker
			$('.my-colorpicker1').colorpicker()
			//color picker with addon
			$('.my-colorpicker2').colorpicker()

			//Timepicker
			$('.timepicker').timepicker({
				showInputs : false
			})
		})
	</script>






	<!-- Modals of voter identification  -->

	<div class="modal fade " id="showVoterIdForOffline">
		<div class="modal-dialog modal-sm ">
			<div class="modal-content" style="border-radius: 10px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<b style="color: red;">Offline-Voting</b> Identification
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">


					<div class="form-group has-feedback" style="width: 100%">
						<div class="input-group">
							<input type="text" class="form-control" name="voteVoterId"
								id="offLineVoteVoterId" placeholder="13 digit Voter-Id"
								data-inputmask="'mask': '99-99-99-999-9999'" data-mask>
							<div class="input-group-addon" style="background-color: blue;"
								id="offLineVoteVoterIdVerifyBgId">
								<i><a href="#" id="offlineVoteVoterIdVerify"
									style="color: yellow;"><strong
										id="offlineVoteVoterIdButtonId">Verify</strong></a></i>
							</div>
						</div>
						<!-- /.input group -->
						<span id="voteVoterId_err" style="color: red; font-weight: bold;"></span>
					</div>
					<!-- /.form group -->
					<div class="form-group has-feedback" style="width: 100%">
						<div class="input-group">
							<input type="text" class="form-control" name="secret"
								id="offLineSecretId" placeholder="Voting Secret">
							<div class="input-group-addon" style="background-color: grey;"
								id="offLineSecretBgColorId">
								<i><a href="#" id="offLineSecretVerifyId"
									style="color: yellow;"><strong id="offLineSecretButtonId">Verify</strong></a></i>
							</div>
						</div>
						<!-- /.input group -->
						<span id="secret_err" style="color: red; font-weight: bold;"></span>
					</div>
					<!-- /.form group -->

					<div class="form-group" style="width: 100%">
						<div class="input-group">
							<label>Election:</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="ecType" value="A" id="ecTypeId"
								checked="checked"> &nbsp;&nbsp; Assembly <input
								type="radio" name="ecType" value="L" id="ecTypeId">
							&nbsp;&nbsp; Loksabha
						</div>
					</div>
					<!-- /.form group -->

					<div class="form-group" style="width: 100%; text-align: center;">
						<label>Year : 2020 </label>
					</div>
					<!-- /.form group -->


					<p>
						In actual, it'll be <span style="font-weight: bold; color: red;">exercised
							through an EVM</span>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="offLineVoteVoterVerificationSubmitId">Submit Identity
						Proof</button>
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
					<h4 class="modal-title">
						<b style="color: red;">Online-Voting</b> Identification
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<div class="form-group has-feedback" style="width: 100%">
						<div class="input-group">
							<input type="text" class="form-control" name="onlineVoteVoterId"
								id="onlineVoteVoterId" placeholder="13 digit Voter-Id"
								data-inputmask="'mask': '99-99-99-999-9999'" data-mask>
							<div class="input-group-addon" style="background-color: blue;"
								id="onlineVoteIdVerifyBgId">
								<i><a href="#" id="onlineVoteVoterIdVerify"
									style="color: yellow;"><strong
										id="onlineVoteVoterIdButtonId">Verify</strong></a></i>
							</div>
						</div>
						<!-- /.input group -->
						<span id="onlineVoteVoterId_err"
							style="color: red; font-weight: bold;"></span>
					</div>
					<!-- /.form group -->
					<div class="form-group has-feedback" style="width: 100%">
						<div class="input-group">
							<input type="text" class="form-control" name="secretOnline"
								id="secretOnlineId" placeholder="Voting Secret">
							<div class="input-group-addon" style="background-color: grey;"
								id="secretOnlineBgColorId">
								<i><a href="#" id="secretOnlineVerifyId"
									style="color: yellow;"><strong id="secretOnlineButtonId">Verify</strong></a></i>
							</div>
						</div>
						<!-- /.input group -->
						<span id="secretOnline_err" style="color: red; font-weight: bold;"></span>
					</div>
					<!-- /.form group -->
					<div class="form-group has-feedback" style="width: 100%">
						<div class="input-group">
							<input type="text" class="form-control" name="votingOtp"
								id="votingOtpId" placeholder="Voting OTP"
								data-inputmask="'mask': '9999'" data-mask>
							<div class="input-group-addon" style="background-color: grey;"
								id="votingOtpBgColorId">
								<i><a href="#" id="votingOtpVerifyId" style="color: yellow;"><strong
										id="votingOtpButtonId">Verify</strong></a></i>
							</div>
						</div>
						<!-- /.input group -->
						<span id="votingOtp_err" style="color: red; font-weight: bold;"></span>
					</div>
					<!-- /.form group -->

					<div class="form-group" style="width: 100%">
						<div class="input-group">
							<label>Election:</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="ecTypeOnline" value="A" checked="checked">
							&nbsp;&nbsp; Assembly <input type="radio" name="ecTypeOnline"
								value="L"> &nbsp;&nbsp; Loksabha
						</div>
					</div>
					<!-- /.form group -->


					<div class="form-group" style="width: 100%; text-align: center;">
						<label>Year : 2020 </label>
					</div>
					<!-- /.form group -->


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="onlineVotingVerificationSubmitId">Submit Identity
						Proof</button>
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
					<h4 class="modal-title">
						<b style="color: red;">Authentication</b> Required
					</h4>
				</div>
				<div class="modal-body " style="border-radius: 10px;">
					<b>First authenticate [<span style="color: red;">Login</span>]
						yourself.
					</b>
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

	<!-- Javascript of offline and online voter identification  -->
	<script type="text/javascript">
		$(document).ready(function() {

			//Code for opening online voting verification
			var canOnlineBalletBeOpened = "<c:out value="${canBeOpened}"/> ";
			if (canOnlineBalletBeOpened == 1)
				$('#showVoterIdForOnline').modal('show');
			$("#showVoterIdForOnline").on("hidden.bs.modal", function() {
				window.open("/", _self);
			});
			////////////////////////////////////

			//Initialize Maskin
			$('[data-mask]').inputmask();
			//Disable anchor links
			$('#offLineSecretVerifyId').css('pointer-events', 'none');
			$('#secretOnlineVerifyId').css('pointer-events', 'none');
			$('#votingOtpVerifyId').css('pointer-events', 'none');

			//Disable submit button
			$("#offLineVoteVoterVerificationSubmitId").attr("disabled", true);
			$("#onlineVotingVerificationSubmitId").attr("disabled", true);

			$("#offineLinkId").click(function() {
				$('#showVoterIdForOffline').modal('show');
			});

			$("#onlineLinkId").click(function() {
				$('#showVoterIdForOnline').modal('show');
			});

		});

		//Offline	
		//Voter Id verification
		$("#offlineVoteVoterIdVerify")
				.click(
						function() {
							//alert("Going to verify");
							event.preventDefault();
							var voteVoterId = $("#offLineVoteVoterId").val();
							//alert(voteVoterId);
							///Ajax call Started	
							$
									.ajax({
										type : 'POST',
										url : "/pub/hm/voter/voterIdVerification?voteVoterId="
												+ voteVoterId,
										beforeSend : function(xhr) {
											xhr.setRequestHeader("Accept",
													"application/json");
											xhr.setRequestHeader(
													"Content-Type",
													"application/json");
										},
										success : function(response) {
											//alert("Response is coming");
											$('#successMsgId')
													.html(
															"<span style='color:green;font:bold;'>"
																	+ response.statusMsg
																	+ "</span>");
											if (response.status) {
												//Do something if required	
												$("#voteVoterId_err").html("");//Clean previous error msg if any

												//if Voter id is verified . Turn its background green and show action response.               
												$(
														'#offLineVoteVoterIdVerifyBgId')
														.css(
																'background-color',
																'green');
												$('#offLineVoteVoterIdButtonId')
														.text("Verified");
												//Disable voterId Verification once verified               
												$('#offLineVoteVoterId').prop(
														"disabled", true);
												$('#offLineVoteVoterIdVerify')
														.css('pointer-events',
																'none');
												//Enable anchor tag                
												$('#offLineSecretVerifyId')
														.css('pointer-events',
																'auto');
												//Change background of aadhar verification on voter id verifcation success
												$('#offLineSecretBgColorId')
														.css(
																'background-color',
																'blue');
											} else {
												//alert("Form has an error = "+response.statusMsg);
												$("#voteVoterId_err").html(
														response.statusMsg);
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
		$("#offLineSecretVerifyId").click(
				function() {
					event.preventDefault();
					var secretId = $("#offLineSecretId").val();
					///Ajax call Started	
					$.ajax({
						type : 'POST',
						url : "/pub/hm/voter/secretIdVerification?secretId="
								+ secretId,
						beforeSend : function(xhr) {
							xhr.setRequestHeader("Accept", "application/json");
							xhr.setRequestHeader("Content-Type",
									"application/json");
						},
						success : function(response) {
							//alert("Response is coming");

							if (response.status) {
								//Do something if required	
								$("#secret_err").html("");//Clean previous error msg if any							
								//if Voter id is verified . Turn its background green and show action response.               
								$('#offLineSecretBgColorId').css(
										'background-color', 'green');
								//Disable voterId Verification once verified               
								$('#offLineSecretId').prop("disabled", true);
								$('#offLineSecretVerifyId').css(
										'pointer-events', 'none');
								$('#offLineSecretButtonId').text("Verified");

								$("#offLineVoteVoterVerificationSubmitId")
										.attr("disabled", false);

							} else {
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

		$("#offLineVoteVoterVerificationSubmitId").click(
				function() {
					//var secretId = $("#secretId").val();
					var voterId = $("#offLineVoteVoterId").val();
					var electionType = $("input[name='ecType']:checked").val();
					window.location.replace("/pub/vote/offline?voterId="
							+ voterId + "&electionType=" + electionType);

				});

		//####################  Online	Voter identification ###################//
		//Voter Id verification
		$("#onlineVoteVoterIdVerify")
				.click(
						function() {
							event.preventDefault();
							var onlineVoteVoterId = $("#onlineVoteVoterId")
									.val();
							///Ajax call Started	
							$
									.ajax({
										type : 'POST',
										url : "/pvt/online/voter/voterIdVerification?voteVoterId="
												+ onlineVoteVoterId,
										beforeSend : function(xhr) {
											xhr.setRequestHeader("Accept",
													"application/json");
											xhr.setRequestHeader(
													"Content-Type",
													"application/json");
										},
										success : function(response) {
											//alert("Response is coming");						
											if (response.status) {
												//Do something if required	
												$("#onlineVoteVoterId_err")
														.html("");//Clean previous error msg if any

												//if Voter id is verified . Turn its background green and show action response.               
												$('#onlineVoteIdVerifyBgId')
														.css(
																'background-color',
																'green');
												$('#onlineVoteVoterIdButtonId')
														.text("Verified");
												//Disable voterId Verification once verified               
												$('#onlineVoteVoterId').prop(
														"disabled", true);
												$('#onlineVoteVoterIdVerify')
														.css('pointer-events',
																'none');
												//Enable anchor tag                
												$('#secretOnlineVerifyId').css(
														'pointer-events',
														'auto');
												//Change background of aadhar verification on voter id verifcation success
												$('#secretOnlineBgColorId')
														.css(
																'background-color',
																'blue');
											} else {
												//alert("Form has an error = "+response.statusMsg);
												$("#onlineVoteVoterId_err")
														.html(
																response.statusMsg);
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
		$("#secretOnlineVerifyId")
				.click(
						function() {
							event.preventDefault();
							var secretId = $("#secretOnlineId").val();
							var onlineVoteVoterId = $("#onlineVoteVoterId")
									.val();

							///Ajax call Started	
							$
									.ajax({
										type : 'POST',
										url : "/pvt/online/voter/secretIdVerification?secretId="
												+ secretId
												+ "&voterId="
												+ onlineVoteVoterId,
										beforeSend : function(xhr) {
											xhr.setRequestHeader("Accept",
													"application/json");
											xhr.setRequestHeader(
													"Content-Type",
													"application/json");
										},
										success : function(response) {
											//alert("Response is coming");

											if (response.status) {
												//Do something if required	
												$("#secretOnline_err").html("");//Clean previous error msg if any							
												//if Voter id is verified . Turn its background green and show action response.               
												$('#secretOnlineBgColorId')
														.css(
																'background-color',
																'green');
												//Disable voterId Verification once verified               
												$('#secretOnlineId').prop(
														"disabled", true);
												$('#secretOnlineVerifyId').css(
														'pointer-events',
														'none');
												$('#secretOnlineButtonId')
														.text("Verified");
												//Now enable mobile otp link and change background color							
												$('#votingOtpVerifyId').css(
														'pointer-events',
														'auto');
												$('#votingOtpBgColorId').css(
														'background-color',
														'blue');

											} else {
												//alert("Form has an error = "+response.statusMsg);
												$("#secretOnline_err").html(
														response.statusMsg);
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
		$("#votingOtpVerifyId")
				.click(
						function() {
							event.preventDefault();
							var mobileOtp = $("#votingOtpId").val();
							///Ajax call Started	
							$
									.ajax({
										type : 'POST',
										url : "/pvt/online/voter/mobileOtpVerification?mobileOtp="
												+ mobileOtp,
										beforeSend : function(xhr) {
											xhr.setRequestHeader("Accept",
													"application/json");
											xhr.setRequestHeader(
													"Content-Type",
													"application/json");
										},
										success : function(response) {
											//alert("Response is coming");

											if (response.status) {
												//Do something if required
												$("#votingOtp_err").html("");//Clean previous error msg if any							
												//if Voter id is verified . Turn its background green and show action response.               
												$('#votingOtpBgColorId').css(
														'background-color',
														'green');
												//Disable voterId Verification once verified               
												$('#votingOtpId').prop(
														"disabled", true);
												$('#votingOtpVerifyId').css(
														'pointer-events',
														'none');
												$('#votingOtpButtonId').text(
														"Verified");

												$(
														"#onlineVotingVerificationSubmitId")
														.attr("disabled", false);

											} else {
												//alert("Form has an error = "+response.statusMsg);
												$("#votingOtp_err").html(
														response.statusMsg);
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

		$("#onlineVotingVerificationSubmitId").click(
				function() {
					//alert("Online voter id verification submitted")
					var voterId = $("#onlineVoteVoterId").val();
					var electionType = $("input[name='ecTypeOnline']:checked")
							.val();
					window.open("/pvt/vote/online?voterId=" + voterId
							+ "&electionType=" + electionType);

				});
	</script>

	<script type='text/javascript'
		src='//cdn.jsdelivr.net/jquery.marquee/1.4.0/jquery.marquee.min.js'></script>
	<script type="text/javascript">
		$(function() {
			loadMarque();			
		});

		function loadMarque() {
			/* stop form from submitting normally */
			method = 'GET';
			url = "/loadMarque";
			$.ajax({
				type : method,
				url : url,
				data : {},
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(response) {
					if (response.status) {
						//alert("Response is coming ="+response.marqueeList);
						loadMarqueeFromResponse(response.marqueeList);
						//Do something if required			
					} else {
						showBusinessEerror(response.fieldErrMsgMap);
					}
				},
				error : function(jqXHR, exception) {
					formatErrorMessage(jqXHR, exception);
				}
			});

		}


		function loadMarqueeFromResponse(marqueStringGlobal){
			$('.marquee').html(marqueStringGlobal);

			$('.marquee').marquee({
				//speed in milliseconds of the marquee
				duration : 7000,
				//gap in pixels between the tickers
				gap : 50,
				//time in milliseconds before the marquee will start animating
				delayBeforeStart : 0,
				//'left' or 'right'
				direction : 'right',
				//true or false - should the marquee be duplicated to show an effect of continues flow
				duplicated : true
			});
			}
	</script>

</body>
</html>






