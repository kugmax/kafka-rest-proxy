package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.service;

import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.clients.kafka.RestProxyClient;
import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model.Message;
import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RestProxyClient client;

    @Override
    public String push(String msg) {

        HashMap<String, String> m = new HashMap<>();
        m.put("msg", msg);

        Record record = new Record();
        record.setValue(m);

        Message message = new Message(Arrays.asList(record));

        System.out.println("##########");
        System.out.println(message);

        String resp = client.push("app_topic", message);

        System.out.println(resp);

        return resp;
    }

    @Override
    public List<String> getAll() {
        return null;
    }
}
