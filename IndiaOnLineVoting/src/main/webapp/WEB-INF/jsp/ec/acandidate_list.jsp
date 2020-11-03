
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>&nbsp;&nbsp;&nbsp;&nbsp;Assembly-Candidate List [ <strong>EC Test Data</strong> ]</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
		<li class="active">Assembly-Candidate List</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<div class="row">
		<div class="col-md-9">
			<div class="box box-primary ">
				<div class="box-header with-border">

					<div class="box-title">
						
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
					<table  id="assemblyCandidateId" width="100%"
						class="table table-striped table-bordered table-hover table-condensed dt-responsive data-table">
						<thead>
							<tr>
								<th width="6%">#</th>
								<th width="7%"><input id="chkAll" type="checkbox">&nbsp;All</th>

								<th width="6%">ID</th>
								<th>Year</th>
								<th>State</th>
								<th>Loksabha</th>
								<th>Assembly</th>
								<th>Candidate</th>
								<th>Party</th>
								<th>Symbol</th>
								
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
	<div class="modal-dialog modal-sm">
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
						
						
						
						<div class="form-group" id=""electionYearLabledId">
							<label for="inputSortName" class="col-sm-4 control-label">
								Year</label>
							<div class="col-sm-7">								
									<select class="form-control" name="electionYear"id="electionYearId">
									<option value="">-- Select --</option>
									
									<% for(int i=2020;i<2031;i++){%>
									
										<option value="<%=i %>"><%=i %></option>
										
										<%}%>
								</select>
									
									
									<span
									id="electionYearId_err" class="field-error"></span>
							</div>
						</div>	
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
								<select class="form-control" name="loksabhaNameId" id="loksabhaNameId">
									<option value="">-- Select --</option>

								</select> <span id="loksabhaNameId_err" class="field-error"></span>
							</div>
						</div>
						
						<div class="form-group">
							<label for="inputAssemblyName" class="col-sm-4 control-label">
								Assembly</label>
							<div class="col-sm-7">
								<select class="form-control" name="assemblyNameId" id="assemblyNameId">
									<option value="">-- Select --</option>

								</select> <span id="assemblyNameId_err" class="field-error"></span>
							</div>
						</div>
						<div class="form-group" id="acandidateNameLabledId">
							<label for="inputSortName" class="col-sm-4 control-label">
								Candidate</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="acandidateName"
									id="acandidateNameId" placeholder="Candidate Name"> <span
									id="acandidateNameId_err" class="field-error"></span>
							</div>
						</div>
						
						
						<div class="form-group" id="partyCategoryLabledId">
							<label for="inputSortName" class="col-sm-4 control-label">
								Nominated</label>
							<div class="col-sm-7">
								<input type="radio"   name="nominated" value="P"	id="RNominatedId" checked="checked"> &nbsp;By Reg.-Party<br>
								<input type="radio"  name="nominated" value="I"	id="INominatedId"> &nbsp;As an Independent								
							</div>
						</div>	
						
						
						
						<div class="form-group" id="partyNameLableId">
							<label for="inputPartyName" class="col-sm-4 control-label">
								Party</label>
							<div class="col-sm-7">
								<select class="form-control" name="partyNameId"
									id="partyNameId">
									<option value="">-- Select --</option>

								</select> <span id="partyNameId_err" class="field-error"></span>
	    					</div>
						</div>				
						
						<div class="form-group" id="symbolLabledId" style="display: none;">
							<label for="inputSortName" class="col-sm-4 control-label">
								Symbol</label>
							<div class="col-sm-7">Image</div>
							
						</div>
						<div class="form-group" id="symbolLabledId" style="display: none;">
							<label for="inputSortName" class="col-sm-4 control-label">
								Upload</label>
							
							<div class="col-sm-7"><input type="file"></div>
						</div>
						
						
						

                        <div class="form-group" id="acandidateNoLabledId">
							<label for="inputSortName" class="col-sm-4 control-label">
								Position </label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="acandidateNo"
									id="acandidateNoId" placeholder="Ballet-Position"> <span
									id="acandidateNoId_err" class="field-error"></span>
							</div>
						</div>
						<input hidden="true" name="id" id="acandidateRecordId" />
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/assets/pageSpecific/js/acandidate_list.js"></script>


