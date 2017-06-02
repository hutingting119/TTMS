$(document).ready(function(){
	var str;
	$("#regist_body a").click(function() {
		alert(1);
	});
	
	$("#regist").click(function() {
		if(str === "regist_organization") {
			// 注册机构
			var registOrganizationName = $("#registOrganizationName").val();
			var registOrganizationPeople = $("#registOrganizationPeople").val();
			var registOrganizationNumber = $("#registOrganizationNumber").val();
			var registOrganizationStyle = $("#registOrganizationStyle").val();
			registOrganizationStyle  = (registOrganizationStyle === "---请选择组织类型---" ? "" : registOrganizationStyle);
			var registOrganizationPhone = $("#registOrganizationPhone").val();
			var registOrganizationBusiness = $("#registOrganizationBusiness").val();
			registOrganizationBusiness  = (registOrganizationForm === "---请选择业务类型---" ? "" : registOrganizationBusiness);
			var registOrganizationForm = $("#registOrganizationForm").val();
			registOrganizationForm  = registOrganizationForm === "---请选择形式---" ? "" : registOrganizationForm;
			var registOrganizationLocation = $("#registOrganizationLocation").val();
			registOrganizationLocation = (registOrganizationLocation === "---请选择区域---" ? "" : registOrganizationLocation);
			var registOrganizationCheck = $("#registOrganizationCheck").val();
			registOrganizationCheck = (registOrganizationCheck === "---请选择类型---" ? "" : registOrganizationCheck);
			var registOrganizationEmail = $("#registOrganizationEmail").val();
			var registOrganizationPassword = $("#registOrganizationPassword").val();
			var registOrganizationPasswordSure = $("#registOrganizationPasswordSure").val();
			
			if(!(registOrganizationPassword === registOrganizationPasswordSure)) {
				alert("两次密码输入不一致！");
				return;
			}
			
			if(registOrganizationName === "" || registOrganizationPeople === "" 
				|| registOrganizationNumber === "" || registOrganizationStyle === "" 
					|| registOrganizationPhone === "" || registOrganizationBusiness === "" || registOrganizationForm === "" 
						|| registOrganizationLocation === "" || registOrganizationCheck === "" || registOrganizationEmail === "" 
							|| registOrganizationPassword === "") {
				alert("上述内容均不能为空！");
				return;
			}
			
			$.ajax({
				url : "organizationRegistAction",
				type : "post",
				data : {
					"password" : registOrganizationPassword,
					"name" : registOrganizationName,
					"people" : registOrganizationPeople,
					"number" : registOrganizationNumber,
					"style" : registOrganizationStyle,
					"phone" : registOrganizationPhone,
					"business" : registOrganizationBusiness,
					"form" : registOrganizationForm,
					"location" : registOrganizationLocation,
					"check" : registOrganizationCheck,
					"email" : registOrganizationEmail,
					"type" : "organization",
				},
				success : function(result) {
					if(result.errorNumber === "000000") {
						alert("注册成功！\n登录的账号是：\n" + result.id);
						$("#about-modal-regist").modal('hide');
					}
				},
				error : function() {
					alert("由于网络问题，注册失败！");
				},
			});

			return;
		}
		var experience = $("#experience").val();
		var service = $("#service").val();
		service = (service === "---请选择服务意向---" ? "" : service);
		var specialty = $("#specialty").val();
		specialty = (specialty === "---请选择特长---" ? "" : specialty);
		var address = $("#address").val();
		var qq = $("#qq").val();
		var street = $("#street").val();
		street = (street === "---请选择所属街区---" ? "" : street);
		var profession = $("#profession").val();
		profession = (profession === "---请选择职业---" ? "" : profession);
		var birthday = $("#birthday").val();
		var idcard = $("#idcard").val();
		var email = $("#email").val();
		var phone = $("#phone").val();
		var ethnic = $("#ethnic").val();
		ethnic = (ethnic === "---请选择名族---" ? "" : ethnic);
		var sex = $("#sex").val();
		sex = (sex === "---请选择性别---" ? "" : sex);
		var name = $("#name").val();
		var password = $("#registPassword").val();
		var registPasswordSure = $("#registPasswordSure").val();
		var type = "volunteer";
		
		if(!(registPasswordSure === password)) {
			alert("两次密码输入不一致！");
			return;
		}
		
		if(password === "" || name === "" || sex === "" 
			|| ethnic === "" || phone === "" || idcard === "" || birthday === "" 
				|| profession === "" || street === "" || address === "" || specialty === ""	|| service === "") {
			alert("以上内容除QQ、Email、工作经验或志愿服务经历选项，其余均为必填项！\n请检查后重新填写");
			return;
		}
		
		$.ajax({
			url : "volunteerRegistAction",
			type : "post",
			data : {
				"experience" : experience,
				"service" : service,
				"specialty" : specialty,
				"street" : street,
				"address" : address,
				"profession" : profession,
				"qq" : qq,
				"birthday" : birthday,
				"idcard" : idcard,
				"email" : email,
				"phone" : phone,
				"ethnic" : ethnic,
				"sex" : sex,
				"name" : name,
				"password" : password,
				"type" : type,
			},
			success : function(result) {
				if(result.errorNumber === "000000") {
					alert("注册成功！\n登录的账号是：\n" + result.id);
					$("#about-modal-regist").modal('hide');
				}
			},
			error : function() {
				alert("由于网络问题，注册失败！");
			},
		});
	});
	
	$("#login").click(function() {
		var id = $("#loginId").val();
		var password = $("#loginPassword").val();
		
		if(id === "" || password === "") {
			alert("用户名和密码缺一不可！");
			return;
		}
		
		$.ajax({
			url : "userLoginAction",
			type : "post",
			data : {
				"id" : id,
				"password" : password,
			},
			success : function(result) {
				if(result.errorNumber === "000000") {
					window.location = result.url + "?" + "id=" + id;
				} else {
					alert(result.errorMessage);
				}
			},
			error : function() {
				alert("由于网络问题，登录失败！");
			},
		});
	});
	
	$('#about-modal-regist').on('hidden.bs.modal', function (e) {
		// 注册机构
		var registOrganizationName = $("#registOrganizationName").val("");
		var registOrganizationPeople = $("#registOrganizationPeople").val("");
		var registOrganizationNumber = $("#registOrganizationNumber").val("");
		var registOrganizationStyle = $("#registOrganizationStyle").val("---请选择组织类型---");
		var registOrganizationPhone = $("#registOrganizationPhone").val("");
		var registOrganizationBusiness = $("#registOrganizationBusiness").val("---请选择业务类型---");
		var registOrganizationForm = $("#registOrganizationForm").val("---请选择形式---");
		var registOrganizationLocation = $("#registOrganizationLocation").val("---请选择区域---");
		var registOrganizationCheck = $("#registOrganizationCheck").val("---请选择类型---");
		var registOrganizationEmail = $("#registOrganizationEmail").val("");
		var registOrganizationPassword = $("#registOrganizationPassword").val("");
		var registOrganizationPasswordSure = $("#registOrganizationPasswordSure").val("");
		var experience = $("#experience").val("");
		var service = $("#service").val("---请选择服务意向---");
		var specialty = $("#specialty").val("---请选择特长---");
		var address = $("#address").val("");
		var qq = $("#qq").val("");
		var street = $("#street").val("---请选择所属街区---");
		var profession = $("#profession").val("---请选择职业---");
		var birthday = $("#birthday").val("");
		var idcard = $("#idcard").val("");
		var email = $("#email").val("");
		var phone = $("#phone").val("");
		var ethnic = $("#ethnic").val("---请选择名族---");
		var sex = $("#sex").val("---请选择性别---");
		var name = $("#name").val("");
		var password = $("#registPassword").val("");
		var registPasswordSure = $("#registPasswordSure").val("");
	})
	
	$('#about-modal-login').on('hidden.bs.modal', function (e) {
		$("#id").val("");
		$("#password").val("");
	})
});