package com.dnd.api.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import com.dnd.BasterdzApiApplication;

@Configuration
@EnableFeignClients(basePackageClasses = BasterdzApiApplication.class)
public class FeignClientConfig {
}
