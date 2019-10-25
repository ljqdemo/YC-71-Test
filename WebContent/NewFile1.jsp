<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input id="email" type="text" title="邮箱">
<button>登陆</button>
</body>
<script type="text/javascript">
email.onchange= function check() {
	var email = this.value;
	var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	if(reg.test(email)){
		alert("邮箱格式正确");
	}else{
		alert("邮箱格式不正确");
	}
}
</script>
</html>