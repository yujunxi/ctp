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
			field : 'operate',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var d = "<div style='line-height: 100px;'><a class = 'remove' href='javascript:void(0)'>删除</a></div>";
				return d;
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
		url:"/ctp/shopCar/del",
		type:"POST",
		data:{
			"id":row.id
		},
		success:function(data){
			selections = 0;	//	清空总额
			reload();
		},
		error:function(data){
			
		}
	})
}

$('#table').on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
    selections = getIdSelections();
    sumPrice(selections);
});

$('#add').click(function () {
    var ids = getIdSelections();
    if(ids.length<=0){
    	alert("选择一条记录");
    }else{
    	for(var i = 0 ; i < ids.length ; i++){
    		remove(ids[i]);
    		order(ids[i]);
    	}
    }
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

/**
 * 统计金额
 */
function sumPrice(ids){
	price = 0;
	for(var i=0;i<ids.length;i++){
		price += ids[i].goodsNum*ids[i].goodsPrice;
	}
	$('#sumPrice').html(price);
}

function order(row){
	$.ajax({
		url:"/ctp/order/add",
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
			alert("下单成功");
		},
		error:function(data){
			alert("下单失败");
		}
	});
}