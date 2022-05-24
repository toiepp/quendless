package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.*;
import com.hyberlet.quendless.repository.QueueMemberRepository;
import com.hyberlet.quendless.repository.QueueRepository;
import com.hyberlet.quendless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;
    @Autowired
    private QueueMemberRepository queueMemberRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Queue> getAllQueues() {
        // todo: realise
        return null;
    }

    public List<Queue> getGroupQueues(Group group) {
        return queueRepository.getQueuesByGroup(group);
    }

    public Queue getQueueById(long queueId) {
        return queueRepository.getById(queueId);
    }

    public Queue createQueue(Queue queue) {
        System.out.println(queue);
        Queue createdQueue = queueRepository.save(queue);
        return createdQueue;
    }

    public Queue editQueue(Queue queue) {
        // todo: realise
        return null;
    }

    public void deleteQueue(long queue_id) {
        // todo: realise
    }

    public List<User> getQueueMembers(long queue_id) {
        Queue queue = queueRepository.getById(queue_id);
        List<QueueMember> queueMembers = queueMemberRepository.getQueueMembersByQueueOrderByPositionAsc(queue);
        List<User> users = new LinkedList<>();
        for (QueueMember member : queueMembers) {
            users.add(userRepository.getById(member.getUser().getUserId()));
        }
        return users;
    }

    public QueueMember insertUserInQueue(User user, Queue queue) {
        QueueMember member = queueMemberRepository.getQueueMembersByQueueAndUser(queue, user);
        if (member != null)
            return null;

        QueueMember queueMember = new QueueMember();
        queueMember.setUser(user);
        queueMember.setQueue(queue);
        List<QueueMember> queueMembers = queueMemberRepository.getQueueMembersByQueueOrderByPositionAsc(queue);
        int position;
        if (queueMembers.isEmpty())
            position = 1;
        else
            position = queueMembers.get(queueMembers.size() - 1).getPosition() + 1;
        queueMember.setPosition(position);
        return queueMemberRepository.save(queueMember);
    }

    public QueueMember removeUserFromQueue(User user, Queue queue) {
        QueueMember member = queueMemberRepository.getQueueMembersByQueueAndUser(queue, user);
        queueMemberRepository.delete(member);
        return member;
    }

    public void passUserBehind(User user, Queue queue) {
        // todo: realise
    }
}
