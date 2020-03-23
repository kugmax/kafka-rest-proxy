package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model;

import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private String key;
    private String value;
    private int partition;
}
