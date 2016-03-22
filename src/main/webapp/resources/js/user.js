$(function () {
	initTable();
});

function initTable(){
	$('#table').bootstrapTable({
		columns : [{
			field: 'state',
			checkbox: 'true'
		},{
			field : 'accountCode',
			title : '账号'
		}, {
			field : 'password',
			title : '密码'
		}, {
			field : 'createTime',
			title : '创建时间'
		}, {
			field : 'operate',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var s = "<a class = 'edit' href='javascript:void(0)'>修改</a>";
				var d = "<a class = 'remove' href='javascript:void(0)'>删除</a>";
				return s + ' ' + d;
			},
			events : 'operateEvents'
		} ],
		pagination: true,
		pageSize: 10,
		pageNumber: 1,
		pageList: [10,25,50],
		showRefresh: true, 
		formatLoadingMessage: function () {
		    return "请稍等，正在加载中...";
		},
		formatNoMatches: function () {  //没有匹配的结果
		    return '无符合条件的记录';
		},
		formatShowingRows: function(pageFrom,pageTo,totalRows){
			return "显示第"+ pageFrom +"到第"+pageTo+"条记录，总共"+totalRows+"条记录";
		},
		formatRecordsPerPage: function(pageNumber){
			return "每页显示"+pageNumber+"条记录";
		}
	});
}

var operateEvents = {
	'click .edit' : function(e, value, row, index) {
		var e = $.find(".active");
		
		var params = {
				"account":row.account
		};
		
		showContent(
				e,"/ctp/user/toEditUser",params);
	},
	'click .remove' : function(e, value, row, index) {
		remove(row);
	}
};

/**
 * 刷新
 */
function reload(){
	$('#table').bootstrapTable('refresh');
}

/**
 * 修改
 * @param row
 */
function edit(row) {
	$.ajax({
		url:"http://localhost:8080/ctp/user/getUser",
		type:"POST",
		data:{
			"account":row.accountCode
		},
		success:function(data){
			console.log(data)
		}
	})
}

/**
 * 删除
 * @param row
 */
function remove(row){
	$.ajax({
		url:"http://localhost:8080/ctp/user/delUser",
		type:"POST",
		data:{
			"account":row.accountCode
		},
		success:function(data){
			reload();
		},
		error:function(data){
			
		}
	})
}

$('#table').on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
    $('#remove').prop('disabled', !$('#table').bootstrapTable('getSelections').length);
    selections = getIdSelections();
    console.log(selections)
});

$('#remove').click(function () {
    var ids = getIdSelections();
    removeSelect(ids);
    $('#remove').prop('disabled', true);
});

function getIdSelections() {
    return $.map($('#table').bootstrapTable('getSelections')
    		, function (row) {
        return row.accountCode
    });
}

/**
 * 删除所选内容
 */
function removeSelect(ids){
	$.ajax({
		url:"/ctp/user/delSelectUser",
		type:"POST",
		data:{
			"accountList":ids
		},
		success:function(data){
			reload();
		}
	})
}