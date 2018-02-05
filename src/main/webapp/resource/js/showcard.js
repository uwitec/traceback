
function showCard(){
	window.parent.readCard(function(result){		
		if(result.error==null ||result.error == undefined){
			console.log(result);
			var html = "<div id='win'>";
			//8 业务状态码
			var yecode=result.success["8"].substr(0,2);
			switch(yecode){
				//采购
				case "01": 
					html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务类型:【采购】</div>";
					//9 业务状态码
					yecode=result.success["9"].substr(0,2);
					switch(yecode){
						case "01": 
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【发卡后】</div>";
							break;
						case "02":
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【一次检斤后】</div>";
							break;
						case "03":
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【卸货厂后】</div>";
							break;
						case "04":
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【二次检斤后】</div>";
							break;
					}
					break;
				//销售
				case "02":
					html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务类型:【销售】</div>";
					//9 业务状态码
					yecode=result.success["9"].substr(0,2);
					switch(yecode){
						case "01": 
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【发卡后】</div>";
							break;
						case "02":
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【一次检斤后】</div>";
							break;
						case "03":
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【发货厂后】</div>";
							break;
						case "04":
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【二次检斤后】</div>";
							break;
					}
					break;
				//取样
				case "03":
					html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务类型:【取样】</div>";
					//9 业务状态码
					yecode=result.success["9"].substr(0,2);
					switch(yecode){
						case "01": 
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【发卡后】</div>";
							break;
						case "02":
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【取样后】</div>";
							break;
						
					}
					break;
				//组样
				case "04":
					html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务类型:【组样】</div>";
					//9 业务状态码
					yecode=result.success["9"].substr(0,2);
					switch(yecode){
						case "01": 
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【发卡后】</div>";
							break;
						case "02":
							html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【化验完成后】</div>";
							break;
					}
					break;
				default:
					html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>业务状态:【无】</div>";
			}
			
			//12 运输计划单号
			html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>运输计划单号:【"+result.success["12"]+"】</div>";
			//13 过磅单据号
			html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>过磅单据号:【"+result.success["13"]+"】</div>";
			//14 物资类型
			html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>物资类型:【"+result.success["14"]+"】</div>";
			//17 车牌号
			html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>车牌号:【"+result.success["17"]+"】</div>";
			//仓库
			html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>仓库:【"+result.success["5"]+"】</div>";
			//毛重
			html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>毛重:【"+result.success["37"]+"】</div>";
			//轴数
			html+="<div style='font-size:15px;margin-top:5px;margin-left:5px;'>轴数:【"+get_car_num(result.success["17"])+"】</div>";
			
			html+="</div>";
			//console.log("html-->"+html);
			var note_form_panel_win = Ext.create("Ext.Window", {
				title : "IC卡信息",
				closeAction : "hide",
				width : 350,
				height : 300,
				autoScroll : true,
				defaults :{
					padding : '5 5 5 5'
				},
				html : html
			});
				
			note_form_panel_win.show();
		}else{
			Ext.Msg.alert("提示",result.error );
		}
	}); 
	
}
function get_car_num(carno){
		var num = "";
		Ext.Ajax.request({
			async:false,
			url : '/cp/car/get_car_axle?carno='+carno,
			success : function(response) {
				var json = Ext.JSON.decode(response.responseText);
				num = json.car.axle;
			},
			method : 'post'
		});
		return num;
}



