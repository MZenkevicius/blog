package com.mariusblog.blogas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
public class CustomProfileConfig {

//    //Not recommended
//    @Autowired
//    private Environment environment;

    @Bean
    public String defaultProfileTestBean(Environment environment) {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("Active profiles: " + Arrays.toString(activeProfiles));
        return "";
    }

    /**
     * Execute this method (initialize myCustomProfileTestBean bean) only if myCustomProfile is active
     * @return: whatever (just for test)
     */
    @Profile(value = {"myCustomProfile"})
    @Bean
    public String myCustomProfileTestBean(Environment environment) {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("Active profiles: " + Arrays.toString(activeProfiles));
        return "";
    }
}

