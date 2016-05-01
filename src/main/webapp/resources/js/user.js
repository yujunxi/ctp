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
			title : '账号',
			formatter : function(value,row,index){
				var html = "<div>"+row.account+"</div>";
				return html;
			}
		},{
			field : 'email',
			title : '邮箱',
			formatter : function(value,row,index){
				var html = "<div>"+row.email+"</div>";
				return html;
			}
			
		} ,{
			field : 'createTime',
			title : '创建时间',
			formatter : function(value,row,index){
				var html = "<div>"+new Date(row.createTime).toLocaleDateString()+"</div>";
				return html;
			}
			
		},
		/*{
			field : 'status',
			title : '账号状态',
			formatter : function(value,row,index){
				if(row.status==0){
					var html = "<div>正使用</div>";
				}else if(row.status==1){
					var html = "<div>注销</div>"
				}
				return html;
			}
		}, */
		{
			field : 'operate',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var a = ""
				if(row.status==0){
					/*a = "<a class = 'change' href='javascript:void(0)'>注销</a>"*/
				}else{
					a = "<a class = 'change' href='javascript:void(0)'>恢复</a>"
				}
				var s = "<a class = 'edit' href='javascript:void(0)'>修改</a>";
				var d = "<a class = 'remove' href='javascript:void(0)'>删除</a>";
				return a+' '+s + ' ' + d;
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
		update(row);
	},
	'click .remove' : function(e, value, row, index) {
		remove(row);
	},'click .change' : function(e, value, row, index) {
		changeStatus(row);
	}
};

function update(row){
	$("#userid").val(row.id);
	$("#accountname").val(row.account);
	$("#email1").val(row.email);
	$("#myModal1").modal('show')
}
/**
 * 刷新
 */
function reload(){
	$('#table').bootstrapTable('refresh');
}

/**
 * 删除
 * @param row
 */
function remove(row){
	$.ajax({
		url:"/ctp/user/delUser",
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
    $('#remove').prop('disabled', !$('#table').bootstrapTable('getSelections').length);
    selections = getIdSelections();
});

$('#remove').click(function () {
    var ids = getIdSelections();
    removeSelect(ids);
    $('#remove').prop('disabled', true);
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

function add(){
	$.ajax({
		url:"/ctp/user/add",
		type:"POST",
		data:{
			"account":$("#account").val(),
			"password":$("#password").val(),
			"email":$("#email").val()
		},
		success:function(data){
			$("#myModal").modal('hide');
			$("#msg-content").empty().html("添加成功");
			$("#msg").modal('show');
			reload();
		},
		error:function(data){
			
		}
	})
}

function changeStatus(row){
	$.ajax({
		url:"/ctp/user/changeStatus",
		type:"POST",
		data:{
			"id":row.id
		},
		success:function(data){
			$("#msg-content").empty().html("操作成功");
			$("#msg").modal('show');
			reload();
		},
		error:function(data){
			
		}
		
	})
}

function save(row){
	$.ajax({
        url:"/ctp/user/editUser",
        type:"POST",
        data:{
        	"id":$("#userid").val(),
        	"account":$("#accountname").val(),
        	"mail":$("#email1").val(),
        	"password":$("#pwd").val()
        },
        success:function(data){
        	$("#userid").val("");
        	$("#accountname").val("");
        	$("#email1").val("");
        	$("#pwd").val("");
        	$("#myModal1").modal('hide');
        	$("#msg-content").empty().html("添加成功");
			$("#msg").modal('show');
        	reload();
        },
        error:function(data){
        }
    })
}

function resetpassword(){
	$.ajax({
        url:"/ctp/user/resetPwd",
        type:"get",
        success:function(data){
        	$("#pwd").val(data.pwd)
        },
        error:function(data){
        }
    })
}