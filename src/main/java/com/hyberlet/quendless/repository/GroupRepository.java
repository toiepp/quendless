package com.hyberlet.quendless.repository;

import com.hyberlet.quendless.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> getGroupsByNameContaining(String template);
}
