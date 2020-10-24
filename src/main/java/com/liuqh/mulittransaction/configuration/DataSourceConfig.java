package com.liuqh.mulittransaction.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * LL 2020年10月23日 下午6:55:19
 */

//@Configuration
//@MapperScan(basePackages = {"com.liuqh.mulittransaction.mapper"}, sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {

	@Value("${spring.datasource.ds1.driver-class-name}")
	private String driverName;
	@Value("${spring.datasource.ds1.url}")
	private String url;
	@Value("${spring.datasource.ds1.username}")
	private String username;
	@Value("${spring.datasource.ds1.password}")
	private String password;

	@Value("${spring.datasource.ds2.driver-class-name}")
	private String driverName2;
	@Value("${spring.datasource.ds2.url}")
	private String url2;
	@Value("${spring.datasource.ds2.username}")
	private String username2;
	@Value("${spring.datasource.ds2.password}")
	private String password2;

	@Primary
	@Bean(name = "test1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.ds1")
	public DataSource getDateSource1() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverName);
		return dataSource;
	}

	@Bean(name = "test2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.ds2")
	public DataSource getDateSource2() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url2);
		dataSource.setUsername(username2);
		dataSource.setPassword(password2);
		dataSource.setDriverClassName(driverName2);
		return dataSource;
	}

	@Bean(name = "dynamicDataSource")
	public DynamicDataSource DataSource(@Qualifier("test1DataSource") DataSource test1DataSource,
			@Qualifier("test2DataSource") DataSource test2DataSource) {
		Map<Object, Object> targetDataSource = new HashMap<>();
		targetDataSource.put(DataSourceType.DataBaseType.DS1, test1DataSource);
		targetDataSource.put(DataSourceType.DataBaseType.DS2, test2DataSource);
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSource);
		dataSource.setDefaultTargetDataSource(test1DataSource);
		return dataSource;
	}

	@Bean(name = "SqlSessionFactory")
	public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dynamicDataSource);
		bean.setConfigLocation(new ClassPathResource("mapper/config/mybatis-config.xml"));
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
		return bean.getObject();
	}
}
