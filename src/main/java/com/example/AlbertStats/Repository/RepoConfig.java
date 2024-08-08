package com.example.AlbertStats.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//This class to configure our JDBCTemplate
@Configuration //Define our beans
@ComponentScan("com.example.AlbertStats.Repository")//tell spring where to find our definition of beans
@PropertySource("classpath:application.properties")//give our application.properties to our environment variable
public class RepoConfig {
    //environment is just get our variables from application.properties file
    @Autowired
    private Environment environment;//this is basically our application.properties file
    private final String URL;
    private final String USER;
    private final String DRIVER;
    private final String PASSWORD;

    public RepoConfig() {//TODO, get our attributes from our environment variable, ex: spring.datasource.EXAMPLE
        URL = "";
        USER = "";
        DRIVER = "";
        PASSWORD = "";
    }

    @Bean
    public DataSource dataSourceMySQL() {//get our basic details to connect to our MYSQL database
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty(URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER));
        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
        driverManagerDataSource.setPassword(environment.getProperty(DRIVER));
        return driverManagerDataSource;
    }
    @Bean
    public JdbcTemplate mySQLtemplate(DataSource dataSourceMySql) {//get dataSourceMySql comes directly from our bean uptop
        return new JdbcTemplate(dataSourceMySql);
    }

}
