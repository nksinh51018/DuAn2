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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
	<h3 style="text-align: center;">Sản phẩm</h3><br/>
	<div class="row">
		
		<div class="col-md-4">
			<form id="formsp-tsp">
							<label for="tensanpham-tsp">Tên sản phẩm</label> 
							<input data="" type="text" class="form-control" name="tensanpham" id="tensanpham-tsp" placeholder="Tên sản phẩm" /><br /> 
							<label for="giatien-tsp">Giá tiền</label> 
							<input type="text" class="form-control" name="giatien" id="giatien-tsp" placeholder="Giá tiền" /><br /> 
							<label for="chatlieu-tsp">Chất liệu</label> 
							<input type="text" class="form-control" name="chatLieu" id="chatlieu-tsp" placeholder="Chất liệu" /><br /> 
							<label for="sel1">Danh mục</label> 
							<select name="danhMucSanPham" class="form-control" id="danhmuc-tsp">
								<c:forEach var="i" items="${loaiSanPhams }">
									<option value="${i.getTenLoai() }">${i.getTenLoai() }</option>
								</c:forEach>
							</select> <br /> 
							<label for="mota">Mô tả</label>
							<textarea name="mota" rows="5" class="form-control"
								id="mota-tsp" placeholder="Mô tả"></textarea>
							<br /> 
							
        				<div id="container-chitietsanpham-tsp">
        					<div class="chitietsanpham-tsp" data="">
							<label>Chi tiết</label> <select name="mamau"
								class="form-control mamau-tsp">
								<c:forEach var="mau" items="${maus }">
									<option data-value="${mau.getId() }" value="${mau.getTenMau() }">${mau.getTenMau() }</option>
								</c:forEach>
							</select> <br /> 
							<select name="masize" class="form-control masize-tsp"
								>
								<c:forEach var="size" items="${sizes }">
									<option data-value="${size.getId() } value="${size.getTenSize() }">${size.getTenSize() }</option>
								</c:forEach>
							</select> <br /> 
							<input name="soluong" min="1" value="1" type="number" class="form-control soluong-tsp" placeholder="Số lượng" /><br />
							<button style="width: 100%;margin-bottom: 10px;" class="btn-tsp btn-chitiet-tsp btn btn-danger">Thêm chi tiết</button>
						</div>
        				</div>
        				<div id="container-HinhAnh-tsp">
        					<label for="hinhanh-tsp">Hình ảnh Màu Green</label> 
							<input name="hinhanh-tsp" type="file" class="form-control hinhanh-tsp" /><br />
        				</div>
        				</form>
        				
        				<div style="display: none;" id="chitietsanpham-tsp" class="chitietsanpham-tsp" data="">
							<label>Chi tiết</label> <select name="mamau"
								class="form-control mamau-tsp">
								<c:forEach var="mau" items="${maus }">
									<option data-value="${mau.getId() }" value="${mau.getTenMau() }">${mau.getTenMau() }</option>
								</c:forEach>
							</select> <br /> 
							<select name="masize" class="form-control masize-tsp">
								<c:forEach var="size" items="${sizes }">
									<option data-value="${size.getId() } value="${size.getTenSize() }">${size.getTenSize() }</option>
								</c:forEach>
							</select> <br /> 
							<input name="soluong" min="1" value="1"  type="number" class="form-control soluong-tsp" placeholder="Số lượng" /><br />
							<button style="width: 100%;margin-bottom: 10px;"  class="btn-tsp btn-chitiet-tsp btn btn-danger">Thêm chi tiết</button>
						</div>
						
						
				
							<button style="width: 100%;margin-bottom: 10px;" id="btn-capnhat-tsp" class="btn-tsp btn btn-danger hide">Cập nhật sản phẩm</button>
			
					
							<button style="width: 100%;margin-bottom: 10px;" id="btn-thoatcapnhat-tsp"  class="btn-tsp btn btn-danger hide">Thoát cập nhật</button>
				
		</div>
		<div class="col-md-8">
			<div style="float: right;">
							<button id="btn-themsanpham-tsp" class="btn btn-danger">Thêm sản phẩm</button>
							<button id="xoasanpham" class="btn btn-danger">Xóa sản
								phẩm</button>
						</div>
						<table id="table_themsanpham" class="table">

							<thead>
								<tr>
									<td>
										<span>
											<div class="checkbox">
												<label><input id="check_all" type="checkbox"
													value="" />Chọn tất cả</label>
											</div>
										</span>
									</td>
									<td><span>Mã sản phẩm</span></td>
									<td><span>Tên sản phẩm</span></td>
									<td><span>Giá tiền</span></td>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="sp" items="${sanPhams }">
									<tr>
										<td>
											<div class="checkbox">
												<label><input type="checkbox"
													value="${sp.getId() }" /></label>
											</div>
										</td>
										<td>${sp.getId() }</td>
										<td>${sp.getTenSanPham() }</td>
										<td>${sp.getGiaTien() }</td>
										<td class="capnhat-tsp btn btn-danger" data-value=${sp.getId() }>Sửa</td>
									</tr>
								</c:forEach>
							</tbody>

						</table>
					<nav style="text-align: center; margin: auto"
							aria-label="Page navigation example">
							<ul class="pagination">
								<c:choose>
									<c:when test="${soluongsanpham%5==0 }">
										<c:forEach begin="1" end="${soluongsanpham/5 }" var="i">

											<li class="page-item-themsanpham page-item"><a
												class="page-link" href="#">${i }</a></li>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach begin="1" end="${soluongsanpham/5+1 }" var="i">
											<li class="page-item-themsanpham page-item"><a
												class="page-link" href="#">${i }</a></li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</ul>
						</nav>
				</div>
		</div>
	</div>
		
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript" src='<c:url value="/resources/js/themsanpham.js" />'></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>