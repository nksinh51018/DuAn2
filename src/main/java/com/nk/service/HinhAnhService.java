package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.HinhAnhDAO;
import com.nk.dao.impl.HinhAnhImpl;
import com.nk.entity.HinhAnh;

@Service
public class HinhAnhService implements HinhAnhDAO{

	@Autowired
	private HinhAnhImpl hinhAnhImpl;
	
	public HinhAnh getUrlBySanPhamAndMau(int idSanPham, int idMau) {
		// TODO Auto-generated method stub
		return hinhAnhImpl.getUrlBySanPhamAndMau(idSanPham, idMau);
	}

}
