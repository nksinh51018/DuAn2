package com.nk.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Mau implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6606373256922513658L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  id;
	
	private String TenMau;
	@OneToMany(mappedBy = "mau",cascade = CascadeType.ALL)
	private Set<ChiTietSanPham> chiTietSanPhams;
	
	@OneToMany(mappedBy = "mau",cascade = CascadeType.ALL)
	private Set<HinhAnh> hinhAnhs;
	
	public Mau(String ten) {
		// TODO Auto-generated constructor stub
		this.TenMau = ten;
	}
	
	public Mau() {
		// TODO Auto-generated constructor stub
	}

	public Set<HinhAnh> getHinhAnhs() {
		return hinhAnhs;
	}

	public void setHinhAnhs(Set<HinhAnh> hinhAnhs) {
		this.hinhAnhs = hinhAnhs;
	}

	public Set<ChiTietSanPham> getChiTietSanPhams() {
		return chiTietSanPhams;
	}

	public void setChiTietSanPhams(Set<ChiTietSanPham> chiTietSanPhams) {
		this.chiTietSanPhams = chiTietSanPhams;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenMau() {
		return TenMau;
	}

	public void setTenMau(String tenMau) {
		TenMau = tenMau;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
