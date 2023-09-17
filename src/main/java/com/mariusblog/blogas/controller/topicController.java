package com.mariusblog.blogas.controller;

import com.mariusblog.blogas.Service.CommentService;
import com.mariusblog.blogas.Service.TopicService;
import com.mariusblog.blogas.entity.Comment;
import com.mariusblog.blogas.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
        /*
         * If Post-Redirect-Get pattern is not used then
         * after user does POST, and gets server response,
         * the last request made is POST.
         * PROBLEM: if user presses refresh button - user makes last request (POST)
         * and once more sends the last request to the server (thus duplicate data can occur on the server.
         * (uncomment to try it out)
         * */
//        model.addAttribute("topic", topic);
//        return "topic";
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
    public String listTopics(Model model,
                             @PageableDefault (sort = { "title"}, direction = Sort.Direction.DESC, size = 2, page = 1)
                             Pageable pageable)
    {
        Page<Topic> bookPage = topicService.findPaginated((PageRequest) pageable);

                model.addAttribute("topicPage", bookPage);

                int totalPages = bookPage.getTotalPages();
                if (totalPages > 0) {
                    List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
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
