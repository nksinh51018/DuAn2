package com.nk.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.nk.dao.TaiKhoanDAO;
import com.nk.entity.TaiKhoan;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaiKhoanImpl implements TaiKhoanDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public TaiKhoan findTaiKhoanById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		TaiKhoan taiKhoan = session.find(TaiKhoan.class, id);
		return taiKhoan;
	}

	@Transactional
	public TaiKhoan findTaiKhoanByTenTaiKhoan(String tenTaiKhoan) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		TaiKhoan taiKhoan = (TaiKhoan) session.createQuery("FROM taikhoan tk WHERE tk.TenDangNhap = ?1").setParameter(1, tenTaiKhoan).getSingleResult();
		return taiKhoan;
	}

	public boolean KiemTraDangNhap(String tenTaiKhoan, String matKhau) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		TaiKhoan taiKhoan = (TaiKhoan) session.createQuery("FROM taikhoan tk WHERE tk.TenDangNhap = ?1 and tk.MatKhau = ?2").setParameter(1, tenTaiKhoan).setParameter(2, matKhau).getSingleResult();
		if(taiKhoan ==null) {
			return false;
		}
		else {
			return true;
		}
	}
	@Transactional
	public boolean dangki(String tenTaiKhoan, String matKhau) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List taiKhoan =  session.createQuery("FROM taikhoan tk WHERE tk.TenDangNhap = ?1").setParameter(1, tenTaiKhoan).getResultList();
		if(taiKhoan.isEmpty()) {
			TaiKhoan tk = new TaiKhoan();
			tk.setTenDangNhap(tenTaiKhoan);
			tk.setMatKhau(matKhau);
			tk.setVaiTro("ROLE_USER");
			tk.setEnable(true);
			session.save(tk);
			return true;
		}
		else {
			return false;
		}
	}
	@Transactional
	public boolean capNhatTaiKhoan(TaiKhoan taiKhoan,boolean thayDoi,String matKhauCu) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		if(thayDoi) {
			List taiKhoan2 = session.createQuery("FROM taikhoan tk WHERE tk.TenDangNhap = ?1 AND tk.MatKhau = ?2").setParameter(1, taiKhoan.getTenDangNhap()).setParameter(2, matKhauCu).getResultList();
			if(taiKhoan2.isEmpty()) {
				return false;
			}
			try {
				session.merge(taiKhoan);
				return true;
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("dsd");
				e.printStackTrace();
				return false;
			}
		}
		else {
			List taiKhoan2 = session.createQuery("FROM taikhoan tk WHERE tk.TenDangNhap = ?1").setParameter(1, taiKhoan.getTenDangNhap()).getResultList();
			if(!taiKhoan2.isEmpty()) {
				TaiKhoan taiKhoan3 = (TaiKhoan) taiKhoan2.get(0);
				String mk = taiKhoan3.getMatKhau();
				taiKhoan.setMatKhau(mk);
				try {
					session.merge(taiKhoan);
					return true;
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("dsd");
					e.printStackTrace();
					return false;
				}
			}
		}
		System.out.println("dsad");
		return false;
	}
	
	
}
