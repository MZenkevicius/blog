package com.mariusblog.blogas.repo;


import com.mariusblog.blogas.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByTitle(String topicTitle);
    @Query("SELECT t FROM Topic t WHERE t.title LIKE %?1%"
            + " OR CONCAT(t.header, '') LIKE %?1%")
    List<Topic> findTopicsByKeyword(String keyword);

    /*
    JpaRepository extends ListPagingAndSortingRepository and knows how to handle pageable
     */
    @Override
    Page<Topic> findAll(Pageable pageable);

}