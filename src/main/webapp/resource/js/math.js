//验证是不是小数
 function isNumber(oNum){ 
	  if(!oNum) return false; 
	  var strP=/^\d+(\.\d+)?$/; 
	  if(!strP.test(oNum)) return false; 
	  try{ 
	  if(parseFloat(oNum)!=oNum) return false; 
	  } 
	  catch(ex) 
	  { 
	   return false; 
	  } 
	  return true; 
}
	 
//计算四舍六入，五看情况	
function sisheliuru(num,digit){
	
	var ratio = Math.pow(10, parseInt(digit)),
    _num = num * ratio,
    mod = _num % 1,
    integer = Math.floor(_num);
    if(mod > 0.5){
        return (integer + 1) / ratio;
    }else if(mod < 0.5){
        return integer / ratio;
    }else{
        return (integer % 2 === 0 ? integer : integer + 1) / ratio;
    }
	
}
////制保留小数位，如：2，会在2后面补上000.即2.000
function toDecimal2(num,round) {
	if(!isNumber(num)){
		return "";
	}
	var f = parseFloat(num);
    if (isNaN(f)) { 	
    	return "";    
    } 
    var tol =1;
    for(var m=1;m<=round;m++){
    	tol *=10;
    }
     f = Math.round(num*tol)/tol; 
    
    var s = f.toString();
   
    var rs = s.indexOf('.');   
    
    if (rs < 0) {    
        rs = s.length;    
        s += '.';    
    }  
    var tolLenght=parseInt(rs)+ parseInt(round);
    while (s.length <= tolLenght) {    
        s += '0'; 
    }
    return s;    
}

		