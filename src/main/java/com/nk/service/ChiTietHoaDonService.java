package com.nk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.ChiTietHoaDonDAO;
import com.nk.dao.impl.ChiTietHoaDonImpl;
import com.nk.entity.ChiTietHoaDon;

@Service
public class ChiTietHoaDonService implements ChiTietHoaDonDAO{

	@Autowired
	private ChiTietHoaDonImpl chiTietHoaDonImpl;
	
	public void luuChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
		// TODO Auto-generated method stub
		chiTietHoaDonImpl.luuChiTietHoaDons(chiTietHoaDons);
	}

}
