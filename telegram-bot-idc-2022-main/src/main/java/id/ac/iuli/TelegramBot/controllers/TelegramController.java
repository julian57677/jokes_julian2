package id.ac.iuli.TelegramBot.controllers;

import id.ac.iuli.TelegramBot.utils.telegram.RespondMessageTelegram;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TelegramController {

    private final RestTemplate restTemplate;

    public TelegramController(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(){
        System.out.println("IULI");
        //Send a message to telegram chat
        String token = "5303764871:AAHV6AuhJXZrxJRA3MTbPNvn8i2e7Cs4eVM";
        String chatId = "936703837";
        String url ="https://api.telegram.org/bot"+token+"/sendMessage?chat_id="+chatId+"&text={text}";

        RespondMessageTelegram respondMessageTelegram =
                this.restTemplate.getForObject(url, RespondMessageTelegram.class, "Hello IULI 2");

        return ResponseEntity.ok("OK");
    }
}
