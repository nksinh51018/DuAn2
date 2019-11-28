/**
 * 
 */

$(document).ready(function(){
	
	$("#check_all").change(function() {
		if(this.checked){
			$("#table_themsanpham input").each(function() {
				this.checked=true;
			})
		}
		else{
			$("#table_themsanpham input").each(function() {
				this.checked=false;
			})
		}
		
	})
	
	$("body").on("click",".page-item-themsanpham",function(){
		var spbatdau=($(this).text()-1)*5;
		$(".page-item-themsanpham").removeClass("active");
		$(this).addClass("active");
		$("#check_all").each(function() {
			this.checked=false;
		})
		$.ajax({
			url:"/DuAn2/admin/api/laySanPhamlimit",
			type:"GET",
			data:{
				spbatdau: spbatdau,
			},
			success: function(value){
				var tbodythemsanpham=$("#table_themsanpham").find("tbody");
				tbodythemsanpham.empty();
				tbodythemsanpham.append(value);
				
			}
		})
	})
	$("body").on("click",".btn-chitiet-tsp",function(){
		this.remove();
		var chitietclone = $("#chitietsanpham-tsp").clone();
		chitietclone.removeAttr("id");
		chitietclone.show();
		$("#container-chitietsanpham-tsp").append(chitietclone);
		$("#container-HinhAnh-tsp").empty();
		var mauSanPhams = [];
		$("#container-chitietsanpham-tsp> .chitietsanpham-tsp").each(function() {
			var mausp=$(this).find("select[name='mamau']").val();
			var kt = true;
			for(var i=0;i<mauSanPhams.length;i++){
				if(mauSanPhams == mausp){
					kt= false;
				}
			}
			if(kt){
				mauSanPhams.push(mausp);
				$("#container-HinhAnh-tsp").append("<label for='hinhanh-tsp'>Hình ảnh Màu " + mausp 
						+"</label>" 
						+"<input name='hinhanh-tsp' type='file' class='form-control'"
							+" id='hinhanh-tsp' /><br />");
			}
		})
	})
	
	$("#xoasanpham").click(function() {
		$("#table_themsanpham > tbody input:checked").each(function() {
			var id =$(this).val();
			var a=this;
			$.ajax({
				url:"/DuAn2/admin/api/xoaSanPham",
				type:"POST",
				data:{
					id: id,
				},
				success: function(value){
					if(value=="false"){
						alert("Xóa không thành công");
					}
					else{
						a.closest("tr").remove();
					}
				}
			})
		})
	})
	var id = 0;
	$("body").on("click",".capnhat-tsp",function(){
		id= $(this).attr("data-value");
		$.ajax({
			url:"/DuAn2/admin/api/laysanphamtheoma",
			type:"POST",
			data:{
				id:id,
			},
			success: function(value){
				console.log(value);
				$("#tensanpham-tsp").val(value.tensanpham);
				$("#tensanpham-tsp").attr("data",value.masanpham);
				$("#giatien-tsp").val(value.giatien);
				$("#mota-tsp").val(value.mota);
				$("#danhmuc-tsp").val(value.danhMucSanPham);
				$("#chatlieu-tsp").val(value.chatLieu);
				$("#container-chitietsanpham-tsp").empty();
				for(var i=0;i<value.chiTietSanPhams.length;i++){
					var chitietclone = $("#chitietsanpham-tsp").clone();
					chitietclone.removeAttr("id");
					chitietclone.attr("data",value.chiTietSanPhams[i].id);
					chitietclone.find(".mamau-tsp").val(value.chiTietSanPhams[i].tenMau);
					chitietclone.find(".masize-tsp").val(value.chiTietSanPhams[i].tenSize);
					chitietclone.find(".soluong-tsp").val(value.chiTietSanPhams[i].soLuong);
					if(i!=value.chiTietSanPhams.length-1){
						chitietclone.find(".btn-chitiet-tsp").remove();
					}
					chitietclone.show();
					$("#container-chitietsanpham-tsp").append(chitietclone);
				}
				$("#btn-capnhat-tsp").removeClass("hide");
				$("#btn-thoatcapnhat-tsp").removeClass("hide");
				$("#container-HinhAnh-tsp").empty();
				var mauSanPhams = [];
				$("#container-chitietsanpham-tsp> .chitietsanpham-tsp").each(function() {
					var mausp=$(this).find("select[name='mamau']").val();
					var kt = true;
					for(var i=0;i<mauSanPhams.length;i++){
						if(mauSanPhams == mausp){
							kt= false;
						}
					}
					if(kt){
						mauSanPhams.push(mausp);
						$("#container-HinhAnh-tsp").append("<label for='hinhanh-tsp'>Hình ảnh Màu " + mausp 
								+"</label>" 
								+"<input name='hinhanh-tsp' type='file' class='form-control hinhanh-tsp' data = '"+ mausp+"' "
									+" /><br />");
					}
				})
			}
		})
	})
	$("body").on("click","#btn-thoatcapnhat-tsp",function(){
		$("#container-chitietsanpham-tsp").empty();
		var chitietclone = $("#chitietsanpham-tsp").clone();
		chitietclone.removeAttr("id");
		chitietclone.show();
		$("#container-chitietsanpham-tsp").append(chitietclone);
		$("#btn-capnhat-tsp").addClass("hide");
		$("#btn-thoatcapnhat-tsp").addClass("hide");
		
	})
	
	var files=[];
	var tenhinh="";
	$("body").on("change",".hinhanh-tsp",function(){
		files=event.target.files;
		tenhinh=files[0].name;
		var forms= new FormData();
		forms.append("file",files[0]);
		console.log(forms);
		$.ajax({
			url:"/DuAn2/admin/api/uploadHinhAnh",
			type:"POST",
			data:forms,
			contentType:false,
			processData:false,
			entype:"multipart/form-data",
			success: function(value){
				alert(value)
			}
		})
	})
	
	$("#btn-themsanpham-tsp").click(function() {
		var formdatas=$("#formsp-tsp").serializeArray();
		json ={};
		arrayChitiet=[];
		$.each(formdatas,function(i,field){
			if(field.name!="mamau"&&field.name!="masize"&&field.name!="soluong"){
				
					json[field.name] =field.value;
				
			}
			
		})
		$("#container-chitietsanpham-tsp> .chitietsanpham-tsp").each(function() {
			objChitiet={};
			var mausp=$(this).find("select[name='mamau']").val();
			var sizesp=$(this).find("select[name='masize']").val();
			var soluong=$(this).find("input[name='soluong']").val();
//			var mau3 = {};
//			mau3["id"]=mausp;
//			var size3 = {};
//			size3["id"]=sizesp;
			objChitiet["maMau"]=mausp;
			objChitiet["maSize"]=sizesp;
			objChitiet["soLuong"]=soluong;
			arrayChitiet.push(objChitiet);
		})
		json["chiTietSanPhams"]=arrayChitiet;
		json["masanpham"]=0;
		
		
		arrayMau=[];
		$("#container-HinhAnh-tsp> .hinhanh-tsp").each(function(e ) {
			objMau={};
			var ten =$(this).attr("data");
			var str = $(this).val();
			var hinhanh =str.split(/(\\|\/)/g).pop();
			objMau["maMau"]=ten;
			objMau["url"]=hinhanh;
			arrayMau.push(objMau);
		})
		json["hinhAnhs"]=arrayMau;
		var datajson= JSON.stringify(json);
		console.log(datajson);
		
		$.ajax({
			url:"/DuAn2/admin/api/themSanPham",
			type:"POST",
			contentType: "application/json; charset=utf-8",
			dataType: 'json',
			data:JSON.stringify(json),
			success: function(value){
				console.log(value);
			},
			error: function(data){
				console.log(data.responseText);
	        }
			
		})
	})
	$("body").on("change",".mamau-tsp",function(){
		$("#container-HinhAnh-tsp").empty();
		var mauSanPhams = [];
		$("#container-chitietsanpham-tsp> .chitietsanpham-tsp").each(function() {
			var mausp=$(this).find("select[name='mamau']").val();
			var kt = true;
			for(var i=0;i<mauSanPhams.length;i++){
				if(mauSanPhams == mausp){
					kt= false;
				}
			}
			if(kt){
				mauSanPhams.push(mausp);
				$("#container-HinhAnh-tsp").append("<label for='hinhanh-tsp'>Hình ảnh Màu " + mausp 
						+"</label>" 
						+"<input name='hinhanh-tsp' type='file' class='form-control hinhanh-tsp' data = '"+ mausp+"' "
							+" /><br />");
			}
		})
	})
	
	$("#btn-capnhat-tsp").click(function() {
		var formdatas=$("#formsp-tsp").serializeArray();
		json ={};
		arrayChitiet=[];
		$.each(formdatas,function(i,field){
			if(field.name!="mamau"&&field.name!="masize"&&field.name!="soluong"){
				
					json[field.name] =field.value;
				
			}
			
		})
		$("#container-chitietsanpham-tsp> .chitietsanpham-tsp").each(function() {
			objChitiet={};
			var mausp=$(this).find("select[name='mamau']").val();
			var sizesp=$(this).find("select[name='masize']").val();
			var soluong=$(this).find("input[name='soluong']").val();
			var idsss = $(this).attr("data");
			objChitiet["id"]=idsss;
			objChitiet["maMau"]=mausp;
			objChitiet["maSize"]=sizesp;
			objChitiet["soLuong"]=soluong;
			arrayChitiet.push(objChitiet);
		})
		json["chiTietSanPhams"]=arrayChitiet;
		json["masanpham"]=$("#tensanpham-tsp").attr("data");
		
		
		arrayMau=[];
		$("#container-HinhAnh-tsp> .hinhanh-tsp").each(function(e ) {
			objMau={};
			var ten =$(this).attr("data");
			var str = $(this).val();
			var hinhanh =str.split(/(\\|\/)/g).pop();
			objMau["maMau"]=ten;
			objMau["url"]=hinhanh;
			arrayMau.push(objMau);
		})
		json["hinhAnhs"]=arrayMau;
		var datajson= JSON.stringify(json);
		console.log(datajson);
		
		$.ajax({
			url:"/DuAn2/admin/api/capNhatSanPham",
			type:"POST",
			contentType: "application/json; charset=utf-8",
			dataType: 'json',
			data:JSON.stringify(json),
			success: function(value){
				console.log(value);
			},
			error: function(data){
				console.log(data.responseText);
	        }
			
		})
	})

	
})