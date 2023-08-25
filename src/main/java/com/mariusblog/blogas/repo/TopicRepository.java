package com.mariusblog.blogas.repo;


import com.mariusblog.blogas.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
