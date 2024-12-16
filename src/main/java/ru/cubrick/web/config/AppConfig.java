package ru.cubrick.web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "ru.cubrick.web")
public class AppConfig {

    private final Environment environment;
    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan("ru.cubrick.web");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        Properties props = new Properties();

        factoryBean.setJpaProperties(getHibernateProperties());
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(getEntityManagerFactory().getObject());

        return jtm;
    }

    private Properties getHibernateProperties() {
        Properties props = new Properties();
        props.getProperty("hibernate.dialect");
        props.getProperty("hibernate.show_sql");
        props.getProperty("hibernate.hbm2ddl.auto");
        props.getProperty("hibernate.format_sql");
        return props;
    }
}
