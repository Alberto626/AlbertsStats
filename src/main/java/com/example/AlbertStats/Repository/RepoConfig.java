package com.example.AlbertStats.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

//This class to configure our JDBCTemplate
@Configuration //Define our beans
@ComponentScan("com.example.AlbertStats.Repository")//tell spring where to find our definition of beans
public class RepoConfig {
    @Value("${}")
    private String URL;
    @Value("${}")
    private String USER;
    @Value("${}")
    private String DRIVER;
    @Value("${}")
    private String PASSWORD;


    @Bean
    public DataSource dataSourceMySQL() {//get our basic details to connect to our MYSQL database
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(URL);
        driverManagerDataSource.setUsername(USER);
        driverManagerDataSource.setDriverClassName(DRIVER);
        driverManagerDataSource.setPassword(PASSWORD);
        return driverManagerDataSource;
    }
    @Bean
    public JdbcTemplate mySQLtemplate(DataSource dataSourceMySql) {//get dataSourceMySql comes directly from our bean uptop
        return new JdbcTemplate(dataSourceMySql);
    }

}
