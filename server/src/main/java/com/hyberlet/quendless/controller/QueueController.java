package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.controller.exceptions.AccessDeniedException;
import com.hyberlet.quendless.controller.exceptions.BadRequestException;
import com.hyberlet.quendless.controller.exceptions.EntityNotFoundException;
import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.QueueMember;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.model.dto.QueueDto;
import com.hyberlet.quendless.model.dto.QueueMemberDto;
import com.hyberlet.quendless.model.dto.ServerMessage;
import com.hyberlet.quendless.service.*;
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
    @Autowired
    private QueueMemberService queueMemberService;
    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private DtoService dtoService;

    @Operation(
            summary = "Получить очереди",
            description = "Возвращает список очередей в которых состоит пользователь"
    )
    @GetMapping("/queues")
    public List<QueueDto> getQueues() {
        User user = userService.getCurrentUser();
        List<Queue> queues = queueService.getUserQueues(user);
        return dtoService.queuesToDto(queues);
    }

    @Operation(
            summary = "Получить всех участников очереди по её id",
            description = "Если очереди не существует - NotFound. Если пользователь не состоит в ней - Forbidden"
    )
    @GetMapping("/queues/{queueId}/members")
    public List<QueueMemberDto> getQueueMembers(@PathVariable UUID queueId) {
        List<QueueMember> members = queueService.getQueueMembers(queueId);
        return dtoService.queueMembersToDto(members);
    }
    @Operation(
            summary = "Получить очередь по её id",
            description = "Если очереди не существует - NotFound. Если пользователь не состоит в ней - Forbidden"
    )
    @GetMapping("/queues/{queueId}")
    public QueueDto getQueue(@PathVariable UUID queueId) {
        Queue queue = queueService.getQueueById(queueId);
        Group group = groupService.getGroupById(queue.getGroup().getGroupId());
        User user = userService.getCurrentUser();
        if (groupMemberService.isMember(user, group))
            return dtoService.queueToDto(queue);
        throw new AccessDeniedException("access denied");
    }

    @Operation(
            summary = "Создаёт очередь в группе с указанным id",
            description = "Если группы не существует - NotFound. Если пользователь не состоит в этой группе - Forbidden"
    )
    @PostMapping("/groups/{groupId}/queues")
    @ResponseStatus(HttpStatus.CREATED)
    public QueueDto createQueue(@RequestBody Queue queue, @PathVariable UUID groupId) throws EntityNotFoundException {
        Group group = groupService.getGroupById(groupId);
        queue.setGroup(group);
        return dtoService.queueToDto(queueService.createQueue(queue));
    }

    @Operation(
            summary = "Встать в очередь по её id",
            description = ""
    )
    @PostMapping("/queues/{queueId}/join")
    public QueueMemberDto joinQueue(@PathVariable UUID queueId) {
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queueId);
        QueueMember queueMember = queueMemberService.getQueueMember(queue, user);
        if (queueMember != null)
            throw new BadRequestException("User is already added in queue");
        return dtoService.queueMemberToDto(
                queueMemberService.createQueueMember(user, queue)
        );
    }

    @Operation(
            summary = "Выйти из очереди по id",
            description = ""
    )
    @PostMapping("/queues/{queueId}/leave")
    public QueueMemberDto leaveQueue(@PathVariable UUID queueId) {
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queueId);
        QueueMember queueMember = queueMemberService.getQueueMember(queue, user);
        if (queueMember == null)
            throw new BadRequestException("User is not in queue");
        return dtoService.queueMemberToDto(
                queueMemberService.deleteQueueMember(user, queue)
        );
    }

    @Operation(
            summary = "Изменить очередь по id",
            description = ""
    )
    @PutMapping("/queues")
    public QueueDto editQueue(@RequestBody Queue queue) {
        User user = userService.getCurrentUser();
        if (permissionService.isModerator(user, queue)) {
            return dtoService.queueToDto(queueService.editQueue(queue));
        }
        return dtoService.queueToDto(queue);
    }

    @Operation(
            summary = "Удалить очередь по id",
            description = ""
    )
    @DeleteMapping("/queues/{queueId}")
    public ServerMessage deleteQueue(@PathVariable UUID queueId) {
        User user = userService.getCurrentUser();
        Queue queue = queueService.getQueueById(queueId);
        if (permissionService.isModerator(user, queue)) {
            queueService.deleteQueue(queue);
            return new ServerMessage("Ok");
        }
        return new ServerMessage("Forbidden");
    }
}
