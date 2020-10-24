package com.liuqh.mulittransaction.dao;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuqh.mulittransaction.BaseTest;
import com.liuqh.mulittransaction.entity.Order2;
import com.liuqh.mulittransaction.mapper.ds2.OrderMapper;

/**   
 * LL
 * 2020年10月23日 下午10:50:11
 */

public class OrderMapperTest extends BaseTest {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Test
	public void testInsert() {
		Order2 o=new Order2();
		o.setPrice(new BigDecimal(10));
		o.setProductId(1L);
		orderMapper.insert(o);
		System.out.println(o);
	}
	

}
