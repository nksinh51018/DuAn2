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
	var url = window.location.href;
//	var s3=",Red";
//	alert(s1.concat(s3,s2));
	var strMau = url.slice(url.indexOf("mau/")+4,url.indexOf("/gia"));
	var arrMau = strMau.split(",");
	var strSize = url.slice(url.indexOf("size")+5,url.length);
	var arrSize = strSize.split(",");
	var loai = url.slice(url.indexOf("loaisanpham/")+12,url.indexOf("/mau/"));
	var strGia = url.slice(url.indexOf("gia/")+4,url.indexOf("/size/"));
	$("#nameLoai").text( url.slice(url.indexOf("loaisanpham/")+12,url.length));
	$(".btnMau").each(function() {
		console.log($(this).attr("data-text"));
		for(var i=0;i<arrMau.length;i++){
			if(String($(this).attr("data-text"))===arrMau[i]){
				$(this).focus();
			}
		}
		
	})
	
	$(".btnMau").click(function() {
		var mau="";
		var kt = true;
		for(var i=0;i<arrMau.length;i++){
			if(String($(this).attr("data-text"))===arrMau[i]){
				kt=false;
			}
		}
		if(kt){
			if(url[url.indexOf("mau/")+4]=='g'){
				mau= "/"+$(this).attr("data-text");
			}
			else{
				mau= ","+$(this).attr("data-text");
			}
		}
		var s2=url.slice(url.indexOf("/gia"),url.lengh);
		var s1=url.slice(0,url.indexOf("/gia"));
		
		 window.location.href=s1.concat(mau,s2);
	})
	$(".chkGia").click(function() {
		var gia="";
		var s2=url.slice(url.indexOf("/size"),url.lengh);
		var s1=url.slice(0,url.indexOf("gia")+4);
		gia=$(this).attr("data-min")+"/"+$(this).attr("data-max");
		
		 window.location.href=s1.concat(gia,s2);
	})
	
	$(".btnSize").click(function() {
		var size="";
		var kt = true;
		for(var i=0;i<arrSize.length;i++){
			if(String($(this).attr("data-text"))===arrSize[i]){
				kt=false;
			}
		}
		if(kt){
		try {
			if(url[url.indexOf("size")+4]!='/'){
				size= "/"+$(this).attr("data-text");
			}
			else{
				size= ","+$(this).attr("data-text");
			}
			
		}
		catch(err) {
			size= ","+$(this).attr("data-text");
		}}
		var s2=url.slice(url.indexOf("/size")+5,url.lengh);
		var s1=url.slice(0,url.indexOf("size")+4);
		
		 window.location.href=url+size;
	})
		$("#timkiem").click(function() {
		var a=$("#txtTimkiem").val();
		if(a.length>0&&a!=null){
			window.location.href = "/DuAn2/timkiem/"+a;
		}
		
	})
})