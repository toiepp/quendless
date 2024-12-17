package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.*;
import com.hyberlet.quendless.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoService {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private QueueMemberService queueMemberService;
    @Autowired
    private PermissionService permissionService;

    public GroupMemberDto toDto(GroupMember groupMember) {
        return new GroupMemberDto(
                groupMember.getGroupMemberId(),
                groupToDto(groupMember.getGroup()),
                userToDto(groupMember.getUser())
        );
    }

    public List<GroupMemberDto> groupMembersToDto(List<GroupMember> members) {
        return members.stream().map(this::toDto).toList();
    }

    public List<GroupDto> groupsToDto(List<Group> groups) {
        User user = userService.getCurrentUser();
        return groups.stream().map((group) -> groupToDto(group, user)).toList();
    }

    public GroupDto groupToDto(Group group, User user) {
        return new GroupDto(
                group.getGroupId(),
                group.getName(),
                group.getDescription(),
                groupMemberService.isMember(user, group),
                permissionService.isModerator(user, group)
        );
    }

    public GroupDto groupToDto(Group group) {
        User user = userService.getCurrentUser();
        return groupToDto(group, user);
    }

    public QueueMemberDto queueMemberToDto(QueueMember queueMember) {
        return new QueueMemberDto(
                queueMember.getQueueMemberId(),
                queueToDto(queueMember.getQueue()),
                userToDto(queueMember.getUser()),
                queueMember.getPosition()
        );
    }

    public List<QueueMemberDto> queueMembersToDto(List<QueueMember> members) {
        return members.stream().map(this::queueMemberToDto).toList();
    }

    public List<QueueDto> queuesToDto(List<Queue> queues) {
        User user = userService.getCurrentUser();
        return queues.stream().map((group) -> queueToDto(group, user)).toList();
    }

    public QueueDto queueToDto(Queue queue) {
        User user = userService.getCurrentUser();
        return queueToDto(queue, user);
    }

    public QueueDto queueToDto(Queue queue, User user) {
        return new QueueDto(
                queue.getQueueId(),
                queue.getName(),
                queue.getDescription(),
                queue.getEventBegin().toString(),
                queue.getEventEnd().toString(),
                queue.getGroup().getGroupId(),
                queueMemberService.isQueueMember(user, queue),
                queueMemberService.isModerator(user, queue)
        );
    }

    public List<UserDto> usersToDto(List<User> users) {
        return users.stream().map(this::userToDto).toList();
    }

    public UserDto userToDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getLogin()
        );
    }
}
