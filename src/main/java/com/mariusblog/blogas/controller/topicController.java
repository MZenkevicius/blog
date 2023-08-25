package com.mariusblog.blogas.controller;

import com.mariusblog.blogas.Service.TopicService;
import com.mariusblog.blogas.entity.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/topics")
public class topicController {

    private TopicService topicService;

    public void TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    public topicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/filter")
    public String filterTopics(@RequestParam String topicTitle, Model model) {
        List<Topic> topics = topicService.findTopicsByTitle(topicTitle);
        model.addAttribute("topics", topics);
        return "topics";
}

    @GetMapping
    public String getTopics(Model model) {
        List<Topic> topics = topicService.getAllTopics();
        model.addAttribute("topics", topics);
        return "topics";
    }

    @GetMapping("/{id}")
    public String getTopic(@PathVariable Long id,  Model model) {
        Topic topic = topicService.getTopic(id);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @GetMapping("/add")
    public String getAddTopicForm(Model model) {
        model.addAttribute("newTopic", new Topic());
        return "addTopic";
    }

    @PostMapping
    public String postTopics(Topic newTopic, Model model) {

        System.out.println(newTopic);

        Topic savedTopic = topicService.addNewTopic(newTopic);
        model.addAttribute("newTopic", savedTopic);
        return "topic";
    }
}
