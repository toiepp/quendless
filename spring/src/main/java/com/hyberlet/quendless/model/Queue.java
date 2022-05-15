package com.hyberlet.quendless.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name = "queue")
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "queue_id")
    private LocalDate queueId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "event_begin")
    private LocalDateTime eventBegin;

    @Column(name = "event_end")
    private LocalDateTime eventEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}
