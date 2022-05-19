package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    public Group createGroup() {
        // todo: realise
        return null;
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
