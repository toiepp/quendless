package com.hyberlet.quendless.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "invite")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invite_id")
    private Long inviteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "status")
    private String status;
}
