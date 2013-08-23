package com.zhentao.stream.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zhentao.stream.mysql.EmbeddedMysqlDatabaseBuilder;


@Configuration
@Profile("integration")
public class EmbeddedDataSourceConfig implements DataSourceConfig {

    @Override
    @Bean(destroyMethod="shutdown")
    public DataSource dataSource() {
        return new EmbeddedMysqlDatabaseBuilder().addSqlScript("schema.sql").addSqlScript("init.sql").build();
    }
}
