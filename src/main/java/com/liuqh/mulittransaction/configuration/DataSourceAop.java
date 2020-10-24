package com.liuqh.mulittransaction.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.liuqh.mulittransaction.configuration.DataSourceType.DataBaseType;

/**   
 * LL
 * 2020年10月23日 下午6:59:04
 */
//@Aspect
//@Component
public class DataSourceAop {
    @Before("execution(* com.liuqh.mulittransaction.mapper.ds1..*.*(..))")
    public void setDataSource2test01() {
        System.err.println("DS1业务");
        DataSourceType.setDataBaseType(DataBaseType.DS1);
    }
    
    @Before("execution(* com.liuqh.mulittransaction.mapper.ds2..*.*(..))")
    public void setDataSource2test02() {
        System.err.println("DS2业务");
        DataSourceType.setDataBaseType(DataBaseType.DS2);
    }
}