package com.liuqh.mulittransaction.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**   
 * LL
 * 2020年10月23日 下午6:30:41
 */

@Table(name = "order2")
@Data
public class Order2 {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	private Long id;
	
	private BigDecimal price;
	
	private Long productId;
}
