package com.cts.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.base.dao.ImageDao;
import com.cts.web.base.model.Image;
import com.cts.web.base.service.ImageService;

@Service("imageService")
public class ImageServiceImpl extends GenericServiceImpl<Image,String> implements ImageService {

    @Resource(name="imageDao")
    private ImageDao dao;
    
    public ImageServiceImpl() {
        super();
    }

    @Override
    protected GenericDao<Image,String> getDao() {
        return this.dao;
    }
}