$(document).ready(function(){
	var adminInfo;

	var getUrlParam = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		   
		if (r != null) { 
			return unescape(r[2]);
		}
	  
		return null;
	}
	
	$.ajax({
		url : "getAdminSelfInfo",
		type : "post",
		data : {
			id : getUrlParam("id"),
		},
		success : function(result) {
			adminInfo = result;
		},
		error : function() {},
	});
	
	/**
	 * 修改模态框中的内容
	 */
	
	$("#addNewAdmin").on("show.bs.modal", function() {
		$("#newAdminAddress").val("");
		$("#newAdminPhone").val("");
		$("#newAdminName").val("");
		$("#newAdminPasswordSureOkG").val("");
		$("#newAdminPassword").val("");
	});	

	/**
	 * 添加新管理员
	 */
	
	$("#addNewAdminAction").click(function() {
		var newAdminAddress = $("#newAdminAddress").val();
		var newAdminPhone = $("#newAdminPhone").val();
		var newAdminName = $("#newAdminName").val();
		var newAdminPasswordSureOk = $("#newAdminPasswordSureOkG").val();
		var newAdminPassword = $("#newAdminPassword").val();
		
		if(newAdminPassword !== newAdminPasswordSureOk) {
			alert("两次密码输入不一样");
			return;
		}
		
		if(newAdminAddress === "" || newAdminPhone === "" || newAdminName === "" || newAdminPassword === "") {
			alert("上述内容均为必填项");
			return;
		}
		
		var date = new Date();
		var date = date.toLocaleDateString();
		
		$.ajax({
			url : "adminRegistAction",
			type : "post",
			data : {
				password : newAdminPassword,
				phone : newAdminPhone,
				address : newAdminAddress,
				name : newAdminName,
				time : date,
				people : adminInfo.name,
			},
			success : function(result) {
				if(result.errorNumber === "000000") {
					alert("添加新管理员成功\n该管理员的账号是：" + result.id);
					return;
				}
				
				alert(result.errorMessage);
			},
			error : function() {},
		});
	});
	
	/**
	 * 初始化志愿者列表
	 */
	$("#volunteerList").click(function() {
		$.ajax({
			url : "addVolunteerListModel",
			type : "post",
			data : {
				id : getUrlParam("id"),
			},
			success : function(result) {
				$("#addVolunteerListModel").html("");
				var str = "";
				for(var index = 0; index < result.length; index++) {
					var data = new Date(result[index].time);
					var date = data.toLocaleDateString();
					var temp = '<tr> ' +
					'<td></td>' +
					'<td>'+ result[index].id +'</td>' +
					'<td>'+ result[index].name +'</td>' +
					'<td>'+ result[index].sex +'</td>' +
					'<td>'+ result[index].phone +'</td>' +
					'<td>'+ result[index].email +'</td>' +
					'<td>'+ result[index].birthday +'</td>' +
					'<td>'+ result[index].qq +'</td>' +
					'<td>'+ result[index].address +'</td>' +
					'<td>'+ result[index].street +'</td>' +
					'</tr>';
					str += temp;
				}
				
				$("#addVolunteerListModel").append(str);
			},
			error : function() {},
		});
	});
	
	/**
	 * 初始化机构列表
	 */
	$("#organizationList").click(function() {
		$.ajax({
			url : "addOrganizationListModel",
			type : "post",
			data : {
				id : getUrlParam("id"),
			},
			success : function(result) {
				$("#addOrganizationListModel").html("");
				var str = "";
				for(var index = 0; index < result.length; index++) {
					var data = new Date(result[index].time);
					var date = data.toLocaleDateString();
					var temp = '<tr> ' +
					'<td></td>' +
					'<td>'+ result[index].id +'</td>' +
					'<td>'+ result[index].number +'</td>' +
					'<td>'+ result[index].name +'</td>' +
					'<td>'+ result[index].people +'</td>' +
					'<td>'+ result[index].phone +'</td>' +
					'<td>'+ result[index].business +'</td>' +
					'<td>'+ result[index].style +'</td>' +
					'<td>'+ result[index].location +'</td>' +
					'<td>'+ result[index].email +'</td>' +
					'<td>'+ result[index].form +'</td>' +
					'</tr>';
					str += temp;
				}
				
				$("#addOrganizationListModel").append(str);
			},
			error : function() {},
		});
	});
	
	/**
	 * 初始化活动列表
	 */
	
	$("#activityList").click(function() {
		$.ajax({
			url : "addActivityListModel",
			type : "post",
			data : {
				id : getUrlParam("id"),
			},
			success : function(result) {
				$("#addActivityListModel").html("");
				var str = "";
				for(var index = 0; index < result.length; index++) {
					var data = new Date(result[index].time);
					var date = data.toLocaleDateString();
					var temp = '<tr> ' +
					'<td></td>' +
					'<td>'+ result[index].id +'</td>' +
					'<td>'+ result[index].company +'</td>' +
					'<td>'+ result[index].userId +'</td>' +
					'<td>'+ result[index].head +'</td>' +
					'<td>'+ result[index].phone +'</td>' +
					'<td>'+ result[index].type +'</td>' +
					'<td>'+ result[index].time +'</td>' +
					'<td>'+ result[index].address +'</td>' +
					'</tr>';
					str += temp;
				}
				
				$("#addActivityListModel").append(str);
			},
			error : function() {},
		});
	});
	/**
	 * 移除事件
	 */
	
	var removeOneAdminAction = function() {
		if(getUrlParam("id") !== "admin") {
			alert("您的权限不足，无法进行删除操作！");
			return;
		}
		var id = $(this)[0].id;
		$.ajax({
			url : "removeOneAdminAction",
			data : {
				"id" : id,
			},
			type : "post",
			success : function(result) {
				if(result.errorNumber === "000000") {
					alert("删除管理员成功");
					$("#" + id).css("display", "none");
					
					return;
				}
				
				alert(result.errorMessage);
			},
			error : function() {},
		});
	};
	
	/**
	 * 初始化admin列表
	 */
	$("#adminList").click(function() {
		$.ajax({
			url : "addAdminListModel",
			type : "post",
			data : {
				id : getUrlParam("id"),
			},
			success : function(result) {
				$("#addAdminListModel").html("");
				for(var index = 0; index < result.length; index++) {
					var data = new Date(result[index].time);
					var date = data.toLocaleDateString();
					var temp = '<tr> ' +
					'<td></td>' +
					'<td>'+ result[index].id +'</td>' +
					'<td>'+ result[index].name +'</td>' +
					'<td>'+ result[index].phone +'</td>' +
					'<td>'+ result[index].address +'</td>' +
					'<td>'+ date +'</td>' +
					'<td>'+ result[index].people +'</td>' +
					'<td><a id=' + result[index].id + ' href="#">移除</a></td>' +
					'</tr>';
					$("#addAdminListModel").append(temp);
					$("#" + result[index].id).click(removeOneAdminAction);
				}
				
			},
			error : function() {},
		});
	});
	
	/**
	 * 导航栏事件
	 */
	$("#list li a").click(function() {
		var selectWho = $(this).attr("href");
		selectWho = selectWho.substring(1);
		$("#welcomeList").hide();
		$("#volunteerListModel").hide();
		$("#organizationListModel").hide();
		$("#adminListModel").hide();
		$("#activityListModel").hide();
		$("#" + selectWho).show();
	});
	
	/**
	 * 退出事件
	 */
	$("#userExit").click(function() {
		window.location = "./index.html";
	});	
	
	/**
	 * 模态框显示消除内容事件
	 */
	$("#modifyAdminPassword").on("show.bs.modal", function() {
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#newPasswordSure").val("");
	});
	
	/**
	 * 修改密码模态框
	 */
	$("#modifyAdminPasswordSure").click(function() {
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
			url : "modfiyAdminPassword",
			type : "post",
			data : {
				"id" : id,
				"oldPassword" : oldPassword,
				"newPassword" : newPassword,
			},
			success : function(result) {
				if(result.errorNumber === "000000") {
					alert("密码修改成功！");
					$("#modifyAdminPassword").modal('hide');
					return;
				}
				
				alert(result.errorMessage);
				$("#modifyAdminPassword").modal('hide');
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	});
});
