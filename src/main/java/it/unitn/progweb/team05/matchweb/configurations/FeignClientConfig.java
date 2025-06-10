package it.unitn.progweb.team05.matchweb.configurations;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "it.unitn.progweb.team05.matchweb.feign")
public class FeignClientConfig {
}