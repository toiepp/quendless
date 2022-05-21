package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.GroupMember;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.GroupMemberRepository;
import com.hyberlet.quendless.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getUserGroups(User user) {
        List<GroupMember> groupMembers = groupMemberRepository.getGroupMembersByUser(user);
        System.out.println(groupMembers);
        List<Group> groups = new LinkedList<>();
        for (GroupMember member : groupMembers) {
            groups.add(groupRepository.getById(member.getGroup().getGroupId()));
        }
        return groups;
    }

    public Group createGroup(Group group) {
        Group createdGroup = groupRepository.save(group);
        return createdGroup;
    }

    public Group editGroup() {
        // todo: realise
        return null;
    }

    public Group getGroupById(long group_id) {
        // todo: realise
        return null;
    }

    public List<Group> findGroupsByNameTemplate(String template) {
        // todo: realise
        return null;
    }

    public void deleteGroup(long group_id) {
        // todo: realise
    }

    public void addUserToGroup(long user_id, long group_id) {
        // todo: realise
    }

    public void addQueueToGroup(long queue_id, long group_id) {
        // todo: realise
    }

    public boolean isModer(User user, Group group) {
        // todo: realise
        return false;
    }

}
