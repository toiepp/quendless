package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.controller.exceptions.EntityNotFoundException;
import com.hyberlet.quendless.model.*;
import com.hyberlet.quendless.repository.QueueMemberRepository;
import com.hyberlet.quendless.repository.QueueRepository;
import com.hyberlet.quendless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;
    @Autowired
    private QueueMemberRepository queueMemberRepository;
    @Autowired
    private UserService userService;

    @LoggedAction
    public List<Queue> getAllQueues() {
        // todo: implement
        return null;
    }

    @LoggedAction
    public List<Queue> getGroupQueues(Group group) {
        return queueRepository.getQueuesByGroup(group);
    }

    @LoggedAction
    public Queue getQueueById(UUID queueId) {
        return queueRepository.getById(queueId);
    }

    @LoggedAction
    public Queue createQueue(Queue queue) {
        System.out.println(queue);
        return queueRepository.save(queue);
    }

    @LoggedAction
    public Queue editQueue(Queue queue) {
        // todo: implement
        return null;
    }

    @LoggedAction
    public void deleteQueue(long queue_id) {
        // todo: implement
    }

    @LoggedAction
    public List<User> getQueueMembers(UUID queue_id) {
        Optional<Queue> queue = queueRepository.findById(queue_id);
        if (queue.isEmpty())
            throw new EntityNotFoundException("queue does not exist " + queue_id);
        List<QueueMember> queueMembers = queueMemberRepository.getQueueMembersByQueueOrderByPositionAsc(queue.get());
        List<User> users = new LinkedList<>();
        for (QueueMember member : queueMembers) {
            Optional<User> user = userService.findUserById(member.getUser().getUserId());
            user.ifPresent(users::add);
        }
        return users;
    }

    @LoggedAction
    public void passUserBehind(User user, Queue queue) {
        // todo: implement
    }
}
