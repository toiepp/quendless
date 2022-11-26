package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.model.Invite;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InviteService {

    @LoggedAction
    public Invite getInviteById(UUID invite_id) {
        // todo: realise
        return null;
    }


    @LoggedAction
    public void changeStatus(UUID invite_id, String status) {
        // todo: realise
    }

}
