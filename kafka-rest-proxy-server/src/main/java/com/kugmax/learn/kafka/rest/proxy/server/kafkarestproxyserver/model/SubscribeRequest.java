package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model;

import lombok.*;

import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeRequest {
    private List<String> topics;
}
