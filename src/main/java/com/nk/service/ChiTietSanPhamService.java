package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.ChiTietSanPhamDAO;
import com.nk.dao.impl.ChiTietSanPhamImpl;
import com.nk.entity.ChiTietSanPham;

@Service
public class ChiTietSanPhamService implements ChiTietSanPhamDAO{

	@Autowired
	private ChiTietSanPhamImpl chiTietSanPhamImpl;
	
	public ChiTietSanPham getId(Integer idSanPham, Integer idMau, Integer idSize) {
		// TODO Auto-generated method stub
		return chiTietSanPhamImpl.getId(idSanPham, idMau, idSize);
	}

}
