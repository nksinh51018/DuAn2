package com.nk.dao;

import com.nk.entity.TaiKhoan;

public interface TaiKhoanDAO {

	public TaiKhoan findTaiKhoanById(int id);
	public TaiKhoan findTaiKhoanByTenTaiKhoan(String tenTaiKhoan);
	public boolean KiemTraDangNhap(String tenTaiKhoan,String matKhau);
	public boolean dangki(String tenTaiKhoan,String matKhau);
	public boolean capNhatTaiKhoan(TaiKhoan taiKhoan,boolean thayDoi,String matKhauCu);
}
