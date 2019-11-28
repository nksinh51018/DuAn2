/**
 * 
 */

$(document).ready(function(){
	var mau_size= $("#mau-size").text();
	var json_mau_size= JSON.parse(mau_size);
	console.log(json_mau_size);
	
	var mau_anh= $("#mau-anh").text();;
	var json_mau_anh= JSON.parse(mau_anh);
	console.log(json_mau_anh);
	$(".btnMau").click(function() {
		var a= $(this).attr("data-value");
		for(var i = 0; i< json_mau_anh.length; i++){
			if( json_mau_anh[i].mau==a){
				$("#mainhinhanh").attr('src',"/DuAn2/resources/image/"+json_mau_anh[i].hinhanh);
			}
		}
		var b=[];
		$("#textMau").text("Chọn màu sản phẩm: "+ $(this).attr("data-text"));
		$("#textSize").text("Chọn size sản phẩm: ");
		$("#textMau").attr("data",$(this).attr("data-value"));
		$("#textMau").attr("data-text",$(this).attr("data-text"));
		for(var i = 0; i< json_mau_size.length; i++){
			if( json_mau_size[i].mau==a){
				b.push(json_mau_size[i].size);
			}
		}
		$(".btnSize").each(function() {
			var c =  $(this).attr("data-value");
			$(this).attr("disabled", true);
			for(var i = 0; i< b.length; i++){
				if( c==b[i]){
					$(this).attr("disabled", false);
				}
			}
		})
	});
	$(".btnSize").click(function() {
		$("#textSize").text("Chọn size sản phẩm: "+ $(this).text());
		$("#textSize").attr("data",$(this).attr("data-value"));
		$("#textSize").attr("data-text",$(this).attr("data-text"));
	});
	$(".slide_hinhanh").click(function() {
		var a= $(this).attr('src');
		$("#mainhinhanh").attr('src',a);
	});
	$("#themvaogiohang").click(function() {
		var idSanPham = $("#ten").attr("data");
		var idMau = $("#textMau").attr("data");
		var idSize = $("#textSize").attr("data");
		var soluong = $("#soluong").val();
		var tenSanPham = $("#ten").text();
		var tenMau =  $("#textMau").attr("data-text");
		var tenSize =  $("#textSize").attr("data-text");
		var srcAnh = $("#mainhinhanh").attr('src');
		var hinhanh = srcAnh.slice(23,srcAnh.length);
		var gia= $("#gia").text();
		$.ajax({
			url:"/DuAn2/api/themgiohang",
			type:"GET",
			data:{
				idSanPham: idSanPham,
				idMau: idMau,
				idSize: idSize,
				soluong: soluong,
				tenSanPham: tenSanPham,
				tenMau: tenMau,
				tenSize: tenSize,
				hinhanh: hinhanh,
				gia: gia
			},
			success: function(value){
				$("#soluonggiohang").addClass("circle_so");
				$("#soluonggiohang").text(value);
				alert("Thêm sản phẩm thành công");
				$("#menu-content").append('<div>'
								+'<div class="container">'
									+'<div class="row">'
										+'<div class="col-md-3">'
											+'<img '
											+'src="/DuAn2/resources/image/'+hinhanh+'" '
											+'style="background-size: cover;width: 50%;"/>'
										+'</div>'
										+'<div class="col-md-8">'
											+'<span data="'+idSanPham+'" class="sanPham">Tên sản phẩm: '+tenSanPham+'</span><br/>'
											+'<span data="'+idMau+'" class="mau">Màu: '+tenMau+'</span><br/>'
											+'<span data="'+  idSize+'" class="size">Size: '+ tenSize+'</span><br/>'
											+'<span>Số lượng: '+soluong +'</span><br/>'
											+'<span>Tổng tiền: '+soluong *gia +' </span>'
										+'</div>'
										+'<div class="xoa col-md-1">'
										+	'<span>Xoá</span>'
										+'</div>'
									+'</div>'
								+'</div>'
							+'</div>'
							+'<div class="dropdown-divider"></div>');
			},
			error: function() {
				alert("Vui lòng chọn màu và size sản phẩm");
			}
			
		});
	})
	
	$("#timkiem").click(function() {
		var a=$("#txtTimkiem").val();
		if(a.length>0&&a!=null){
			window.location.href = "/DuAn2/timkiem/"+a;
		}
		
	})
	
	
});