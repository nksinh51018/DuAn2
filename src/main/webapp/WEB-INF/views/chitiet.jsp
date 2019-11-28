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
		<div class="col-md-4">
			<c:forEach var="i" items="${sanPham.getHinhAnhs() }">
				<img class="slide_hinhanh" style="width: 50%" alt="" src='<c:url value="/resources/image/${i.getUrl() }" /> ' />
			</c:forEach>
		</div>
		<div class="col-md-4">
			<img id="mainhinhanh" style="width: 100%" alt="" src='<c:url value="/resources/image/${sanPham.getHinhAnhs().get(0).getUrl() }" /> ' />
		</div>
		
		<div class="col-md-4">
			<h3 id="ten" data="${sanPham.getId()}">${sanPham.getTenSanPham()}</h3>
			<h4 id="gia" style="color: red">${sanPham.getGiaTien() }</h4 style="color: red"><h4>VND</h4>
			<h5 id="textMau" data="" data-text="">Chọn màu sản phẩm: </h5>
			<c:forEach var="i" items="${maus }">
				<button class="btnMau" style="background-color: ${i.getTenMau()};width: 25px;height: 25px;margin: 2px;" data-text="${i.getTenMau()}" data-value="${i.getId() }"></button>
			</c:forEach>
			<br/>
			<h5 id="textSize" data="" data-text="">Chọn size sản phẩm: </h5>
			<c:forEach var="i" items="${sizes }">
				<button class="btnSize" style="margin: 2px;"  data-value="${i.getId() }" data-text="${i.getTenSize()}">${i.getTenSize()}</button>
			</c:forEach>
			<p>Chất liệu: ${sanPham.getChatLieu() }</p>
			<p>Mô tả: ${sanPham.getMoTa() }</p>
			<span>Số lượng:  </span>
			<input type="number" value="1" min="1" id="soluong"/>
			<br/>
			<br/>
			<button class="btn btn-danger" id="themvaogiohang">Thêm vào giỏ hàng</button>
		</div>
	</div>	
		<span style="display: none;" id = "mau-size">
		[
			<c:forEach var="i" items="${sanPham.getChiTietSanPhams() }">
				<c:if test="${i.getId() != sanPhamChiTiet }">
					{"mau":"${i.getMau().getId()}","size": "${i.getSize().getId()}"},
				</c:if>
				<c:if test="${i.getId() == sanPhamChiTiet }">
					{"mau":"${i.getMau().getId()}","size": "${i.getSize().getId()}"}
				</c:if>
					
			</c:forEach>
			]
		</span><br/>
		<span  style="display: none;" id = "mau-anh">
		[
			<c:forEach var="i" items="${sanPham.getHinhAnhs() }">
			
				<c:if test="${i.getId() != sanPhamHinhAnh }">
					{"mau":${i.getMau().getId()},"hinhanh": "${i.getUrl()}"},
				</c:if>
				<c:if test="${i.getId() == sanPhamHinhAnh }">
					{"mau":${i.getMau().getId()},"hinhanh": "${i.getUrl()}"}
				</c:if>
					
			</c:forEach>
			]
		</span>

	</div>
	<script type="text/javascript" >
		var message = "${sanPham.getTenSanPham() }";
	</script>
	<script type="text/javascript" src='<c:url value="/resources/js/custom.js" />'></script>
</body>
</html>