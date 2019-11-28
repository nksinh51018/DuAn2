package com.nk.dao;

import java.util.List;

import com.nk.entity.Mau;

public interface MauDAO {
	public List<Mau> getAllMau();
	public Mau getMauByName(String name);
}
