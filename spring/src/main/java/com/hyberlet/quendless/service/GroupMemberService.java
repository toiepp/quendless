package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.GroupMember;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.GroupMemberRepository;
import com.hyberlet.quendless.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@LoggedAction
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PermissionService permissionService;

    @LoggedAction
    public GroupMember createGroupMember(Group group, User user) {
        GroupMember member = new GroupMember();
        member.setUser(user);
        member.setGroup(group);
        permissionService.createPermission(
                user,
                "group",
                group.getGroupId(),
                "membership",
                null
        );
        return groupMemberRepository.save(member);
    }

    @LoggedAction
    public void deleteGroupMembersOfGroup(UUID groupId) {
        Group group = groupRepository.getById(groupId);
        List<GroupMember> members = groupMemberRepository.getGroupMembersByGroup(group);
        groupMemberRepository.deleteAll(members);
    }

}
