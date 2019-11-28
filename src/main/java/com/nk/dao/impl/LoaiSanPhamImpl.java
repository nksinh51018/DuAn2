package com.nk.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nk.dao.LoaiSanPhamDao;
import com.nk.entity.LoaiSanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class LoaiSanPhamImpl implements LoaiSanPhamDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LoaiSanPham> getAllLoaiSanPham() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<LoaiSanPham> loaiSanPhams = session.createQuery("FROM LoaiSanPham").getResultList();
		return loaiSanPhams;
	}
	
	public LoaiSanPham getLoaiSanPhamByTen(String ten) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List list = session.createQuery("FROM LoaiSanPham lsp WHERE lsp.TenLoai= ?1").setParameter(1, ten).getResultList();
		if(!list.isEmpty()) {
			LoaiSanPham loaiSanPham = (LoaiSanPham) list.get(0);
			return loaiSanPham;
		}
		return null;
	}

}
