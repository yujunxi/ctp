package com.cts.web.sys.service;

import java.util.List;

import com.cts.common.model.TreeNode;
import com.cts.common.service.GenericService;
import com.cts.web.sys.model.Function;

public interface FunctionService extends GenericService<Function, String>{

	List<TreeNode> getFunction();

}
