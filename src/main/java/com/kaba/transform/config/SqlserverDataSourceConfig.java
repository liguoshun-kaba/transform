package com.kaba.transform.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//扫描 Mapper 接口并容器管理
@MapperScan(basePackages = {"com.kaba.transform.dao.sqlserver"}, sqlSessionFactoryRef = "sqlserverFactory")
public class SqlserverDataSourceConfig {

    @Primary
    @Bean(name = "sqlserverDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.sqlserver")
    public DataSource sqlserverDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "sqlserverFactory")
    public SqlSessionFactory sqlserverFactory(@Qualifier("sqlserverDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //这里注意, 设置该参数时打成jar启动会找不到该包下的类,目前未找到解决方案
        bean.setTypeAliasesPackage("com.kaba.transform.entity.generation");
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/sqlserver/*/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Qualifier("sqlserverTransaction")
    public PlatformTransactionManager secondaryManager(@Qualifier("sqlserverDataSource") DataSource sqlserverDataSource) {
        return new DataSourceTransactionManager(sqlserverDataSource);
    }
}