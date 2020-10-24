package com.liuqh.mulittransaction.service.ds2.impl;
/**   
 * LL
 * 2020年10月23日 下午6:07:31
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liuqh.mulittransaction.entity.Order2;
import com.liuqh.mulittransaction.entity.Product;
import com.liuqh.mulittransaction.mapper.ds1.ProductMapper;
import com.liuqh.mulittransaction.mapper.ds2.OrderMapper;
import com.liuqh.mulittransaction.service.ds2.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	@Transactional
	@Override
	public Order2 insert(Order2 o) {
		orderMapper.insert(o);
		return o;
	}

	
	
	@Transactional
	@Override
	public Order2 insertProductAndOrder(Product p, Order2 o) {
		productMapper.insert(p);
		o.setProductId(p.getId());
		orderMapper.insert(o);
		return o;
	}
	
	


}
