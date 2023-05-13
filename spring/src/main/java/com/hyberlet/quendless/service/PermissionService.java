package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.model.Group;
import com.hyberlet.quendless.model.Permission;
import com.hyberlet.quendless.model.Queue;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.GroupRepository;
import com.hyberlet.quendless.repository.PermissionRepository;
import com.hyberlet.quendless.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private QueueRepository queueRepository;

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    @LoggedAction
    public Permission createPermission(User user, String objectType, UUID objectId, String permissionType, LocalDateTime expire) {
        Permission permission = new Permission();
        permission.setUser(user);
        permission.setObjectType(objectType);
        permission.setObjectId(objectId);
        permission.setPermissionType(permissionType);
        permission.setExpire(expire);
        return permissionRepository.save(permission);
    }

    @LoggedAction
    public Boolean isModerator(User user, Group group) {
        if (isAdmin(user))
            return true;
        List<Permission> permissions = permissionRepository.getPermissionsByUser(
                user,
                "group",
                group.getGroupId()
        );
        for (Permission permission : permissions) {
            if (Objects.equals(permission.getPermissionType(), "moderation"))
                return true;
        }
        return false;
    }

    @LoggedAction
    public Boolean isModerator(User user, Queue queue) {
        Queue serverQueue = queueRepository.getById(queue.getQueueId());
        Group serverGroup = groupRepository.getById(serverQueue.getGroup().getGroupId());
        return isModerator(user, serverGroup);
    }

    @LoggedAction
    public Boolean isAdmin(User user) {
        List<Permission> permissions = permissionRepository.getUserAdminPermissions(user);
        return permissions.size() > 0;
    }

}
