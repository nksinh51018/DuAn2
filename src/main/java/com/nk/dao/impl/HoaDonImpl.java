package com.nk.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nk.dao.HoaDonDAO;
import com.nk.entity.HoaDon;

@Repository
@Scope(proxyMode =  ScopedProxyMode.TARGET_CLASS)
@Transactional
public class HoaDonImpl implements HoaDonDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public int themHoaDon(HoaDon hoaDon) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(hoaDon); 
	}

}
