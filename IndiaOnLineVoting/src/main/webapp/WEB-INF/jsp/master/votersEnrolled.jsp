
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>Voters Enrolled</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
		<li class="active">Voters Enrolled</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary ">
				<div class="box-header with-border">

					<div class="box-title">
						<button type="button">
							<i class="fa fa-plus addClass" data-toggle="modal">&nbsp;</i>
						</button>
						<button type="button" id="deleteSelected">
							<i class="fa fa-trash-o" data-toggle="modal">&nbsp;</i>
						</button>
						<button type="button">
							<i class="fa fa-search searchClass" data-toggle="modal">&nbsp;</i>
						</button>
						<button type="button" id="reloadGrid">
							<i class="fa fa-refresh">&nbsp;Grid</i>
						</button>
						<button type="button" id="refreshGrid">
							<i class="fa fa-refresh">&nbsp;Page</i>
						</button>
					</div>
				</div>

				<div class="box-body ">
					<table id="votersEnrolledId" width="100%"
						class="table table-striped table-bordered table-hover table-condensed dt-responsive data-table">
						<thead>
							<tr>
								<th width="6%">#</th>
								<th width="7%"><input id="chkAll" type="checkbox">&nbsp;All</th>

								<th width="6%">ID</th>
								<th>State Name</th>
								<th>Loksabha Name</th>
								<th>Assembly Name</th>
								<th>Booth Name</th>
								<th>Booth No</th>
								<th>Voter</th>								
								<th>Father</th>
								<th>Voter Id</th>
								<th>Voting Mode</th>
								<th>Address</th>


								<th width="6%">View</th>
								<th width="6%">Edit</th>
								<th width="6%">Delete</th>
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



<!-- modal for add/update/view/search -->
<div class="modal fade" id="modal-common">
	<div class="modal-dialog modal-md">
		<div class="modal-content" style="border-radius: 10px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="commonModalTitleId">Common Modal</h4>
			</div>
			<div class="modal-body">
				<!-- form start -->
				<form class="form-horizontal" id="commonFormId" action="">
					<div class="box-body">
						<h5 id="successMsgId"></h5>
						<h5 id="searchMsgId"></h5>
						<strong style="color: red;" id="searchHideContentId"><a
							href="#" id="voterlocationId">Voter's Location </a></strong>
						<div id="voterLocationShowHideId">
							<div class="form-group">
								<label for="inputStateName" class="col-sm-4 control-label">
									State</label>
								<div class="col-sm-7">
									<select class="form-control" name="stateNameId"
										id="stateNameId">
										<option value="">-- Select --</option>

									</select> <span id="stateNameId_err" class="field-error"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="inputLoksabhaName" class="col-sm-4 control-label">
									Loksabha</label>
								<div class="col-sm-7">
									<select class="form-control" name="loksabhaNameId"
										id="loksabhaNameId">
										<option value="">-- Select --</option>

									</select> <span id="loksabhaNameId_err" class="field-error"></span>
								</div>
							</div>

							<div class="form-group" id="assemblyLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									Assembly</label>
								<div class="col-sm-7">
									<select class="form-control" name="assemblyNameId"
										id="assemblyNameId">
										<option value="">-- Select --</option>

									</select> <span id="assemblyNameId_err" class="field-error"></span>
								</div>
							</div>

							<div class="form-group" id="pollingBoothNameIdLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									Booth</label>
								<div class="col-sm-7">
									<select class="form-control" name="pollingBoothNameId"
										id="pollingBoothNameId">
										<option value="">-- Select --</option>
									</select> <span id="pollingBoothNameId_err" class="field-error"></span>
								</div>
							</div>
						</div>



						<!-- Voter Location -->
						<strong style="color: red;" id="searchHide1ContentId"><a
							href="#" id="voterDetailId"> Voter's Details </a></strong>
						<div id="voterDetailShowHideId">

							<div class="form-group" id="voterNameLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									Voter</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="voterName"
										id="voterNameId" placeholder="Voter Name"> <span
										id="voterNameId_err" class="field-error"></span>
								</div>
							</div>

							<div class="form-group" id="sexLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									Sex</label>
								<div class="col-sm-7">
									<select class="form-control" name="sex" id="sexId">
										<option value="">-- Select --</option>
										<option value="M">Male</option>
										<option value="F">Female</option>
									</select> <span id="sexId_err" class="field-error"></span>
								</div>
							</div>

							<div class="form-group" id="dobForPageLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									Dob</label>
								<div class="col-sm-7">
								    
									<input type="text" class="form-control" name="dobForPage" id="dobForPageId" placeholder="yyyy-mm-dd">
								
								<!-- /.input group -->
								 <span id="dobForPageId_err"
										class="field-error"></span>
								</div>
							</div>


							<div class="form-group" id="fatherNameLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									Father</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="fatherName"
										id="fatherNameId" placeholder="fatherName"> <span
										id="fatherNameId_err" class="field-error"></span>
								</div>
							</div>

							<div class="form-group" id="voterIdLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									voterId</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="voterId"
										id="voterIdId"
										placeholder="System itself generates this voterId."> <span
										id="voterIdId_err" class="field-error"></span>
								</div>
							</div>

							<div class="form-group" id="voting_modeLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									voting_mode</label>
								<div class="col-sm-7">
									<input type="radio" name="voting_mode"
										id="offlineVoting_modeId" value="0" checked="checked">&nbsp;Offline
									<input type="radio" name="voting_mode" id="onlineVoting_modeId"
										value="1">&nbsp;Online <span id="voting_modeId_err"
										class="field-error"></span>
								</div>
							</div>

							<div class="form-group" id="addressLabledId">
								<label for="inputSortName" class="col-sm-4 control-label">
									Address</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="address"
										id="addressId" placeholder="address"> <span
										id="addressId_err" class="field-error"></span>
								</div>
							</div>
						</div>
						<input hidden="true" name="id" id="votersEnrolledRecordId" />
					</div>
					<!-- /.box-body -->

					<!-- /.box-footer -->
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				<button type="button"
					class="btn btn-warning commonButtonActionClass"
					id="commonButtonActionId">Save</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<!-- modal for delete -->
<div class="modal fade" id="modal-delete">
	<div class="modal-dialog modal-sm modal-confirm">
		<div class="modal-content" style="border-radius: 10px;">
			<div class="modal-header">
				<h4 class="modal-title">Are you sure?</h4>

			</div>
			<div class="modal-body">
				<h5 id="deleteSuccessMsgId"></h5>
				<label>Do you really want to delete this record? This
					process cannot be undone.</label>
			</div>
			<input hidden="true" name="recordId" id="recordIdForDelete" />
			<div class="modal-footer">
				<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-danger" id="deleteFormButtonId">Delete</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<!-- modal for selected Delete -->
<div class="modal fade" id="modal-sDelete">
	<!-- modal for selected delete -->
	<div class="modal-dialog modal-sm modal-confirm">
		<div class="modal-content" style="border-radius: 10px;">
			<div class="modal-header">
				<h4 class="modal-title">Are you sure?</h4>

			</div>
			<div class="modal-body">
				<h5 id="deleteSelectedSuccessMsgId"></h5>
				<label id="showAllertMsg"></label>
				<div id="showSelectedRow" style="margin-top: 10px;"></div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-danger"
					id="deleteSelFormButtonId">Delete</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<!-- modal for success -->
<div class="modal fade" id="modal-success">
	<div class="modal-dialog modal-sm modal-success">
		<div class="modal-content" style="border-radius: 10px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Success Message</h4>
			</div>
			<div class="modal-body">
				<div class="modal-body">Record has been saved successfully.</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left"
					data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-primary">Yes</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- End of modals for form -->

<!-- jQuery 3  -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script
	src="${contextPath}/resources/assets/pageSpecific/js/votersEnrolled.js"></script>
<script type="text/javascript">
<!-- Theme style -->
<link rel="stylesheet" href="${contextPath}/resources/assets/dist/css/AdminLTE.min.css">
<script>
$(function () {
	//Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
});
</script>

