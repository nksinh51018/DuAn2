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
		<h1>Thanh toán</h1>
		<div class="row" style="margin-top: 50px;">
			<div class="col-md-4">
				<div style="text-align: center">THÔNG TIN GIAO HÀNG</div>
				<img alt="" src='<c:url value="/resources/image/so1.png" />' style="background-size: cover;width: 100%;margin-bottom: 10px;margin-top: 10px;">
				<div class="dropdown">
				  <button id="btnDiachi" class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
				  style="border: 1px solid;width: 100%">
				    ${taiKhoan.getDiaChi() }
				  </button>
				  <div id="selectDiaChi" class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 100%">
				    <div class="dropdown-item" id="diachicu">${taiKhoan.getDiaChi() }</div>
				    <div class="dropdown-item" id="diachimoi">Địa chỉ mới</div>
				  </div>
				  <div id="formDiaChi" style="display: none">
				  	<div class="form-group">
					   <label for="txtTen">Tên: </label>
					    <input type="text" data="${taiKhoan.getId() }" name="ten" class="form-control" id="txtTen" aria-describedby="tenHelp" placeholder="Nhập tên" value="${taiKhoan.getTen() }">
					    <small id="tenHelp" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
					   <label for="txtSDT">Số điện thoại: </label>
					    <input type="tel" name="sdt" class="form-control" id="txtSDT" aria-describedby="sdtHelp" placeholder="Nhập số điện thoại" value="${taiKhoan.getSDT() }">
					    <small id="sdtHelp" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
					   <label for="txtDiaChi">Địa chỉ: </label>
					    <input type="text" name="diaChi" class="form-control" id="txtDiaChi" aria-describedby="diaChiHelp" placeholder="Nhập địa chỉ" value="${taiKhoan.getDiaChi() }">
					    <small id="diaChiHelp" class="form-text text-muted"></small>
					</div>
				  </div>
				</div>
			</div>
			<div class="col-md-8">
					<div style="text-align: center">THÔNG TIN ĐƠN HÀNG</div>
					<img alt="" src='<c:url value="/resources/image/so2.png" />' style="background-size: cover;width: 100%;margin-bottom: 10px;margin-top: 10px;">	
					<table class="table">
						<thead>
							<tr>
								<td><span>SẢN PHẨM</span></td>
								<td><span>MÀU SẮC</span></td>
								<td><span>KÍCH CỠ</span></td>
								<td><span>SỐ LƯỢNG</span></td>
								<td><span>GIÁ</span></td>
								<td><span>TỔNG TIỀN</span></td>
							</tr>
						</thead>
						<tbody>
							<c:set var="soLuong" value="0"></c:set>
							<c:set var="tongTien" value="0"></c:set>
							<c:forEach var="i" items="${ giohang}">
								<tr class="hang">
									<td><img
											class="sanPham"
											data="${i.getIdSanPham() }"
											src='<c:url value="/resources/image/${i.getHinhanh() }" />'
											style="background-size: cover;width: 50px;"/></td>
									<td><span data="${i.getIdMau() }" class="mau"><div style="width: 30px;height: 30px;background-color: ${i.getTenMau() };"></div></span></td>
									<td><span data="${i.getIdSize() }" class="size">${i.getTenSize() }</span></td>
									<td><span class="soLuong_chitiet">${i.getSoluong() }</span></td>
									<c:set var="soLuong" value="${soLuong+i.getSoluong() }"></c:set>
									<td><span class="gia_dh">${i.getGia() }</span></td>
									<td><span class="tongTien_chitiet"> ${i.getSoluong() * Integer.parseInt(i.getGia()) }</span></td>
									<c:set var="tongTien" value="${tongTien + i.getSoluong() * Integer.parseInt(i.getGia()) }"></c:set>
									<td><span class="xoa-dh">xóa</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<span id="soluong-dh" data="${soLuong }">Số lượng: ${soLuong }</span><br/>
					<span id="tongTien-dh" data="${tongTien }">Tổng tiền: ${tongTien }</span></br/>
					<button id="dathang" class="btn btn-danger" style="width: 100%">Đặt hàng</button>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/dathang.js" />'></script>
</body>
</html>