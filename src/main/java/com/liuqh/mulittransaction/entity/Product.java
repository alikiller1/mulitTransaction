package com.liuqh.mulittransaction.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**   
 * LL
 * 2020年10月23日 下午4:21:50
 */

@Table(name="product")
@Data
public class Product {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	private Long id;
	
	private String pName;
}
