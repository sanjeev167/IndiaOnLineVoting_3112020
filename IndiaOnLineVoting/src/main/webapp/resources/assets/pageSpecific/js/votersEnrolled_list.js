/*******************************************************************************
 * @Author: Sanjeev Kumar
 * @Date: 12/2/2019 *
 ******************************************************************************/


//###########################################################//
//########## Start of grid and CRUD related code ###########//
//#########################################################//

$(document).ready(function() {
  
	// [0] Load grid while opening the page.
	loadGrid("", "","","");
	loadGridOffline("", "","","");
	loadGridOnline("", "","","");
	preparePage();
	// [1] Open an add modal.
	$(document).on("click",".addClass",function() {
		clearFormData();
		cleanAllMsg();
		showAllHidden();
		cleanAllHiddenInput();
		removeReadOnlyProp();
		//$('#pollingBoothLabledId').show();
		$('#saveFormButtonId').attr("disabled", false);	
			
		$("#commonModalTitleId").html('Add a record');
		$('.commonButtonActionClass').show();
		$('.commonButtonActionClass').attr('id',
		'saveFormButtonId');
		$("#saveFormButtonId").html('Save');
		$('#modal-common').modal('toggle');
		$("#searchMsgId").html('');
	});

	// [2] Save a record.
	$(document).on("click", "#saveFormButtonId", function(e) {		
		cleanAllMsg();		
		saveAndUpdateRecord();	
		refreshGridPage();// Refresh with recently added record
	});

	// [3] Open an edit modal.
	$(document).on("click",".eClass",function() {		
		cleanAllMsg();
		showAllHidden();
		cleanAllHiddenInput();
		removeReadOnlyProp();
		
		$('#updateFormButtonId').attr("disabled", false);		
		getIdBasedRecord($(this).attr("href").split('=')[1]);

		$("#commonModalTitleId")
		.html('Update a record');
		$('.commonButtonActionClass').show();
		$('.commonButtonActionClass').attr('id',
		'updateFormButtonId');
		$("#updateFormButtonId").html('Update');
		$('#modal-common').modal('toggle');
		$("#searchMsgId").html('');
	});

	// [4] Update a record.
	$(document).on("click", "#updateFormButtonId", function() {
		cleanAllMsg();
		
		saveAndUpdateRecord();	
		refreshGridPage();// Reload with recently edited record
	});

	// [5] Open a delete confirm modal.
	$(document).on("click",	".dClass",function() {		
		cleanAllMsg();
		hideAllRequired();
		cleanAllHiddenInput();
		event.preventDefault(); // Will restrict the clicked url to be posted
		$('#deleteFormButtonId').attr("disabled", false);
		$('#recordIdForDelete').val($(this).attr("href").split('=')[1]);
		$('#modal-delete').modal('toggle');
	});

	// [6] Delete a record.
	$('#deleteFormButtonId').on('click', function(e) {
		deleteRecord($('#recordIdForDelete').val());
		$('#deleteFormButtonId').attr("disabled", true);
		refreshGridPage();
	});

	// [7] Open a view modal.
	$(document).on("click", ".vClass", function() {	
		
		cleanAllMsg();
		showAllHidden();
		cleanAllHiddenInput();
		applyReadOnlyProp();
		//$('#pollingBoothLabledId').show();
		getIdBasedRecord($(this).attr("href").split('=')[1]);
		$("#commonModalTitleId").html('View a record');
		$('#modal-common').modal('toggle');
		$("#searchMsgId").html('');
	});
	
	$(document).on("click", ".history", function() {	   
	     var voterId=$(this).attr("href").split('=')[1];		
	     loadGridAuditTrail(voterId);
		 $('#auditTrailModalId').modal('show');
			
	});
	
	
	

	// [8] Open a selected delete confirm modal.
	$("#deleteSelected").click(function() {		
		cleanAllMsg();
		hideAllRequired();
		cleanAllHiddenInput();
		if (GetSelectedRowID() == "") {
			$("#deleteSelFormButtonId").attr("disabled", true);
			$('deleteSelFormButtonId').prop('disabled', true);
			$('#showAllertMsg')
			.html("Please select at least one record.");
			$('#showSelectedRow').html("");
		} else {
			$('#deleteSelFormButtonId').attr("disabled", false);
			$('#showAllertMsg').html(
					"<label>Do you really want to delete these "
					+ GetSelectedRowID()[2]
					+ " records? This process cannot be undone.</label>");
			$('#showSelectedRow').html(GetSelectedRowID()[1]);
		}
		$('#deleteSelFormButtonId').attr("disabled", false);
		$('#modal-sDelete').modal('toggle');
	});

	// [9] Delete selected records.
	$('#deleteSelFormButtonId').on('click', function(e) {
		if (GetSelectedRowID() != "") {
			deleteSelectedRecord(GetSelectedRowID()[0]);
			$('#deleteSelFormButtonId').attr("disabled", true);
		}
		refreshGridPage();
	});

	// [10] Open a search modal.
	$(document).on("click",".searchClass",function() {
	    
		clearFormData();
		cleanAllMsg();
		cleanAllHiddenInput();
		hideAllRequired();		
		removeReadOnlyProp();
		$("#searchMsgId").html(
		'<span style="color:green"><h4>Search with any combination.</h4><\span>');
		$("#searchMsgId").show();
		$("#commonModalTitleId").html('Search records');
		$('.commonButtonActionClass').show();
		$('.commonButtonActionClass').attr('id', 'searchFormButtonId');
		$("#searchFormButtonId").html('Search');
		$('#modal-common').modal('toggle');
		
		$("#searchHideContentId").hide();
	    $("#searchHide1ContentId").hide();
	});
	
	// [11] Open a search modal.
	$(document).on("click",".SearchOffLineVotersClass",function() {
	    
		clearFormData();
		cleanAllMsg();
		cleanAllHiddenInput();
		hideAllRequired();		
		removeReadOnlyProp();
		$("#searchMsgId").html(
		'<span style="color:green"><h4>Search with any combination.</h4><\span>');
		$("#searchMsgId").show();
		$("#commonModalTitleId").html('Search records');
		$('.commonButtonActionClass').show();
		$('.commonButtonActionClass').attr('id', 'searchFormButtonId');
		$("#searchFormButtonId").html('SearchOffLineVoters');
		$('#modal-common').modal('toggle');
		
		$("#searchHideContentId").hide();
	    $("#searchHide1ContentId").hide();
	});
	
	// [12] Open a search modal.
	$(document).on("click",".SearchOnLineVotersClass",function() {
	    
		clearFormData();
		cleanAllMsg();
		cleanAllHiddenInput();
		hideAllRequired();		
		removeReadOnlyProp();
		$("#searchMsgId").html(
		'<span style="color:green"><h4>Search with any combination.</h4><\span>');
		$("#searchMsgId").show();
		$("#commonModalTitleId").html('Search records');
		$('.commonButtonActionClass').show();
		$('.commonButtonActionClass').attr('id', 'searchFormButtonId');
		$("#searchFormButtonId").html('SearchOnLineVoters');
		$('#modal-common').modal('toggle');
		
		$("#searchHideContentId").hide();
	    $("#searchHide1ContentId").hide();
	});

	// [13] Search record
	$(document).on("click", "#searchFormButtonId", function(e) {
	   
		var stateId = $('#stateNameId').val();
		var loksabhaNameId = $('#loksabhaNameId').val();
		var assemblyNameId=	$('#assemblyNameId').val();	
		var pollingBoothNameId=	$('#pollingBoothNameId').val();	
		if($('#searchFormButtonId').text()=="Search")	
		   searchAndReloadGrid(stateId,loksabhaNameId,assemblyNameId,pollingBoothNameId);
		if($('#searchFormButtonId').text()=="SearchOffLineVoters")	
		   searchAndReloadGridOfflineVoters(stateId,loksabhaNameId,assemblyNameId,pollingBoothNameId);
		   
	   if($('#searchFormButtonId').text()=="SearchOnLineVoters")	
		   searchAndReloadGridOnlineVoters(stateId,loksabhaNameId,assemblyNameId,pollingBoothNameId);
	});

	// [14] New one.
	$("#reloadGrid").click(function() {
		reloadGrid();
	});
	
	// [15] New one.
	$("#refreshGrid").click(function() {
		refreshGridPage();
	});
	
	

});// End of document ready


$('#stateNameId').on('change', function(e) {
	cleanAllMsg();
	$('#loksabhaNameId').empty().append('<option  value="">-Select-</option>');
	loadStateCombo($('#stateNameId').val(),"");
	$('#assemblyNameId').empty().append('<option  value="">-Select-</option>');
	$('#pollingBoothNameId').empty().append('<option  value="">-Select-</option>');	
});

$('#loksabhaNameId').on('change', function(e) {
	cleanAllMsg();		
	$('#assemblyNameId').empty().append('<option  value="">-Select-</option>');	
	loadAssemblyCombo($('#loksabhaNameId').val(),"");
	$('#pollingBoothNameId').empty().append('<option  value="">-Select-</option>');
});

$('#assemblyNameId').on('change', function(e) {

	cleanAllMsg();		
	$('#pollingBoothNameId').empty().append('<option  value="">-Select-</option>');	
	loadPollingBoothCombo($('#assemblyNameId').val(),"");
});






//Reload Grid.
function reloadGrid() {
	event.preventDefault();
	clearCheckBoxSelection();

	//var table = $('#stateId').DataTable();

	// table.clear().draw();
	// table.ajax.reload();//Loading existing opened page only
	// table.ajax.url( "paginated?name="+""+"&sortname="+""+"&phonecode="+""+"").load();	
	// Reset column order    
	// table.colReorder.reset();

	// Redraw table (and reset main search filter and column specific filter if any)	
	// t.search( '' ).columns().search( '' ).draw();//Will work without global search4

	window.location.replace("/pub/ec/voters");


}
function refreshGridPage() {
	event.preventDefault();
	clearCheckBoxSelection();	
	t.draw();//Loading existing opened page only
	
	//t.ajax.url( "paginated?name="+""+"&sortname="+""+"&phonecode="+""+"").load();
	// t.search( '' ).columns().search( '' ).draw();//Will work without global search4
	// Example call to load a new file
	//  t.fnReloadAjax( 'media/examples_support/json_source2.txt' );

	// Example call to reload from original file
	//t.fnReloadAjax();
}


function searchAndReloadGrid(stateNameId,loksabhaNameId,assemblyNameId,pollingBoothNameId) {
	clearCheckBoxSelection();
	var table = $('#votersEnrolledId').DataTable();
	table.ajax.url(
			"/pub/ec/paginated?stateNameId=" + stateNameId +
			 "&loksabhaNameId=" +loksabhaNameId+ 
			 "&assemblyNameId=" +assemblyNameId+ 
			 "&pollingBoothNameId=" +pollingBoothNameId).load();
	
	$("#successMsgId").html("<span style='font:strong'>Search completed. Check the grid.</span>");
}
function searchAndReloadGridOfflineVoters(stateNameId,loksabhaNameId,assemblyNameId,pollingBoothNameId) {
	clearCheckBoxSelection();
	var table = $('#votersEnrolledOfflineId').DataTable();
	table.ajax.url(
			"/pub/ec/paginatedOffline?stateNameId=" + stateNameId +
			 "&loksabhaNameId=" +loksabhaNameId+ 
			 "&assemblyNameId=" +assemblyNameId+ 
			 "&pollingBoothNameId=" +pollingBoothNameId).load();
	
	$("#successMsgId").html("<span style='font:strong'>Search completed. Check the grid.</span>");
}
function searchAndReloadGridOnlineVoters(stateNameId,loksabhaNameId,assemblyNameId,pollingBoothNameId) {
	clearCheckBoxSelection();
	var table = $('#votersEnrolledOnlineId').DataTable();
	table.ajax.url(
			"/pub/ec/paginatedOffline?stateNameId=" + stateNameId +
			 "&loksabhaNameId=" +loksabhaNameId+ 
			 "&assemblyNameId=" +assemblyNameId+ 
			 "&pollingBoothNameId=" +pollingBoothNameId).load();
	
	$("#successMsgId").html("<span style='font:strong'>Search completed. Check the grid.</span>");
}

//Get Selected Row IDs
function GetSelectedRowID() {
	var table = $('#votersEnrolledId').DataTable();
	var checkedRowIdAndName = [];
	var checkedRowIds = [];
	var checkedRowNames = [];
	var recordCount = 0;
	$(".chkIndvRow").each(function() {
		if ($(this).is(':checked')) {
			// $(this).val() will return row index
			var selectedRows = table.rows($(this).val()).data();
			checkedRowNames.push(selectedRows[0].stateName +" => "+
					             selectedRows[0].loksabhaName +" => "+
					              selectedRows[0].pollingBoothName +" => "
					             +selectedRows[0].voterName + "</br>");
			checkedRowIds.push(selectedRows[0].id);
			recordCount++;
		}
	});
	// Here You will get all selected persons ID
	// alert("Selected Rows:- " + checkedRowIds);
	if (recordCount > 0) {
		checkedRowIdAndName.push(checkedRowIds);
		checkedRowIdAndName.push(checkedRowNames);
		checkedRowIdAndName.push(recordCount);
	}
	return checkedRowIdAndName;
}

//## Code for loading grid ##
var t;
function loadGrid(stateNameId,loksabhaNameId,assemblyNameId,pollingBoothNameId) {
	t = $('#votersEnrolledId').DataTable(
			{
				"responsive": true,
				"retrieve" : true,// used for refreshing
				"bAutoWidth" : true,
				//"scrollY" : '110vh',
				//"scrollCollapse" : true,
				"lengthMenu" : [ 5, 10, 15 ],
				"processing" : true,
				"serverSide" : true,
				"ordering" : true,
				"searching" : true,
				"aaSorting" : [ [ 2, "asc" ], [ 3, "asc" ], [ 4, "asc" ] ],
				"ajax" : {
					
					"url" : "/pub/ec/paginated?stateNameId=" + stateNameId + "&loksabhaNameId=" + loksabhaNameId+"&assemblyNameId="+assemblyNameId
					+"&pollingBoothNameId="+pollingBoothNameId,

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
						"sortable" : false,
					},

					{
						"data" : "id",
						"name" : "ID",
						"title" : "ID",
						"sortable" : false,
						"searchable" : false,
						"bVisible" : false, // used for hiding a column
						
					},
					{
						"data" : "stateName",
						"name" : "stateName",
						"title" : "State"
					},
					{
						"data" : "loksabhaName",
						"name" : "loksabhaName",
						"title" : "Loksabha"
					},
					{
						"data" : "assemblyName",
						"name" : "assemblyName",
						"title" : "Assembly"
					},
					{
						"data" : "pollingBoothName",
						"name" : "pollingBoothName",
						"title" : "Booth"
					},
					
                   {
						"data" : "pollingBoothNo",
						"name" : "pollingBoothNo",
						"title" : "B.No",
						"searchable" : false,
					},
					
					{
						"data" : "voterName",
						"name" : "voterName",
						"title" : "Voter"
					},
					{
						"data" : "voting_mode",
						"render" : function(data, type, row) {
							if(data==0)
							  return '<Strong style="color:blue;">Offline</strong>';
							if(data==1)
							  return '<Strong style="color:red;">Online</strong>'; 
						},
						"title" : "Voting"
					},
			
			        {
						"data" : "voterId",
						"name" : "voterId",
						"title" : "voterId"
					},			       
					
					{
						"data" : null,
						"sortable" : false,
						"render" : function(data, type, row) {
							return '<a class="vClass" href=?record_id='
							+ row.id + '>'
							+ '<i class="fa fa-eye"></i>' + '</a>';
						}
					}  ]
			});

	
}// End of loading grid



//## Code for loading grid ##
var toffline;
function loadGridOffline(stateNameId,loksabhaNameId,assemblyNameId,pollingBoothNameId) {
	toffline = $('#votersEnrolledOfflineId').DataTable(
			{
				"responsive": true,
				"retrieve" : true,// used for refreshing
				"bAutoWidth" : true,
				//"scrollY" : '110vh',
				//"scrollCollapse" : true,
				"lengthMenu" : [ 5, 10, 15 ],
				"processing" : true,
				"serverSide" : true,
				"ordering" : true,
				"searching" : true,
				"aaSorting" : [ [ 2, "asc" ], [ 3, "asc" ], [ 4, "asc" ] ],
				"ajax" : {
					
					"url" : "/pub/ec/paginatedOffline?stateNameId=" + stateNameId + "&loksabhaNameId=" + loksabhaNameId+"&assemblyNameId="+assemblyNameId
					+"&pollingBoothNameId="+pollingBoothNameId,

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
						"sortable" : false,
						"searchable" : false,
						"bVisible" : false, // used for hiding a column
						
					},
					{
						"data" : "stateName",
						"name" : "stateName",
						"title" : "State"
					},
					{
						"data" : "loksabhaName",
						"name" : "loksabhaName",
						"title" : "Loksabha"
					},
					{
						"data" : "assemblyName",
						"name" : "assemblyName",
						"title" : "Assembly"
					},
					{
						"data" : "pollingBoothName",
						"name" : "pollingBoothName",
						"title" : "Booth"
					},
					
                   {
						"data" : "pollingBoothNo",
						"name" : "pollingBoothNo",
						"title" : "B.No",
						"searchable" : false,
					},
					
					{
						"data" : "voterName",
						"name" : "voterName",
						"title" : "Voter"
					},
					{
						"data" : "voting_mode",
						"render" : function(data, type, row) {
							if(data==0)
							  return '<Strong style="color:blue;">Offline</strong>';
							if(data==1)
							  return '<Strong style="color:red;">Online</strong>'; 
						},
						"title" : "Voting"
					},
			
			        {
						"data" : "voterId",
						"name" : "voterId",
						"title" : "voterId"
					},			       
					
					{
						"data" : null,
						"sortable" : false,
						"render" : function(data, type, row) {
							return '<a class="vClass" href=?record_id='
							+ row.id + '>'
							+ '<i class="fa fa-eye"></i>' + '</a>';
						}
					}  ]
			});

	
}// End of loading grid


//## Code for loading grid ##
var tonline;
function loadGridOnline(stateNameId,loksabhaNameId,assemblyNameId,pollingBoothNameId) {
	tonline = $('#votersEnrolledOnlineId').DataTable(
		{
				"responsive": true,
				"retrieve" : true,// used for refreshing
				"bAutoWidth" : true,
				//"scrollY" : '110vh',
				//"scrollCollapse" : true,
				"lengthMenu" : [ 5, 10, 15 ],
				"processing" : true,
				"serverSide" : true,
				"ordering" : true,
				"searching" : true,
				"aaSorting" : [ [ 2, "asc" ], [ 3, "asc" ], [ 4, "asc" ] ],
				"ajax" : {
					
					"url" : "/pub/ec/paginatedOnline?stateNameId=" + stateNameId + "&loksabhaNameId=" + loksabhaNameId+"&assemblyNameId="+assemblyNameId
					+"&pollingBoothNameId="+pollingBoothNameId,

					"type" : "POST",
				},

				"columns" : [
					{
						"searchable" : false,
						"orderable" : false,
						"targets" : 0,
						"render" : function(data, type, full, meta) {
							return meta.row + 1;// Will send row index
						},
						
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
						"sortable" : false,
					},

					{
						"data" : "id",
						"name" : "ID",
						"title" : "ID",
						"sortable" : false,
						"searchable" : false,
						"bVisible" : false, // used for hiding a column
						
					},
					{
						"data" : "stateName",
						"name" : "stateName",
						"title" : "State"
					},
					{
						"data" : "loksabhaName",
						"name" : "loksabhaName",
						"title" : "Loksabha"
					},
					{
						"data" : "assemblyName",
						"name" : "assemblyName",
						"title" : "Assembly"
					},
					{
						"data" : "pollingBoothName",
						"name" : "pollingBoothName",
						"title" : "Booth"
					},
					
                   {
						"data" : "pollingBoothNo",
						"name" : "pollingBoothNo",
						"title" : "B.No",
						"searchable" : false,
					},
					
					{
						"data" : "voterName",
						"name" : "voterName",
						"title" : "Voter"
					},
					{
						"data" : "voting_mode",
						"render" : function(data, type, row) {
							if(data==0)
							  return '<Strong style="color:blue;">Offline</strong>';
							if(data==1)
							  return '<Strong style="color:red;">Online</strong>'; 
						},
						"title" : "Voting"
					},
			
			        {
						"data" : "voterId",
						"name" : "voterId",
						"title" : "voterId"
					},			       
					
					{
						"data" : null,
						"sortable" : false,
						"render" : function(data, type, row) {
							return '<a class="vClass" href=?record_id='
							+ row.id + '>'
							+ '<i class="fa fa-eye"></i>' + '</a>';
						}
					},
					{
						"data" : null,
						"sortable" : false,
						"render" : function(data, type, row) {
							return '<a class="history" href=#record_id='
							+ row.id + '>'
							+ '<i class="fa fa-history"></i>' + '</a>';
						}
					}  ]
			});

	
}// End of loading grid
//###########################################################//
//########## End of grid and CRUD related code #############//
//#########################################################//



//#######################################################################//
//########## Start: Methods for supporting above operations ############//
//#####################################################################//

var baseUrl = '/pub/ec';

function clearFormData() {
	//alert("Clearing form data");
	$("#addEditFormId").trigger("reset");
	$('#stateNameId').val("");
	$('#loksabhaNameId').val("");	
	$('#assemblyNameId').val("");
	$('#pollingBoothNameId').val("");
	$('#pollingBoothNoId').val("");
	
	$('#voterNameId').val("");
	$('#sexId').val("");
	$('#dobForPageId').val("");
	$('#fatherNameId').val("");
	$('#voterIdId').val("");
	$('#voting_modeId').val("");	
	$('#addressId').val("");
	
	
	//Will make loksabha combo clean
	$('#loksabhaNameId').empty()
    .append('<option selected="selected" value="">-Select-</option>');
    //Will make assembly combo clean
	$('#assemblyNameId').empty()
    .append('<option selected="selected" value="">-Select-</option>');
    //Will make polling booth combo clean
	$('#pollingBoothNameId').empty()
    .append('<option selected="selected" value="">-Select-</option>');
}
//This will be called for cleaning the error message already shown on the page.
function cleanAllMsg() {
	$("#successMsgId").html("");
	$('#deleteSuccessMsgId').html("");
	$('#deleteSelectedSuccessMsgId').html("");
	$('#stateNameId_err').html("");
	$('#loksabhaNameId_err').html("");
	$('#assemblyNameId_err').html("");
	$('#pollingBoothNameId_err').html("");
	$('#pollingBoothNoId_err').html("");
	
	
	$('#voterNameId_err').html("");
	$('#sexId_err').html("");
	$('#dobForPageId_err').html("");
	$('#fatherNameId_err').html("");
	$('#voterIdId_err').html("");
	$('#voting_modeId_err').html("");	
	$('#addressId_err').html("");
	
	
}

function hideAllRequired() {	
	$("#searchMsgId").hide();
	$('.commonButtonActionClass').hide();
	
	$('#voterNameLabledId').hide();
	$('#sexLabledId').hide();
	$('#dobForPageLabledId').hide();
	$('#fatherNameLabledId').hide();
	$('#voterIdLabledId').hide();
	$('#voting_modeLabledId').hide();	
	$('#addressLabledId').hide();
	
}

function showAllHidden() {	
	$("#searchMsgId").show();
	$('.commonButtonActionClass').show();
	
	$('#voterNameLabledId').show();
	$('#sexLabledId').show();
	$('#dobForPageLabledId').show();
	$('#fatherNameLabledId').show();
	$('#voterIdLabledId').show();
	$('#voting_modeLabledId').show();	
	$('#addressLabledId').show();
	
	$("#searchHideContentId").show();
	$("#searchHide1ContentId").show();
}

function cleanAllHiddenInput() {
	$('#votersEnrolledRecordId').val("");
	$('#recordIdForDelete').val("");
	
}

//Apply read only on all the fields of the form so that thevalue of the field
//can not be changed.
function applyReadOnlyProp() {	
	$('#stateNameId').attr('disabled', true);
	$('#loksabhaNameId').attr('disabled', true);
	$('#assemblyNameId').attr('disabled', true);
	$('#pollingBoothNameIdId').attr('disabled', true);
	$('#pollingBoothNoId').attr("readonly", true);	
	
	$('#voterNameId').prop("readonly", true);
	$('#sexId').attr("readonly", true);
	$('#dobForPageId').prop("readonly", true);
	$('#fatherNameId').prop("readonly", true);
	$('#voterIdId').prop("readonly", true);
	
	$('input[name=voting_mode]').attr("disabled",true);
	
	$('#addressId').prop("readonly", true);
}

//Remove read only from all the fields of the form so that they could be
//changed.
function removeReadOnlyProp() {	
	$('#stateNameId').attr('disabled', false);
	$('#loksabhaNameId').attr('disabled', false);
	$('#assemblyNameId').attr('disabled', false);
	$('#pollingBoothNameIdId').attr('disabled', false);
	$('#pollingBoothNoId').attr("readonly", false);	
	
	$('#voterNameId').prop("readonly", false);
	$('#sexId').attr("readonly", false);
	$('#dobForPageId').prop("readonly", false);
	$('#fatherNameId').prop("readonly", false);
	$('#voterIdId').prop("readonly", true);
	
	
	$('input[name=voting_mode]').attr("disabled",false);
	
	$('#addressId').prop("readonly", false);
}
/**
$('#voterlocationId').on('click', function(e) {
   $("#voterLocationShowHideId").hide();
   $("#voterDetailShowHideId").show();
  
});

 $("#voterDetailShowHideId").hide();
$('#voterDetailId').on('click', function(e) {  
   $("#voterLocationShowHideId").show();
   $("#voterDetailShowHideId").hide();
});
**/


	
//If the form requires anything pre-loaded. it can be done here.
function preparePage() {
   loadCountryCombo();  
}

//This will be called with an ajax response object and it will be used for
//loading the page with the response.
function loadRecordInForm(response) {
	$('#votersEnrolledRecordId').val(response.id);
		
	//Clean and load combo
			
	$('#loksabhaNameId').empty().append('<option  value="">-Select-</option>');	
	selectedId=response.loksabhaNameId;
	loadStateCombo(response.stateNameId,selectedId);	
		
	$('#assemblyNameId').empty().append('<option  value="">-Select-</option>');	
	selectedId=response.assemblyNameId;
	loadAssemblyCombo(response.loksabhaNameId,selectedId);
	
	$('#pollingBoothNameId').empty().append('<option  value="">-Select-</option>');	
	selectedId=response.pollingBoothNameId;
	loadPollingBoothCombo(response.assemblyNameId,selectedId);
	
	
	
	// Assign combo values for getting selected
	$('#stateNameId').val(response.stateNameId);
	$('#loksabhaNameId').val(response.loksabhaNameId);	
	$('#assemblyNameId').val(response.assemblyNameId);
	
	$('#pollingBoothNameId').val(response.pollingBoothNameId);
	
	
	
	//Voter's Details
	
	$('#voterNameId').val(response.voterName);
	$('#sexId').val(response.sex);
	$('#dobForPageId').val(response.dobForPage);
	$('#fatherNameId').val(response.fatherName);
	$('#voterIdId').val(response.voterId);
	if(response.voting_mode==0)
	  $("#offlineVoting_modeId").prop("checked", true);
	else
	  $("#onlineVoting_modeId").prop("checked", true);  
	
	
	
	$('#addressId').val(response.address);
	
	
}



//Fetch a record based on id
function getIdBasedRecord(id) {
	/* stop form from submitting normally */
	event.preventDefault();	
	method = 'GET';
	url = baseUrl + "/getRecord" + "?id=" + id;
	// alert("posting url = "+url);
	$.ajax({
		type : method,
		url : url,
		data : {},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			// alert("Response is coming");
			$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
			if(response.status){
				//alert(response.formObject.stateNameId);
				
				loadRecordInForm(response.formObject);
			}else{
			     //alert("Error is coming= "+response.fieldErrMsgMap);
				showBusinessEerror(response.fieldErrMsgMap);
			}
			// Now load the page with response
			if (response.status == "ErrorFree")
				loadRecord(response);
			else
				showBusinessEerror(response);
		},
		error : function(jqXHR, exception) {			
			formatErrorMessage(jqXHR, exception);
		}
	});	
}// End of getIdBasedRecord

function showBusinessEerror(fieldErrMsgMap) {	
	$('#stateNameId_err').html(fieldErrMsgMap.stateNameId);	
	$('#loksabhaNameId_err').html(fieldErrMsgMap.loksabhaNameId);
	$('#assemblyNameId_err').html(fieldErrMsgMap.assemblyNameId);
	$('#pollingBoothNameId_err').html(fieldErrMsgMap.pollingBoothName);
	$('#pollingBoothNoId_err').html(fieldErrMsgMap.pollingBoothNo);
	
	
	$('#voterNameId_err').html(fieldErrMsgMap.voterName);	
	$('#sexId_err').html(fieldErrMsgMap.sex);	
	$('#dobForPageId_err').html(fieldErrMsgMap.dobForPage);	
	$('#fatherNameId_err').html(fieldErrMsgMap.fatherName);	
	$('#voterIdId_err').html(fieldErrMsgMap.voterId);	
	$('#voting_modeId_err').html(fieldErrMsgMap.voting_mode);	
	$('#addressId_err').html(fieldErrMsgMap.address);	
}

function saveAndUpdateRecord() { 
	
	/* stop form from submitting normally */
	event.preventDefault();
	/* get the form data of the from */
	// var formSerializeData = $('#commonFormId').serialize();
	var id = $('#pollingBoothRecordId').val();	
	var stateNameId = $('#stateNameId').val();	
	var loksabhaNameId = $('#loksabhaNameId').val();
	var assemblyNameId = $('#assemblyNameId').val();
	var pollingBoothNameId = $('#pollingBoothNameId').val();
		
	var voterName= $('#voterNameId').val();
	var sex= $('#sexId').val();
	var dobForPage= $('#dobForPageId').val();
	var fatherName= $('#fatherNameId').val();
	var voterId= $('#voterIdId').val();
	var voting_mode= $('#voting_modeId').val();
	var address= $('#addressId').val();
	
	//alert("stateNameId = "+stateNameId);
	//alert("loksabhaNameId = "+loksabhaNameId);
	var json = {
			"id" : id,
			"stateNameId" : stateNameId,
			"loksabhaNameId" : loksabhaNameId,
			"assemblyNameId" :assemblyNameId,
			"pollingBoothNameId" : pollingBoothNameId,
			
			"voterName" : voterName,
			"sex" : sex,
			"dobForPage" : dobForPage,
			"fatherName" : fatherName,
			"voterId" : voterId,
			"voting_mode" : voting_mode,
			"address":address
	};

	
	$.ajax({
		type : 'POST',
		url : baseUrl + "/saveAndUpdateRecord",
		data : JSON.stringify(json),

		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			// alert("Response is coming");
			$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
			if(response.status){
				//Do something if required	
				$('#saveFormButtonId').attr("disabled", true);
			}else{
				//alert("Form has an error");
				showBusinessEerror(response.fieldErrMsgMap);
			}

		},
		error : function(jqXHR, exception) {			
			formatErrorMessage(jqXHR, exception);
		}
	});
	return false;
}

function deleteRecord(id) {	
	/* stop form from submitting normally */
	event.preventDefault();	
	method = 'GET';
	url = baseUrl + "/deleteRecord" + "?id=" + id;
	// alert("posting url = "+url);
	$
	.ajax({
		type : method,
		url : url,
		data : {},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			$('#deleteSuccessMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
			if(response.status){
				//Do something if required			
			}else{
				showBusinessEerror(response.fieldErrMsgMap);
			}
		},
		error : function(jqXHR, exception) {			
			formatErrorMessage(jqXHR, exception);
		}
	});	
}

function deleteSelectedRecord(recordIdArray) {
	//alert("Going to delete a record "+recordIdArray);
	//alert("recordIdArray[0] = "+GetSelectedRowID()[0][0]);
	//alert("recordIdArray[1] = "+GetSelectedRowID()[0][1]);
	/* stop form from submitting normally */
	event.preventDefault();
	
	method = 'POST';
	url = baseUrl + "/deleteSelected";
	// alert("posting url = "+url);
	var idsArray=[];
	for(i=0;i<recordIdArray.length;i++)
		idsArray[i]=recordIdArray[i];	
	$.ajax({
		type : method,
		url : url,
		data : JSON.stringify(idsArray),//JSON.stringify(sendRecordIdArray),
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			$('#deleteSelectedSuccessMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
			if(response.status){
				//Do something if required
			}else{				
				showBusinessEerror(response.fieldErrMsgMap);
			}

		},
		error : function(jqXHR, exception) {			
			formatErrorMessage(jqXHR, exception);
		}
	});
}



function loadCountryCombo(){	
	/* stop form from submitting normally */	
	method = 'GET';
	url = "/pvt/master/state" + "/list";	
	$
	.ajax({
		type : method,
		url : url,
		data : {},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {			
			if(response.status){
				var ele = document.getElementById('stateNameId');	       
		        for (var i = 0; i < response.comboList.length; i++) {		        	
		            // POPULATE SELECT ELEMENT WITH JSON.
		            ele.innerHTML = ele.innerHTML +
		                '<option value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
		        }
				
				//Do something if required			
			}else{
				showBusinessEerror(response.fieldErrMsgMap);
			}
		},
		error : function(jqXHR, exception) {			
			formatErrorMessage(jqXHR, exception);
		}
	});
	
}




function loadStateCombo(id,selectedId){	
	
	/* stop form from submitting normally */	
	method = 'GET';
	url = "/pvt/master/loksabha" + "/list?id=" + id;		
	$
	.ajax({
		type : method,
		url : url,
		data : {},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {			
			if(response.status){				
						
				var ele = document.getElementById('loksabhaNameId');	       
		        for (var i = 0; i < response.comboList.length; i++) {		        	
		            // POPULATE SELECT ELEMENT WITH JSON.
		        	if(selectedId == response.comboList[i]['id'])
		            ele.innerHTML = ele.innerHTML +
		                '<option selected value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
		        	else
		        		 ele.innerHTML = ele.innerHTML +
			                '<option value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
			        		
		        }
				
				//Do something if required			
			}else{
				
				showBusinessEerror(response.fieldErrMsgMap);
			}
		},
		error : function(jqXHR, exception) {			
			formatErrorMessage(jqXHR, exception);
		}
	});
	
	}
	
	
	
function loadAssemblyCombo(id,selectedId){		
	/* stop form from submitting normally */	
	method = 'GET';
	url = "/pvt/master/assembly/" + "list?id=" + id;		
	$
	.ajax({
		type : method,
		url : url,
		data : {},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {			
			if(response.status){				
						//alert("Response is coming ");
				var ele = document.getElementById('assemblyNameId');	       
		        for (var i = 0; i < response.comboList.length; i++) {		        	
		            // POPULATE SELECT ELEMENT WITH JSON.
		        	if(selectedId == response.comboList[i]['id'])
		            ele.innerHTML = ele.innerHTML +
		                '<option selected value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
		        	else
		        		 ele.innerHTML = ele.innerHTML +
			                '<option value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
			        		
		        }
				
				//Do something if required			
			}else{
				
				showBusinessEerror(response.fieldErrMsgMap);
			}
		},
		error : function(jqXHR, exception) {			
			formatErrorMessage(jqXHR, exception);
		}
	});
	
	}
	
	
function loadPollingBoothCombo(id,selectedId){	

	/* stop form from submitting normally */	
	method = 'GET';
	url = "/pvt/master/pollingBooth/" + "list?id=" + id;		
	$
	.ajax({
		type : method,
		url : url,
		data : {},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {			 
			if(response.status){				
						//alert("Response is coming ");
				var ele = document.getElementById('pollingBoothNameIdId');	       
		        for (var i = 0; i < response.comboList.length; i++) {		        	
		            // POPULATE SELECT ELEMENT WITH JSON.
		        	if(selectedId == response.comboList[i]['id'])
		            ele.innerHTML = ele.innerHTML +
		                '<option selected value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
		        	else
		        		 ele.innerHTML = ele.innerHTML +
			                '<option value="' + response.comboList[i]['id'] + '">' + response.comboList[i]['name'] + '</option>';
			        		
		        }
				
				//Do something if required			
			}else{
				
				showBusinessEerror(response.fieldErrMsgMap);
			}
		},
		error : function(jqXHR, exception) {			
			formatErrorMessage(jqXHR, exception);
		}
	});
	
	}	
	



//#######################################################################//
//########## End: Methods for supporting above operations ############//
//#####################################################################//

function loadGridAuditTrail(voterId) {
		
	 $('#votersAuditTrailId').DataTable(
				{
					"responsive": true,
					"retrieve" : true,// used for refreshing
					"bAutoWidth" : true,
					//"scrollY" : '110vh',
					//"scrollCollapse" : true,
					"lengthMenu" : [ 5, 10, 15],
					"processing" : true,
					"serverSide" : true,
					"ordering" : true,
					"searching" : true,
					"aaSorting" : [ [ 1, "asc" ], [ 4, "asc" ] ],
					"ajax" : {
						
						"url" : "/pub/ec/auditTrailPaginated?voterId=" + voterId,

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
