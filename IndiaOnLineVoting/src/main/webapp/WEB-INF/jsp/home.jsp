
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- DataTables -->
<link rel="stylesheet"
	href="<%request.getContextPath();%>/resources/assets/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
</head>
<body>
	<!-- Full Width Column -->
	<!-- Content Wrapper. Contains page content -->

	<div class="col-md-6">
		<div class="box box-solid">

			<!-- /.box-header -->
			<div class="box-body">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"
							class=""></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"
							class=""></li>
						<li data-target="#carousel-example-generic" data-slide-to="3"
							class=""></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img
								src="<%request.getContextPath();%>/resources/assets/ec/img/ec40.jpg"
								alt="First slide">

							<div class="carousel-caption">First Slide</div>
						</div>
						<div class="item">
							<img
								src="<%request.getContextPath();%>/resources/assets/ec/img/ec50.jpg"
								alt="Second slide">

							<div class="carousel-caption">Second Slide</div>
						</div>
						<div class="item">
							<img
								src="<%request.getContextPath();%>/resources/assets/ec/img/ec30.jpg"
								alt="Third slide">

							<div class="carousel-caption">Third Slide</div>
						</div>
						<div class="item">
							<img
								src="<%request.getContextPath();%>/resources/assets/ec/img/ec20.jpg"
								alt="Fourth slide">

							<div class="carousel-caption">Fourth Slide</div>
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-example-generic"
						data-slide="prev"> <span class="fa fa-angle-left"></span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						data-slide="next"> <span class="fa fa-angle-right"></span>
					</a>
				</div>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->
	</div>
	<!-- /.col -->
	<div class="col-md-3">

		<!-- Profile Image -->
		<div class="box box-primary" style="height: auto">
		<!-- /.box-header -->
			<div class="box-body">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"
							class=""></li>
						
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img
								src="<%request.getContextPath();%>/resources/assets/ec/img/ec40.jpg"
								alt="First slide">

							<div class="carousel-caption">First Slide</div>
						</div>
						<div class="item">
							<img
								src="<%request.getContextPath();%>/resources/assets/ec/img/ec50.jpg"
								alt="Second slide">

							<div class="carousel-caption">Second Slide</div>
						</div>
						
					</div>
					<a class="left carousel-control" href="#carousel-example-generic"
						data-slide="prev"> <span class="fa fa-angle-left"></span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						data-slide="next"> <span class="fa fa-angle-right"></span>
					</a>
				</div>
			</div>
			<!-- /.box-body -->
		
			<div class="box-body box-profile">
				<img class="profile-user-img img-responsive img-circle" height="100"
					width="50"
					src="<%request.getContextPath();%>/resources/assets/ec/img/onlineVote2.jpg">
				<p />
				
				
				
				<h3 class="profile-username text-center">IndiaOnlineVoting<br>
				   [ <strong style="color:red;">Has following stuffs</strong> ]
				</h3>

				<ul class="list-group list-group-unbordered">
					<li class="list-group-item"><a href="/">Can Register-For-Online-Voting</a></li>					
					<li class="list-group-item"><a href="#">Can-Lock-Your-Vote-For-Online-Voting</a></li>
					<li class="list-group-item"><a href="#">Can Cast-Your-Vote-Online</a></li>
					<li class="list-group-item"><a href="#">Scheduled-Online-Voting-Registration [ By EC ]</a></li>
					<li class="list-group-item"><a href="#">Scheduled-Vote-Locking [ By EC ]</a></li>
					<li class="list-group-item"><a href="#">Can-Check-Your-Vote-Locking-History</a></li>
					<li class="list-group-item"><a href="#">Election-Result-Activation</a></li>						
				</ul>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->
	</div>
	<!-- /.col -->



	<!-- jQuery 3 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- DataTables -->
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="<%request.getContextPath();%>/resources/assets/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<!-- page script -->
	<script>
		$(function() {
			$('#example1').DataTable()
			$('#example2').DataTable()
			$('#example3').DataTable({
				'paging' : true,
				'lengthChange' : false,
				'searching' : false,
				'ordering' : true,
				'info' : true,
				'autoWidth' : false
			})
		})
	</script>
</body>
</html>
