package org.chirkov.firstSpringMvcProject.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan("org.chirkov.firstSpringMvcProject")
@EnableWebMvc
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
public class SpringConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;
    private final Environment environment;


    @Autowired
    public SpringConfig(ApplicationContext applicationContext, Environment environment) {
        this.applicationContext = applicationContext;
        this.environment = environment;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry  registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }
    //    jdbc template
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        JDBC
//        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getProperty("driver")));
//        dataSource.setUrl(this.environment.getProperty("url"));
//        dataSource.setUsername(this.environment.getProperty("user"));
//        dataSource.setPassword(this.environment.getProperty("password"));

//        hibernate
        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getRequiredProperty("hibernate.driver_class")));
        dataSource.setUrl(this.environment.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(this.environment.getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(this.environment.getRequiredProperty("hibernate.connection.password"));

        return dataSource;
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource());
//    }


    //hibernate
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));

        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("org.chirkov.firstSpringMvcProject.models");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());

        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());

        return transactionManager;
    }

    public Environment getEnvironment() {
        return environment;
    }
}

