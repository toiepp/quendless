package com.hyberlet.quendless.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "name")
    private String name;

}
