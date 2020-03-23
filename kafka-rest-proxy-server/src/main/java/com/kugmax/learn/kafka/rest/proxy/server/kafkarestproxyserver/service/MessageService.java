package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.service;

import java.util.List;

public interface MessageService {
    String push(String msg);

    List<String> getAll();
}
