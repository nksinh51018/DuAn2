package com.nk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.dao.MauDAO;
import com.nk.dao.impl.MauImpl;
import com.nk.entity.Mau;

@Service
public class MauService implements MauDAO{

	@Autowired
	private MauImpl mauImpl;
	
	public List<Mau> getAllMau() {
		// TODO Auto-generated method stub
		return mauImpl.getAllMau();
	}

	public Mau getMauByName(String name) {
		// TODO Auto-generated method stub
		return mauImpl.getMauByName(name);
	}

}
