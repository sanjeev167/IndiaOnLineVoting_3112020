
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>Sensitive Link</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
		<li class="active">Sensitive Link</li>
	</ol>
</section>



<!-- Main content -->
<section class="content container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary ">
				<div class="box-header with-border">

					<div class="box-title">
						<div class="box-tools pull-right">
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
				</div>

				<div class="box-body ">
					<table width="100%" id="sensitiveLinkId"
						class="table table-striped table-bordered table-hover table-condensed dt-responsive data-table">
						<thead>
							<tr>
								<th width="6%">#</th>
								<th width="7%"><input id="chkAll" type="checkbox">&nbsp;All</th>
								<th width="6%">ID</th>
								<th>Link Name</th>
								<th>Page Url</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th width="6%">State</th>
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





<!-- Modals for this form -->
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
						<div class="form-group">
							<label for="inputCountryName" class="col-sm-5 control-label">
								 Name</label>
							<div class="col-sm-7">
								<input type="select" class="form-control" name="name"
									id="nameId" placeholder="Sensitive Link Name"> <span
									id="name_err" class="field-error"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputSortName" class="col-sm-5 control-label">
								Page Url</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="pageUrl"
									id="pageUrlId" placeholder="pageUrl">
								<span id="pageUrl_err" class="field-error"></span>
							</div>
						</div>
						
						<div class="form-group" id="StartDateId2">
							<label for="StartDate" class="col-sm-5 control-label">
								Start Date</label>
							<div class="col-sm-7">
								<input type="text" min="0" class="form-control"
									name="activateStartDateS" id="activateStartDateSId"
									placeholder="DD-MM-YYYY hh:mm:ss a" data-inputmask="'mask': '99-99-9999 99:99:99 AA'" data-mask> <span
									id="activateStartDateS_err" class="field-error"></span>
							</div>
						</div>
						
						<div class="form-group" id="EndDateId2">
							<label for="StartDate" class="col-sm-5 control-label">
								End Date</label>
							<div class="col-sm-7">
								<input type="text" min="0" class="form-control"
									name="activateEndDateS" id="activateEndDateSId"
									placeholder="DD-MM-YYYY hh:mm:ss a" data-inputmask="'mask': '99-99-9999 99:99:99 AA'" data-mask> <span
									id="activateEndDateS_err" class="field-error"></span>
							</div>
						</div>
						
						<div class="form-group" id="activationMessageId2">
							<label for="activationMessage" class="col-sm-5 control-label">
								Activation-Message</label>
							<div class="col-sm-7">
								<input type="text" min="0" class="form-control"
									name="activationMessage" id="activationMessageId"
									placeholder="Activation Message" > <span
									id="activationMessage_err" class="field-error"></span>
							</div>
						</div>
						<div class="form-group" id="denyMessageId2">
							<label for="denyMessage" class="col-sm-5 control-label">
								Deny-Message</label>
							<div class="col-sm-7">
								<input type="text" min="0" class="form-control"
									name="denyMessage" id="denyMessageId"
									placeholder="Deny Message" > <span
									id="denyMessage_err" class="field-error"></span>
							</div>
						</div>
						<div class="form-group" id="channelStateId2">
							<label for="channelState" class="col-sm-5 control-label">
								Channel State</label>
							<div class="col-sm-7">
							    <select name="channelStateS" id="channelStateSId" class="form-control select2" style="width: 100%;">
							         <option value="">-Select-</option>
							         <option value="1">Active</option>
							         <option value="0">Deactivate</option>
							    </select>
								
									
									<span
									id="channelStateS_err" class="field-error"></span>
							</div>
						</div>
						
						
						<input hidden="true" name="id" id="sensitiveLinkRecordId" />
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

<!-- End of modal for form -->



<!-- jQuery 3  -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<script src="${contextPath}/resources/assets/pageSpecific/js/sensitiveLink.js"></script>




