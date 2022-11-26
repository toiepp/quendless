package com.hyberlet.quendless.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ModeratorService {
    public void changeRole(UUID user_id, UUID group_id, String role) {
        // todo: realise
    }

    public boolean isModerator(UUID user_id, UUID group_id) {
        // todo: realise
        return false;
    }
}
