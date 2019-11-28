package com.nk.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "sanPham")
public class SanPham implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4611862305583185596L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String TenSanPham;
	private String GiaTien;
	private String MoTa;
	private String ChatLieu;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idLoaiSanPham")
	private LoaiSanPham LoaiSanPham;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "sanPham",cascade = CascadeType.ALL)
	private List<ChiTietSanPham> chiTietSanPhams;
	public LoaiSanPham getLoaiSanPham() {
		return LoaiSanPham;
	}
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "sanPham",cascade = CascadeType.ALL)
	private List<HinhAnh> hinhAnhs;
	
	public List<HinhAnh> getHinhAnhs() {
		return hinhAnhs;
	}

	public void setHinhAnhs(List<HinhAnh> hinhAnhs) {
		this.hinhAnhs = hinhAnhs;
	}

	public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
		LoaiSanPham = loaiSanPham;
	}
	
	public List<ChiTietSanPham> getChiTietSanPhams() {
		return chiTietSanPhams;
	}
	public void setChiTietSanPhams(List<ChiTietSanPham> chiTietSanPhams) {
		this.chiTietSanPhams = chiTietSanPhams;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenSanPham() {
		return TenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		TenSanPham = tenSanPham;
	}
	public String getGiaTien() {
		return GiaTien;
	}
	public void setGiaTien(String giaTien) {
		GiaTien = giaTien;
	}
	public String getMoTa() {
		return MoTa;
	}
	public void setMoTa(String moTa) {
		MoTa = moTa;
	}
	public String getChatLieu() {
		return ChatLieu;
	}
	public void setChatLieu(String chatLieu) {
		ChatLieu = chatLieu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
