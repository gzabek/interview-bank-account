package com.gzabek.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.gzabek.bank.service.BalanceService;
import com.gzabek.bank.service.BalanceServiceImpl;

@Configuration
@ImportResource("classpath:domain-applicationContext.xml")
public class ServicesContext {

    @Bean
    public BalanceService balanceService() {
        return new BalanceServiceImpl();
    }

}
