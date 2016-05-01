var price = 0;
function initTable(){
	$('#table').bootstrapTable({
		columns : [{
			field: 'state',
			checkbox: 'true'
		},{
			title : '商品名',
			align : 'center',
			formatter : function(value,row,index){
				var src = "/goods/"+row.goodsImg;
				var html = "<img src='"+src+"' width='100px'/ align='middle' class='left' style='margin-left:20px'><div style='line-height: 100px;'>"+row.goodsName+"</div>";
				return html;
			}
		}, {
			title : '数量',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height: 100px;'>"+row.goodsNum+"</div>";
				return html;
			}
		}, {
			title : '单价(元)',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height: 100px;'>"+row.goodsPrice+"</div>";
				return html;
			}
		},{
			title : '金额(元)',
			align : 'center',
			formatter : function(value,row,index){
				var price = row.goodsNum * row.goodsPrice;
				var html = "<div style='line-height: 100px;'>"+price+"</div>";
				return html;
			}
		}, {
			title : '卖家',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height: 100px;'>"+row.seller+"</div>";
				return html;
			}
		},{
			title : '状态',
			align : 'center',
			formatter : function(value,row,index){
				var status = "";
				switch(row.status)
				{
				case 1:
				  status = "卖家已发货";
				  break;
				case 2:
				  status = "完成交易";
				  break;
				default:
				  status = "等待卖家发货";
				}
				var html = "<div style='line-height: 100px;'>"+status+"</div>";
				return html;
			}
		}, {
			title : '交易方式',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height: 100px;'>货到付款</div>";
				return html;
			}
		},{
			title : '完成时间',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height: 100px;'>2016/4/12</div>";
				return html;
			}
		},{
			field : 'operate',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var d = "";
				var r = "<a class = 'confirm' id = 'confirm' href='javascript:void(0)'>确认收货</a>";
				if(row.status==1){
					var d = "<div><a class = 'remove' href='javascript:void(0)'>关闭订单</a>&nbsp;&nbsp;&nbsp;"+r+"</div>";
				}else if(row.stats==2){
					var d = "<div><a class = 'get' id = 'get' href='javascript:void(0)'>查看订单信息</a></div>";
				}else{
					d = "<div><a class = 'remove' href='javascript:void(0)'>关闭订单</a></div>";
				}
				return "<div style='line-height: 100px;'>"+ d +"</div>";
			},
			events : 'operateEvents'
		} ],
		pagination: true,
		pageSize: 10,
		pageNumber: 1,
		pageList: [10,25,50],
		formatLoadingMessage: function () {
		    return "请稍等，正在加载中...";
		},
		formatNoMatches: function () {  //没有匹配的结果
		    return '购物车没有商品';
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
	'click .remove' : function(e, value, row, index) {
		remove(row);
	},
	'click .confirm' : function(e, value, row, index){
		confirmOrder(row);
	}
};

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
		url:"http://localhost:8080/ctp/order/del",
		type:"POST",
		data:{
			"id":row.orderId
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
    selections = getIdSelections();
});

function confirmOrder(row){
	$.ajax({
		url:"/ctp/goods/confirmGoods",
		type:"POST",
		data:{
			"id":row.orderId
		},
		success:function(data){
			reload();
		},
		error:function(data){
			
		}
	})
}

/**
 * 返回对象
 * @returns
 */
function getIdSelections() {
    return $.map($('#table').bootstrapTable('getSelections')
    		, function (row) {
        return row;
    });
}

function order(row){
	$.ajax({
		url:"http://localhost:8080/ctp/order/add",
		data:{
			"goodsImg":row.goodsImg,
			"goodsCode":row.goodsCode,
			"goodsName":row.goodsName,
			"goodsNum":row.goodsNum,
			"owner":account,
			"seller":row.seller,
			"goodsPrice":row.goodsPrice
		},
		success:function(data){
			alert(data);
		},
		error:function(data){
			alert(data);
		}
	});
}