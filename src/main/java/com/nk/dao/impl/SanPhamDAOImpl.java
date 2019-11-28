package com.nk.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import com.nk.dao.SanPhamDAO;
import com.nk.entity.HinhAnh;
import com.nk.entity.SanPham;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class SanPhamDAOImpl implements SanPhamDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<SanPham> laydsSanPhamLimit(int spbatdau,int soluong) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query<SanPham> sql=  session.createQuery("FROM SanPham");
		sql.setCacheable(true);
		List<SanPham> sanPhams = sql.setFirstResult(spbatdau).setMaxResults(soluong).getResultList();
//		System.out.println(sessionFactory.getStatistics()
//		        .getDomainDataRegionStatistics("com.nk.entity.SanPham")
//		        .g);
		return sanPhams;
	}
	public SanPham getSanPhamById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		 //sessionFactory.getCache().evict(SanPham.class, id);
		SanPham sanPham = session.find(SanPham.class, id);
		return sanPham;
	}
	public List<SanPham> locSanPham(String loai, String[] maus, String giamin,String giamax, String[] sizes) {
		// TODO Auto-generated method stub
		int vitri=2;
		Session session = sessionFactory.getCurrentSession();
		String sql="SELECT distinct sp from SanPham as sp, ChiTietSanPham as ctsp, Mau as m, Size as s, LoaiSanPham as lsp " + 
				"where lsp.TenLoai = ?1 " + 
				"and sp.LoaiSanPham = lsp " + 
				"and sp = ctsp.sanPham "+
				"and ctsp.mau = m "+
				"and ctsp.size = s ";
		if(maus!=null) {
			sql+= "and (";
			for (int i = 0; i < maus.length; i++) {
				if(i != maus.length-1) {
					sql+= "m.TenMau = "+"?"+vitri+" or ";
					vitri++;
				}
				else {
					sql+="m.TenMau = "+"?"+vitri+")";
					vitri++;
				}
			}
		}
		if(sizes!=null) {
			sql+= "and (";
			for (int i = 0; i < sizes.length; i++) {
				if(i != sizes.length-1) {
					sql+= "s.TenSize = "+"?"+vitri+" or ";
					vitri++;
				}
				else {
					sql+="s.TenSize = "+"?"+vitri+")";
					vitri++;
				}
			}
		}
		if(giamin!=null && giamax != null) {
			sql+=" and "+"sp.GiaTien >=" +giamin +" and "+ "sp.GiaTien <="+giamax;
		}
		Query<SanPham> query = session.createQuery(sql);
		query.setParameter(1, loai);
		vitri=2;
		if(maus!=null) {
			for(int i=0;i< maus.length;i++) {
				query.setParameter(vitri, maus[i]);
				vitri++;
			}
		}
		if(sizes!=null) {
			for(int i=0;i< sizes.length;i++) {
				query.setParameter(vitri, sizes[i]);
				vitri++;
			}
		}
		List<SanPham> sanPhams = query.getResultList();
		System.out.println(sanPhams.size());
		return sanPhams;
	}
	public List<SanPham> timKiemByName(String tk) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> sanPhams = session.createQuery("FROM SanPham as sp WHERE sp.TenSanPham like ?1").setParameter(1, "%"+tk+"%").getResultList();
		return sanPhams;
	}
	public int soLuongSanPham() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM SanPham";
		List<SanPham> sanPhams = session.createQuery(sql).getResultList();
		
		return sanPhams.size();
	}
	public boolean xoaSanPham(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		SanPham sanPham = session.get(SanPham.class, id);
		try {
		session.delete(sanPham);
		return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean themSanPham(SanPham sanPham) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try{
			session.save(sanPham);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean capNhatSanPham(SanPham sanPham) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try{
			session.merge(sanPham);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

}
