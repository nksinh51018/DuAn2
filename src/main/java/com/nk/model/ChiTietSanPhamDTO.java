package com.nk.model;

import java.io.Serializable;

public class ChiTietSanPhamDTO implements Serializable{
	private String maMau;
	private String maSize;
	private String soLuong;
	private String tenMau;
	private String tenSize;
	private String id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getMaMau() {
		return maMau;
	}
	public void setMaMau(String maMau) {
		this.maMau = maMau;
	}
	public String getMaSize() {
		return maSize;
	}
	public void setMaSize(String maSize) {
		this.maSize = maSize;
	}
	public String getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
