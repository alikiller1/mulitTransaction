package com.liuqh.mulittransaction.mapper.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.liuqh.mulittransaction.entity.Product;


/**   
 * LL
 * 2020年10月23日 下午4:28:44
 */
public interface ProductMapper{
	
	@Insert("insert into product (p_name) values (#{pName})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	public int insert(Product p);
	
	@Select("select * from product")
	List<Product> selectAll();
}
