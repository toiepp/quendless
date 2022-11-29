package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.controller.exceptions.EntityNotFoundException;
import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.QueueMember;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.service.GroupService;
import com.hyberlet.quendless.service.QueueService;
import com.hyberlet.quendless.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @Operation(
            summary = "Получить всех участников очереди по её id",
            description = "Если очереди не существует - NotFound. Если пользователь не состоит в ней - Forbidden"
    )
    @GetMapping("/queues/{queueId}/members")
    public List<User> getQueueMembers(@PathVariable UUID queueId) {
        List<User> members = queueService.getQueueMembers(queueId);
        System.out.println(members);
        return members;
    }
    @Operation(
            summary = "Получить очередь по её id",
            description = "Не реализовано. Если очереди не существует - NotFound. Если пользователь не состоит в ней - Forbidden"
    )
    @GetMapping("/queues/{queueId}")
    public Queue getQueue(@PathVariable UUID queueId) {
        // todo: implement
        return null;
    }

    @Operation(
            summary = "Создаёт очередь в группе с указанным id",
            description = "Если группы не существует - NotFound. Если пользователь не состоит в этой группе - Forbidden"
    )
    @PostMapping("/groups/{groupId}/queues")
    @ResponseStatus(HttpStatus.CREATED)
    public Queue createQueue(@RequestBody Queue queue, @PathVariable UUID groupId) throws EntityNotFoundException {
        Group group = groupService.getGroupById(groupId);
        queue.setGroup(group);
        return queueService.createQueue(queue);
    }

    @Operation(
            summary = "Встать в очередь по её id",
            description = ""
    )
    @GetMapping("/queues/{queueId}/join")
    public QueueMember joinQueue(@PathVariable UUID queueId) {
        System.out.println(queueId);
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queueId);
        return queueService.insertUserInQueue(user, queue);
    }

    @Operation(
            summary = "Выйти из очереди по id",
            description = ""
    )
    @GetMapping("/queues/{queueId}/leave")
    public QueueMember leaveQueue(@PathVariable UUID queueId) {
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queueId);
        return queueService.removeUserFromQueue(user, queue);
    }

    @Operation(
            summary = "Изменить очередь по id",
            description = "Не реализовано"
    )
    @PutMapping("/queues/{queueId}")
    public String editQueue(@PathVariable UUID queueId, @RequestBody Queue queue) {
        // todo: implement
        return null;
    }

    @Operation(
            summary = "Удалить очередь по id",
            description = "Не реализовано"
    )
    @DeleteMapping("/queues/{queueId}")
    public String deleteQueue(@PathVariable UUID queueId) {
        // todo: implement
        return null;
    }
}
