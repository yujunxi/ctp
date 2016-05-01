package com.cts.web.base.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.base.model.MsgBox;

public interface MsgBoxService extends GenericService<MsgBox, String>{

	List<MsgBox> findByObj(String string);

	List<MsgBox> findByObj2(String string);

}
