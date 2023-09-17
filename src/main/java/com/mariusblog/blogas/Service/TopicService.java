package com.mariusblog.blogas.Service;

import com.mariusblog.blogas.entity.Topic;
import com.mariusblog.blogas.repo.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
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


    public List<Topic> filterTopicsByKeyword(String keyword) {
        return topicRepository.findTopicsByKeyword(keyword);

    }
    public Page<Topic> findPaginated(PageRequest pageable) {
        return topicRepository.findAll(pageable);
    }
}


