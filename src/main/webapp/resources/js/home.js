/**
 * 
 */
$(document).ready(function(){
	
	$("#timkiem").click(function() {
		var a=$("#txtTimkiem").val();
		if(a.length>0&&a!=null){
			window.location.href = "/DuAn2/timkiem/"+a;
		}
	})
	//$(".xoa").click(function()
	$("body").on("click",".xoa",function() {
		var cha= $(this).closest(".row");
		var idSanPham = $(this).closest(".row").find(".sanPham").attr("data");
		var idMau = $(this).closest(".row").find(".mau").attr("data");
		var idSize = $(this).closest(".row").find(".size").attr("data");
		$.ajax({
			url:"/DuAn2/api/xoagiohang",
			type:"GET",
			data:{
				idSanPham: idSanPham,
				idMau: idMau,
				idSize: idSize,
			},
			success: function(value){
				cha.remove();
				$("#soluonggiohang").addClass("circle_so");
				$("#soluonggiohang").text(value);
			}
		});
	})
})