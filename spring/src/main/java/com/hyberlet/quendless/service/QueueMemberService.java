package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.QueueMember;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.QueueMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@LoggedAction
public class QueueMemberService {
    @Autowired
    private QueueMemberRepository queueMemberRepository;
    @Autowired
    private PermissionService permissionService;

    @LoggedAction
    public QueueMember createQueueMember(User user, Queue queue) {
        QueueMember member = queueMemberRepository.getQueueMembersByQueueAndUser(queue, user);
        if (member != null)
            return null;

        QueueMember queueMember = new QueueMember();
        queueMember.setUser(user);
        queueMember.setQueue(queue);
        List<QueueMember> queueMembers = queueMemberRepository.getQueueMembersByQueueOrderByPositionAsc(queue);
        int position;
        if (queueMembers.isEmpty())
            position = 1;
        else
            position = queueMembers.get(queueMembers.size() - 1).getPosition() + 1;
        queueMember.setPosition(position);
        return queueMemberRepository.save(queueMember);
    }

    @LoggedAction
    public QueueMember getQueueMember(Queue queue, User user) {
        return queueMemberRepository.getQueueMembersByQueueAndUser(queue, user);
    }

    @LoggedAction
    public QueueMember deleteQueueMember(User user, Queue queue) {
        QueueMember member = queueMemberRepository.getQueueMembersByQueueAndUser(queue, user);
        queueMemberRepository.delete(member);
        return member;
    }

    @LoggedAction
    public Boolean isQueueMember(User user, Queue queue) {
        QueueMember member = queueMemberRepository.getQueueMembersByQueueAndUser(queue, user);
        return member != null;
    }

    @LoggedAction
    public Boolean isModerator(User user, Queue queue) {
        return permissionService.isModerator(user, queue);
    }
}
