package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Invite;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.service.GroupMemberService;
import com.hyberlet.quendless.service.GroupService;
import com.hyberlet.quendless.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupMemberService groupMemberService;

    @GetMapping("/groups")
    public List<Group> getGroups() {
        User user = userService.getCurrentUser();
        List<Group> groups = groupService.getUserGroups(user);
        return groups;
    }

    @GetMapping("/group/{group_id}")
    public Group getGroup(@PathVariable long group_id) {
        // TODO: realise
        return null;
    }

    @GetMapping("/group/{group_id}/queues")
    public List<Queue> getGroupQueues(@PathVariable long group_id) {
        // TODO: realise
        return null;
    }

    @GetMapping("/group/{group_id}/members")
    public List<User> getGroupMembers(@PathVariable long group_id) {
        // TODO: realise
        return null;
    }

    @GetMapping("/group/find/{template}")
    public List<Group> findGroups(@PathVariable String template) {
        // TODO: realise
        return null;
    }

    @PostMapping("/group")
    public Group createGroup(@RequestBody Group group) {
        Group createdGroup = groupService.createGroup(group);
        User creator = userService.getCurrentUser();
        groupMemberService.createGroupMember(createdGroup, creator, "moderator");
        return createdGroup;
    }

    @PutMapping("/group")
    public Group editGroup(@RequestBody Group group) {
        // todo: realise
        return null;
    }

    @DeleteMapping("/group/{group_id}")
    public String deleteGroup(@PathVariable long group_id) {
        // todo: realise
        return null;
    }


}
