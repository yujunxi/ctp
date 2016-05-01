$(function () {
	initTable();
});

function initTable(){
	$('#table').bootstrapTable({
		columns : [{
			field: 'state',
			checkbox: 'true'
		},{
			field : 'goodsCode',
			title : '商品ID',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height:100px;'>"+row.goodsCode+"</div>";
				return html;
			}
		},{
			field : 'imgName',
			title : '图片',
			align : 'center',
			formatter : function(value,row,index){
				var src = "/goods/"+value;
				var html = "<img src='"+src+"'width='100'/>";
				return html;
			}
		},{
			field : 'goodsName',
			title : '商品标题',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height:100px;'>"+row.goodsName+"</div>";
				return html;
			}
		}, {
			field : 'seller',
			title : '销售者',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height:100px;'>"+row.seller+"</div>";
				return html;
			}
		}, {
			field : 'createTime',
			title : '创建时间',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height:100px;'>"+new Date(row.createTime).toLocaleDateString()+"</div>";
				return html;
			}
		}, {
			field : 'audit',
			title : '审核状态',
			align : 'center',
			formatter : function(value,row,index){
				var html;
				if(row.audit==1){
					html = "已审核";
				}else if(row.audit==0){
					html = "未审核";
				}else{
					html = "作废";
				}
				return "<div style='line-height:100px;'>"+html+"</div>";
			}
		},{
			field : 'operate',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
 				var s = "<a class = 'edit' href='javascript:void(0)'>修改</a>"; 
				var b = "";
				switch(row.audit){
				case 1:
				b = "<a class = 'audit' href='javascript:void(0)'>作废</a>";break;
				case 0:
				default:
				b = "<a class = 'audit' href='javascript:void(0)'>审核</a>";break;
				}
				var d = "<a class = 'remove' href='javascript:void(0)'>删除</a>";
				return '<div style="line-height:100px;">'+ b + ' ' + d+'</div>';
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
	},
	'click .remove' : function(e, value, row, index) {
		remove(row);
	},
	'click .audit' : function(e, value, row, index){
		audit(row);
	}
};

function add(){
	$('#myModal').modal('show')
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
		url:"/ctp/goods/delGoods",
		type:"POST",
		data:{
			"goodsCode":row.goodsCode
		},
		success:function(data){
			$("#msg-content").empty().html("删除成功");
			$("#msg").modal('show');
			reload();
		},
		error:function(data){
			
		}
	})
}

/**
 * 
 */
function audit(row){
	$.ajax({
		url:"/ctp/goods/auditGoods",
		type:"POST",
		data:{
			"goodsCode":row.goodsCode,
			"audit":row.audit
		},
		success:function(data){
			$("#msg-content").empty().html("审核成功");
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
        return row.goodsCode
    });
}

/**
 * 删除所选内容
 */
function removeSelect(ids){
	$.ajax({
		url:"/ctp/goods/delSelectGoods",
		type:"POST",
		data:{
			"goodsList":ids
		},
		success:function(data){
			$("#msg-content").empty().html("删除成功");
			$("#msg").modal('show');
			reload();
		}
	})
}