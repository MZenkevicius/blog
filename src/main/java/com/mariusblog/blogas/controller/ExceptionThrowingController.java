package com.mariusblog.blogas.controller;

import com.mariusblog.blogas.Service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/throwing")
public class ExceptionThrowingController {

    private final TopicService topicService;

    public ExceptionThrowingController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/exception")
    public String getException() {
        return topicService.throwException();
    }

    @GetMapping("/customException")
    public String getCustomException() {
        throw new CustomException("CustomException for a test reason");
    }


}

