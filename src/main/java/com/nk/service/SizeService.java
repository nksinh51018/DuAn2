package com.nk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.SizeDAO;
import com.nk.dao.impl.SizeImpl;
import com.nk.entity.Size;

@Service
public class SizeService implements SizeDAO{
	
	@Autowired
	private SizeImpl sizeImpl;

	public List<Size> getAllSize() {
		// TODO Auto-generated method stub
		return sizeImpl.getAllSize();
	}

	public Size getSizeByName(String name) {
		// TODO Auto-generated method stub
		return sizeImpl.getSizeByName(name);
	}

}
