package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.QueueMember;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.service.GroupService;
import com.hyberlet.quendless.service.QueueService;
import com.hyberlet.quendless.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class QueueController {

    @Autowired
    private UserService userService;
    @Autowired
    private QueueService queueService;
    @Autowired
    private GroupService groupService;

    @GetMapping("/queue/{queue_id}/members")
    public List<User> getQueueMembers(@PathVariable long queue_id) {
        List<User> members = queueService.getQueueMembers(queue_id);
        System.out.println(members);
        return members;
    }

    @GetMapping("/queue/{queue_id}")
    public Queue getQueue(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }

    @PostMapping("/group/{group_id}/queues")
    public Queue createQueue(@RequestBody Queue queue, @PathVariable Long group_id) {
        Group group = groupService.getGroupById(group_id);
        System.out.println(group);
        queue.setGroup(group);
        Queue createdQueue = queueService.createQueue(queue);
        return createdQueue;
    }

    @GetMapping("/queue/{queue_id}/join")
    public QueueMember joinQueue(@PathVariable long queue_id) {
        System.out.println(queue_id);
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queue_id);
        QueueMember member = queueService.insertUserInQueue(user, queue);
        return member;
    }

    @GetMapping("/queue/{queue_id}/leave")
    public QueueMember leaveQueue(@PathVariable long queue_id) {
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queue_id);
        QueueMember member = queueService.removeUserFromQueue(user, queue);
        return member;
    }

    @PutMapping("/queue/{queue_id}")
    public String editQueue(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }

    @DeleteMapping("/queue/{queue_id}")
    public String deleteQueue(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }
}
