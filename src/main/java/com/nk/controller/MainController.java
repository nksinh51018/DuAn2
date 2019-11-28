package com.nk.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dao.impl.LoaiSanPhamImpl;
import com.nk.entity.ChiTietSanPham;
import com.nk.entity.LoaiSanPham;
import com.nk.entity.Mau;
import com.nk.entity.SanPham;
import com.nk.entity.Size;
import com.nk.entity.TaiKhoan;
import com.nk.model.GioHang;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.MauService;
import com.nk.service.SanPhamService;
import com.nk.service.SizeService;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes({"gioHang","tendangnhap"})
public class MainController {


	@Autowired
	private SanPhamService sanPhamService;

	@Autowired
	private LoaiSanPhamService loaiSanPhamService;

	@Autowired
	private MauService mauService;

	@Autowired
	private SizeService sizeService;

	@GetMapping("/")
	public String home(ModelMap modelMap, HttpSession httpSession) {
		List<SanPham> sanPhams = sanPhamService.laydsSanPhamLimit(0,12);
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("sanPhams", sanPhams);
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		System.out.println(ten);
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		return "home";
	}

	@GetMapping("/chitiet/{id}")
	public String chitiet(@PathVariable int id, ModelMap modelMap, HttpSession httpSession) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		SanPham sanPham = sanPhamService.getSanPhamById(id);
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("sanPham", sanPham);
		modelMap.addAttribute("sanPhamChiTiet",
				sanPham.getChiTietSanPhams().get(sanPham.getChiTietSanPhams().size() - 1).getId());
		modelMap.addAttribute("sanPhamHinhAnh", sanPham.getHinhAnhs().get(sanPham.getHinhAnhs().size() - 1).getId());
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		Set<Mau> maus = new HashSet<Mau>();
		for (ChiTietSanPham chiTietSanPham : sanPham.getChiTietSanPhams()) {
			maus.add(chiTietSanPham.getMau());
		}
		modelMap.addAttribute("maus", maus);
		Set<Size> sizes = new HashSet<Size>();
		for (ChiTietSanPham chiTietSanPham : sanPham.getChiTietSanPhams()) {
			sizes.add(chiTietSanPham.getSize());
		}
		modelMap.addAttribute("sizes", sizes);
		return "chitiet";
	}

	@GetMapping("/login")
	public String login(ModelMap modelMap, HttpSession httpSession) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		return "login";
	}
	@GetMapping("/login_process")
	public String login_process(ModelMap modelMap, HttpSession httpSession) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			modelMap.addAttribute("tendangnhap", ((UserDetails) userDetails).getUsername());
			return "redirect:/";
		} else {
			modelMap.addAttribute("message", "Đăng nhập thất bại");
		}
		return "login";
	}

	@GetMapping("/loaisanpham/{loai}")
	public String loaiSanPham(@PathVariable String loai, HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		LoaiSanPham lsp = loaiSanPhamService.getLoaiSanPhamByTen(loai);
		System.out.println(lsp.getSanPhams().get(0).getHinhAnhs().get(0).getUrl());
		modelMap.addAttribute("lsp", lsp);
		modelMap.addAttribute("loai", loai);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "loaisanpham";
	}

	@GetMapping(value = { "/loaisanpham/{loai}/mau/{maus1}/gia/{giamin}/{giamax}/size/{sizes1}",
			})
	public String locSanPhamAndMauAndGiaAndSize(@PathVariable String loai, @PathVariable String[] maus1,
			@PathVariable String giamin, @PathVariable String giamax, @PathVariable String[] sizes1,
			HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		List<SanPham> sanPhams = sanPhamService.locSanPham(loai, maus1, giamin, giamax, sizes1);
		for (SanPham sanPham : sanPhams) {
			System.out.println(sanPham.getTenSanPham());
		}
		modelMap.addAttribute("sanPhams", sanPhams);
		modelMap.addAttribute("loai", loai);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "locsanpham";
	}
	
	@GetMapping(value = {
			"/loaisanpham/{loai}/mau/{maus1}/gia/size",
			})
	public String locSanPhamMau(@PathVariable String loai, @PathVariable String[] maus1,
			HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		List<SanPham> sanPhams = sanPhamService.locSanPham(loai, maus1, null, null, null);
		for (SanPham sanPham : sanPhams) {
			System.out.println(sanPham.getTenSanPham());
		}
		modelMap.addAttribute("sanPhams", sanPhams);
		modelMap.addAttribute("loai", loai);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "locsanpham";
	}
	
	@GetMapping(value = {
			"/loaisanpham/{loai}/mau/gia/{giamin}/{giamax}/size",
			})
	public String locSanPhamGiaa(@PathVariable String loai,
			@PathVariable String giamin, @PathVariable String giamax,
			HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		List<SanPham> sanPhams = sanPhamService.locSanPham(loai, null, giamin, giamax, null);
		for (SanPham sanPham : sanPhams) {
			System.out.println(sanPham.getTenSanPham());
		}
		modelMap.addAttribute("sanPhams", sanPhams);
		modelMap.addAttribute("loai", loai);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "locsanpham";
	}
	
	@GetMapping(value = {
			"/loaisanpham/{loai}/mau/gia/size/{sizes1}",
			})
	public String locSanPhamSize(@PathVariable String loai,
			@PathVariable String[] sizes1,
			HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		List<SanPham> sanPhams = sanPhamService.locSanPham(loai, null, null, null, sizes1);
		for (SanPham sanPham : sanPhams) {
			System.out.println(sanPham.getTenSanPham());
		}
		modelMap.addAttribute("sanPhams", sanPhams);
		modelMap.addAttribute("loai", loai);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "locsanpham";
	}
	
	@GetMapping(value = {
			"/loaisanpham/{loai}/mau/{maus1}/gia/{giamin}/{giamax}/size",
			})
	public String locSanPhamMauAndGia(@PathVariable String loai,
			@PathVariable String[] maus1,
			@PathVariable String giamin, @PathVariable String giamax,
			HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		List<SanPham> sanPhams = sanPhamService.locSanPham(loai, maus1, giamin, giamax, null);
		for (SanPham sanPham : sanPhams) {
			System.out.println(sanPham.getTenSanPham());
		}
		modelMap.addAttribute("sanPhams", sanPhams);
		modelMap.addAttribute("loai", loai);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "locsanpham";
	}
	
	@GetMapping(value = {
			"/loaisanpham/{loai}/mau/{maus1}/gia/size/{sizes1}",
			})
	public String locSanPhamMauAndSize(@PathVariable String loai,
			@PathVariable String[] maus1,
			@PathVariable String[] sizes1,
			HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		List<SanPham> sanPhams = sanPhamService.locSanPham(loai, maus1, null, null, sizes1);
		for (SanPham sanPham : sanPhams) {
			System.out.println(sanPham.getTenSanPham());
		}
		modelMap.addAttribute("sanPhams", sanPhams);
		modelMap.addAttribute("loai", loai);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "locsanpham";
	}
	
	@GetMapping(value = {
			"/loaisanpham/{loai}/mau/gia/{giamin}/{giamax}/size/{sizes1}",
			})
	public String locSanPhamMauAndSize(@PathVariable String loai,
			@PathVariable String giamin, @PathVariable String giamax,
			@PathVariable String[] sizes1,
			HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		List<SanPham> sanPhams = sanPhamService.locSanPham(loai, null, giamin, giamax, sizes1);
		for (SanPham sanPham : sanPhams) {
			System.out.println(sanPham.getTenSanPham());
		}
		modelMap.addAttribute("sanPhams", sanPhams);
		modelMap.addAttribute("loai", loai);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		return "locsanpham";
	}
	
	@GetMapping("/timkiem/{tk}")
	public String timkiem(@PathVariable String tk,
			HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		List<SanPham> sanPhams = sanPhamService.timKiemByName(tk);
		for (SanPham sanPham : sanPhams) {
			System.out.println(sanPham.getTenSanPham());
		}
		modelMap.addAttribute("sanPhams", sanPhams);
		List<Mau> maus = mauService.getAllMau();
		modelMap.addAttribute("maus", maus);
		List<Size> sizes = sizeService.getAllSize();
		modelMap.addAttribute("sizes", sizes);
		modelMap.addAttribute("tk",tk);
		return "timkiem";
	}


	@GetMapping("/dangki")
	public String dangki(HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHang = (List<GioHang>)httpSession.getAttribute("gioHang");
		modelMap.addAttribute("giohang", gioHang);
		String ten = (String) httpSession.getAttribute("tendangnhap");
		if (ten != null) {
			modelMap.addAttribute("ten", ten.charAt(0));
		}
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAllLoaiSanPham();
		modelMap.addAttribute("loaiSanPhams", loaiSanPhams);
		return "dangki";
	}


	@GetMapping(value = "/test/{firstNameIds}/{asd}")
	@ResponseBody
	public String test(@PathVariable String[] firstNameIds, @PathVariable String[] asd) {
		// firstNameIds: [1,2,3,4]
		return "Dummy";
	}

}
