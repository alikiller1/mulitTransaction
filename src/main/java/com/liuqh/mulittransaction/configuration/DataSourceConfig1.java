package com.liuqh.mulittransaction.configuration;

import javax.sql.DataSource;
//import javax.transaction.UserTransaction;
import javax.transaction.UserTransaction;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * LL 2020年10月23日 下午4:35:06
 */

//表示这个类为一个配置类
@Configuration
//配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.liuqh.mulittransaction.mapper.ds1", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSourceConfig1 {

	@Value("${spring.datasource.ds1.driver-class-name}")
	private String driverName;
	@Value("${spring.datasource.ds1.url}")
	private String url;
	@Value("${spring.datasource.ds1.username}")
	private String username;
	@Value("${spring.datasource.ds1.password}")
	private String password;

	// 将这个对象放入Spring容器中
	@Bean(name = "test1DataSource")
	// 表示这个数据源是默认数据源
	@Primary
	// 读取application.properties中的配置参数映射成为一个对象
	// prefix表示参数的前缀
	public DataSource getDateSource() {
		/*DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverName);
		return dataSource;*/
		
		MysqlXADataSource dataSource = new MysqlXADataSource();
		dataSource.setUrl(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setXaDataSource(dataSource);
		ds.setUniqueResourceName("test1DataSource");
		return ds;
	}

	@Bean(name = "test1SqlSessionFactory")
	// 表示这个数据源是默认数据源
	@Primary
	// @Qualifier表示查找Spring容器中名字为test1DataSource的对象
	public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource datasource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		bean.setConfigLocation(new ClassPathResource("mapper/config/mybatis-config.xml"));
		bean.setMapperLocations(
				// 设置mybatis的xml所在位置
				new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
		return bean.getObject();
	}

	@Bean("test1SqlSessionTemplate")
	// 表示这个数据源是默认数据源
	@Primary
	public SqlSessionTemplate test1sqlsessiontemplate(
			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sessionfactory) {
		return new SqlSessionTemplate(sessionfactory);
	}

	/*
	 * 使用这个来做总事务 后面的数据源就不用设置事务了
	 * */
	@Bean(name = "transactionManager")
	@Primary
	public JtaTransactionManager regTransactionManager() {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		UserTransaction userTransaction = new UserTransactionImp();
		return new JtaTransactionManager(userTransaction, userTransactionManager);
	}

	/*@Bean(name = "test1TransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}*/
}