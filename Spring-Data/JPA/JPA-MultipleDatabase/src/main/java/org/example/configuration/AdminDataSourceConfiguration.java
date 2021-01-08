package org.example.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "tertiaryEntityManagerFactory",
        transactionManagerRef = "tertiaryTransactionManager",
        basePackages = {"org.example.repository.admin"})
public class AdminDataSourceConfiguration {

    @Value("${spring.jpa.properties.hibernate.ddl-auto}")
    private String ddl_auto;

    @Value("${spring.jpa.properties.hibernate.show-sql}")
    private String show_sql;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernate_dialect;

    @Value("${spring.jpa.properties.hibernate.id.new_generator_mappings}")
    private String generator_mappings;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String format_sql;

    @Bean(name = "tertiaryDataSourceProperties")
    @ConfigurationProperties("spring.datasource-tertiary")
    public DataSourceProperties tertiaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "tertiaryDataSource")
    public DataSource tertiaryDataSource(@Qualifier("tertiaryDataSourceProperties") DataSourceProperties tertiaryDataSourceProperties) {
        return tertiaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "tertiaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tertiaryEntityManagerFactory(
            EntityManagerFactoryBuilder tertiaryEntityManagerFactoryBuilder, @Qualifier("tertiaryDataSource") DataSource tertiaryDataSource) {
        Map<String, String> tertiaryJpaProperties = new HashMap<>();
        tertiaryJpaProperties.put("hibernate.dialect", hibernate_dialect);
        tertiaryJpaProperties.put("hibernate.hbm2ddl.auto", ddl_auto);
        tertiaryJpaProperties.put("hibernate.id.new_generator_mappings", generator_mappings);
        tertiaryJpaProperties.put("hibernate.show_sql", show_sql);
        tertiaryJpaProperties.put("hibernate.format_sql", format_sql);
        return tertiaryEntityManagerFactoryBuilder
                .dataSource(tertiaryDataSource)
                .packages("org.example.entity.tertiary")
                .persistenceUnit("tertiaryDataSource")
                .properties(tertiaryJpaProperties)
                .build();
    }

    @Bean(name = "tertiaryTransactionManager")
    public PlatformTransactionManager tertiaryTransactionManager(
            @Qualifier("tertiaryEntityManagerFactory") EntityManagerFactory tertiaryEntityManagerFactory) {
        return new JpaTransactionManager(tertiaryEntityManagerFactory);
    }

}
