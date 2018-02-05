package com.tmsps.traceback.util.math;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/10/31 0031.
 */
public class BigDecimalTools {

    public static BigDecimal mul(BigDecimal base,BigDecimal ratio,int round ){

        return base.multiply(ratio.divide(new BigDecimal(100))).setScale(round,BigDecimal.ROUND_HALF_DOWN);
    }

    public static BigDecimal getBaseNum(BigDecimal min_base,BigDecimal max_base,BigDecimal avgSalary){

        if(avgSalary.compareTo(min_base)==-1){
            return min_base;
        }
        if(avgSalary.compareTo(max_base)==1){
            return max_base;
        }
        return avgSalary;
    }

    public static BigDecimal sub(BigDecimal new_base,String old_base){
        BigDecimal old = "null".equals(old_base)?new BigDecimal("0"):new BigDecimal(old_base);
        return new_base.subtract(old);
    }

}
