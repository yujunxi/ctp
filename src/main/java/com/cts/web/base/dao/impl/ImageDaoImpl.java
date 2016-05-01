package com.cts.web.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.base.dao.ImageDao;
import com.cts.web.base.model.Image;

@Repository("imageDao")
public class ImageDaoImpl extends GenericDaoImpl<Image,String> implements ImageDao {
	
	 public ImageDaoImpl() {
	   super();
	   setClazz(Image.class);
	 }
}


