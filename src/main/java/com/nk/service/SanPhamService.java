package com.nk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.SanPhamDAO;
import com.nk.dao.impl.SanPhamDAOImpl;
import com.nk.entity.SanPham;

@Service
public class SanPhamService implements SanPhamDAO{

	@Autowired
	private SanPhamDAOImpl sanPhamDAOImpl;
	
	public List<SanPham> laydsSanPhamLimit(int spbatdau,int soluong) {
		// TODO Auto-generated method stub
		List<SanPham > sanPhams = sanPhamDAOImpl.laydsSanPhamLimit(spbatdau,soluong); 
		return sanPhams;
	}

	public SanPham getSanPhamById(int id) {
		// TODO Auto-generated method stub
		return sanPhamDAOImpl.getSanPhamById(id);
	}

	public List<SanPham> locSanPham(String loai, String[] maus, String giamin,String giamax, String[] sizes) {
		// TODO Auto-generated method stub
		List<SanPham > sanPhams = sanPhamDAOImpl.locSanPham(loai, maus, giamin,giamax, sizes);
		System.out.println(sanPhams.size());
		return sanPhams;
	}

	public List<SanPham> timKiemByName(String tk) {
		// TODO sanPhamDAOImpl-generated method stub
		return sanPhamDAOImpl.timKiemByName(tk);
	}

	public int soLuongSanPham() {
		// TODO Auto-generated method stub
		return sanPhamDAOImpl.soLuongSanPham();
	}

	public boolean xoaSanPham(int id) {
		// TODO Auto-generated method stub
		return sanPhamDAOImpl.xoaSanPham(id);
	}

	public boolean themSanPham(SanPham sanPham) {
		// TODO Auto-generated method stub
		return sanPhamDAOImpl.themSanPham(sanPham);
	}

	public boolean capNhatSanPham(SanPham sanPham) {
		// TODO Auto-generated method stub
		return sanPhamDAOImpl.capNhatSanPham(sanPham);
	}

}
