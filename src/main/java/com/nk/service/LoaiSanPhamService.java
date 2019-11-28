package com.nk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.LoaiSanPhamDao;
import com.nk.dao.impl.LoaiSanPhamImpl;
import com.nk.entity.LoaiSanPham;

@Service
public class LoaiSanPhamService implements LoaiSanPhamDao{

	@Autowired
	private LoaiSanPhamImpl loaiSanPhamImpl;
	
	public List<LoaiSanPham> getAllLoaiSanPham() {
		// TODO Auto-generated method stub
		return loaiSanPhamImpl.getAllLoaiSanPham();
	}

	public LoaiSanPham getLoaiSanPhamByTen(String ten) {
		// TODO Auto-generated method stub
		return loaiSanPhamImpl.getLoaiSanPhamByTen(ten);
	}

}
