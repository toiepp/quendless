package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {

    public List<Queue> getAllQueues() {
        // todo: realise
        return null;
    }

    public List<Queue> getGroupQueues(Group group) {
        // todo: realise
        return null;
    }

    public Queue getQueueById(long queue_id) {
        // todo: realise
        return null;
    }

    public Queue createQueue(Queue queue) {
        // todo: realise
        return null;
    }

    public Queue editQueue(Queue queue) {
        // todo: realise
        return null;
    }

    public void deleteQueue(long queue_id) {
        // todo: realise
    }

    public List<User> getQueueMembers(long queue_id) {
        // todo: realise
        return null;
    }

    public void insertUserInQueue(User user, Queue queue) {
        // todo: realise
    }

    public void removeUserFromQueue(User user, Queue queue) {
        // todo: realise
    }

    public void passUserBehind(User user, Queue queue) {
        // todo: realise
    }
}
