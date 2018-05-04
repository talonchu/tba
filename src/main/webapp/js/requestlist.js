function rejectBtn(id) {
	var rejectDiv = $(document.createElement("div"));
	rejectDiv.attr("class", "reason");
	rejectDiv.append("<br />");
	var rmessage = $(document.createElement("div"));// create child div for show
	// message
	rmessage.attr("style", "color: red; height: 5px;");
	rejectDiv.append(rmessage);
	rejectDiv.append("<br />");
	var reasonArea = $(document.createElement("textarea"));// create textarea
	// for input reason
	reasonArea.attr("name", "rejectReason")
	reasonArea.attr("cols", "40");
	reasonArea.attr("rows", "10");
	reasonArea.val("please input the reason");
	rejectDiv.append(reasonArea);
	rejectDiv.append("<br />");
	var submitReject = $(document.createElement("input"));// create button for
	// reject
	submitReject.attr("class", "reason_tbn");
	submitReject.attr("type", "button");
	submitReject.attr("value", "reject");
	var cancel = $(document.createElement("input"));// create button for cancel
	cancel.attr("class", "reason_tbn");
	cancel.attr("type", "button");
	cancel.attr("value", "cancel");
	rejectDiv.append(submitReject);
	rejectDiv.append("&nbsp;&nbsp;&nbsp;");
	rejectDiv.append(cancel);
	$(document.body).append(rejectDiv);
	$("#lock_div").show();
	rejectDiv.show();
	reasonArea.click(function() {
		if (reasonArea.val() == "please input the reason"
				|| reasonArea.val() == "") {
			reasonArea.val("");
			rmessage.text("");
		}
	});
	submitReject.click(function() {
		if (reasonArea.val().length > 150) {
			rmessage.text("150 characters limits");
			return false;
		} else if (reasonArea.val() == "please input the reason"
				|| reasonArea.val() == "") {
			rmessage.text("please input the reason");
			return false;
		}
		commitOperation(rejectDiv, "rejectRequestAction?requestId=" + id
				+ "&rejectReason=" + reasonArea.val());
	});
	cancel.click(function() {
		rmessage.text("");
		cencleOperation(rejectDiv);
		reasonArea.val("please input the reason");
	});
}

function commitOperation(operationDiv, action) {
	var form = $("#requestListForm");
	form.attr("action", action);
	form.submit();
	operationDiv.hide();
	$("#lock_div").hide();
}

function cencleOperation(operationDiv) {
	$("#lock_div").hide();
	operationDiv.hide();
}

function editBtn(id) {
	$("#lock_div").show();
	var form = $("#requestListForm");
	form.attr("action", "reEditAction?requestInfo.id=" + id);
	form.submit();
}


function approveBtn(id) {
	$("#lock_div").show();
	var form = $("#requestListForm");
	form.attr("action","approveRequestAction?requestId=" + id);
	form.submit();
}

function repealBtn(id) {
	$("#lock_div").show();
	var form  = $("#requestListForm");
	form.attr("action","repealApprovedAction?requestId="+id);
	form.submit();
}


function getDetail(id) {
	if(!$("#lock_div").is(":visible")) {
		location.href = "requestDetailAction?requestInfo.id=" + id;
	}
}