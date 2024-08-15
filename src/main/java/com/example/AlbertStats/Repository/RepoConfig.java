package com.example.AlbertStats.Repository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Driver;

//This class to configure our JDBCTemplate
@Configuration //Define our beans
@ComponentScan("com.example.AlbertStats.Repository")//tell spring where to find our definition of beans
public class RepoConfig {
    //TODO add secrets management later, i don't want key details on a application.properties
    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASSWORD;


    @Bean
    public DataSource dataSourceMySQL() {//get our basic details to connect to our MYSQL database
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setJdbcUrl(URL);
        dataSource.setConnectionTimeout(1000);
        return dataSource;
    }
    @Bean
    public JdbcTemplate mySQLtemplate(DataSource dataSourceMySql) {//get dataSourceMySql comes directly from our bean uptop
        return new JdbcTemplate(dataSourceMySql);//NOTES, this wont finish until data source is finished
    }

}
