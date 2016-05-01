package com.cts.web.sys.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.common.model.TreeNode;
import com.cts.web.sys.model.Function;

public interface FunctionDao extends GenericDao<Function, String>{

	List<TreeNode> getFunction();

}
