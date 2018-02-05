var areaTreeCombo = Ext.create("Ext.ux.comboboxtree", {  
	id : 'area_tree_id',  
	name : 'area_tree_name',
	editable:false,
	hiddenName : 'hiddenNamex',  
	storeUrl : getServerHttp()+'/view_cp/area/area.json',
	cascade : 'child',//级联方式:1.child子级联;2.parent,父级联,3,both全部级联  
	checkModel:'single',//当json数据为不带checked的数据时只配置为single,带checked配置为double为单选,不配置为多选  
	fieldLabel : '区域树',  
	labelWidth : 80,  
	treeNodeParam : '' 
});