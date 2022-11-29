package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.Action;
import com.hyberlet.quendless.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService  {
    @Autowired
    private ActionRepository actionRepository;

    public Action createAction(Action action) {
        return actionRepository.save(action);
    }
}
