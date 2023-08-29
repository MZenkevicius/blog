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
        List<Topic> topics = topicRepository.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Topic> list;

        if (topics.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, topics.size());
            list = topics.subList(startItem, toIndex);
        }

        Page<Topic> topicPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), topics.size());

        return topicPage;
    }
}


