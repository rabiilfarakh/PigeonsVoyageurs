package com.example.PigeonsVoyageurs.breeder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/breeder/login")
public class BreederAuthController {

//    @Value("${jwt.secret}")
//    private String secret;
//
//    @PostMapping
//    public String getToken(
//            @RequestHeader("param1") String param1,
//            @RequestHeader("param2") String param2){
//        return "";
//    }
}
