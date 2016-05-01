package com.cts.web.base.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.base.model.MsgBox;

public interface MsgBoxDao extends GenericDao<MsgBox, String>{

	List<MsgBox> findByObj(String string);

	List<MsgBox> findByObj2(String string);

}
