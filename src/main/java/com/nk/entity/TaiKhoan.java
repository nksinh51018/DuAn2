package com.nk.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="taikhoan")
public class TaiKhoan implements Serializable{

	private static final long serialVersionUID = 7788573051403565353L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	
	private String TenDangNhap;
	private String MatKhau;
	private String VaiTro;
	private boolean enable;
	private String Ten;
	private boolean GioiTinh;
	private String DiaChi;
	private String SDT;
	
	
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public boolean isGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	@OneToMany(mappedBy = "taiKhoan",cascade = CascadeType.ALL)
	private Set<HoaDon> hoaDons;

	public Set<HoaDon> getHoaDons() {
		return hoaDons;
	}
	public void setHoaDons(Set<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenDangNhap() {
		return TenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		TenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	public String getVaiTro() {
		return VaiTro;
	}
	public void setVaiTro(String vaiTro) {
		VaiTro = vaiTro;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
