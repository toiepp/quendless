package com.hyberlet.quendless.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "queue_member")
public class QueueMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "queue_member_id")
    private Long queueMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id")
    private Queue queue;

    @Column(name = "position")
    private Integer position;

}
