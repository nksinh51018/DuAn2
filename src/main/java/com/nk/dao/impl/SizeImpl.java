package com.nk.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nk.dao.SizeDAO;
import com.nk.entity.Mau;
import com.nk.entity.Size;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class SizeImpl implements SizeDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Size> getAllSize() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Size> sizes = session.createQuery("FROM Size").getResultList();
		return sizes;
	}

	public Size getSizeByName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM Size s  WHERE s.TenSize = ?1";
		List sizes = session.createQuery(sql).setParameter(1, name).getResultList();
		if(!sizes.isEmpty()) {
			Size size = (Size) sizes.get(0);
			return size;
		}
		return null;
	}

}
