package com.mariusblog.blogas.Service;

import com.mariusblog.blogas.controller.CustomStatusException;
import com.mariusblog.blogas.entity.Topic;
import com.mariusblog.blogas.repo.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic addNewTopic(Topic newTopic) {
        return topicRepository.save(newTopic);
    }

    public Topic getTopic(Long id) {
        return topicRepository.findById(id).get();
    }

    public List<Topic> filterTopicsByKeyword(String keyword) {
        return topicRepository.findTopicsByKeyword(keyword);
    }
    public Page<Topic> findPaginated(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }
    public String throwException() {
        throw  new CustomStatusException("Just to test how this works");
    }
}


