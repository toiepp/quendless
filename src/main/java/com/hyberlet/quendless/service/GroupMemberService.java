package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.GroupMember;
import com.hyberlet.quendless.model.Role;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.GroupMemberRepository;
import com.hyberlet.quendless.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private RoleRepository roleRepository;

    public GroupMember createGroupMember(Group group, User user, String roleName) {
        GroupMember member = new GroupMember();
        member.setUser(user);
        member.setGroup(group);
        Role role = roleRepository.getRoleByName(roleName);
        member.setRole(role);
        return groupMemberRepository.save(member);
    }

}
