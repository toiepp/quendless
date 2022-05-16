package com.hyberlet.quendless.repository;

import com.hyberlet.quendless.model.QueueMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueMemberRepository extends JpaRepository<QueueMember, Long> {
}