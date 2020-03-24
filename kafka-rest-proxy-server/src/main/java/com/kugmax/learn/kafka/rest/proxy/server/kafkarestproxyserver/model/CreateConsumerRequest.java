package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConsumerRequest {
    private String name;
    private String format;

    @JsonProperty(value = "auto.offset.reset")
    private String autoOffsetReset;

    @JsonProperty("auto.commit.enable")
    private String autoCommitEnable = "true";

    @JsonProperty("fetch.min.bytes")
    private String fetchMinBytes;

    @JsonProperty("consumer.request.timeout.ms")
    private String timeout;
}
