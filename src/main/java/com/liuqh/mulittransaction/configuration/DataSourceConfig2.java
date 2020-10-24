package com.liuqh.mulittransaction.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * LL 2020年10月23日 下午4:35:06
 */

//表示这个类为一个配置类
@Configuration
//配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.liuqh.mulittransaction.mapper.ds2", sqlSessionFactoryRef = "test2SqlSessionFactory")
public class DataSourceConfig2 {

	@Value("${spring.datasource.ds2.driver-class-name}")
	private String driverName;
	@Value("${spring.datasource.ds2.url}")
	private String url;
	@Value("${spring.datasource.ds2.username}")
	private String username;
	@Value("${spring.datasource.ds2.password}")
	private String password;

	// 将这个对象放入Spring容器中
	@Bean(name = "test2DataSource")
	// 表示这个数据源是默认数据源
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
		ds.setUniqueResourceName("test2DataSource");
		return ds;
	}

	@Bean(name = "test2SqlSessionFactory")
	// 表示这个数据源是默认数据源
	// @Qualifier表示查找Spring容器中名字为test1DataSource的对象
	public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test2DataSource") DataSource datasource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		bean.setConfigLocation(new ClassPathResource("mapper/config/mybatis-config.xml"));
		bean.setMapperLocations(
				// 设置mybatis的xml所在位置
				new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
		return bean.getObject();
	}

	@Bean("test2SqlSessionTemplate")
	// 表示这个数据源是默认数据源
	public SqlSessionTemplate test1sqlsessiontemplate(
			@Qualifier("test2SqlSessionFactory") SqlSessionFactory sessionfactory) {
		return new SqlSessionTemplate(sessionfactory);
	}

	/*@Bean(name = "test2TransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
	  return new DataSourceTransactionManager(dataSource);
	}*/

}