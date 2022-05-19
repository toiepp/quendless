package com.hyberlet.quendless.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModeratorController {

    @GetMapping("/group/{group_id}/get_moder/{user_id}")
    public String getModeration(@PathVariable long group_id, @PathVariable long user_id) {
        // todo: realise
        return null;
    }

    @GetMapping("/group/{group_id}/revoke_moder/{user_id}")
    public String revokeModeration(@PathVariable long group_id, @PathVariable long user_id) {
        // todo: realise
        return null;
    }

}
