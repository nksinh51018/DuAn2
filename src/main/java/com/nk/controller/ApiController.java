package com.nk.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nk.entity.ChiTietHoaDon;
import com.nk.entity.ChiTietSanPham;
import com.nk.entity.HinhAnh;
import com.nk.entity.HoaDon;
import com.nk.entity.LoaiSanPham;
import com.nk.entity.Mau;
import com.nk.entity.SanPham;
import com.nk.entity.Size;
import com.nk.entity.TaiKhoan;
import com.nk.model.ChiTietSanPhamDTO;
import com.nk.model.GioHang;
import com.nk.model.HinhAnhDTO;
import com.nk.model.jsonSanpham;
import com.nk.service.ChiTietHoaDonService;
import com.nk.service.ChiTietSanPhamService;
import com.nk.service.HinhAnhService;
import com.nk.service.HoaDonService;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.MauService;
import com.nk.service.SanPhamService;
import com.nk.service.SizeService;
import com.nk.service.TaiKhoanService;

@RestController
@SessionAttributes({"gioHang","tendangnhap"})
public class ApiController {
	
	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@Autowired
	private HoaDonService hoaDonService;
	
	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;
	
	@Autowired
	private ChiTietHoaDonService chiTietHoaDonService;
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@Autowired
	private MauService mauService;
	
	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@Autowired
	private HinhAnhService hinhAnhService;
	
	@GetMapping(path="/api/themgiohang",produces = "application/json;charset=UTF-8")
	public int themGioHang(HttpSession httpSession , @RequestParam("idSanPham") int idSanPham,
			@RequestParam("idMau") int idMau,@RequestParam("idSize") int idSize,@RequestParam int soluong,
			@RequestParam String tenSanPham,@RequestParam String tenMau,@RequestParam String tenSize,
			@RequestParam String hinhanh,@RequestParam String gia) {
		List<GioHang> gioHangs;
		if(httpSession.getAttribute("gioHang")==null) {
			gioHangs = new ArrayList<GioHang>();
			GioHang gioHang = new GioHang();
			gioHang.setIdMau(idMau);
			gioHang.setIdSanPham(idSanPham);
			gioHang.setIdSize(idSize);
			gioHang.setSoluong(soluong);
			gioHang.setGia(gia);
			gioHang.setHinhanh(hinhanh);
			gioHang.setTenMau(tenMau);
			gioHang.setTenSanPham(tenSanPham);
			gioHang.setTenSize(tenSize);
			gioHangs.add(gioHang);
			httpSession.setAttribute("gioHang", gioHangs);
			System.out.println(gioHangs.size());
		}
		else {
			gioHangs =(List<GioHang>) httpSession.getAttribute("gioHang");
			int vitri = vitri(idSanPham, idMau, idSize, gioHangs);
			if(vitri < 0 ) {
				GioHang gioHang = new GioHang();
				gioHang.setIdMau(idMau);
				gioHang.setIdSanPham(idSanPham);
				gioHang.setIdSize(idSize);
				gioHang.setSoluong(soluong);
				gioHang.setGia(gia);
				gioHang.setHinhanh(hinhanh);
				gioHang.setTenMau(tenMau);
				gioHang.setTenSanPham(tenSanPham);
				gioHang.setTenSize(tenSize);
				gioHangs.add(gioHang);
				
				httpSession.setAttribute("gioHang", gioHangs);
				System.out.println(gioHangs.size());
			}
			else {
				gioHangs.get(vitri).setSoluong(soluong);
				httpSession.setAttribute("gioHang", gioHangs);
				System.out.println(gioHangs.size());
			}
			
		}
		
		return gioHangs.size();
	}
	
	private int vitri(int idSanPham,int idMau,int idSize,List<GioHang> gioHangs) {
		for(int i=0;i<gioHangs.size();i++) {
			if(gioHangs.get(i).getIdSanPham()==idSanPham&&gioHangs.get(i).getIdMau()==idMau&&gioHangs.get(i).getIdSize()==idSize) {
				return i;
			}
		}
		
		return -1;
		
	}
	
	@PostMapping("/api/dangki")
	public boolean dangki(@RequestParam String tenDangNhap,@RequestParam String matKhau) {
		return taiKhoanService.dangki(tenDangNhap, matKhau);
	}
	
	@GetMapping(path="/api/xoagiohang",produces = "application/json;charset=UTF-8")
	public int xoaGioHang(HttpSession httpSession , @RequestParam("idSanPham") int idSanPham,
			@RequestParam("idMau") int idMau,@RequestParam("idSize") int idSize) {
		List<GioHang> gioHangs= null;
		gioHangs = (List<GioHang>) httpSession.getAttribute("gioHang");
		if(gioHangs!=null) {
			int v = vitri(idSanPham, idMau, idSize,  gioHangs);
			System.out.println(v);
			gioHangs.remove(v);
			httpSession.setAttribute("gioHang", gioHangs);
		}
		return gioHangs.size();
	}
	
	@PostMapping("/user/api/capnhatTaiKhoan")
	public boolean capnhatTaikhoan(@RequestParam String id,
			@RequestParam String gioiTinh,
			@RequestParam String ten,
			@RequestParam String diaChi,
			@RequestParam String sdt,
			@RequestParam String tenDangNhap,
			@RequestParam String matKhauHienTai,
			@RequestParam String thayDoi,
			@RequestParam String matKhauMoi) {
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setDiaChi(diaChi);
		taiKhoan.setEnable(true);
		if(gioiTinh.equals("true")) {
			taiKhoan.setGioiTinh(true);
		}
		else {
			taiKhoan.setGioiTinh(false);
		}
		taiKhoan.setId(Integer.parseInt(id));
		taiKhoan.setSDT(sdt);
		taiKhoan.setTen(ten);
		taiKhoan.setTenDangNhap(tenDangNhap);
		taiKhoan.setVaiTro("ROLE_USER");
		if(thayDoi.equals("true")) {
			taiKhoan.setMatKhau(matKhauMoi);
			return taiKhoanService.capNhatTaiKhoan(taiKhoan, true,matKhauHienTai);
		}
		else {
			taiKhoan.setMatKhau(matKhauHienTai);
			return taiKhoanService.capNhatTaiKhoan(taiKhoan, false,matKhauHienTai);
		}
	}
	
	@PostMapping("/user/api/datHang")
	public void dathang(@RequestParam String sdt,@RequestParam String diaChi,HttpSession httpSession) { 
		HoaDon hoaDon = new HoaDon();
		hoaDon.setSdt(sdt);
		hoaDon.setDiaChi(diaChi);
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			String tenTaiKhoan = ((UserDetails)userDetails).getUsername();
			TaiKhoan taiKhoan = taiKhoanService.findTaiKhoanByTenTaiKhoan(tenTaiKhoan);
			hoaDon.setTaiKhoan(taiKhoan);
		}
		int id= hoaDonService.themHoaDon(hoaDon);
		hoaDon.setId(id);
		List<ChiTietHoaDon> chiTietHoaDons =new ArrayList<ChiTietHoaDon>();
		List<GioHang> gioHangs= null;
		gioHangs = (List<GioHang>) httpSession.getAttribute("gioHang");
		for (GioHang gioHang : gioHangs) {
			ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
			ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getId(gioHang.getIdSanPham(), gioHang.getIdMau(), gioHang.getIdSize());
			chiTietHoaDon.setChiTietSanPham(chiTietSanPham);
			chiTietHoaDon.setSoLuong(gioHang.getSoluong());
			chiTietHoaDon.setHoaDon(hoaDon);
			chiTietHoaDons.add(chiTietHoaDon);
		}
		System.out.println(chiTietHoaDons.size());
		chiTietHoaDonService.luuChiTietHoaDons(chiTietHoaDons);
	}
	
	@PostMapping("/user/api/datHang2")
	public void dathang(HttpSession httpSession) {
		HoaDon hoaDon = new HoaDon();
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			String tenTaiKhoan = ((UserDetails)userDetails).getUsername();
			TaiKhoan taiKhoan = taiKhoanService.findTaiKhoanByTenTaiKhoan(tenTaiKhoan);
			hoaDon.setTaiKhoan(taiKhoan);
			hoaDon.setDiaChi(taiKhoan.getDiaChi());
			hoaDon.setSdt(taiKhoan.getSDT());
		}
		int id= hoaDonService.themHoaDon(hoaDon);
		hoaDon.setId(id);
		List<ChiTietHoaDon> chiTietHoaDons =new ArrayList<ChiTietHoaDon>();
		List<GioHang> gioHangs= null;
		gioHangs = (List<GioHang>) httpSession.getAttribute("gioHang");
		for (GioHang gioHang : gioHangs) {
			ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
			ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getId(gioHang.getIdSanPham(), gioHang.getIdMau(), gioHang.getIdSize());
			chiTietHoaDon.setChiTietSanPham(chiTietSanPham);
			chiTietHoaDon.setSoLuong(gioHang.getSoluong());
			chiTietHoaDon.setHoaDon(hoaDon);
			chiTietHoaDons.add(chiTietHoaDon);
		}
		System.out.println(chiTietHoaDons.size());
		chiTietHoaDonService.luuChiTietHoaDons(chiTietHoaDons);
	}
	
	@GetMapping(path="/admin/api/laySanPhamlimit",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String Laysanphamlimit(int spbatdau){
		String kq="";
		List<SanPham> list=sanPhamService.laydsSanPhamLimit(spbatdau, 5);
		
		for (SanPham sanPham : list) {
			kq+="<tr>";
			kq+="<td>\r\n" + 
					"								<div class=\"checkbox\">\r\n" + 
					"									<label><input type=\"checkbox\" value=\""+sanPham.getId()+"\" /></label>\r\n" + 
					"								</div>\r\n" + 
					"							</td>";
			kq+="<td>"+sanPham.getId()+"</td>";
			kq+="<td>"+sanPham.getTenSanPham()+"</td>";
			kq+="<td>"+sanPham.getGiaTien()+"</td>";
			kq+="<td class='capnhat-tsp btn btn-danger' data-value='"+sanPham.getId()+"'>Sửa</td>";
			kq+="</tr>";
		}
		return kq;
	}
	
	@PostMapping("/admin/api/xoaSanPham")
	public boolean xoaSanPham(@RequestParam int id) {
		System.out.println(id);
		return sanPhamService.xoaSanPham(id);
	}
	
	@PostMapping(path = "/admin/api/laysanphamtheoma",produces = "application/json;charset=UTF-8")
	public jsonSanpham laysanpham(@RequestParam int id) {
		SanPham sanPhams = sanPhamService.getSanPhamById(id);
		jsonSanpham jsonSanpham=new jsonSanpham();
		jsonSanpham.setDanhMucSanPham(sanPhams.getLoaiSanPham().getTenLoai());
		List<ChiTietSanPhamDTO> chiTietSanPhams =new ArrayList<ChiTietSanPhamDTO>();
		
		for (ChiTietSanPham chitiet : sanPhams.getChiTietSanPhams()) {
			ChiTietSanPhamDTO ct=new ChiTietSanPhamDTO();
			ct.setId(chitiet.getId()+"");
			ct.setMaMau(chitiet.getMau().getId()+"");
			ct.setMaSize(chitiet.getSize().getId()+"");
			ct.setSoLuong(chitiet.getSoLuong()+"");
			ct.setTenMau(chitiet.getMau().getTenMau());
			ct.setTenSize(chitiet.getSize().getTenSize()+"");
			chiTietSanPhams.add(ct);
		}
		jsonSanpham.setChatLieu(sanPhams.getChatLieu());
		jsonSanpham.setChiTietSanPhams(chiTietSanPhams);
		jsonSanpham.setMasanpham(sanPhams.getId());
		jsonSanpham.setGiatien(sanPhams.getGiaTien());
		jsonSanpham.setHinhsanpham(sanPhams.getHinhAnhs().get(0).getUrl());
		jsonSanpham.setMota(sanPhams.getMoTa());
		jsonSanpham.setTensanpham(sanPhams.getTenSanPham());
		return jsonSanpham;
	}
	
	@PostMapping(path = "/api/test",
			 //consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
		        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public GioHang test(@RequestBody GioHang gioHang) {
		return gioHang;
	}
	@Autowired
	ServletContext context;
	
	@PostMapping("/admin/api/uploadHinhAnh")
	public String uploadfile(HttpServletRequest request) {
		final int size = 1024*1024*3;
        final int maxsize = 1024*1024*50;
        final String address = context.getRealPath("/resources/image/");
        System.out.println(address);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart) {
        	request.setAttribute("message", "Chua co file");
        }
        else {
        	DiskFileItemFactory factory = new DiskFileItemFactory();

        	// Set factory constraints
        	factory.setSizeThreshold(size);
        	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        	// Create a new file upload handler
        	ServletFileUpload upload = new ServletFileUpload(factory);

        	// Set overall request size constraint
        	upload.setSizeMax(maxsize);

        	// Parse the request
        	try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();

				    if (!item.isFormField()) {
				    	String fileName = item.getName();
				    	String pathFile  = address + File.separator + fileName;
				    	File f = new File(pathFile);
				    	try {
							item.write(f);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							request.setAttribute("message", "loi viet file");
						}
				    } else {
				        
				    }
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				request.setAttribute("message", "loi upload file");
			}
        }
		return isMultipart+"";
	}
	@PostMapping(path = "/admin/api/themSanPham",
			// consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
		        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	
	public jsonSanpham themSanPham(@RequestBody jsonSanpham datajson) {
		System.out.println("themSanPham");
		System.out.println(datajson.getHinhAnhs().size());
		SanPham sanPham = new SanPham();
		sanPham.setChatLieu(datajson.getChatLieu());
		List<ChiTietSanPham> chiTietSanPhams = new ArrayList<ChiTietSanPham>();
		for (ChiTietSanPhamDTO chiTietSanPham : datajson.getChiTietSanPhams()) {
			ChiTietSanPham chiTietSanPham2 = new ChiTietSanPham();
			Mau mau = mauService.getMauByName(chiTietSanPham.getMaMau());
			Size size = sizeService.getSizeByName(chiTietSanPham.getMaSize());
			System.out.println("Mau: "+ mau.getTenMau()+" - Size: "+size.getTenSize());
			chiTietSanPham2.setMau(mau);
			chiTietSanPham2.setSize(size);
			chiTietSanPham2.setSoLuong(Integer.parseInt(chiTietSanPham.getSoLuong()));
			chiTietSanPham2.setSanPham(sanPham);
			chiTietSanPhams.add(chiTietSanPham2);
		}
		
		sanPham.setChiTietSanPhams(chiTietSanPhams);
		sanPham.setGiaTien(datajson.getGiatien());
		List<HinhAnh> hinhAnhs = new ArrayList<HinhAnh>();
		for (HinhAnhDTO hinhanh : datajson.getHinhAnhs()) {
			HinhAnh hinhAnh2 = new HinhAnh();
			Mau mau = mauService.getMauByName(hinhanh.getMaMau());
			System.out.println(mau.getTenMau());
			hinhAnh2.setMau(mau);
			hinhAnh2.setUrl(hinhanh.getUrl());
			hinhAnh2.setSanPham(sanPham);
			hinhAnhs.add(hinhAnh2);
		}
		sanPham.setHinhAnhs(hinhAnhs);
		LoaiSanPham loaiSanPham = loaiSanPhamService.getLoaiSanPhamByTen(datajson.getDanhMucSanPham());
		sanPham.setLoaiSanPham(loaiSanPham);
		sanPham.setMoTa(datajson.getMota());
		sanPham.setTenSanPham(datajson.getTensanpham());
		sanPham.setChatLieu(datajson.getChatLieu());
		sanPhamService.themSanPham(sanPham);
		return datajson;
	}
	
	@PostMapping(path = "/admin/api/capNhatSanPham",
			// consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
		        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	
	public jsonSanpham capNhatSanPham(@RequestBody jsonSanpham datajson) {
		System.out.println("themSanPham");
		
		SanPham sanPham = new SanPham();
		int id = datajson.getMasanpham();
		sanPham.setChatLieu(datajson.getChatLieu());
		System.out.println(datajson.getChatLieu());
		List<ChiTietSanPham> chiTietSanPhams = new ArrayList<ChiTietSanPham>();
		for (ChiTietSanPhamDTO chiTietSanPham : datajson.getChiTietSanPhams()) {
			ChiTietSanPham chiTietSanPham2 = new ChiTietSanPham();
			Mau mau = mauService.getMauByName(chiTietSanPham.getMaMau());
			Size size = sizeService.getSizeByName(chiTietSanPham.getMaSize());
			System.out.println("Mau: "+ mau.getTenMau()+" - Size: "+size.getTenSize());
			chiTietSanPham2.setMau(mau);
			chiTietSanPham2.setSize(size);
			chiTietSanPham2.setSoLuong(Integer.parseInt(chiTietSanPham.getSoLuong()));
			chiTietSanPham2.setSanPham(sanPham);
			chiTietSanPham2.setId(Integer.parseInt(chiTietSanPham.getId()));
			chiTietSanPhams.add(chiTietSanPham2);
		}
		
		sanPham.setChiTietSanPhams(chiTietSanPhams);
		sanPham.setGiaTien(datajson.getGiatien());
		List<HinhAnh> hinhAnhs = new ArrayList<HinhAnh>();
		for (HinhAnhDTO hinhanh : datajson.getHinhAnhs()) {
			Mau mau = mauService.getMauByName(hinhanh.getMaMau());
			System.out.println(mau.getTenMau());
			HinhAnh hinhAnh2 = hinhAnhService.getUrlBySanPhamAndMau(id, mau.getId());
			hinhAnh2.setMau(mau);
			if(hinhanh.getUrl()==null||hinhanh.getUrl()=="") {
				
			}
			else {
				hinhAnh2.setUrl(hinhanh.getUrl());
				hinhAnh2.setSanPham(sanPham);
				hinhAnhs.add(hinhAnh2);
			}
		}
		sanPham.setHinhAnhs(hinhAnhs);
		LoaiSanPham loaiSanPham = loaiSanPhamService.getLoaiSanPhamByTen(datajson.getDanhMucSanPham());
		sanPham.setLoaiSanPham(loaiSanPham);
		sanPham.setMoTa(datajson.getMota());
		sanPham.setTenSanPham(datajson.getTensanpham());
		sanPham.setChatLieu(datajson.getChatLieu());
		sanPham.setId(id);
		sanPhamService.capNhatSanPham(sanPham);
		return datajson;
	}
	
}
