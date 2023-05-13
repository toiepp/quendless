package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.controller.exceptions.AccessDeniedException;
import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.GroupMember;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.model.dto.GroupDto;
import com.hyberlet.quendless.model.dto.GroupMemberDto;
import com.hyberlet.quendless.model.dto.QueueDto;
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
    @Autowired
    private DtoService dtoService;

    @Operation(
            summary = "Получить группы.",
            description = "Возвращает список групп, которых состоит пользователь. Если указан шаблон, ищет среди всех групп по шаблону названия.")
    @GetMapping("/groups")
    public List<GroupDto> getGroups(@RequestParam Optional<String> template) {
        List<Group> groups;
        if (template.isPresent()) {
            groups = groupService.findGroupsByNameTemplate(template.get());
        } else {
            User user = userService.getCurrentUser();
            groups = groupService.getUserGroups(user);
        }
        return dtoService.groupsToDto(groups);
    }

    @Operation(
            summary = "Получить группу по id.",
            description = "Если не существует - NotFound. Пользователь должен в ней состоять, иначе - Forbidden."
    )
    @GetMapping("/groups/{groupId}")
    public GroupDto getGroup(@PathVariable UUID groupId) {
        Group group = groupService.getGroupById(groupId);
        User user = userService.getCurrentUser();
        if (groupMemberService.isMember(user, group))
            return dtoService.groupToDto(group, user);
        throw new AccessDeniedException("access denied");
    }

    @Operation(
            summary = "Получить очереди группы.",
            description = "Если группа не существует - NotFound. Пользователь должен состоять в этой группе, иначе - Forbidden."
    )
    @GetMapping("/groups/{groupId}/queues")
    public List<QueueDto> getGroupQueues(@PathVariable UUID groupId) {
        Group group = groupService.getGroupById(groupId);
        User user = userService.getCurrentUser();
        if (groupService.getUserGroups(user).contains(group)) {
            return dtoService.queuesToDto(queueService.getGroupQueues(group));
        }
        throw new AccessDeniedException("access denied");

    }

    @Operation(
            summary = "Получить список участников группы.",
            description = "Если группа не существует - NotFound. Пользователь должен состоять в этой группе, иначе - Forbidden."
    )
    @GetMapping("/groups/{groupId}/members")
    public List<GroupMemberDto> getGroupMembers(@PathVariable UUID groupId) {
        Group group = groupService.getGroupById(groupId);
        List<GroupMember> members = groupMemberService.getGroupMembers(group);
        return dtoService.groupMembersToDto(members);
    }

    @Operation(
            summary = "Создать новую группу",
            description = "Создатель группы автоматически получает права модератора бессрочно"
    )
    @PostMapping("/groups")
    public GroupDto createGroup(@RequestBody Group group) {
        User creator = userService.getCurrentUser();
        Group createdGroup = groupService.createGroup(group);
        groupMemberService.createGroupMember(createdGroup, creator);
        return dtoService.groupToDto(createdGroup);
    }

    @Operation(
            summary = "Редактировать поля группы"
    )
    @PutMapping("/groups")
    public GroupDto editGroup(@RequestBody Group group) {
        User user = userService.getCurrentUser();
        if (permissionService.isModerator(user, group)) {
            return dtoService.groupToDto(groupService.editGroup(group));
        }
        return dtoService.groupToDto(group);
    }

    @Operation (
            summary = "Удалить группу по id"
    )
    @DeleteMapping("/groups/{groupId}")
    public ServerMessage deleteGroup(@PathVariable UUID groupId) {
        User user = userService.getCurrentUser();
        Group group = groupService.getGroupById(groupId);
        if (permissionService.isModerator(user, group)) {
            groupService.deleteGroup(groupId);
            return new ServerMessage("Ok");
        }
        return new ServerMessage("Forbidden");
    }

    // todo: переделать через заявки
    @Operation(
            summary = "Вступить в группу по id",
            description = "Если группа не существует - NotFound. Также создает права члена группы."
    )
    @PostMapping("/groups/{groupId}/join")
    public GroupMemberDto joinUserToGroup(@PathVariable UUID groupId) {
        User user = userService.getCurrentUser();
        Group group = groupService.getGroupById(groupId);
        return dtoService.toDto(groupMemberService.createGroupMember(group, user));
    }

    @Operation(
            summary = "Выйти из группу по id",
            description = "Если группа не существует - NotFound. Также создает права члена группы."
    )
    @PostMapping("/groups/{groupId}/leave")
    public ServerMessage leaveUserFromGroup(@PathVariable UUID groupId) {
        User user = userService.getCurrentUser();
        Group group = groupService.getGroupById(groupId);
        groupMemberService.deleteGroupMember(user, group);
        return new ServerMessage("Ok");
    }


}
