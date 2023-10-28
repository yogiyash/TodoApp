package com.microservices.controllers;

import com.microservices.models.Greeting;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleContextResolver;

import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/hello")
public class GreetingsController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    public ResponseEntity<Greeting> greet(){
        Locale locale = LocaleContextHolder.getLocale();
        Greeting response  = new Greeting(new Date(),messageSource.getMessage("message.goodmorning",null,locale));
        return ResponseEntity.ok(response);
    }

}
