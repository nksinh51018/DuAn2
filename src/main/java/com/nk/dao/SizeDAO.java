package com.nk.dao;

import java.util.List;

import com.nk.entity.Size;

public interface SizeDAO {

	public List<Size> getAllSize();
	public Size getSizeByName(String name);
}
