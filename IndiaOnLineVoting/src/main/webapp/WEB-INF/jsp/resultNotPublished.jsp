<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<head>
<title>IndiaOnLineVoting | Temporarily Resource-Unavailable </title>
</head>
<body>
	<section class="content-header">
	   <h1>
       Temporarily Resource-Unavailable 
      </h1>	
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>

			<li class="active"> Temporarily Resource-Unavailable </li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class="error-page">
			<h2 class="headline text-red">
              <span class="info-box-icon bg-aqua"><img
						src="<%request.getContextPath();%>/resources/assets/ec/img/unavailable.jpg" /></span>
              
            </h2>

			<div class="error-content">
				<h3>
					<i class="fa fa-warning text-red"></i><strong> Oops! Resource is temporarily unavailable.</strong>
				</h3>

				<p style="font-size: 20px;"><strong style="color:red;">Either this link access time has expired OR </strong>
				     <c:out	value="${notAvailabilityMsg}" />
					  <a href="#"
						onclick="javascript:history.go(-1)"><strong style="color: red">&nbsp;Return
								to previous page</strong></a> or try using the search form.					
				</p>
               
				<form class="search-form">
					<div class="input-group">
						<input type="text" name="search" class="form-control"
							placeholder="Search">

						<div class="input-group-btn">
							<button type="submit" name="submit"
								class="btn btn-danger btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					<!-- /.input-group -->
				</form>
			</div>
		</div>
		<!-- /.error-page -->

	</section>
	<!-- /.content -->
	
	<!-- jQuery 3 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"
		type="text/javascript"></script>
	
</body>
</html>