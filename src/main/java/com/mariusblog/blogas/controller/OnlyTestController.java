package com.mariusblog.blogas.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@PropertySource("classpath:mySecrets.properties")
@Profile(value = "!prodProfile")
@RestController
@RequestMapping("/hello")
public class OnlyTestController {

    private final Environment environment;

    @Value("${my.custom.environment.property}")
    private String environmentProperty;

    //    @Value("${some.value.from.property.source}")
    private final String[] someValue;

    public OnlyTestController(Environment environment, @Value("#{'${values}'.split(';')}") String[] prop) {
        this.environment = environment;
        this.someValue = prop;
    }

    @GetMapping("/profiles")
    public String helloProfiles() {
        String allActiveProfiles = Arrays.toString(environment.getActiveProfiles());
        String message = "Some info %s about currently active profiles that should not be visible in PROD, like %s"
                .formatted(allActiveProfiles, this.environmentProperty);
        return message.concat("\n some value from propertySource: %s".formatted(Arrays.toString(someValue)));
    }

}