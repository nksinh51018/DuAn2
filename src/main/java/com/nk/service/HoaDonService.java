package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.HoaDonDAO;
import com.nk.dao.impl.HoaDonImpl;
import com.nk.entity.HoaDon;

@Service
public class HoaDonService implements HoaDonDAO{

	@Autowired
	private HoaDonImpl hoaDonImpl;
	
	public int themHoaDon(HoaDon hoaDon) {
		// TODO Auto-generated method stub
		return hoaDonImpl.themHoaDon(hoaDon);
	}

}
