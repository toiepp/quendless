package com.hyberlet.quendless.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long groupId;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
