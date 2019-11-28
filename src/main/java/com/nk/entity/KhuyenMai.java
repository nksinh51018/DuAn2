package com.nk.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KhuyenMai implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019665682980582264L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String TenKhuyenMai;
	private String ThoiGianBatDau;
	private String ThoiGianKetThuc;
	private String MoTa;
	private String HinhAnh;
	private int PhanTram;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenKhuyenMai() {
		return TenKhuyenMai;
	}
	public void setTenKhuyenMai(String tenKhuyenMai) {
		TenKhuyenMai = tenKhuyenMai;
	}
	public String getThoiGianBatDau() {
		return ThoiGianBatDau;
	}
	public void setThoiGianBatDau(String thoiGianBatDau) {
		ThoiGianBatDau = thoiGianBatDau;
	}
	public String getThoiGianKetThuc() {
		return ThoiGianKetThuc;
	}
	public void setThoiGianKetThuc(String thoiGianKetThuc) {
		ThoiGianKetThuc = thoiGianKetThuc;
	}
	public String getMoTa() {
		return MoTa;
	}
	public void setMoTa(String moTa) {
		MoTa = moTa;
	}
	public String getHinhAnh() {
		return HinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		HinhAnh = hinhAnh;
	}
	public int getPhanTram() {
		return PhanTram;
	}
	public void setPhanTram(int phanTram) {
		PhanTram = phanTram;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
