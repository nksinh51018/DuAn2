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
		<h3 id="nameLoai">${loai }</h3>
		<div id="textlocSanPham">Lọc sản phẩm</div>
		<div class="row">
			
			<div id="locSanPham" class="row" style="width: 100%;margin-left: 15px;display: none;">
				<div class="row" style="width: 100%;height: 30px;background-color: rgba(0, 0, 0, 0.5)">
					<div class="col-md-4">
						MÀU SẮC CHÍNH
					</div>
					<div class="col-md-4">
						ĐƠN GIÁ
					</div>
					<div class="col-md-4">
						KÍCH CỠ
					</div>
				</div>
				<div class="row" style="width: 100%;background-color: rgba(0, 0, 0, 0.1)">
					<div class="col-md-4">
						<c:forEach var="i" items="${maus }">
							<button class="btn btnMau" style="background-color: ${i.getTenMau()};width: 25px;height: 25px;margin: 2px;" data-text="${i.getTenMau()}" data-value="${i.getId() }"></button>
						</c:forEach>
					</div>
					<div class="col-md-4">
						<input class="chkGia" type="checkbox" data-min="10000" data-max="30000">10000 - 30000<br/>
						<input class="chkGia" type="checkbox" data-min="30000" data-max="60000">30000 - 60000<br/>
						<input class="chkGia" type="checkbox" data-min="60000" data-max="100000">60000 - 100000<br/>
					</div>
					<div class="col-md-4">
						<c:forEach var="i" items="${sizes }">
							<button class="btn btnSize" style="margin: 2px;background-color: white;" data-text="${i.getTenSize()}" data-value="${i.getId() }">${i.getTenSize()}</button>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row" id="content">
			<c:forEach var="i" items="${lsp.getSanPhams() }">
				<div class="col-md-3">
					<div style="padding: 5px">
						<a href='<c:url value="/chitiet/${i.getId() }" />'>
						<c:url var="url0" value="/resources/image/${i.getHinhAnhs().get(0).getUrl() }" />
						<c:url var="url1" value="/resources/image/${i.getHinhAnhs().get(1).getUrl() }" />
						<div class="card">
							<img
								src='<c:url value="/resources/image/${i.getHinhAnhs().get(0).getUrl() }" />'
								class="card-img-top"
								onmouseover="this.src='${url1}'"
								onmouseout="this.src='${url0}'"
								style="transition:2s;"
								 />
							<div class="card-body">
								<h5 class="card-title">${i.getTenSanPham()}</h5>
								<p class="card-text">${i.getMoTa() }</p>
							</div>
						</div>
						</a>
					</div>

				</div>
			</c:forEach>
			</div>
		</div>

	</div>
	
	<script type="text/javascript" src='<c:url value="/resources/js/loaisanpham.js" />'></script>
	
</body>
</html>