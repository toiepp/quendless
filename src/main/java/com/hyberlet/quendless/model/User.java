package com.hyberlet.quendless.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "user", schema = "public")
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    @ToString.Exclude
    private Photo photo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore
    private List<GroupMember> groupMemberships;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore
    private List<QueueMember> queueMemberships;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore
    private List<Invite> invites;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
