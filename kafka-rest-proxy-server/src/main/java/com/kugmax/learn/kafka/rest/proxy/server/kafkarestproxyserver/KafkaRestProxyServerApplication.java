package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class KafkaRestProxyServerApplication {

	@Value("${message.consumer.groupName}")
	private String consumerGroupName;

	@Value("${spring.application.instance_id}")
	private String instanceId;

	@Value("${kafka.cluster.host}")
	private String kafkaHost;

	public static void main(String[] args) {
		SpringApplication.run(KafkaRestProxyServerApplication.class, args);
	}

	@Bean
	public KafkaConsumer<String, Object> kafkaConsumer() {
		Properties config = new Properties();
		config.put("client.id", instanceId);
		config.put("group.id", consumerGroupName);
		config.put("bootstrap.servers", kafkaHost);
		config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return new KafkaConsumer<>(config);
	}
}
