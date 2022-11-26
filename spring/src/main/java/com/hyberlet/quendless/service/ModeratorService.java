package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ModeratorService {
    @LoggedAction
    public void changeRole(UUID user_id, UUID group_id, String role) {
        // todo: realise
    }

    @LoggedAction
    public boolean isModerator(UUID user_id, UUID group_id) {
        // todo: realise
        return false;
    }
}
