package com.nk.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nk.dao.MauDAO;
import com.nk.entity.Mau;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class MauImpl implements MauDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Mau> getAllMau() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Mau> maus = session.createQuery("FROM Mau").getResultList();
		return maus;
	}

	public Mau getMauByName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM Mau m WHERE m.TenMau = ?1";
		List maus = session.createQuery(sql).setParameter(1, name).getResultList();
		if(!maus.isEmpty()) {
			Mau mau = (Mau) maus.get(0);
			return mau;
		}
		return null;
	}

}
