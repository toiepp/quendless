package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueueController {

    @GetMapping("/queue/{queue_id}/members")
    public List<User> getQueueMembers(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }

    @GetMapping("/queue/{queue_id}")
    public Queue getQueue(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }

    @PostMapping("/group/{group_id}/queues")
    public Queue createQueue(@RequestBody Queue queue, @PathVariable long group_id) {
        // todo: realise
        return null;
    }

    @GetMapping("/queue/{queue_id}/join")
    public String joinQueue(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }

    @GetMapping("/queue/{queue_id}/leave")
    public String leaveQueue(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }

    @PutMapping("/queue/{queue_id}")
    public String editQueue(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }

    @DeleteMapping("/queue/{queue_id}")
    public String deleteQueue(@PathVariable long queue_id) {
        // todo: realise
        return null;
    }
}
