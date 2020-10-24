package com.liuqh.mulittransaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liuqh.mulittransaction.entity.Order2;
import com.liuqh.mulittransaction.entity.Product;
import com.liuqh.mulittransaction.service.UserService;
import com.liuqh.mulittransaction.service.ds1.ProductService;
import com.liuqh.mulittransaction.service.ds2.OrderService;

import lombok.extern.slf4j.Slf4j;

/**
 * LL 2020年10月24日 下午4:40:31
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Transactional
	@Override
	public Order2 insertProductAndOrder(Product p, Order2 o) {
		productService.insert(p);
		o.setProductId(p.getId());
		try {
			orderService.insert(o);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return o;
	}

}
