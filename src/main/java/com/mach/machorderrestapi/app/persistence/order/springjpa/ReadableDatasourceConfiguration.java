//package com.mach.machorderrestapi.app.persistence.order.springjpa;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class ReadableDatasourceConfiguration {
//	@Bean
//	@ConfigurationProperties("spring.datasource.read")
//	public DataSourceProperties readableDatasourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean
//	@ConfigurationProperties("spring.datasource.read.configuration")
//	public DataSource readableDatasource() {
//		return readableDatasourceProperties()
//			.initializeDataSourceBuilder()
//				.type(HikariDataSource.class)
//				.build();
//	}
//
//	@Bean(name = "readableEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean readableEntityManagerFactory(DataSource dataSource) {
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource);
//		em.setPackagesToScan("com.mach.machorderrestapi"); // Altere para o pacote correto
//		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // Se necessário
//		return em;
//	}
//}
