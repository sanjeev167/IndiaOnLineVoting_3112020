<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">  
<!-- <meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval()+1 %>;"> -->

<title>IndiaOnLineVoting</title>

<!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/bower_components/Ionicons/css/ionicons.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/plugins/iCheck/all.css">
  <!-- Bootstrap Color Picker -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
  <!-- Bootstrap time Picker -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/plugins/timepicker/bootstrap-timepicker.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="${contextPath}/resources/assets/bower_components/select2/dist/css/select2.min.css">
  
  
	
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
</style>	
</head>

<body class="hold-transition skin-blue  sidebar-mini fluid">
<div class="wrapper">
		<header class="main-header">
			<%@ include file="includes/voter_header.jsp"%>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar" >
			<%@ include file="includes/voter_left_menu.jsp"%>
		</aside>
		<!-- Content Wrapper. Contains page content -->
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
<script src="${contextPath}/resources/assets/bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="${contextPath}/resources/assets/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${contextPath}/resources/assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${contextPath}/resources/assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="${contextPath}/resources/assets/bower_components/moment/min/moment.min.js"></script>
<script src="${contextPath}/resources/assets/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="${contextPath}/resources/assets/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="${contextPath}/resources/assets/bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="${contextPath}/resources/assets/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src="${contextPath}/resources/assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${contextPath}/resources/assets/plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src="${contextPath}/resources/assets/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${contextPath}/resources/assets/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${contextPath}/resources/assets/dist/js/demo.js"></script>	
	
	
	
	
<!-- DataTables -->
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script	src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
<script	src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<script	src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
<script	src="${contextPath}/resources/assets/pageSpecific/js/jQueryGridFeature.js"></script>


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
		}).parentsUntil(".sidebar-menu > .treeview-menu").addClass('active');
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
	$(document).ready(function () {
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
	    }
		else if (jqXHR.status === 0) {
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
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
    //Money Euro
    $('[data-mask]').inputmask()

    //Date range picker
    $('#reservation').daterangepicker()
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A' })
    //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
      }
    )

    //Date picker
    $('#datepicker').datepicker({
      autoclose: true
    })

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
      checkboxClass: 'icheckbox_minimal-red',
      radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass   : 'iradio_flat-green'
    })

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
  })
</script>




</body>
</html>






