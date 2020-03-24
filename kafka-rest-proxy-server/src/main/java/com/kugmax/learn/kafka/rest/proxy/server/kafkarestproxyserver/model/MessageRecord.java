package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model;

import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRecord {
    private String topic;
    private String key;
    private Object value;
    private int partition;
    private long offset;
}