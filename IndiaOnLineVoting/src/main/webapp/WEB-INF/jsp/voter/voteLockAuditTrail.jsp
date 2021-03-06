
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>Vote-Locking-Audit-Trail</h1>
	<ol class="breadcrumb">
		<li><a hre f="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
		<li class="active">Vote-Locking-Audit-Trail</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary ">
				

				<div class="box-body ">
					<table id="votersAuditTrailId" width="100%"
						class="table table-striped table-bordered table-hover table-condensed dt-responsive data-table">
						<thead>
							<tr>
								<th width="6%">#</th>								
								<th>Voter</th>					
                                <th>Voter Id</th>                                                               
								<th>Voting Mode</th>
								<th>dateOfLocking</th>
								<th>IP-Address</th>				
							</tr>
						</thead>

					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
</section>
<!-- /.content -->


<!-- jQuery 3  -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<!-- Theme style -->
<link rel="stylesheet" href="${contextPath}/resources/assets/dist/css/AdminLTE.min.css">
<script>
$(function () {
	loadGrid("");
});
	//## Code for loading grid ##
	var t;
	function loadGrid(voterId) {
		
	 $('#votersAuditTrailId').DataTable(
				{
					"responsive": true,
					"retrieve" : true,// used for refreshing
					"bAutoWidth" : true,
					//"scrollY" : '110vh',
					//"scrollCollapse" : true,
					"lengthMenu" : [ 5, 10, 15, 20 ],
					"processing" : true,
					"serverSide" : true,
					"ordering" : true,
					"searching" : true,
					"aaSorting" : [ [ 1, "asc" ], [ 4, "asc" ] ],
					"ajax" : {
						
						"url" : "paginated?voterId=" + voterId,

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
							"data" : "name",
							"name" : "name",
							"title" : "Voter"
						},
						{
							"data" : "voterId",
							"name" : "voterId",
							"title" : "Voter-Id"
						},						
						
						
						{
							"data" : "lockStatusId",
							"render" : function(data, type, row) {
								if(data==0)
								  return '<Strong style="color:blue;">Unlock</strong>';
								if(data==1)
								  return '<Strong style="color:red;">Lock</strong>'; 
							},
							"title" : "Voting-Status"
						},

						

						{
							"data" : "dateOfLocking",
							"name" : "dateOfLocking",
							"title" : "Lock-Changed-On"
						},

						{
							"data" : "reqIpAdd",
							"name" : "reqIpAdd",
							"title" : "IP-Address-Used"
						}	
						
						
						
						]
				});

		
	}// End of loading grid


	

</script>

