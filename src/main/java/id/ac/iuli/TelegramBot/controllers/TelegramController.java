package id.ac.iuli.TelegramBot.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import id.ac.iuli.TelegramBot.utils.telegram.RespondMessageTelegram;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class TelegramController {

    private final RestTemplate restTemplate;

    public TelegramController(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(){
        //Send a message to telegram chat
        String token = "5306798811:AAG1B_zpPZIjNlRDYXIhRRKqC0Y9AAIrCAU";
        String chatId = "871662860";
        String url ="https://api.telegram.org/bot"+token+"/sendMessage?chat_id="+chatId+"&text={text}";

        RespondMessageTelegram respondMessageTelegram =
                this.restTemplate.getForObject(url, RespondMessageTelegram.class, "Hello Julian");

        return ResponseEntity.ok("OK");
    }



    @GetMapping("/getJoke")
    public ResponseEntity<String> getJoke(){
        String url = "https://v2.jokeapi.dev/joke/Any";
        try {
            URL jurl = new URL(url);
            URLConnection request = jurl.openConnection();
            request.connect();

            InputStreamReader isReader = new InputStreamReader((InputStream) request.getContent());
            BufferedReader reader = new BufferedReader(isReader);
            StringBuffer sb = new StringBuffer();
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }
            System.out.println(sb.toString());

            JsonObject jo = JsonParser.parseString(sb.toString()).getAsJsonObject();

            String category = jo.get("category").getAsString();
            String type = jo.get("type").getAsString();

            String joke = "";
            if(type.equals("single")){
                joke = jo.get("joke").getAsString();
            }else{
                //twopart
                joke = jo.get("setup").getAsString();
                joke += "\n";
                joke += jo.get("delivery").getAsString();
            }

            System.out.println(joke);


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/jokes")
    public ResponseEntity<String> jokes(){
        String url1 = "https://v2.jokeapi.dev/joke/Any";
        try {
            URL jurl = new URL(url1);
            URLConnection request = jurl.openConnection();
            request.connect();

            InputStreamReader isReader = new InputStreamReader((InputStream) request.getContent());
            BufferedReader reader = new BufferedReader(isReader);
            StringBuffer sb = new StringBuffer();
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }
            System.out.println(sb.toString());

            JsonObject jo = JsonParser.parseString(sb.toString()).getAsJsonObject();

            String category = jo.get("category").getAsString();
            String type = jo.get("type").getAsString();

            String joke = "";
            if(type.equals("single")){
                joke = jo.get("joke").getAsString();
            }else{
                //twopart
                joke = jo.get("setup").getAsString();
                joke += "\n";
                joke += jo.get("delivery").getAsString();
            }

            String token = "5306798811:AAG1B_zpPZIjNlRDYXIhRRKqC0Y9AAIrCAU";
            String chatId = "871662860";
            String url ="https://api.telegram.org/bot"+token+"/sendMessage?chat_id="+chatId+"&text={text}";

            RespondMessageTelegram respondMessageTelegram =
                    this.restTemplate.getForObject(url, RespondMessageTelegram.class, joke);


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("OK");
    }

}