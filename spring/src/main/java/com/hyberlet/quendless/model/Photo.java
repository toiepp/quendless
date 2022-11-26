package com.hyberlet.quendless.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "photo_id")
    private UUID photoId;

    @Column
    private String path;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photo")
    @JsonIgnore
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photo")
    @JsonIgnore
    @ToString.Exclude
    private List<Group> groups = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Photo photo = (Photo) o;
        return photoId != null && Objects.equals(photoId, photo.photoId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}