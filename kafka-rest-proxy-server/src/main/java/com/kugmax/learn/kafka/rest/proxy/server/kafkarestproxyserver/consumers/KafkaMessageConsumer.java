package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class KafkaMessageConsumer implements InitializingBean {

    private final KafkaConsumer<String, Object> consumer;
    private ExecutorService executor;

    public KafkaMessageConsumer(KafkaConsumer<String, Object> consumer) {
        this.consumer = consumer;
    }

    public void process(ConsumerRecord<String, Object> record) {
        System.out.println(record);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        executor = Executors.newSingleThreadExecutor();
        executor.submit(this::run);
    }

    public void run() {
        try {
            consumer.subscribe(Collections.singletonList("app_topic"));

            while (true) {
                ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(3));
                System.out.println("poll " + records.count());

                records.forEach(this::process);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
