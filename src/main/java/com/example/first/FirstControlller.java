package com.example.first;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FirstControlller {

    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/forward")
    public Message forwardMessage(@RequestBody Message msg) {
        System.out.println("First Service received message, forwarding now...");

        String secondServiceUrl = "http://localhost:8082/receive";

        // Forward the payload to the second web service using RestTemplate
        Message responseFromSecond = restTemplate.postForObject(
                secondServiceUrl,
                msg,
                Message.class
        );
        System.out.println("Messagge from second service: " + responseFromSecond.message());
        return responseFromSecond;
    }
}
