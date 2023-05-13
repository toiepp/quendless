package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.controller.exceptions.AccessDeniedException;
import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.GroupMember;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.model.dto.ServerMessage;
import com.hyberlet.quendless.service.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private QueueService queueService;
    @Autowired
    private PermissionService permissionService;

    @Operation(
            summary = "Получить группы.",
            description = "Возвращает список групп, которых состоит пользователь. Если указан шаблон, ищет среди всех групп по шаблону названия.")
    @GetMapping("/groups")
    public List<Group> getGroups(@RequestParam Optional<String> template) {
        List<Group> groups;
        if (template.isPresent()) {
            groups = groupService.findGroupsByNameTemplate(template.get());
        } else {
            User user = userService.getCurrentUser();
            groups = groupService.getUserGroups(user);
        }
        return groups;
    }

    @Operation(
            summary = "Получить группу по id.",
            description = "Если не существует - NotFound. Пользователь должен в ней состоять, иначе - Forbidden."
    )
    @GetMapping("/groups/{groupId}")
    public Group getGroup(@PathVariable UUID groupId) {
        Group group = groupService.getGroupById(groupId);
        User user = userService.getCurrentUser();
        if (groupService.getUserGroups(user).contains(group))
            return group;
        throw new AccessDeniedException("access denied");
    }

    @Operation(
            summary = "Получить очереди группы.",
            description = "Если группа не существует - NotFound. Пользователь должен состоять в этой группе, иначе - Forbidden."
    )
    @GetMapping("/groups/{groupId}/queues")
    public List<Queue> getGroupQueues(@PathVariable UUID groupId) {
        Group group = groupService.getGroupById(groupId);
        User user = userService.getCurrentUser();
        if (groupService.getUserGroups(user).contains(group)) {
            return queueService.getGroupQueues(group);
        }
        throw new AccessDeniedException("access denied");

    }

    @Operation(
            summary = "Получить список участников группы.",
            description = "Если группа не существует - NotFound. Пользователь должен состоять в этой группе, иначе - Forbidden."
    )
    @GetMapping("/groups/{group_id}/members")
    public List<User> getGroupMembers(@PathVariable UUID group_id) {
        // TODO: implement
        return null;
    }

    @Operation(
            summary = "Создать новую группу",
            description = "Создатель группы автоматически получает права модератора бессрочно"
    )
    @PostMapping("/groups")
    public Group createGroup(@RequestBody Group group) {
        User creator = userService.getCurrentUser();
        Group createdGroup = groupService.createGroup(group);
        groupMemberService.createGroupMember(createdGroup, creator);
        return createdGroup;
    }

    @Operation(
            summary = "Редактировать поля группы"
    )
    @PutMapping("/groups")
    public Group editGroup(@RequestBody Group group) {
        User user = userService.getCurrentUser();
        if (permissionService.isModerator(user, group)) {
            return groupService.editGroup(group);
        }
        return group;
    }

    @Operation (
            summary = "Удалить группу по id"
    )
    @DeleteMapping("/groups/{groupId}")
    public ServerMessage deleteGroup(@PathVariable UUID groupId) {
        User user = userService.getCurrentUser();
        Group group = groupService.getGroupById(groupId);
        if (permissionService.isModerator(user, group)) {
            groupMemberService.deleteGroupMembersOfGroup(groupId);
            groupService.deleteGroup(groupId);
            return new ServerMessage("Ok");
        }
        return new ServerMessage("Forbidden");
    }

    // TODO: переделать через заявки
    @Operation(
            summary = "Вступить в группу по id",
            description = "Если группа не существует - NotFound. Также создает права члена группы."
    )
    @GetMapping("/groups/{groupId}/join")
    public GroupMember joinUserToGroup(@PathVariable UUID groupId) {
        User user = userService.getCurrentUser();
        Group group = groupService.getGroupById(groupId);
        return groupMemberService.createGroupMember(group, user);
    }


}
