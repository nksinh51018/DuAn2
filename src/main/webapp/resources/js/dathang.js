/**
 * 
 */
$(document).ready(function(){
	
	$("#diachicu").click(function() {
		var text = $(this).text();
		$("#formDiaChi").hide();
		$("#btnDiachi").text(text);
	})
	
	$("#diachimoi").click(function() {
		var text = $(this).text();
		$("#btnDiachi").text(text);
		$("#formDiaChi").show();
	})
	
	$("body").on("click",".xoa-dh",function() {
		var cha= $(this).closest(".hang");
		var idSanPham = $(this).closest(".hang").find(".sanPham").attr("data");
		var idMau = $(this).closest(".hang").find(".mau").attr("data");
		var idSize = $(this).closest(".hang").find(".size").attr("data");
		var tongTien_chitiet_text = $(this).closest(".hang").find(".tongTien_chitiet").text();
		var soLuong_chitiet_text = $(this).closest(".hang").find(".soLuong_chitiet").text();
		$.ajax({
			url:"/DuAn2/api/xoagiohang",
			type:"GET",
			data:{
				idSanPham: idSanPham,
				idMau: idMau,
				idSize: idSize,
			},
			success: function(value){
				var tongTien_chitiet= parseInt(tongTien_chitiet_text, 10);
				var tongTien_text = $("#tongTien-dh").attr("data");
				var tongTien= parseInt(tongTien_text, 10);
				$("#tongTien-dh").text("Tổng tiền: "+ (tongTien - tongTien_chitiet));
				var soLuong_chitiet= parseInt(soLuong_chitiet_text, 10);
				var soLuong_text = $("#soluong-dh").attr("data");
				var soLuong= parseInt(soLuong_text, 10);
				console.log(soLuong_chitiet);
				console.log(soLuong_text);
				$("#soluong-dh").text("Số lượng: "+ (soLuong - soLuong_chitiet));
				cha.remove();
				$("#soluonggiohang").addClass("circle_so");
				$("#soluonggiohang").text(value);
				$(".xoa").each(function() {
					var cha2= $(this).closest(".row");
					var idSanPham2 = $(this).closest(".row").find(".sanPham").attr("data");
					var idMau2 = $(this).closest(".row").find(".mau").attr("data");
					var idSize2 = $(this).closest(".row").find(".size").attr("data");
					if(idSanPham2 == idSanPham && idMau2== idMau &&  idSize2 == idSize){
						cha2.remove();
					}
					
				})
			}
		});
	})
	
	$("body").on("click","#dathang",function() {
		console.log($("#btnDiachi").text());
		console.log($("#diachimoi").text());
		if($("#btnDiachi").text() == $("#diachimoi").text()){
			var sdt = $("#txtSDT").val();
			var diachi = $("#txtDiaChi").val();
			$.ajax({
				url:"/DuAn2/user/api/datHang",
				type:"POST",
				data:{
					sdt: sdt,
					diaChi: diachi,
				},
			
				success: function(value){
					alert("Đặt hàng thành công");
				}
			
			})
		}
		else{
			$.ajax({
				url:"/DuAn2/user/api/datHang2",
				type:"POST",
				success: function(value){
					alert("Đặt hàng thành công");
				}
			
			})
		}
	})
	
	$("#dathang").click(function() {
		alert("dasd");
		console.log($("#btnDiachi").text());
		console.log($("#diachimoi").text());
		if($("#selectDiaChi").text() == $("#diachimoi").text()){
			alert("sad");
		}
	})
		
})