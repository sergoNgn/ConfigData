package com.example.lab4sentence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class SentenceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/sentence")
    public String getSentence() {

        return  getWord("lab-4-subj") + " " +
                getWord("lab-4-verb") + " " +
                getWord("lab-4-article") + " " +
                getWord("lab-4-adjective") + " " +
                getWord("lab-4-noun");

    }

    private String getWord(String service) {
        List<ServiceInstance> instanceList = discoveryClient.getInstances(service);
        return
                Optional.ofNullable(instanceList.get(0))
                .map(instance -> new RestTemplate().getForObject(instanceList.get(0).getUri(), String.class))
                .orElse(org.apache.commons.lang.StringUtils.EMPTY);
    }
}
