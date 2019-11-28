package com.nk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.entity.LoaiSanPham;
import com.nk.entity.TaiKhoan;
import com.nk.model.GioHang;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.TaiKhoanService;

@Controller
@RequestMapping("/user")
@SessionAttributes({"gioHang","tendangnhap"})
public class UserController {
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@Autowired
	private TaiKhoanService taiKhoanService;

	@GetMapping
	@ResponseBody
	public String test() {
		return "test user";
	}
	
	@GetMapping("/thongtin")
	public String thongtin(ModelMap modelMap,HttpSession httpSession) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			modelMap.addAttribute("tendangnhap", ((UserDetails) userDetails).getUsername());
			String ten = (String) ((UserDetails) userDetails).getUsername();
			if (ten != null) {
				modelMap.addAttribute("ten", ten.charAt(0));
			}
			System.out.println(ten);
			TaiKhoan taiKhoan = taiKhoanService.findTaiKhoanByTenTaiKhoan(ten);
			modelMap.addAttribute("taiKhoan", taiKhoan);
			return "thongtin";
		}
		else {
			return "login";
		}
	}
	
	@GetMapping("/dathang")
	public String dahang(ModelMap modelMap,HttpSession httpSession) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			modelMap.addAttribute("tendangnhap", ((UserDetails) userDetails).getUsername());
			String ten = (String) ((UserDetails) userDetails).getUsername();
			if (ten != null) {
				modelMap.addAttribute("ten", ten.charAt(0));
			}
			System.out.println(ten);
			TaiKhoan taiKhoan = taiKhoanService.findTaiKhoanByTenTaiKhoan(ten);
			modelMap.addAttribute("taiKhoan", taiKhoan);
			return "dathang";
		}
		else {
			return "login";
		}
	}
	
	
}
