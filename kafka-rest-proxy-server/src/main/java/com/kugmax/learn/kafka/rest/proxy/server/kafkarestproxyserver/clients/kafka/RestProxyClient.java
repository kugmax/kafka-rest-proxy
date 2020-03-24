package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.clients.kafka;

import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.config.RestProxyClientConfiguration;
import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "kafka-rest-proxy", url = "${kafka.rest.proxy.url}", configuration = RestProxyClientConfiguration.class)
public interface RestProxyClient {

    @PostMapping(value="/topics/{topic}",
            consumes = "application/vnd.kafka.json.v2+json",
            produces = {
                    "application/vnd.kafka.v2+json",
                    "application/vnd.kafka+json",
                    "application/json"})
    String push(@PathVariable("topic") String topic, Message body);

    @PostMapping(value ="/consumers/{groupName}",
            consumes = "application/vnd.kafka.json.v2+json",
            produces = {
                    "application/vnd.kafka.v2+json",
                    "application/vnd.kafka+json",
                    "application/json"})
    CreateConsumerResponse createConsumer(@PathVariable("groupName") String groupName, CreateConsumerRequest request);

    @DeleteMapping(value ="/consumers/{groupName}/instances/{instanceID}",
            consumes = "application/vnd.kafka.json.v2+json",
            produces = {
                    "application/vnd.kafka.v2+json",
                    "application/vnd.kafka+json",
                    "application/json"})
    String deleteConsumer(@PathVariable("groupName") String groupName, @PathVariable("instanceID") String instanceID);


    @PostMapping(value ="/consumers/{groupName}/instances/{instanceID}/subscription",
            consumes = "application/vnd.kafka.json.v2+json",
            produces = {
                    "application/vnd.kafka.v2+json",
                    "application/vnd.kafka+json",
                    "application/json"})
    void subscribe(@PathVariable("groupName") String groupName,
                     @PathVariable("instanceID") String instanceID,
                     SubscribeRequest request);

    @GetMapping(value ="/consumers/{groupName}/instances/{instanceID}/records",
            produces = "application/vnd.kafka.json.v2+json")
    List<MessageRecord> getRecords(@PathVariable("groupName") String groupName,
                                   @PathVariable("instanceID") String instanceID);
}
