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
	preparePage();
	// [1] Open an add modal.
	$(document).on("click",".addClass",function() {
		clearFormData();
		cleanAllMsg();
		hideAllRequired();
		cleanAllHiddenInput();
		removeReadOnlyProp();
		$('#acandidateNameLabledId').show();
		$('#saveFormButtonId').attr("disabled", false);			
		$("#commonModalTitleId").html('Add a candidate');
		$('.commonButtonActionClass').show();
		$('.commonButtonActionClass').attr('id',
		'saveFormButtonId');
		$("#saveFormButtonId").html('Save');
		$('#modal-common').modal('toggle');
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
		hideAllRequired();
		cleanAllHiddenInput();
		removeReadOnlyProp();
		$('#acandidateNameLabledId').show();
		$('#updateFormButtonId').attr("disabled", false);		
		getIdBasedRecord($(this).attr("href").split('=')[1]);

		$("#commonModalTitleId")
		.html('Update a candidate');
		$('.commonButtonActionClass').show();
		$('.commonButtonActionClass').attr('id',
		'updateFormButtonId');
		$("#updateFormButtonId").html('Update');
		$('#modal-common').modal('toggle');
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
		hideAllRequired();
		cleanAllHiddenInput();
		applyReadOnlyProp();
		$('#acandidateNameLabledId').show();
		getIdBasedRecord($(this).attr("href").split('=')[1]);
		$("#commonModalTitleId").html('View a record');
		$('#modal-common').modal('toggle');
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
	});

	// [11] Search record
	$(document).on("click", "#searchFormButtonId", function(e) {
		var stateId = $('#stateNameId').val();
		var loksabhaNameId = $('#loksabhaNameId').val();
		var assemblyNameId = $('#assemblyNameId').val();			
		searchAndReloadGrid(stateId,loksabhaNameId,assemblyNameId);
	});

	// [12] New one.
	$("#reloadGrid").click(function() {
		reloadGrid();
	});
	
	// [12] New one.
	$("#refreshGrid").click(function() {
		refreshGridPage();
	});
	
	

});// End of document ready

$('#loksabhaNameId').on('change', function(e) {
	cleanAllMsg();	
});
$('#stateNameId').on('change', function(e) {
	cleanAllMsg();
	//alert("Going to load State combo");
	
	$('#loksabhaNameId').empty().append('<option  value="">-Select-</option>');	
	loadStateCombo($('#stateNameId').val(),"");
	
	
	
	$('#partyNameId').empty().append('<option  value="">-Select-</option>');	
	loadStateBasedPartyCombo($('#stateNameId').val(),"");
});

$('#loksabhaNameId').on('change', function(e) {
	cleanAllMsg();
	//alert("Going to load State combo");
	
	$('#assemblyNameId').empty().append('<option  value="">-Select-</option>');	
	loadAssemblyCombo($('#loksabhaNameId').val(),"");
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

	window.location.replace("/pvt/master/acandidate/");


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


function searchAndReloadGrid(stateNameId,loksabhaNameId,assemblyNameId,acandidateName) {
	clearCheckBoxSelection();
	var table = $('#loksabhaCandidateId').DataTable();
	table.ajax.url(
			"/pvt/master/Acandidate/paginated?stateNameId=" + stateNameId + "&loksabhaNameId=" +loksabhaNameId+ "&assemblyNameId=" +assemblyNameId).load();
	$("#successMsgId").html("<span style='font:strong'>Search completed. Check the grid.</span>");
}

//Get Selected Row IDs
function GetSelectedRowID() {
	var table = $('#assemblyCandidateId').DataTable();
	var checkedRowIdAndName = [];
	var checkedRowIds = [];
	var checkedRowNames = [];
	var recordCount = 0;
	$(".chkIndvRow").each(function() {
		if ($(this).is(':checked')) {
			// $(this).val() will return row index
			var selectedRows = table.rows($(this).val()).data();
			checkedRowNames.push(selectedRows[0].stateName + " => "+
					             selectedRows[0].loksabhaName + " => "+
					              selectedRows[0].assemblyName + " => "+
					             selectedRows[0].acandidateName + "</br>");
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
function loadGrid(stateNameId,loksabhaNameId,assemblyNameId,acandidateName) {
    
	t = $('#assemblyCandidateId').DataTable(
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
				"aaSorting" : [ [ 3, "asc" ], [ 4, "asc" ], [ 5, "asc" ], [ 6, "asc" ], [ 7, "asc" ] ],
				"ajax" : {
					
					"url" : "/pvt/master/Acandidate/paginated?stateNameId=" + stateNameId + "&loksabhaNameId=" + loksabhaNameId+ "&assemblyNameId=" + assemblyNameId+"&acandidateName="+acandidateName,

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
						"searchable" : false,
					},

					{
						"data" : "id",
						"name" : "ID",
						"title" : "ID",
						"searchable" : false,
						"bVisible" : false, // used for hiding a column
					},
					{
						"data" : "electionYear",
						"name" : "electionYear",
						"title" : "Year"
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
						"data" : "acandidateName",
						"name" : "acandidateName",
						"title" : "Candidate"
					},
					{
						"data" : "partyName",
						"name" : "partyName",
						"title" : "Party"
					},
					
					{
                        "render": function (data, type, JsonResultRow, meta) {
                            return '<img height="50" width="50" src="'+JsonResultRow.imgName+'">';
                        },
                        "sortable" : false,
                        "searchable" : false
                        
                    },
					
					{
						"data" : null,
						"sortable" : false,
						"render" : function(data, type, row) {
							return '<a class="vClass" href=?record_id='
							+ row.id + '>'
							+ '<i class="fa fa-eye"></i>' + '</a>';
						},
						
					},
					{
						"data" : null,
						"sortable" : false,
						"render" : function(data, type, row) {
							return '<a class="eClass" href=?record_id='
							+ row.id + '>'
							+ '<i  class="fa fa-edit"></i>'
							+ '</a>';
						},
						"bVisible" : false, // used for hiding a column
					},
					{
						"data" : null,
						"sortable" : false,
						"render" : function(data, type, row) {
							return '<a class="dClass" href=?record_id='
							+ row.id + '>'
							+ '<i class="fa fa-trash-o"></i>'
							+ '</a>';
						},
						"bVisible" : false, // used for hiding a column
					} ]
			});

	
}// End of loading grid

//###########################################################//
//########## End of grid and CRUD related code #############//
//#########################################################//



//#######################################################################//
//########## Start: Methods for supporting above operations ############//
//#####################################################################//

var baseUrl = '/pvt/master/Acandidate';

function clearFormData() {
	//alert("Clearing form data");
	$("#addEditFormId").trigger("reset");
	$('#loksabhaNameId').val("");
	$('#assemblyNameId').val("");
	$('#stateNameId').val("");
	$('#acandidateNameId').val("");
	$('#acandidateNoId').val("");
	$('#electionYearId').val("");
	$('#partyNameId').val("");
	
	
	//Will make loksabha combo clean
	$('#loksabhaNameId').empty()
    .append('<option selected="selected" value="">-Select-</option>');
    $('#assemblyNameId').empty()
    .append('<option selected="selected" value="">-Select-</option>');
}
//This will be called for cleaning the error message already shown on the page.
function cleanAllMsg() {
	$("#successMsgId").html("");
	$('#deleteSuccessMsgId').html("");
	$('#deleteSelectedSuccessMsgId').html("");
	$('#loksabhaNameId_err').html("");
	$('#assemblyNameId_err').html("");
	$('#stateNameId_err').html("");
	$('#acandidateNameId_err').html("");
	$('#acandidateNoId_err').html("");
	
	$('#electionYear_err').html("");
	$('#partyNameId_err').html("");
	
}

function hideAllRequired() {	
	$("#searchMsgId").hide();
	$('.commonButtonActionClass').hide();
	
	$('#acandidateNameLabledId').hide();
	$('#partyCategoryLabledId').hide();
	$('#partyNameLableId').hide();
	$('#acandidateNoLabledId').hide();
	
}

function cleanAllHiddenInput() {
	$('#acandidateRecordId').val("");
	$('#recordIdForDelete').val("");
	
	$('#acandidateNameLabledId').show();
	$('#partyCategoryLabledId').show();
	$('#partyNameLableId').show();
	
	$('#acandidateNoLabledId').show();
}

//Apply read only on all the fields of the form so that thevalue of the field
//can not be changed.
function applyReadOnlyProp() {	
	$('#stateNameId').attr('disabled', true);
	$('#loksabhaNameId').attr('disabled', true);
	$('#assemblyNameId').attr('disabled', true);
	$('#acandidateNameId').prop("readonly", true);
	$('#acandidateNoId').prop("readonly", true);	
	$('#electionYearId').attr('disabled', true);
	$('#partyNameId').attr('disabled', true);
	$("input[type=radio]").attr('disabled', true);
		
}

//Remove read only from all the fields of the form so that they could be
//changed.
function removeReadOnlyProp() {	
	$('#stateNameId').attr('disabled', false);
	$('#loksabhaNameId').attr('disabled', false);
	$('#assemblyNameId').attr('disabled', false);
	$('#acandidateNameId').prop("readonly", false);
	$('#acandidateNoId').prop("readonly", false);
	$('#electionYearId').attr('disabled', false);
	$('#electionYearId').attr('disabled', false);
	$("input[type=radio]").attr('disabled', false);
	
		
}

//If the form requires anything pre-loaded. it can be done here.
function preparePage() {
   loadCountryCombo();  
}

//This will be called with an ajax response object and it will be used for
//loading the page with the response.
function loadRecordInForm(response) {
	$('#acandidateRecordId').val(response.id);	
	$('#stateNameId').val(response.stateNameId);
	//This will clean the loksabha combo of loksabha and show without select in the combo box	
	$('#loksabhaNameId').empty().append('<option  value="">-Select-</option>');		
	loadStateCombo(response.stateNameId,response.loksabhaNameId);
	$('#assemblyNameId').empty().append('<option  value="">-Select-</option>');		
	loadAssemblyCombo(response.loksabhaNameId,response.assemblyNameId);
	
	$('#loksabhaNameId').val(response.loksabhaNameId);
	$('#assemblyNameId').val(response.assemblyNameId);
	
	$('#acandidateNameId').val(response.acandidateName);
	$('#acandidateNoId').val(response.acandidateNo);
	$('#electionYearId').val(response.electionYear);
	
	$('#partyNameId').empty().append('<option  value="">-Select-</option>');	
	loadStateBasedPartyCombo(response.stateNameId,response.partyNameId)
	
	
	
	
}

//Fetch a e=record based on id
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
	$('#acandidateNameId_err').html(fieldErrMsgMap.acandidateName);
	$('#acandidateNoId_err').html(fieldErrMsgMap.acandidateNo);
	$('#electionYear_err').html(fieldErrMsgMap.electionYear);
	$('#partyNameId_err').html(fieldErrMsgMap.partyNameId);
	
	
	
}

function saveAndUpdateRecord() { 
	
	/* stop form from submitting normally */
	event.preventDefault();
	/* get the form data of the from */
	// var formSerializeData = $('#commonFormId').serialize();
	var id = $('#acandidateRecordId').val();	
	var stateNameId = $('#stateNameId').val();	
	var loksabhaNameId = $('#loksabhaNameId').val();
	var assemblyNameId = $('#assemblyNameId').val();
	var acandidateName = $('#acandidateNameId').val();
	var acandidateNo = $('#acandidateNoId').val();	
	var electionYear = $('#electionYearId').val();
	var partyNameId = $('#partyNameId').val();	
	var nominated=$("input[name='nominated']:checked").val();
	
	//alert("stateNameId = "+stateNameId);
	//alert("loksabhaNameId = "+loksabhaNameId);
	
	var json = {
			"id" : id,
			"stateNameId" : stateNameId,
			"loksabhaNameId" : loksabhaNameId,
			"assemblyNameId":assemblyNameId,
			"acandidateName" : acandidateName,
			"acandidateNo" : acandidateNo,
			"electionYear" :electionYear,
			"partyNameId" : partyNameId,
			"nominated" :nominated
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
			 //alert("Response is coming");
			$('#successMsgId').html("<span style='color:green;font:bold;'>"+response.statusMsg+"</span>");
			if(response.status){
				//Do something if required	
				$('#saveFormButtonId').attr("disabled", true);
			}else{
				alert("Form has an error + "+response.fieldErrMsgMap);
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
	url = "/pvt/master/assembly" + "/list?id=" + id;		
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



function loadStateBasedPartyCombo(id,selectedId){	
	
	/* stop form from submitting normally */	
	method = 'GET';
	url = "/pvt/master/party/" + "/list?stateId=" + id;		
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
				//alert("Response is coming");		
				var ele = document.getElementById('partyNameId');	       
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


