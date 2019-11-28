/**
 * 
 */
$(document).ready(function(){
	
	$("#btnDangKi").click(function() {
		var tenDangNhap = $("#tenDangNhap").val();
		var matKhau = $("#matKhau").val();
		alert(tenDangNhap+" "+matKhau);
		
		var kt= true;
		if(tenDangNhap==""||tenDangNhap==null){
			$("#errorTenDangNhap").text("Trường này bắt buộc");
			kt=false;
		}
		if(matKhau==""||matKhau==null){
			$("#errorMatKhau").text("Trường này bắt buộc");
			kt=false;
		}
		if(kt){
			$.ajax({
				url:"/DuAn2/api/dangki",
				type:"POST",
				data:{
					tenDangNhap: tenDangNhap,
					matKhau: matKhau,
				},
				success: function(value){
					if(value){
						window.location.href = "/DuAn2/login";
					}
					else{
						alert("Đăng kí thất bại");
					}
					
					
				}
			});
		}
		
		
	})
		$("#timkiem").click(function() {
		var a=$("#txtTimkiem").val();
		if(a.length>0&&a!=null){
			window.location.href = "/DuAn2/timkiem/"+a;
		}
		
	})
	
})