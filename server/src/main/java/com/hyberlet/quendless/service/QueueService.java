package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.controller.exceptions.EntityNotFoundException;
import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.QueueMember;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.QueueMemberRepository;
import com.hyberlet.quendless.repository.QueueRepository;
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

    @LoggedAction
    public List<Queue> getUserQueues(User user) {
        List<QueueMember> queueMembers = queueMemberRepository.getQueueMemberByUser(user);
        List<Queue> queues = new LinkedList<>();
        for (var member: queueMembers)
            queues.add(queueRepository.getById(member.getQueue().getQueueId()));
        return queues;
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
        return queueRepository.save(queue);
    }

    @LoggedAction
    public Queue editQueue(Queue queue) {
        Queue serverQueue = queueRepository.getById(queue.getQueueId());
        serverQueue.setName(queue.getName());
        serverQueue.setDescription(queue.getDescription());
        serverQueue.setEventBegin(queue.getEventBegin());
        serverQueue.setEventEnd(queue.getEventEnd());
        queueRepository.save(serverQueue);
        return serverQueue;
    }

    @LoggedAction
    public void deleteQueue(Queue queue) {
        List<QueueMember> members = queueMemberRepository.getQueueMemberByQueue(queue);
        queueMemberRepository.deleteAll(members);
        queueRepository.delete(queue);
    }

    @LoggedAction
    public List<QueueMember> getQueueMembers(UUID queue_id) {
        Optional<Queue> queue = queueRepository.findById(queue_id);
        if (queue.isEmpty())
            throw new EntityNotFoundException("queue does not exist " + queue_id);
        return queueMemberRepository.getQueueMembersByQueueOrderByPositionAsc(queue.get());
    }

    @LoggedAction
    public void passUserBehind(User user, Queue queue) {
        // todo: implement
    }
}
