<%@page import="com.nk.entity.HinhAnh"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container" style="margin-top: 5%">
		<div class="row">
			<h3>Tạo tài khoản</h3>
			
			<input class="form-control" type="text" placeholder="Tên đăng nhập" id="tenDangNhap"/>
			<span id="errorTenDangNhap" style="color: red"></span>
			<br/>
			<br/>
			<input class="form-control" type="password" placeholder="Mật khẩu" id="matKhau"/>
			<span id="errorMatKhau" style="color: red"></span>
			<br/>
			<br/>
			<button class="btn btn-danger" id= "btnDangKi" style="width: 100%">Đăng kí</button>
		</div>
	</div>
	
		<script type="text/javascript" src='<c:url value="/resources/js/dangki.js" />'></script>
	
</body>
</html>