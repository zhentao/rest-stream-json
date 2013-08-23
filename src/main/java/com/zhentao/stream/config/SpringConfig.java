package com.zhentao.stream.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.core.JsonFactory;
import com.zhentao.stream.controller.CreativeController;
import com.zhentao.stream.dao.CreativeDao;
import com.zhentao.stream.dao.CreativeDaoImpl;
import com.zhentao.stream.dao.StreamJdbcTemplate;
import com.zhentao.stream.exception.handler.RestResponseEntityExceptionHandler;
import com.zhentao.stream.service.CreativeService;
import com.zhentao.stream.service.CreativeServiceImpl;

@Configuration
@PropertySource(value = { "file:${runtime.properties}" })
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackageClasses = { RestResponseEntityExceptionHandler.class, CreativeController.class})
public class SpringConfig {

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        // search local properties last by default
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RequestMappingHandlerMapping mapping() {
        return new RequestMappingHandlerMapping();
    }

    @Bean
    public RequestMappingHandlerAdapter adapter() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        adapter.setMessageConverters(new ArrayList<HttpMessageConverter<?>>() {
            private static final long serialVersionUID = 3887357761520931161L;
            {
                add(new MappingJackson2HttpMessageConverter());
            }
        });
        return adapter;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSourceConfig.dataSource());
    }

    @Bean
    public CreativeDao creativeDao() {
        CreativeDaoImpl dao = new CreativeDaoImpl(jsonFactory());
        dao.setJdbcTemplate(streamJdbcTemplate());
        return dao;
    }

    @Bean
    public JdbcTemplate streamJdbcTemplate() {
        StreamJdbcTemplate template = new StreamJdbcTemplate(dataSourceConfig.dataSource());
        template.setFetchSize(Integer.MIN_VALUE);
        return template;
    }
    @Bean
    public CreativeService creativeService() {
        return new CreativeServiceImpl(creativeDao());
    }

    @Bean
    public JsonFactory jsonFactory() {
        return new JsonFactory();
    }
}
