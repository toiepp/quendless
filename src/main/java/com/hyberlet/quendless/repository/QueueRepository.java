package com.hyberlet.quendless.repository;

import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
    List<Queue> getQueuesByGroup(Group group);
}
