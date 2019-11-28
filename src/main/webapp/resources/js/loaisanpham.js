/**
 * 
 */
$(document).ready(function(){
	$("#textlocSanPham").click(function() {
		if($('#locSanPham').css('display') == 'none'){ 
			   $('#locSanPham').show(); 
			} else { 
			   $('#locSanPham').hide(); 
		}
	})
	
	$(".btnMau").click(function() {
		var nameLoai= $("#nameLoai").text();
		window.location.href = "/DuAn2/loaisanpham/"+nameLoai+"/mau/"+$(this).attr("data-text")+"/gia/size";
	})
	
	$(".chkGia").click(function() {
		var nameLoai= $("#nameLoai").text();
		window.location.href = "/DuAn2/loaisanpham/"+nameLoai+"/mau"+"/gia/"+$(this).attr("data-min")+"/"+$(this).attr("data-max")+"/size";
	})
	
	$(".btnSize").click(function() {
		var nameLoai= $("#nameLoai").text();
		window.location.href = "/DuAn2/loaisanpham/"+nameLoai+"/mau"+"/gia"+"/size/"+$(this).attr("data-text");
	})
		$("#timkiem").click(function() {
		var a=$("#txtTimkiem").val();
		if(a.length>0&&a!=null){
			window.location.href = "/DuAn2/timkiem/"+a;
		}
		
	})

})