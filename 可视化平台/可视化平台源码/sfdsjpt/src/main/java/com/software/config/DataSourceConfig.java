package com.software.config;

import com.software.datasource.DynamicDataSource;
import com.software.datasource.EncryptDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

	@Value("${fy-datasource}")
	private String fyDataSource;
	@Value("${jdbc.sybaseDriverClassName}")
	private String sybaseDriverName;
	@Value("${jdbc.mysqlDriverClassName}")
	private String mysqlDriverClassName;
	@Value("${jdbc.pgsqlDriverClassName}")
	private String pgsqlDriverClassName;
	@Value("${mybatis.type-aliases-package}")
	private String aliasePackage;
	@Value("${mybatis.mapper-locations}")
	private String mapperLocation;
	@Autowired
    JdbcProperties jdbcAutoConfig;


	/**
	 * 根据数据源创建SqlSessionFactory
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
//	@Bean(name="SqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) throws Exception {
//		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//		fb.setDataSource(dataSource);
//		fb.setTypeAliasesPackage(aliasePackage);
//		fb.setConfigLocation(new ClassPathResource("mybatis.xml"));
//		fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
//		return fb.getObject();
//	}

	/**
	 * 动态数据源
	 * @return
	 */
	@Primary
	@Bean(name = "DataSource")
	public DataSource dynamicDataSource(){
		Map<Object, Object> targetDataSources = new HashMap<>();
		//存入全部数据源
		for (Map.Entry<String,String> entry : jdbcAutoConfig.getUrl().entrySet()){
			String fyjc = entry.getKey();
			String url = entry.getValue();
			EncryptDataSource ds = new EncryptDataSource();
			ds.setJdbcUrl(url);
			ds.setUsername(jdbcAutoConfig.getUsername().get(fyjc));
			ds.setPassword(jdbcAutoConfig.getPassword().get(fyjc));
			ds.setPoolName(fyjc);
			sameProcess(ds);
			targetDataSources.put(StringUtils.capitalize(fyjc),ds);
		}
		/*EncryptDataSource cpwsdyfxxt = (EncryptDataSource)targetDataSources.get("Cpwsdyfxxt");
		cpwsdyfxxt.setDriverClassName(mysqlDriverClassName);*/
		//设定滨海新区数据库的jdbcdriver
		EncryptDataSource bhxq = (EncryptDataSource)targetDataSources.get("Tjbhxqfy");
		bhxq.setDriverClassName(pgsqlDriverClassName);

		//制定默认数据源
		EncryptDataSource defaultDataSource = (EncryptDataSource) targetDataSources.get(StringUtils.capitalize(fyDataSource));
		targetDataSources.put("Default",defaultDataSource);
		DynamicDataSource ds = new DynamicDataSource();
		ds.setTargetDataSources(targetDataSources);
		ds.setDefaultTargetDataSource(defaultDataSource);
		ds.afterPropertiesSet();
		return ds;
	}
	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

	private void sameProcess(EncryptDataSource ds) {
		ds.setDriverClassName(sybaseDriverName);
		ds.setConnectionTestQuery("select count(1)");
		ds.setMaximumPoolSize(10);
		ds.setMinimumIdle(4);
		ds.setMaxLifetime(2000000);
		ds.setConnectionTimeout(180000);
		// 心跳机制保活
		ds.setIdleTimeout(30000);
	}
}
