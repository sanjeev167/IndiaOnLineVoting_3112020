
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- iCheck -->
<link rel="stylesheet"
	href="${contextPath}/resources/assets/plugins/iCheck/square/blue.css">

<div class="login-box col-md-12" style="padding-top: 2px; margin-top: 2px; margin-left: 35%">
	<div class="login-logo">
		<a href="#"><b>India</b>Online<b>Voting</b></a>
	</div>
	<!-- /.login-logo -->
	<div class="login-box-body"  >
		<p class="login-box-msg">
		<img class="profile-user-img img-responsive img-circle"
								src="<%request.getContextPath();%>/resources/assets/ec/img/forgetpwd.jpg">
		
		</p>
      
       
       
		<span style="color: red; font-weight: bold;">${errorMessge}</span> 

             
		<form action="/forgetPwd" method="POST">
			<div class="form-group has-feedback">
				<input type="text" class="form-control" placeholder="Email"
					name="username"> <span
					class="glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>		
			
			<div class="row">
				<!-- /.col -->
				<div class="col-xs-12 pull-right">
					<button type="submit" class="btn btn-primary btn-block btn-flat">Submit</button>
				</div>
				
				<!-- /.col -->
			</div>
			<h4>Go for&nbsp;&nbsp;<a href="login">Login</a>&nbsp;&nbsp;-OR-&nbsp;&nbsp;
                     <a href="/pub/hm/reg/" class="text-center">Registration</a></h4>
			<p/>
			
		</form>

		


	</div>
	<!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script
	src="${contextPath}/resources/assets/bower_components/jquery/dist/jquery.min.js"></script>
<!-- iCheck -->
<script
	src="${contextPath}/resources/assets/plugins/iCheck/icheck.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$('input').iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue',
			increaseArea : '20%' /* optional */
		});
	});
</script>











