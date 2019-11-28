package com.nk.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nk.dao.ChiTietSanPhamDAO;
import com.nk.entity.ChiTietSanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ChiTietSanPhamImpl implements ChiTietSanPhamDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public ChiTietSanPham getId(Integer idSanPham, Integer idMau, Integer idSize) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql="FROM ChiTietSanPham WHERE sanPham.id = ?1 AND mau.id = ?2 AND size.id = ?3";
		List chiTietSanPhams = session.createQuery(sql).setParameter(1, idSanPham)
				.setParameter(2, idMau)
				.setParameter(3, idSize)
				.getResultList();
		if(!chiTietSanPhams.isEmpty()) {
			ChiTietSanPham chiTietSanPham = (ChiTietSanPham) chiTietSanPhams.get(0);
			return chiTietSanPham;
		}
		return null;
	}

	
}
