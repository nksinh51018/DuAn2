package com.nk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nk.entity.LoaiSanPham;
import com.nk.entity.Mau;
import com.nk.entity.SanPham;
import com.nk.entity.Size;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.MauService;
import com.nk.service.SanPhamService;
import com.nk.service.SizeService;

@Controller
public class AdminController{

//	@GetMapping("/admin")
//	@ResponseBody
//	public String admin() {
//		return "admin";
//	}
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@Autowired 
	private MauService mauService;
	
	@Autowired 
	private SizeService sizeService;
	
	@GetMapping("/admin")
	public String Default(ModelMap modelMap) {
		List<SanPham> sanPhams = sanPhamService.laydsSanPhamLimit(0, 5);
		modelMap.addAttribute("sanPhams", sanPhams);
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		int soluongsanpham = sanPhamService.soLuongSanPham();
		modelMap.addAttribute("soluongsanpham", soluongsanpham);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "themsanpham";
	}
	
	
	
}
