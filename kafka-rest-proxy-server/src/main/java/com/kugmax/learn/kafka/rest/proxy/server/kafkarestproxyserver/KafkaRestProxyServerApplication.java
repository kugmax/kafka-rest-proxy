package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class KafkaRestProxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaRestProxyServerApplication.class, args);
	}

}
