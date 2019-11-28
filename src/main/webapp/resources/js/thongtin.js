/**
 * 
 */
$(document).ready(function(){
	
	$("#chkMatKhau").change(function() {
		
		if(this.checked){
			$("#thayDoiMatKhau").show();
		}
		else{
			$("#thayDoiMatKhau").hide();
		}
	})
	
	$("#btnLuu").click(function() {
		var id = $("#txtTen").attr("data");
		var form =$("#form").serializeArray();
		var jsonid= {"name":"id","value":id };
		var thayDoi = $("#chkMatKhau").is(":checked");
		var matKhauMoi = $("#txtMatKhauMoi").val();
		form.push(jsonid);
		console.log(form);
		$.ajax({
			url:"/DuAn2/user/api/capnhatTaiKhoan",
			type:"POST",
			data:{
				id: id,
				gioiTinh: form[0].value,
				ten: form[1].value,
				diaChi: form[2].value,
				sdt: form[3].value,
				tenDangNhap: form[4].value,
				matKhauHienTai: form[5].value,
				thayDoi: thayDoi,
				matKhauMoi: matKhauMoi
			},
			success: function(value){
				alert(value);
			}
		})
	})
})