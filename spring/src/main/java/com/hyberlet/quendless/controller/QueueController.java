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
import java.util.UUID;

@RestController
public class QueueController {

    @Autowired
    private UserService userService;
    @Autowired
    private QueueService queueService;
    @Autowired
    private GroupService groupService;

    @GetMapping("/queue/{queue_id}/members")
    public List<User> getQueueMembers(@PathVariable UUID queue_id) {
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
    public Queue createQueue(@RequestBody Queue queue, @PathVariable UUID group_id) {
        Group group = groupService.getGroupById(group_id);
        System.out.println(group);
        queue.setGroup(group);
        return queueService.createQueue(queue);
    }

    @GetMapping("/queue/{queue_id}/join")
    public QueueMember joinQueue(@PathVariable UUID queue_id) {
        System.out.println(queue_id);
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queue_id);
        return queueService.insertUserInQueue(user, queue);
    }

    @GetMapping("/queue/{queue_id}/leave")
    public QueueMember leaveQueue(@PathVariable UUID queue_id) {
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queue_id);
        return queueService.removeUserFromQueue(user, queue);
    }

    @PutMapping("/queue/{queue_id}")
    public String editQueue(@PathVariable UUID queue_id) {
        // todo: realise
        return null;
    }

    @DeleteMapping("/queue/{queue_id}")
    public String deleteQueue(@PathVariable UUID queue_id) {
        // todo: realise
        return null;
    }
}
