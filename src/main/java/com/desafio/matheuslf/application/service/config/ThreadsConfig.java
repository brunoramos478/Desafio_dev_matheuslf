package com.desafio.matheuslf.application.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ThreadsConfig {

    @Bean(name = "taskExecutor")
    public Executor virtualThreads() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
