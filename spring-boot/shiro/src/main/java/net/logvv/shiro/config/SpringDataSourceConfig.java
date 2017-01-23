package net.logvv.shiro.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * Created by marlon on 2017/1/23.
 * 多数据源配置
 */
@Configuration
@ConfigurationProperties(locations = "classpath:db-config.properties")
public class SpringDataSourceConfig {

    // 测试环境数据库
    @Bean(name = "developDataSource")
    @Qualifier("developDataSource")
    @ConfigurationProperties(prefix = "dev.mysql")
    public DataSource developDateSource(){
        return DataSourceBuilder.create().build();
    }

    // 生产环境数据库
    @Bean(name = "productDataSource")
    @Qualifier("productDataSource")
    @ConfigurationProperties(prefix = "pro.mysql")
    public DataSource productDataSource(){
        return DataSourceBuilder.create().build();
    }

    /** 开发环境数据源 */
    @Bean(name = "developJdbcTemplate")
    public JdbcTemplate developJdbcTemplate(@Qualifier("developDataSource")DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }

    /** 生产环境数据源 */
    @Bean(name = "productJdbcTemplate")
    public JdbcTemplate productJdbcTemplate(@Qualifier("productDataSource")DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
}
