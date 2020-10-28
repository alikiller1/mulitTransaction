package com.liuqh.mulittransaction.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuqh.mulittransaction.BaseTest;
import com.liuqh.mulittransaction.entity.Order2;
import com.liuqh.mulittransaction.entity.Product;
import com.liuqh.mulittransaction.service.ds1.ProductService;
import com.liuqh.mulittransaction.service.ds2.OrderService;

/**   
 * LL
 * 2020年10月23日 下午6:13:31
 */

public class ServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void testInsert() {
		Product p=new Product();
		p.setPName("篮球");
		productService.insert(p);
		System.out.println(p);
	}

	
	@Test
	public void testInsertOrder() {
		Order2 o=new Order2();
		o.setPrice(new BigDecimal(10));
		o.setProductId(16L);
		orderService.insert(o);
		System.out.println(o);
	}
	
	@Test
	public void testInsertProductAndOrder() {
		Product p=new Product();
		p.setPName("iphone12");
		
		Order2 o=new Order2();
		o.setPrice(new BigDecimal(20));
		
		orderService.insertProductAndOrder(p, o);
		System.out.println(o);
	}
	
	@Test
	public void testInsertProductAndOrder2() {
		Product p=new Product();
		p.setPName("iphone12");
		
		Order2 o=new Order2();
		o.setPrice(new BigDecimal(20));
		
		userService.insertProductAndOrder(p, o);
		System.out.println(o);
	}
}
