package com.hyberlet.quendless.repository;

import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.QueueMember;
import com.hyberlet.quendless.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QueueMemberRepository extends JpaRepository<QueueMember, UUID> {
    List<QueueMember> getQueueMembersByQueueOrderByPositionAsc(Queue queue);
    QueueMember getQueueMembersByQueueAndUser(Queue queue, User user);
}
