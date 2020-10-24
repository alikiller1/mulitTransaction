package com.liuqh.mulittransaction.service.ds2;
/**   
 * LL
 * 2020年10月23日 下午6:06:05
 */

import com.liuqh.mulittransaction.entity.Order2;
import com.liuqh.mulittransaction.entity.Product;

public interface OrderService {
	
	public Order2 insert(Order2 o);
	
	public Order2 insertProductAndOrder(Product p,Order2 o);
}
