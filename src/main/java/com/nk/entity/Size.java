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

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Size implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3811651650296422817L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String TenSize;

	@OneToMany(mappedBy = "size",cascade = CascadeType.ALL)
	private Set<ChiTietSanPham> chiTietSanPhams;
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

	public String getTenSize() {
		return TenSize;
	}

	public void setTenSize(String tenSize) {
		TenSize = tenSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
