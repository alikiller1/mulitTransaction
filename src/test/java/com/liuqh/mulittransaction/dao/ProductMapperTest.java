package com.liuqh.mulittransaction.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuqh.mulittransaction.BaseTest;
import com.liuqh.mulittransaction.entity.Product;
import com.liuqh.mulittransaction.mapper.ds1.ProductMapper;

/**   
 * LL
 * 2020年10月23日 下午4:56:12
 */


public class ProductMapperTest extends BaseTest {
	
	@Autowired
	ProductMapper produMapper;
	
	@Test
	public void testInsert() {
		Product p=new Product();
		p.setPName("帽子");
		int i=produMapper.insert(p);
		System.out.println("i="+i);
		System.out.println(p);
	}
	
	@Test
	public void testQueryAll() {
		List<Product> list=produMapper.queryAll();
		System.out.println(list);
	}
	
	
}
