package com.mariusblog.blogas.controller;

import com.mariusblog.blogas.Service.TopicService;
import com.mariusblog.blogas.entity.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @GetMapping
    public String getTopics(Model model) {
        List<Topic> topics = topicService.getAllTopics();
        model.addAttribute("topics", topics);
        return "topics";
    }
    @GetMapping("/{id}")
    public String getTopic(@PathVariable Long id, Model model) {
        Topic topic = topicService.getTopic(id);
        model.addAttribute("topic", topic);
        return "topic";
}
