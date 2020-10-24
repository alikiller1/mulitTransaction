package com.liuqh.mulittransaction.entity;

import java.math.BigDecimal;

import lombok.Data;

/**   
 * LL
 * 2020年10月23日 下午6:30:41
 */

@Data
public class Order2 {
	
	private Long id;
	
	private BigDecimal price;
	
	private Long productId;
}
