package com.cts.web.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.model.TreeNode;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.sys.dao.FunctionDao;
import com.cts.web.sys.model.Function;
import com.cts.web.sys.service.FunctionService;

@Service(value = "functionService")
public class FunctionServiceImpl extends GenericServiceImpl<Function, String> implements FunctionService{

	@Resource(name="functionDao")
	private FunctionDao dao;
	
	@Override
	protected GenericDao<Function, String> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	public List<TreeNode> getFunction() {
		// TODO Auto-generated method stub
		return dao.getFunction();
	}

}
