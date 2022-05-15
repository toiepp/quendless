package com.hyberlet.quendless.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private Long photoId;

    @Column
    private String path;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photo")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photo")
    @JsonIgnore
    private List<Group> groups = new ArrayList<>();

}
