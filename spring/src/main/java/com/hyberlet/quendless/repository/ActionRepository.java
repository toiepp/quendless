package com.hyberlet.quendless.repository;

import com.hyberlet.quendless.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActionRepository extends JpaRepository<Action, UUID> {

}
