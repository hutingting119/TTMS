$(document).ready(function(){
	var organizationInfo;
	var acticityList;

	var getUrlParam = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		   
		if (r != null) { 
			return unescape(r[2]);
		}
	  
		return null;
	}
	
	$("#perfectOrganization").click(function () {
		$("#registOrganizationName").val(organizationInfo.name);
		$("#registOrganizationPeople").val(organizationInfo.people);
		$("#registOrganizationNumber").val(organizationInfo.number);
		$("#registOrganizationStyle").val(organizationInfo.style);
		$("#registOrganizationPhone").val(organizationInfo.phone);
		$("#registOrganizationBusiness").val(organizationInfo.business);
		$("#registOrganizationForm").val(organizationInfo.form);
		$("#registOrganizationLocation").val(organizationInfo.location);
		$("#registOrganizationCheck").val(organizationInfo.check);
		$("#registOrganizationEmail").val(organizationInfo.email);
	});
	
	$.ajax({
		url : "getOrganizationModel",
		type : "post",
		data : {
			"id" : getUrlParam("id"),
		},
		success : function(result) {
			organizationInfo = result;
			var perfectOrganization = $("#perfectOrganization");
			var appendString = '<tr> ' +
			'<td><input type="checkbox"></input></td>' +
			'<td>'+ result.name +'</td>' +
			'<td>'+ result.people +'</td>' +
			'<td>'+ result.phone +'</td>' +
			'<td>'+ result.email +'</td>' +
			'<td>'+ result.style +'</td>' +
			'<td><a href="#about-modal-modify" data-toggle="modal">修改</a></td>' +
			'</tr>'			
			perfectOrganization.append(appendString);
		},
		error : function() {
			alert("由于网络问题，注册失败！");
		},
	});
	
	$("#list li a").click(function() {
		var selectWho = $(this).attr("href");
		selectWho = selectWho.substring(1);
		$("#welcomeList").hide();
		$("#perfectOrganizationInfo").hide();
		$("#releaseActivity").hide();
		$("#" + selectWho).show();
	});
	
	$("#lookActicity").click(function() {
		$.ajax({
			url : "getAllActivityByOrganization",
			type : "post",
			data : {
				id : organizationInfo.id,
			},
			success : function(result) {
				var getAllActivityByOrganization = $("#getAllActivityByOrganization");
				getAllActivityByOrganization.html("");
				console.log(result);
				for(var index = 0; index < result.length; index++) {
					var appendString = '<tr> ' +
					'<td><input type="checkbox"></input></td>' +
					'<td>'+ result[index].id +'</td>' +
					'<td>'+ result[index].company +'</td>' +
					'<td>'+ result[index].userId +'</td>' +
					'<td>'+ result[index].head +'</td>' +
					'<td>'+ result[index].phone +'</td>' +
					'<td>'+ result[index].type +'</td>' +
					'<td>'+ result[index].time +'</td>' +
					'<td>'+ result[index].address +'</td>' +
					'<td><a href="#" id=' + result[index].id +'>删除</a></td>' +
					'</tr>';
				
					getAllActivityByOrganization.append(appendString);
					$("#" + result[index].id).click(removeOrganizationActivity);
				}
			},
			error : function() {
				alert("由于网络问题，查看失败！");
			},
		});
	});
	
	var removeOrganizationActivity = function() {
		var id = $(this)[0].id;
		
		$.ajax({
			url : "removeOrganizationActivity",
			data : {
				id : id,
			},
			success : function(result) {
				alert("删除活动成功");
				$("#" + id).css("display", "none");
			},
			error : function() {
				alert("由于网络问题，删除失败！");
			},
		});
	};
	
	$("#userExit").click(function() {
		window.location = "./index.html";
	});
	
	$("#addNewActivity").click(function() {
		var activityAddress = $("#activityAddress").val();
		var activityHead = $("#activityHead").val();
		var activityPhone = $("#activityPhone").val();
		var activityType = $("#activityType").val();
		var activityTime = $("#activityTime").val();
		var activityTitle = $("#activityTitle").val();
		var activityExperience = $("#activityExperience").val();
		
		if(activityAddress === "" || activityTime === "" || activityTitle === "" 
			|| activityExperience === "" || activityHead === "" || activityPhone === "" || activityType === "") {
			alert("上述内容缺一不可！");
			return;
		}
		
		$.ajax({
			url : "registNewActivity",
			type : "post",
			data : {
				"userId" : getUrlParam("id"),
				"address" : activityAddress,
				"title" : activityAddress,
				"content" : activityAddress,
				"time" : activityAddress,
				"type" : activityAddress,
				"phone" : activityAddress,
				"head" : activityAddress,
				"status" : "1",
				"company" : organizationInfo.name,
			},
			success : function(result) {
				console.log(result);
				if(result.errorNumber === "000000") {
					$("#addActivity").modal('hide');
					alert("活动添加成功！");
					return ;
				}
				
				alert(result.errorMessage);
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	});
	
	$("#modifyOrganizationInfo").click(function() {
		var registOrganizationName = $("#registOrganizationName").val();
		var registOrganizationPeople = $("#registOrganizationPeople").val();
		var registOrganizationNumber = $("#registOrganizationNumber").val();
		var registOrganizationStyle = $("#registOrganizationStyle").val();
		var registOrganizationPhone = $("#registOrganizationPhone").val();
		var registOrganizationBusiness = $("#registOrganizationBusiness").val();
		var registOrganizationForm = $("#registOrganizationForm").val();
		var registOrganizationLocation = $("#registOrganizationLocation").val();
		var registOrganizationCheck = $("#registOrganizationCheck").val();
		var registOrganizationEmail = $("#registOrganizationEmail").val();
		var id = organizationInfo.id;
		var password = organizationInfo.password;
		var type = organizationInfo.type;
		
		$.ajax({
			url : "modifyOrganizationInfo",
			type : "post",
			data : {
				"name" : registOrganizationName,
				"people" : registOrganizationPeople,
				"number" : registOrganizationNumber,
				"business" : registOrganizationBusiness,
				"check" : registOrganizationCheck,
				"form" : registOrganizationForm,
				"location" : registOrganizationLocation,
				"email" : registOrganizationEmail,
				"id" : id,
				"password" : password,
				"style" : registOrganizationStyle,
				"phone" : registOrganizationPhone,
				"type" : type,
			},
			success : function(result) {
				if(result.errorNumber === "000000") {
					$("#about-modal-modify").modal('hide');
					alert("修改成功！");
					
					$.ajax({
						url : "getOrganizationModel",
						type : "post",
						data : {
							"id" : getUrlParam("id"),
						},
						success : function(result) {
							organizationInfo = result;
						},
						error : function() {
							alert("由于网络问题，注册失败！");
						},
					});
					
					return;
				}
				
				alert(result.errorMessage);
			},
			error : function() {
				alert("由于网络问题，修改失败！");
			}
		});
	});
	
	$("#addActivity").on("show.bs.modal", function() {
		$("#activityAddress").val("");
		$("#activityPhone").val("");
		$("#activityHead").val("");
		$("#activityType").val("");
		$("#activityTime").val("");
		$("#activityTitle").val("");
		$("#activityExperience").val("");
	});
	
	$("#modifyPassword").on("show.bs.modal", function() {
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#newPasswordSure").val("");
	});
	
	$("#modfiyOrganizationPassword").click(function() {
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
			url : "modfiyOrganizationPassword",
			type : "post",
			data : {
				"id" : id,
				"oldPassword" : oldPassword,
				"newPassword" : newPassword,
			},
			success : function(result) {
				if(result.errorNumber === "000000") {
					alert("密码修改成功！");
					$("#modifyPassword").modal('hide');
					return;
				}
				
				alert(result.errorMessage);
				$("#modifyPassword").modal('hide');
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	});
});