package com.liuqh.mulittransaction.service;

import com.liuqh.mulittransaction.entity.Order2;
import com.liuqh.mulittransaction.entity.Product;

/**
 * LL 2020年10月24日 下午4:39:59
 */

public interface UserService {
	public Order2 insertProductAndOrder(Product p, Order2 o);
}
