package com.nk.dao;

import java.util.List;

import com.nk.entity.LoaiSanPham;

public interface LoaiSanPhamDao {
	public List<LoaiSanPham> getAllLoaiSanPham();
	public LoaiSanPham getLoaiSanPhamByTen(String ten);
}
