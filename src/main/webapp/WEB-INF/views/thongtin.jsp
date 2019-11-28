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
	
	<div class="container">
		<h1>Chỉnh sửa thông tin tài khoản</h1>
		<form id="form">
			<p>Giới tính: </p>
		  	<div class="form-check form-check-inline">
		  		
			  <input class="form-check-input" type="radio" name="gioiTinh" id="gioiTinhNam" value="true"
			  	<c:if test="${taiKhoan.isGioiTinh() }">
			  		checked
			  	</c:if>
			  >
			  <label class="form-check-label" for="gioiTinhNam">Nam</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="gioiTinh" id="gioiTinhNu" value="false"
			  <c:if test="${!taiKhoan.isGioiTinh() }">
			  		checked
			  	</c:if>
			  >
			  <label class="form-check-label" for="gioiTinhNu">Nữ</label>
			</div>
			<small id="gioiTinhHelp" class="form-text text-muted"></small>
		  	<div class="form-group">
			   <label for="txtTen">Tên: </label>
			    <input type="text" data="${taiKhoan.getId() }" name="ten" class="form-control" id="txtTen" aria-describedby="tenHelp" placeholder="Nhập tên" value="${taiKhoan.getTen() }">
			    <small id="tenHelp" class="form-text text-muted"></small>
			</div>
			<div class="form-group">
			   <label for="txtDiaChi">Địa chỉ: </label>
			    <input type="text" name="diaChi" class="form-control" id="txtDiaChi" aria-describedby="diaChiHelp" placeholder="Nhập địa chỉ" value="${taiKhoan.getDiaChi() }">
			    <small id="diaChiHelp" class="form-text text-muted"></small>
			</div>
			<div class="form-group">
			   <label for="txtSDT">Số điện thoại: </label>
			    <input type="tel" name="sdt" class="form-control" id="txtSDT" aria-describedby="sdtHelp" placeholder="Nhập số điện thoại" value="${taiKhoan.getSDT() }">
			    <small id="sdtHelp" class="form-text text-muted"></small>
			</div>
			<div class="form-group">
			   <label for="txtTenDangNhap">Tên đăng nhập: </label>
			    <input type="text" name="tenDangNhap" class="form-control" id="txtTenDangNhap" readonly aria-describedby="tenDangNhapHelp" placeholder="Nhập tên đăng nhập" value="${taiKhoan.getTenDangNhap() }">
			    <small id="tenDangNhapHelp" class="form-text text-muted"></small>
			</div>
			<div class="form-group">
			   <label for="txtMatKhau">Mật khẩu hiện tại: </label>
			    <input type="password" name="matKhauHienTai" class="form-control" id="txtMatKhau" aria-describedby="matKhauHelp" value="">
			    <small id="matKhauHelp" class="form-text text-muted"></small>
			</div>
			<div class="form-check">
			    <input name="thayDoi" type="checkbox" class="form-check-input" id="chkMatKhau">
			    <label class="form-check-label" for="chkMatKhau">Thay đổi mật khẩu</label>
			  </div>
			<div id="thayDoiMatKhau" style="display: none;">
				<div class="form-group">
				   <label for="txtMatKhauMoi">Mật khẩu mới: </label>
				    <input type="password" name="matKhauMoi" class="form-control" id="txtMatKhauMoi" aria-describedby="matKhauMoiHelp" value="">
				    <small id="matKhauMoiHelp" class="form-text text-muted"></small>
				</div>
				<div class="form-group">
				   <label for="txtXacNhanMatKhau">Xác nhận mật khẩu: </label>
				    <input type="password" class="form-control" id="txtXacNhanMatKhau" aria-describedby="xacNhanMatKhauHelp" value="">
				    <small id="xacNhanMatKhauHelp" class="form-text text-muted"></small>
				</div>
			</div>
		</form>
		<button id="btnLuu" style="width: 100%" class="btn btn-primary">Lưu</button>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/thongtin.js" />'></script>
</body>

</html>