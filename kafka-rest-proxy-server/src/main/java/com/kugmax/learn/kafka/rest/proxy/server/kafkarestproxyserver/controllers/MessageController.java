package com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.controllers;

import com.kugmax.learn.kafka.rest.proxy.server.kafkarestproxyserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/msg/")
@Controller
public class MessageController {

    @Autowired
    private MessageService service;

    @ResponseBody
    @GetMapping("/push")
    public String push(@RequestParam("msg") String msg) {

        return service.push(msg);
    }

}
