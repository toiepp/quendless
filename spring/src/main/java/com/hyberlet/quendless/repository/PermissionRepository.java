package com.hyberlet.quendless.repository;

import com.hyberlet.quendless.model.Permission;
import com.hyberlet.quendless.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    @Query("select p from permission p " +
            "where p.user = ?1 and p.objectType = ?2 and p.objectId = ?3 and (p.expire is null or current_timestamp() < p.expire)")
    List<Permission> getPermissionsByUser(User user, String objectType, UUID objectId);

    @Query("select p from permission p " +
            "where p.user = ?1 and p.permissionType = 'admin' and (p.expire is null or current_timestamp() < p.expire)")
    List<Permission> getUserAdminPermissions(User user);
}
