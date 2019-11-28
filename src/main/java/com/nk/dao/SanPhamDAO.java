package com.nk.dao;

import java.util.List;

import com.nk.entity.SanPham;

public interface SanPhamDAO {
	public List<SanPham> laydsSanPhamLimit(int spbatdaum,int soluong);
	public SanPham getSanPhamById(int id);
	public List<SanPham> locSanPham(String loai,String [] maus,String giamin,String giamax, String [] sizes);
	public List<SanPham> timKiemByName(String tk);
	public int soLuongSanPham();
	public boolean xoaSanPham(int id);
	public boolean themSanPham(SanPham sanPham);
	public boolean capNhatSanPham(SanPham sanPham);
}
