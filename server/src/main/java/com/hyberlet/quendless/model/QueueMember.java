package com.hyberlet.quendless.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@RequiredArgsConstructor
@Entity(name = "queue_member")
public class QueueMember {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "queue_member_id")
    private UUID queueMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id")
    @ToString.Exclude
    private Queue queue;

    @Column(name = "position")
    private Integer position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QueueMember that = (QueueMember) o;
        return queueMemberId != null && Objects.equals(queueMemberId, that.queueMemberId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
