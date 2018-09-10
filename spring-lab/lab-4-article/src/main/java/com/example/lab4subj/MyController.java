package com.example.lab4subj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MyController {

    @Value("${words}")
    String words;


    @RequestMapping("/")
    public String getWords() {
        String[] words = this.words.split(",");
        return words[new Random().nextInt(words.length)];
    }
}
