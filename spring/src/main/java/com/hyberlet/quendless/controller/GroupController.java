package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.model.*;
import com.hyberlet.quendless.service.GroupMemberService;
import com.hyberlet.quendless.service.GroupService;
import com.hyberlet.quendless.service.QueueService;
import com.hyberlet.quendless.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/groups")
    public List<Group> getGroups() {
        User user = userService.getCurrentUser();
        return groupService.getUserGroups(user);
    }

    @GetMapping("/group/{groupId}")
    public Group getGroup(@PathVariable UUID groupId) {
        // TODO: realise
        return null;
    }

    @GetMapping("/group/{groupId}/queues")
    public List<Queue> getGroupQueues(@PathVariable UUID groupId) {
        Group group = groupService.getGroupById(groupId);
        List<Queue> queues = queueService.getGroupQueues(group);
        System.out.println(queues);
        return queues;
    }

    @GetMapping("/group/{group_id}/members")
    public List<User> getGroupMembers(@PathVariable UUID group_id) {
        // TODO: realise
        return null;
    }

    @GetMapping("/groups/find/{template}")
    public List<Group> findGroups(@PathVariable String template) {
        System.out.println(template);
        return groupService.findGroupsByNameTemplate(template);
    }

    @PostMapping("/group")
    public Group createGroup(@RequestBody Group group) {
        User creator = userService.getCurrentUser();
        Group createdGroup = groupService.createGroup(group);
        groupMemberService.createGroupMember(createdGroup, creator, "moderator");
        return createdGroup;
    }

    @PutMapping("/group")
    public Group editGroup(@RequestBody Group group) {
        // todo: realise
        return null;
    }

    @DeleteMapping("/group/{groupId}")
    public String deleteGroup(@PathVariable UUID groupId) {
        // todo: realise
        return null;
    }

    // TODO: переделать через заявки
    @GetMapping("/group/{groupId}/join")
    public GroupMember joinUserToGroup(@PathVariable UUID groupId) {
        User user = userService.getCurrentUser();
        Group group = groupService.getGroupById(groupId);
        return groupMemberService.createGroupMember(group, user, "user");
    }


}
