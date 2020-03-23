package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.clients.kafka;

import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.config.RestProxyClientConfiguration;
import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "kafka-rest-proxy", url = "${kafka.rest.proxy.url}", configuration = RestProxyClientConfiguration.class)
public interface RestProxyClient {

    @PostMapping(value="/topics/{topic}",
            headers = {"Content-Type: application/vnd.kafka.v2+json",
                    "Accept: application/vnd.kafka.v2+json"}
    )
    String push(@PathVariable("topic") String topic, Message body);
}
