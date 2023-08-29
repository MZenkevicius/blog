package com.mariusblog.blogas.controller;

import com.mariusblog.blogas.Service.CommentService;
import com.mariusblog.blogas.Service.TopicService;
import com.mariusblog.blogas.entity.Comment;
import com.mariusblog.blogas.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/topics")
public class topicController {

    private TopicService topicService;
    private final CommentService commentService;

    public void TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    public topicController(TopicService topicService, CommentService commentService) {
        this.topicService = topicService;
        this.commentService = commentService;
    }

    @GetMapping
    public String getTopics(Model model) {
        List<Topic> topics = topicService.getAllTopics();
        model.addAttribute("topics", topics);
        return "topics";
    }

    @GetMapping("/{id}")
    public String getTopic(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("comment", new Comment());
        Topic topic = topicService.getTopic(id);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @PostMapping("/{id}")
    public String addCommentToTopic(@PathVariable Long id, Comment comment, Model model) {
        Topic topic = topicService.getTopic(id);
        comment.setTopic(topic);
        commentService.addCommentToTopic(comment);
        return "redirect:/topics/" + id;
    }

    @GetMapping("/add")
    public String getAddTopicForm(Model model) {
        model.addAttribute("newTopic", new Topic());
        return "addTopic";
    }

    @PostMapping
    public String addNewTopic(Topic newTopic, Model model) {
        topicService.addNewTopic(newTopic);
        return "redirect:/topics";
    }

        @GetMapping("/filter")
        public String filterTopics(@RequestParam String keyword, Model model) {
            List<Topic> topics = topicService.filterTopicsByKeyword(keyword);
            model.addAttribute("topics", topics);
            return "topics";

        }

            @GetMapping("/list")
            public String listBooks(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
                final int currentPage = page.orElse(1);
                final int pageSize = size.orElse(5);

                System.out.println(">>>>>>>>>    " + currentPage);

                Page<Topic> bookPage = topicService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

                model.addAttribute("topicPage", bookPage);

                int totalPages = bookPage.getTotalPages();
                if (totalPages > 0) {
                    List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                            .boxed()
                            .collect(Collectors.toList());
                    model.addAttribute("pageNumbers", pageNumbers);
                }

                return "listTopics";

            }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
}
