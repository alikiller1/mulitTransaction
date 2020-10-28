package com.liuqh.mulittransaction.mapper.ds2;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.liuqh.mulittransaction.entity.Order2;
import com.liuqh.mulittransaction.entity.Product;


/**   
 * LL
 * 2020年10月23日 下午4:28:44
 */
public interface OrderMapper{
	
	@Insert("insert into order2(price,product_id) values (#{price},${productId})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	public int insert(Order2 o);
	
	@Select("select * from order2")
	List<Product> selectAll();
}
