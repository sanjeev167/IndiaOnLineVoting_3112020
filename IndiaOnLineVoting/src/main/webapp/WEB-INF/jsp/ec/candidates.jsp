
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
	
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>&nbsp;&nbsp;&nbsp;&nbsp;Candidate List [ <strong>EC Test Data</strong> ]</h1>
			<ol class="breadcrumb">
				<li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Candidate List</li>
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
								src="<%request.getContextPath();%>/resources/assets/ec/img/candidate.jpg">

							<h3 class="profile-username text-center">
							   <input type="radio" name="electionType" />&nbsp;Assembly 
							   <input type="radio" name="electionType" />&nbsp;Parliamentary
							</h3>


							<p class="text-muted text-center">
								Year &nbsp;&nbsp;&nbsp;<select><option value="">-Year-</option></select>
							</p>

							<ul class="list-group list-group-unbordered">
								<li class="list-group-item"><b>State</b> <select
									class="pull-right"><option>-Select-</option></select></li>
								
								<li class="list-group-item"><b>Lok Sabha</b><select
									class="pull-right"><option>-Select-</option></select></li>
							    <li class="list-group-item"><b>Assembly</b><select
									class="pull-right"><option>-Select-</option></select></li>
								
							</ul>

							<a href="#" class="btn btn-primary btn-block"><b>View-Election-Result</b></a>
							
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
                

				</div>
				<!-- /.col -->




				<div class="col-md-6">
					<!-- Default box -->
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">Candidate List of [ State => Bihar ] [ Assembly => Muzaffarpur ] [ Loksabha => Muzaffarpur ]</h3>							
						</div>						
						<div class="box-body">
										<table id="example1"
											class="table table-bordered table-striped">
											<thead>
												<tr>
												    <th>#</th>
													<th>Candidate</th>
													<th>Party</th>													
												</tr>
											</thead>
											<tbody>
												<tr>
												    <td>1</td>
													<td>Lalu</td>
													<td>RJD</td>														
												</tr>
												<tr>
												    <td>1</td>
													<td>Lalu</td>
													<td>RJD</td>														
												</tr>
                                               <tr>
												    <td>1</td>
													<td>Lalu</td>
													<td>RJD</td>														
												</tr>
												<tr>
												    <td>1</td>
													<td>Lalu</td>
													<td>RJD</td>														
												</tr>
												<tr>
												    <td>1</td>
													<td>Lalu</td>
													<td>RJD</td>														
												</tr>
											</tbody>
											<tfoot>
												<tr>
													 <th>#</th>
													<th>Candidate</th>
													<th>Party</th>	
												</tr>
											</tfoot>
										</table>
									</div>
									<!-- /.box-body -->
						
						
						
						<!-- /.box-footer-->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
         </section>
		

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
