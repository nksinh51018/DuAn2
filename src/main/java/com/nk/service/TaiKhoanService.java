package com.nk.service;

import java.io.UnsupportedEncodingException;

import org.apache.axis.encoding.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.TaiKhoanDAO;
import com.nk.dao.impl.TaiKhoanImpl;
import com.nk.entity.TaiKhoan;

@Service
public class TaiKhoanService implements TaiKhoanDAO{
	@Autowired
	private TaiKhoanImpl taiKhoanImpl;
	
	public TaiKhoan findTaiKhoanById(int id) {
		// TODO Auto-generated method stu
		TaiKhoan taiKhoan = taiKhoanImpl.findTaiKhoanById(id);
		try {
			taiKhoan.setMatKhau(decodeString(taiKhoan.getMatKhau()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taiKhoan;
	}

	public TaiKhoan findTaiKhoanByTenTaiKhoan(String tenTaiKhoan) {
		// TODO Auto-generated method stub
		TaiKhoan taiKhoan = taiKhoanImpl.findTaiKhoanByTenTaiKhoan(tenTaiKhoan);
		try {
			taiKhoan.setMatKhau(decodeString(taiKhoan.getMatKhau()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taiKhoan;
	}

	public boolean KiemTraDangNhap(String tenTaiKhoan, String matKhau) {
		// TODO Auto-generated method stub
		return taiKhoanImpl.KiemTraDangNhap(tenTaiKhoan, matKhau);
	}

	public boolean dangki(String tenTaiKhoan, String matKhau) {
		// TODO Auto-generated method stub
		try {
			matKhau = encodeString(matKhau);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taiKhoanImpl.dangki(tenTaiKhoan, matKhau);
	}

	public boolean capNhatTaiKhoan(TaiKhoan taiKhoan,boolean thayDoi,String matKhauCu) {
		// TODO Auto-generated method stub
		try {
			matKhauCu = encodeString(matKhauCu);
			taiKhoan.setMatKhau(encodeString(taiKhoan.getMatKhau()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taiKhoanImpl.capNhatTaiKhoan(taiKhoan,thayDoi,matKhauCu);
	}
	
	public String encodeString(String text) throws UnsupportedEncodingException{
		byte[] bytes = text.getBytes("UTF-8");
		String encodeString = Base64.encode(bytes);
		return encodeString;
	}

	// Giải mã hóa một đoạn text (Đã mã hóa trước đó).
	// Decode
	public String decodeString(String encodeText) throws UnsupportedEncodingException {
		byte[] decodeBytes = Base64.decode(encodeText);
		String str = new String(decodeBytes, "UTF-8");
		return str;
	}
	
}
