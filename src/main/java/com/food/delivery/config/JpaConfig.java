package com.food.delivery.config;

import com.food.delivery.dto.CartDto;
import com.food.delivery.dto.CartItemDto;
import com.food.delivery.model.Ingredient;
import com.food.delivery.model.Product;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Configuration for persistence with Hibernate Framework and MySQL database.
 */
@Configuration
@EnableTransactionManagement
public class JpaConfig {

    private final ApplicationContext appContext;

    @Autowired
    public JpaConfig(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @Bean(name = "DataSource")
    public HikariDataSource dataSourceWinMacLinux() {
        return getDataSource("127.0.0.1", "root", "mysql");
    }

    /**
     * Set the DataSource to be used by the SessionFactory
     */
    private HikariDataSource getDataSource(String serverName, String user, String password) {
        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        dataSource.addDataSourceProperty("databaseName", "food_delivery");
        dataSource.addDataSourceProperty("portNumber", "3306");
        dataSource.addDataSourceProperty("serverName", serverName);
        dataSource.addDataSourceProperty("user", user);
        dataSource.addDataSourceProperty("password", password);
        return dataSource;
    }

    /**
     * Binds a Hibernate Session from the implemented factory
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(hibernate5SessionFactoryBean().getObject());
        return manager;
    }

    /**
     * FactoryBean that creates a Hibernate SessionFactory
     */
    @Bean
    public LocalSessionFactoryBean hibernate5SessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource((DataSource) appContext.getBean("DataSource"));
        localSessionFactoryBean.setAnnotatedClasses(
                Ingredient.class, Product.class, CartDto.class, CartItemDto.class
        );
        localSessionFactoryBean.setPackagesToScan("com.food.delivery.dto");

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        //properties.put("hibernate.current_session_context_class","thread");
//        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.hbm2ddl.auto", "create");
        // Shows executed query on console
        properties.put("hibernate.show_sql", "true");

        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }
}
