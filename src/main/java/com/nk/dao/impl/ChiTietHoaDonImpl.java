package com.nk.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nk.dao.ChiTietHoaDonDAO;
import com.nk.entity.ChiTietHoaDon;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ChiTietHoaDonImpl implements ChiTietHoaDonDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void luuChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
			session.save(chiTietHoaDon);
		}
		
	}

}
