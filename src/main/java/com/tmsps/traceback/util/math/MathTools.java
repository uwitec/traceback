package com.tmsps.traceback.util.math;

import java.text.DecimalFormat;

public class MathTools {
	
	public static double getAround(double num,String digit){
		double ratio = Math.pow(10, Integer.parseInt(digit)),
		        _num = num * ratio,
		        mod = _num % 1,
		        integer = Math.floor(_num);
	    if(mod > 0.5){
	        return (integer + 1) / ratio;
	    }else if(mod < 0.5){
	        return integer / ratio;
	    }else{
	        return (integer % 2 == 0 ? integer : integer + 1) / ratio;
	    }
	}
	/**
	 * 小数右边补零
	 * @param round 小数. 后位数
	 * @param value 数值
	 * @return
	 */
	public static String toDecimal2(String round,double value){
		int num = Integer.parseInt(round);
		String fmt = "0.";
		for(int m=0;m<num;m++){
			fmt +="0";
		}
		//System.err.println(fmt);
		DecimalFormat decimalFormat=new DecimalFormat(fmt);//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p = decimalFormat.format(value);
		return p;
	}
	public static void main(String[] args){
		System.err.println(toDecimal2("3",1.0));
		System.err.println(toDecimal2("3",1));
	}
	
}	
