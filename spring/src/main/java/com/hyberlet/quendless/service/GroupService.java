package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.controller.exceptions.EntityNotFoundException;
import com.hyberlet.quendless.model.*;
import com.hyberlet.quendless.model.dto.ServerMessage;
import com.hyberlet.quendless.repository.GroupMemberRepository;
import com.hyberlet.quendless.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @LoggedAction
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @LoggedAction
    public List<Group> getUserGroups(User user) {
        List<GroupMember> groupMembers = groupMemberRepository.getGroupMembersByUser(user);
        System.out.println(groupMembers);
        List<Group> groups = new LinkedList<>();
        for (GroupMember member : groupMembers) {
            groups.add(groupRepository.getById(member.getGroup().getGroupId()));
        }
        return groups;
    }

    @LoggedAction
    public Group createGroup(Group group) {
        Group createdGroup = groupRepository.save(group);
        User user = userService.getCurrentUser();
        permissionService.createPermission(
                user,
                "group",
                createdGroup.getGroupId(),
                "moderation",
                null
        );
        return createdGroup;
    }


    @LoggedAction
    public Group editGroup(Group group) {
        Group serverGroup = groupRepository.getById(group.getGroupId());
        serverGroup.setName(group.getName());
        serverGroup.setDescription(group.getDescription());
        groupRepository.save(serverGroup);
        return group;
    }

    @LoggedAction
    public Group getGroupById(UUID groupId) throws EntityNotFoundException {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isEmpty())
            throw new EntityNotFoundException("no group with id " + groupId.toString());
        return group.get();
    }

    @LoggedAction
    public List<Group> findGroupsByNameTemplate(String template) {
        List<Group> groups = groupRepository.findAll();
        List<Group> result = new LinkedList<>();
        for (Group group : groups) {
            if (group.getName().contains(template)) {
                result.add(group);
            }
        }
        return result;
    }

    @LoggedAction
    public void deleteGroup(UUID groupId) {
        groupRepository.deleteById(groupId);
    }

//    public String addUserToGroup(User user, Group group) {
//        GroupMember member = new GroupMember();
//        return "member";
//    }

    @LoggedAction
    public void addQueueToGroup(long queue_id, long group_id) {
        // todo: realise
    }

}
