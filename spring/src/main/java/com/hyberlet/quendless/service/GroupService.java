package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.GroupMember;
import com.hyberlet.quendless.model.Permission;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.GroupMemberRepository;
import com.hyberlet.quendless.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


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
                userService.getCurrentUser(),
                "group",
                createdGroup.getGroupId(),
                "moderation",
                null
        );
        return createdGroup;
    }


    @LoggedAction
    public Group editGroup() {
        // todo: realise
        return null;
    }

    @LoggedAction
    public Group getGroupById(UUID group_id) {
        Group group = groupRepository.getById(group_id);
        return group;
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
    public void deleteGroup(long group_id) {
        // todo: realise
    }

//    public String addUserToGroup(User user, Group group) {
//        GroupMember member = new GroupMember();
//        return "member";
//    }

    @LoggedAction
    public void addQueueToGroup(long queue_id, long group_id) {
        // todo: realise
    }

    @LoggedAction
    public boolean isModer(User user, Group group) {
        // todo: realise
        return false;
    }

}
