package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.consumers;

import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.clients.kafka.RestProxyClient;
import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model.CreateConsumerRequest;
import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model.CreateConsumerResponse;
import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model.MessageRecord;
import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model.SubscribeRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

//@Service
public class HttpMessageConsumer implements InitializingBean {

    @Autowired
    private RestProxyClient client;

    @Value("${message.consumer.groupName}")
    private String consumerGroupName;

    @Value("${spring.application.instance_id}")
    private String instanceId;

    private volatile String baseUrl;

//    @Scheduled(initialDelay = 2000, fixedDelay = 1000)
    public void fetchRecords() {
        List<MessageRecord> records = client.getRecords(consumerGroupName, instanceId);
        System.out.println("fetched " + records.size() + " records.");

        for (MessageRecord record : records) {
            System.out.println(record);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        TODO: what if consumer already exist

        try {
            createConsumer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            subscribe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createConsumer() {
        CreateConsumerRequest request = new CreateConsumerRequest();
        request.setName(instanceId);
        request.setFormat("json");

        CreateConsumerResponse response = client.createConsumer(consumerGroupName, request);

        this.baseUrl = response.getBaseUrl();
    }

    private void subscribe() {
        client.subscribe(consumerGroupName, instanceId,
                new SubscribeRequest(Collections.singletonList("app_topic"))
        );
    }
}
