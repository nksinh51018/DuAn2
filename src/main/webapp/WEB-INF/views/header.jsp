<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />'/> 
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href='<c:url value="/" />' style="margin-left: 5%"> <img
			src="http://www.inlogo.vn/vnt_upload/product/09_2018/PCdma_TG_400x400.jpg"
			style="background-size: cover; height: 50px" />
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto" >
				
				<c:forEach var="i" items="${loaiSanPhams }">
					<li class="nav-item"><a class="nav-link" href='<c:url value="/loaisanpham/${i.getTenLoai() }" />'>${i.getTenLoai() }</a></li>
				</c:forEach>
			</ul>
			<ul class="navbar-nav">
				<li class="nav-item active">
					<input id="txtTimkiem" type="text" class="form-control" style="width: 500px;margin-top: 13px;height: 20px;"/>
				</li>
				<li class="nav-item" id="timkiem"><a class="nav-link" href='#' style="margin-right: 100px;">
					 <img src='<c:url value="/resources/image/search.png" />'
						style="background-size: cover; height: 20px" />
				</a></li>
				<c:choose>
					<c:when test="${ten==null }">
						<li class="nav-item active"><a class="nav-link" href='<c:url value="/login" />'>Đăng
							Nhập <span class="sr-only">(current)</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item active dropdown">
					        <a class="circle_avata nav-link dropdown" style="text-transform: uppercase" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					        	 ${ten }
					        </a>
					        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
					          <a class="dropdown-item" href='<c:url value="/user/thongtin" />'>Thông tin tài khoản</a>
					          <div class="dropdown-divider"></div>
					          <a class="dropdown-item" href='<c:url value="/logout" />'>Đăng xuất</a>
					        </div>
					      </li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item">
				<a class="nav-link dropdown" href='<c:url value="#" />' id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<img alt="" src='<c:url value="/resources/image/shopping-cart.png" />' style="width: 20px;height: 20px"/>
					<c:choose>
						<c:when test="${giohang.size()==null||giohang.size()==0 }">
							<span style="font-size:10px; position: absolute;top: 21px;right: 15px;" id="soluonggiohang">${giohang.size() }</span>
						</c:when>
						<c:otherwise>
							<span class="circle_so" style="font-size:10px; position: absolute;top: 10px;right: 0px;" id="soluonggiohang">${giohang.size() }</span>
						</c:otherwise>
					</c:choose>
				</a>
					<div id="menu" class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
						
						<div id="menu-content">
						<c:if test="${giohang.size()!=null||giohang.size()==0 }">
						<c:forEach var="i" items="${giohang }">
							<div>
								<div class="container">
									<div class="row">
										<div class="col-md-3">
											<img
											src='<c:url value="/resources/image/${i.getHinhanh() }" />'
											style="background-size: cover;width: 50%;"/>
										</div>
										<div class="col-md-8">
											<span data="${i.getIdSanPham() }" class="sanPham">Tên sản phẩm: ${i.getTenSanPham() }</span><br/>
											<span data="${i.getIdMau() }" class="mau">Màu: ${i.getTenMau() }</span><br/>
											<span data="${i.getIdSize() }" class="size">Size: ${i.getTenSize() }</span><br/>
											<span>Số lượng: ${i.getSoluong() }</span><br/>
											<span>Tổng tiền: ${i.getSoluong() * Integer.parseInt(i.getGia()) }</span>
										</div>
										<div class="xoa col-md-1">
											<span>Xoá</span>
										</div>
									</div>
								</div>
							</div>
							<div class="dropdown-divider"></div>
						</c:forEach>
						</c:if>
						</div>
						<div class="dropdown-item">
					
							<button id="dathang" class="btn btn-danger" style="width: 100%">Đặt hàng</button>
						</div>
						<div class="dropdown-item">
						<a href='<c:url value="/user/dathang"/>'>
							<button class="btn btn-danger" style="width: 100%">Xem giỏ hàng</button>
						</a>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</nav>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript" src='<c:url value="/resources/js/home.js" />'></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>