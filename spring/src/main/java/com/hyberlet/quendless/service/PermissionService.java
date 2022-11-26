package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.Permission;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission createPermission(User user, String objectType, UUID objectId, String permissionType, LocalDateTime expire) {
        Permission permission = new Permission();
        permission.setUser(user);
        permission.setObjectType(objectType);
        permission.setObjectId(objectId);
        permission.setPermissionType(permissionType);
        permission.setExpire(expire);
        return permissionRepository.save(permission);
    }

}
