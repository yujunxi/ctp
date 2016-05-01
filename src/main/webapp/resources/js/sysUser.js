$(function () {
	initTable();
});

function initTable(){
	$('#table').bootstrapTable({
		columns : [{
			field: 'state',
			checkbox: 'true'
		},{
			field : 'account',
			title : '账号'
		}, {
			field : 'password',
			title : '密码'
		}, {
			field : 'operate',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
			/*	var s = "<a class = 'edit' href='javascript:void(0)'>修改</a>";*/
				var d = "<a class = 'remove' href='javascript:void(0)'>删除</a>";
				return  d;
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
		console.log(row.id)
		var params = {
				"id":row.id
		};
		
		showContent(
				e,"/ctp/sysUser/toEditUser",params);
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

function add(){
	$.ajax({
		url:"/ctp/sysUser/add",
		type:"POST",
		data:{
			"account":$("#account").val(),
			"password":$("#password").val()
		},
		success:function(data){
			$("#account").val(""),
			$("#password").val("")
			$("#myModal").modal('hide');
			$("#msg-content").empty().html(data.msg);
			$("#msg").modal('show');
			reload();
		}
	})
}

/**
 * 修改
 * @param row
 */
function edit(row) {
	$.ajax({
		url:"/ctp/sysUser/getUser",
		type:"POST",
		data:{
			"id":row.id
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
	console.log(row.id)
	$.ajax({
		url:"/ctp/sysUser/del",
		type:"POST",
		data:{
			"id":row.id
		},
		success:function(data){
			$("#msg-content").empty().html(data.msg);
			$("#msg").modal('show');
			reload();
		},
		error:function(data){
			
		}
	})
}

$('#table').on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
    $('#confirm').prop('disabled', !$('#table').bootstrapTable('getSelections').length);
    selections = getIdSelections();
});

$('#confirm').click(function () {
    var ids = getIdSelections();
    confirm(ids);
    $('#confirm').prop('disabled', true);
});

function getIdSelections() {
    return $.map($('#table').bootstrapTable('getSelections')
    		, function (row) {
        return row.id
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
			$("#msg-content").empty().html(data.msg);
			$("#msg").modal('show');
			reload();
		}
	})
}

function confirm(ids){
	var e = $.find(".active");
	var params = {
		"ids" : ids
	}
	showContent(
			e,"/ctp/sysUser/toSetRole",params);
}