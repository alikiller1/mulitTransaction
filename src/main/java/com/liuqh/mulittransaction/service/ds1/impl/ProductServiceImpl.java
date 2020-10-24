package com.liuqh.mulittransaction.service.ds1.impl;
/**   
 * LL
 * 2020年10月23日 下午6:07:31
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuqh.mulittransaction.entity.Order2;
import com.liuqh.mulittransaction.entity.Product;
import com.liuqh.mulittransaction.mapper.ds1.ProductMapper;
import com.liuqh.mulittransaction.service.ds1.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	@Transactional
	@Override
	public Product insert(Product p) {
		productMapper.insert(p);
		Order2 o=new Order2();
		return p;
	}

}
