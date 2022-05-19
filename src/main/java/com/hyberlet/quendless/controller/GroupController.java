package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Invite;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @GetMapping("/groups")
    public List<Group> getGroups() {
        // TODO: realise
        return null;
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

    @PostMapping("/groups")
    public Group createGroup(@RequestBody Group group) {
        // todo: realise
        return null;
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
