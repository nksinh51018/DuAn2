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
	<!--<div class="container h-100">

 <div class="row h-100 justify-content-center">

    <form style="margin-top: 50px;height: 50px;"  action="login_process" method="post" class="col-12">
      <div class="form-group" >
      	<div style="font-weight: bold;font-size: 24px;">
      		<span>Đăng nhập</span>
      	</div>
      	<p>${message }</p>
      	<input class="form-control" type="text" name="TenDangNhap" id="TenDangNhap" placeholder="Tên Đăng nhập"/><br/>
		<input class="form-control" type="password" name="MatKhau" id="MatKhau" placeholder="Mật khẩu"/><br/>
		
		<input type="submit" style="width: 100%" class="btn btn-danger" value="Đăng nhập" />
      </div>
    </form> 
    <form action="login_process" method="post" class="col-12">
      <div class="form-group" >
      	<div style="font-weight: bold;font-size: 24px;">
      		<span>Đăng kí</span>
      	</div>
      	<input class="form-control" type="text" name="TenDangNhap" id="TenDangNhap" placeholder="Tên Đăng nhập"/><br/>
		<input class="form-control" type="password" name="MatKhau" id="MatKhau" placeholder="Mật khẩu"/><br/>
		
		<input type="submit" style="width: 100%" class="btn btn-danger" value="Đăng nhập" />
      </div>
    </form>  
  </div>
</div>
-->

	<div class="container">
		<div class="row" style="margin-top: 50px;">
			<div class="col-md-6 ">
				<form  action="login_process" method="post" class="col-12">
      				<div class="form-group" >
      				<div style="font-weight: bold;font-size: 24px;">
      					<span>Đăng nhập</span>
      				</div>
      				<p>${message }</p>
			      	<input class="form-control" type="text" name="TenDangNhap" id="TenDangNhap" placeholder="Tên Đăng nhập"/><br/>
					<input class="form-control" type="password" name="MatKhau" id="MatKhau" placeholder="Mật khẩu"/><br/>
					
					<input type="submit" style="width: 100%" class="btn btn-danger" value="Đăng nhập" />
			      </div>
    			</form> 
			</div>
			<div class="col-md-6">
				
      				<div style="font-weight: bold;font-size: 24px;">
      					<span>Khách hàng mới</span>
      				</div>
      				<br/>
      				<span>Nếu bạn chưa có tài khoản, hãy sử dụng tùy chọn này để đăng ký.</span>
      				<br/>
      				<br/>
      				<a href="dangki">
      					<button class="btn btn-danger">Đăng kí</button>
      				</a>
			</div>
		</div>
	
	</div>
	
	<script type="text/javascript" src='<c:url value="/resources/js/login.js" />'></script>
	
</body>
</html>