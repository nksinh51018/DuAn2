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
			<h3>Tìm kiếm: ${tk }</h3>
			<div class="row" id="content">
			<c:forEach var="i" items="${sanPhams }">
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