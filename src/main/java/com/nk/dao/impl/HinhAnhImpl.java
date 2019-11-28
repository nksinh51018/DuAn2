package com.nk.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nk.dao.HinhAnhDAO;
import com.nk.entity.HinhAnh;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class HinhAnhImpl implements HinhAnhDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public HinhAnh getUrlBySanPhamAndMau(int idSanPham, int idMau) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM HinhAnh ha WHERE ha.sanPham.id = ?1 AND ha.mau.id = ?2";
		List hinhAnhs = session.createQuery(sql).setParameter(1, idSanPham).setParameter(2, idMau).getResultList();
		if(!hinhAnhs.isEmpty()) {
			HinhAnh hinhAnh = (HinhAnh) hinhAnhs.get(0);
			return hinhAnh;
		}
		return null;
	}

}
