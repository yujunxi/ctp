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
				var src = "/goods/"+row.imgName;
				var html = "<img src='"+src+"' width='100px'/ align='middle' class='left' style='margin-left:20px'><div style='line-height: 100px;'>"+row.goodsName+"</div>";
				return html;
			}
		}, {
			title : '数量',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height: 100px;'>"+row.num+"</div>";
				return html;
			}
		}, {
			title : '单价(元)',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height: 100px;'>"+row.price+"</div>";
				return html;
			}
		},{
			title : '金额(元)',
			align : 'center',
			formatter : function(value,row,index){
				var price = row.num * row.price;
				var html = "<div style='line-height: 100px;'>"+price+"</div>";
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
				  status = "已售出";
				  break;
				case 2:
				  status = "已发货";
				  break;
				case 3:
				  status = "完成交易";
				  break;
				default:
				  status = "未售出";
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
			title : '创建时间',
			align : 'center',
			formatter : function(value,row,index){
				var html = "<div style='line-height: 100px;'>"+new Date(row.createTime).toLocaleDateString()+"</div>";
				return html;
			}
		},{
			field : 'operate',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var d = "";
				var r = "<a class = 'send' id = 'send' href='javascript:void(0)'>发货</a>";
				if(row.status==1){
					var d = "<div>"+r+"</div>";
				}else if(row.status==3||row.status==2){
					var d = "<div><a class = 'get' href='javascript:void(0)'>查看订单信息</a></div>";
				}else{
					var d = "<div><a class = 'remove' href='javascript:void(0)'>下架</a></div>";
				}
				return "<div style='line-height: 100px;'>"+ d +"</div>";
			},
			events : 'operateEvents'
		} ],
		pagination: true,
		pageSize: 5,
		pageNumber: 1,
		pageList: [10,25,50],
		formatLoadingMessage: function () {
		    return "请稍等，正在加载中...";
		},
		formatNoMatches: function () {  //没有匹配的结果
		    return '无销售中的商品';
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
	'click .send' : function(e, value, row, index){
		sendGoods(row);
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
		url:"http://localhost:8080/ctp/goods/delGoods",
		type:"POST",
		data:{
			"goodsCode":row.goodsCode
		},
		success:function(data){
			reload();
		},
		error:function(data){
			
		}
	})
}

/**
 * 发货
 * @param row
 */
function sendGoods(row){
	$.ajax({
		url:"http://localhost:8080/ctp/goods/sendGoods",
		type:"POST",
		data:{
			"id":row.goodsCode
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

$('#comfirm').click(function () {
	
});

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
