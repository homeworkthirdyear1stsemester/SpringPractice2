package com.example.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
    // set up variable to hold the properties
    // spring helper class for holding the data from properties file
    @Autowired
    private Environment env;

    // set up a logger for diagnostics

    private Logger logger = Logger.getLogger((getClass().getName()));

    // define a bean for our security datasource
    @Bean
    public DataSource securityDataSource() {
        // create connect pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        // set the jdbc driver class
        try {
            securityDataSource.setDriverClass(this.env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        // log the connection props
        // for sanity's sake, log this info
        // just to make suer we are REALLY reading data from properties file
        this.logger.info(">>> jdbc url=" + this.env.getProperty("jdbc.url"));
        this.logger.info(">>> jdbc user=" + this.env.getProperty("jdbc.user"));

        // set database connection props
        securityDataSource.setJdbcUrl(this.env.getProperty("jdbc.url"));
        securityDataSource.setUser(this.env.getProperty("jdbc.user"));
        securityDataSource.setPassword(this.env.getProperty("jdbc.password"));

        // set connection pool props
        securityDataSource.setInitialPoolSize(this.getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setMinPoolSize(this.getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setMaxPoolSize(this.getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(this.getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    // need a helper method
    // read environment property and convert to int
    private int getIntProperty(String propName) {
        String propVal = env.getProperty(propName);

        // now convert to int
        return Integer.parseInt(propVal);
    }

    // define a bean for ViewResolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }
}