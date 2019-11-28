package com.nk.model;

import java.io.Serializable;

public class GioHang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSanPham;
	private int idMau;
	private int idSize;
	private int soluong;
	private String tenSanPham;
	private String tenMau;
	private String tenSize;
	private String hinhanh;
	private String gia;
	
	
	
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getTenMau() {
		return tenMau;
	}
	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}
	public String getTenSize() {
		return tenSize;
	}
	public void setTenSize(String tenSize) {
		this.tenSize = tenSize;
	}
	public String getHinhanh() {
		return hinhanh;
	}
	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}
	public String getGia() {
		return gia;
	}
	public void setGia(String gia) {
		this.gia = gia;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getIdSanPham() {
		return idSanPham;
	}
	public void setIdSanPham(int idSanPham) {
		this.idSanPham = idSanPham;
	}
	public int getIdMau() {
		return idMau;
	}
	public void setIdMau(int idMau) {
		this.idMau = idMau;
	}
	public int getIdSize() {
		return idSize;
	}
	public void setIdSize(int idSize) {
		this.idSize = idSize;
	}
	
	
}
