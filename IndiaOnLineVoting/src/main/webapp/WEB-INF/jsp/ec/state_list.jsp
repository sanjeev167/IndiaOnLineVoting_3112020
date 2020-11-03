<!-- Content Header (Page header) -->

<section class="content-header">
	<h1>&nbsp;&nbsp;&nbsp;&nbsp;State List [ <strong>EC Test Data</strong> ]</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
		<li class="active">State List</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<div class="row">
		<div class="col-md-9">
			<div class="box box-primary ">
			<div class="box-body ">
					<table id="stateId" width="100%"
						class="table table-striped table-bordered table-hover table-condensed dt-responsive data-table">
						<thead>
							<tr>
								<th width="6%">#</th>
								<th width="7%"><input id="chkAll" type="checkbox">&nbsp;All</th>

								<th width="6%">ID</th>
								<th>Country Name</th>
								<th>State Name</th>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<script type="text/javascript">


$(document).ready(function() {
	
	// [0] Load grid while opening the page.
	loadGrid("", "", "");
	
	});
	
function loadGrid(stateName, countryName,countryId) {
	
	t = $('#stateId').DataTable(
			{
				//"colReorder": true,

				"retrieve" : true,// used for refreshing
				"bAutoWidth" : true,
				//"scrollY" : '110vh',
				//"scrollCollapse" : true,
				"lengthMenu" : [ 5, 10, 15],
				"processing" : true,
				"serverSide" : true,
				"ordering" : true,
				"searching" : true,
				"aaSorting" : [ [ 3, "asc" ], [ 4, "asc" ] ],
				"ajax" : {
					"url" : "/pub/ec/states/paginated?stateName=" + stateName + "&countryName=" + countryName+ "&countryId=" + countryId  + "",

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
						"data" : null,
						"sortable" : false,
						"render" : function(data, type, full, meta) {
							return '<input class="chkIndvRow" value='
							+ meta.row + ' type="checkbox" >';// Will
							// index
						},
						"bVisible" : false, // used for hiding a column
					},

					{
						"data" : "id",
						"name" : "ID",
						"title" : "ID",
						"searchable" : false,
						"bVisible" : false, // used for hiding a column
					},
					{
						"data" : "countryName",
						"name" : "countryName",
						"title" : "Country Name",
						"bVisible" : false, // used for hiding a column
					   
					},
					{
						"data" : "stateName",
						"name" : "stateName",
						"title" : "State Name"
					}
					
					 ]
			});



}// End of loading grid

</script>





