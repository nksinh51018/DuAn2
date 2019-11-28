package com.nk.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.nk.entity.ChiTietSanPham;
import com.nk.entity.HinhAnh;
import com.nk.entity.KhuyenMai;
import com.nk.entity.LoaiSanPham;

public class jsonSanpham implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tensanpham;
	private String giatien;
	private String chatLieu;
	private String  danhMucSanPham;
	private String mota;
	private String hinhsanpham;
	private List<ChiTietSanPhamDTO> chiTietSanPhams;
	private int masanpham;
	private List<HinhAnhDTO> hinhAnhs;
//	private int masanpham;
//	private String  danhMucSanPham;
//	private String tensanpham;
//	private String giatien;
//	private String mota;
//	private String hinhsanpham;
//	private Set<ChiTietSanPham> chiTietSanPhams;
//	private String chatLieu;
	
	public jsonSanpham() {
		
	}
	public jsonSanpham(String tensanpham, String giatien, String chatLieu, String danhMucSanPham, String mota,
			String hinhsanpham, List<ChiTietSanPhamDTO> chiTietSanPhams, int masanpham,List<HinhAnhDTO> hinhAnhs) {
		
		this.tensanpham = tensanpham;
		this.giatien = giatien;
		this.chatLieu = chatLieu;
		this.danhMucSanPham = danhMucSanPham;
		this.mota = mota;
		this.hinhsanpham = hinhsanpham;
		this.chiTietSanPhams = chiTietSanPhams;
		this.masanpham = masanpham;
		this.hinhAnhs = hinhAnhs;
	}
	
	
	public List<HinhAnhDTO> getHinhAnhs() {
		return hinhAnhs;
	}
	public void setHinhAnhs(List<HinhAnhDTO> hinhAnhs) {
		this.hinhAnhs = hinhAnhs;
	}
	public String getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}
	public int getMasanpham() {
		return masanpham;
	}
	public void setMasanpham(int masanpham) {
		this.masanpham = masanpham;
	}
	public String getDanhMucSanPham() {
		return danhMucSanPham;
	}
	public void setDanhMucSanPham(String danhMucSanPham) {
		this.danhMucSanPham = danhMucSanPham;
	}
	public String getTensanpham() {
		return tensanpham;
	}
	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}
	public String getGiatien() {
		return giatien;
	}
	public void setGiatien(String giatien) {
		this.giatien = giatien;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getHinhsanpham() {
		return hinhsanpham;
	}
	public void setHinhsanpham(String hinhsanpham) {
		this.hinhsanpham = hinhsanpham;
	}
	public List<ChiTietSanPhamDTO> getChiTietSanPhams() {
		return chiTietSanPhams;
	}
	public void setChiTietSanPhams(List<ChiTietSanPhamDTO> chiTietSanPhams) {
		this.chiTietSanPhams = chiTietSanPhams;
	}
}
