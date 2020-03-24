package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConsumerResponse {

    @JsonProperty(value = "instance_id")
    private String instanceID;

    @JsonProperty(value = "base_uri")
    private String baseUrl;
}
