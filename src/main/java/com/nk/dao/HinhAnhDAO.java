package com.nk.dao;

import com.nk.entity.HinhAnh;

public interface HinhAnhDAO {
	public HinhAnh getUrlBySanPhamAndMau(int idSanPham,int idMau);
}
