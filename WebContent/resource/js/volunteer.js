$(document).ready(function(){
	var volunteerInfo;
	var acticityList;
	var selfActivityList;
	var selfService;
	var saveMethod;
	var hasService = false;
	
	var getUrlParam = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		   
		if (r != null) { 
			return unescape(r[2]);
		}
	  
		return null;
	};
	
	$("#removeServiceInfo").click(function() {
		var serviceId = $("#serviceId")[0].innerText
		var volunteerId = volunteerInfo.id;
		
		var name = $("#name").val();
		var status = "可用";
		var phone = $("#phone").val();
		var situation = $("#situation").val();
		var introduce = $("#introduce").val();
		
		if(serviceId !== volunteerId) {
			alert("您不是队长，没有操作权限！");
			return;
		}
		
		$.ajax({
			url : "removeServiceInfo",
			type : "post",
			data : {
				"volunteerId" : volunteerId,
				"serviceId" : serviceId,
			},
			success : function(result) {
				if(result.errorNumber === "000000") {
					alert("注销成功");
					$("#belongService").modal('hide');
					
					return;
				}
				
				alert(result.errorMessage);
			},
			error : function() {
				alert("由于网络问题，保存失败！");
			},
		});
	});
	
	$("#saveServiceInfo").click(function() {
		var id = $("#serviceId")[0].innerText;
		var captatin = $("#captatin")[0].innerText;
		
		var name = $("#name").val();
		var status = "可用";
		var phone = $("#phone").val();
		var situation = $("#situation").val();
		var introduce = $("#introduce").val();
		
		if(name === "" || phone === "" || situation === "" || introduce === "") {
			alert("上述内容均为必填项！");
			return ;
		}
		
		if(saveMethod === "updateServiceInfo" && volunteerInfo.id !== id) {
			alert("您不是队长，不能操作");
			return;
		}


		$.ajax({
			url : saveMethod,
			type : "post",
			data : {
				"id" : id,
				"name" : name,
				"captatin" : captatin,
				"phone" : phone,
				"situation" : situation,
				"introduce" : introduce,
				"status" : status,
				"count" : "1",
			},
			success : function(result) {
				alert("保存成功");
				$("#belongService").modal('hide');
			},
			error : function() {
				alert("由于网络问题，保存失败！");
			},
		});
	});
	
	$.ajax({
		url : "getSelfSelectService",
		type : "post",
		data : {
			"id" : getUrlParam("id"),
		},
		success : function(result) {
			if(result.id === null) {
				hasService = false;
				return;
			}
			hasService = true;
		},
		error : function() {
			alert("由于网络问题，注册失败！");
		},
	});
	
	$("#belongService").on("show.bs.modal", function() {
		$.ajax({
			url : "getSelfSelectService",
			type : "post",
			data : {
				"id" : getUrlParam("id"),
			},
			success : function(result) {
				selfService = result;
				if(result.id === null) {
					alert("您还没有加入任何服务队！");
					saveMethod = "saveServiceInfo";
					$("#serviceId")[0].innerText = volunteerInfo.id;
					$("#captatin")[0].innerText = volunteerInfo.name;
					$("#name").val("");
					$("#status").val("");
					$("#phone").val("");
					$("#situation").val("");
					$("#introduce").val("");
					$("#saveServiceInfo")[0].innerText = "创建";
					return;
				}
				
				$("#serviceId")[0].innerText = result.id;
				$("#captatin")[0].innerText = result.captatin;
				$("#name").val(result.name);
				$("#status").val(result.status);
				$("#phone").val(result.phone);
				$("#situation").val(result.situation);
				$("#introduce").val(result.introduce);
				
				saveMethod = "updateServiceInfo";
				$("#saveServiceInfo")[0].innerText = "修改";
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	});
	
	var removeActivityAction = function(e) {
		var activityId = $(this)[0].id;
		var volunteerId = volunteerInfo.id;
		$.ajax({
			url : "removeSelectActivity",
			type : "post",
			data : {
				"activityId" : activityId,
				"volunteerId" : volunteerId,
			},
			success : function(result) {
				$("#" + activityId).css("display", "none");
				alert("取消报名成功！");
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	};
	
	$("#applyActivityList").click(function() {
		$.ajax({
			url : "getSelfSelectActivity",
			type : "post",
			data : {
				id : volunteerInfo.id,
			},
			success : function(result) {
				var addapplyActivity = $("#addapplyActivity");
				addapplyActivity.html("");
				for(var index = 0; index < result.length; index++) {
					var appendString = '<tr> ' +
					'<td><input type="checkbox"></input></td>' +
					'<td>'+ result[index].company +'</td>' +
					'<td>'+ result[index].userId +'</td>' +
					'<td>'+ result[index].head +'</td>' +
					'<td>'+ result[index].phone +'</td>' +
					'<td>'+ result[index].title +'</td>' +
					'<td>'+ result[index].content +'</td>' +
					'<td><a href="#" id=' + result[index].id +'>取消报名</a></td>' +
					'</tr>';
				
					addapplyActivity.append(appendString);
					$("#" + result[index].id).click(removeActivityAction);
				}
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	});
	
	var signClickAction = function(e) {
		var activityId = $(this)[0].id;
		var volunteerId = volunteerInfo.id;
		
		$.ajax({
			url : "addVolunteerActivityAction",
			type : "post",
			data : {
				"activityId" : activityId,
				"volunteerId" :	volunteerId,
			},
			success : function(result) {
				$("#" + activityId).css("display", "none");
				alert("报名成功！");
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	};
	
	var addServiceBySelf = function() {
		var serviceId = $(this)[0].id;
		var volunteerId = volunteerInfo.id;
		
		if(serviceId === volunteerId) {
			$.ajax({
				url : "removeServiceInfo",
				type : "post",
				data : {
					"volunteerId" : volunteerId,
					"serviceId" : serviceId,
				},
				success : function(result) {
					alert("注销成功");
					$("#" + serviceId).css("display", "none");
				},
				error : function() {
					alert("由于网络问题，保存失败！");
				},
			});
			
			return;
		}
		
		if(!hasService) {
			$.ajax({
				url : "addServiceBySelf",
				type : "post",
				data : {
					"serviceId" : serviceId,
					"volunteerId" :	volunteerId,
				},
				success : function(result) {
					if(result.errorNumber === "000000") {
						$("#" + serviceId)[0].innerText = "取消加入";
						hasService = true;
						return;
					}
					
					alert(result.errorMessage);
				},
				error : function() {
					alert("由于网络问题，注册失败！");
				},
			});
		} else {
			$.ajax({
				url : "removeServiceBySelf",
				type : "post",
				data : {
					"serviceId" : serviceId,
					"volunteerId" :	volunteerId,
				},
				success : function(result) {
					if(result.errorNumber === "000000") {
						$("#" + serviceId)[0].innerText = "申请加入";
						hasService = false;

						return;
					}
					
					alert(result.errorMessage);
				},
				error : function() {
					alert("由于网络问题，注册失败！");
				},
			});
		}
	};
	
	$("#serviceSignList").click(function() {
		$.ajax({
			url : "getAllService",
			type : "post",
			data : {
				id : volunteerInfo.id,
			},
			success : function(result) {
				var selectServiceSign = $("#selectServiceSign");
				selectServiceSign.html("");
				for(var index = 0; index < result.length; index++) {
					var showName = "";
					if(result[index].id === volunteerInfo.id) {
						showName = "注销";
					} else if(hasService) {
						showName = "取消加入";
					} else {
						showName = "申请加入";
					}
					var appendString = '<tr> ' +
					'<td><input type="checkbox"></input></td>' +
					'<td>'+ result[index].id +'</td>' +
					'<td>'+ result[index].name +'</td>' +
					'<td>'+ result[index].captatin +'</td>' +
					'<td>'+ result[index].phone +'</td>' +
					'<td>'+ result[index].status +'</td>' +
					'<td>'+ result[index].count +'</td>' +
					'<td><a href="#" id=' + result[index].id +'>' + showName + '</a></td>' +
					'</tr>';
				
					selectServiceSign.append(appendString);
					$("#" + result[index].id).click(addServiceBySelf);
				}
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},

		});
	});
	
	$("#volunteerSignList").click(function() {
		$.ajax({
			url : "getAllActivityByVolunteer",
			type : "post",
			data : {
				id : volunteerInfo.id,
			},
			success : function(result) {
				acticityList = result;
				var volunteerSign = $("#volunteerSignBody");
				volunteerSign.html("");
				var str = "";
				for(var index = 0; index < result.length; index++) {
					var appendString = '<tr> ' +
						'<td><input type="checkbox"></input></td>' +
						'<td>'+ result[index].company +'</td>' +
						'<td>'+ result[index].userId +'</td>' +
						'<td>'+ result[index].head +'</td>' +
						'<td>'+ result[index].phone +'</td>' +
						'<td>'+ result[index].title +'</td>' +
						'<td>'+ result[index].content +'</td>' +
						'<td><a href="#" id=' + result[index].id +'>报名</a></td>' +
					'</tr>'
					
					volunteerSign.append(appendString);
					$("#" + result[index].id).click(signClickAction);
				}
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	});
	
	$.ajax({
		url : "getVolunteerModel",
		type : "post",
		data : {
			"id" : getUrlParam("id"),
		},
		success : function(result) {
			volunteerInfo = result;
			var addVolunteerInfo = $("#addVolunteerInfo");
			var appendString = '<tr> ' +
			'<td><input type="checkbox"></input></td>' +
			'<td>'+ result.id +'</td>' +
			'<td>'+ result.name +'</td>' +
			'<td>'+ result.sex +'</td>' +
			'<td>'+ result.phone +'</td>' +
			'<td></td>' +
			'<td>'+ result.street +'</td>' +
			'<td><a href="#belongService" data-toggle="modal">查看服务队</a></td>' +
		'</tr>'			
			addVolunteerInfo.append(appendString);
		},
		error : function() {
			alert("由于网络问题，注册失败！");
		},
	});
	
	$("#list li a").click(function() {
		var selectWho = $(this).attr("href");
		selectWho = selectWho.substring(1);
		$("#welcomeList").hide();
		$("#perfectUserInfo").hide();
		$("#applyActivity").hide();
		$("#serviceSign").hide();
		$("#volunteerSign").hide();
		$("#" + selectWho).show();
	});
	
	$("#userExit").click(function() {
		window.location = "./index.html";
	});
	
	$("#modifyNewPassword").click(function() {
		var id = getUrlParam('id');
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var newPasswordSure = $("#newPasswordSure").val();
		
		if(oldPassword === "" || newPassword === "" || newPasswordSure === "") {
			alert("上述选项均为必填项！");
			return;
		}
		
		if(newPassword !== newPasswordSure) {
			alert("两次新密码输入不相同！");
			return;
		}
		
		$.ajax({
			url : "modfiyVolunteerPassword",
			type : "post",
			data : {
				"id" : id,
				"oldPassword" : oldPassword,
				"newPassword" : newPassword,
			},
			success : function(result) {
				if(result.errorNumber === "000000") {
					alert("密码修改成功！");
					return;
				}
				
				alert(result.errorMessage);
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	});
});
