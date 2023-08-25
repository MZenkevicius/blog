package com.mariusblog.blogas.Service;

import com.mariusblog.blogas.entity.Topic;
import com.mariusblog.blogas.entity.Topic;
import com.mariusblog.blogas.repo.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic addNewTopic(Topic newTopic) {
        return topicRepository.save(newTopic);
    }

    public List<Topic> findTopicsByTitle(String topicTitle) {
        return topicRepository.findByTitle(topicTitle);
    }

    public Topic getTopic(Long id) {
        return topicRepository.findById(id).get();
    }
}


