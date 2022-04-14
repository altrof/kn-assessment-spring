package com.altrof.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("h2.properties")
@EnableTransactionManagement
public class H2JpaConfig {
}
