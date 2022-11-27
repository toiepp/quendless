package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.model.Invite;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class InviteController {

    @GetMapping("/invite")
    public List<Invite> getUserInvites() {
        // todo: implement
        return null;
    }

    @GetMapping("/group/{group_id}/invites")
    public List<Invite> getGroupInvites() {
        // todo: implement
        return null;
    }

    @PostMapping("/invite{invite_id}")
    public String acceptOrDeclineInvite(@PathVariable UUID invite_id) {
        // todo: implement
        return null;
    }

    @PostMapping("/invite")
    public String sendInvite(@RequestBody Invite invite) {
        // todo: implement
        return null;
    }

    @DeleteMapping("/invite/{invite_id}")
    public String deleteInvite(@PathVariable UUID invite_id) {
        // todo: implement
        return null;
    }
}
