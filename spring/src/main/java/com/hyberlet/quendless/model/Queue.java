package com.hyberlet.quendless.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@RequiredArgsConstructor
@Entity(name = "queue")
public class Queue {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "queue_id")
    private UUID queueId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "event_begin")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventBegin;

    @Column(name = "event_end")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private Group group;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id")
    @ToString.Exclude
    @JsonIgnore
    private List<QueueMember> queueMembers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Queue queue = (Queue) o;
        return queueId != null && Objects.equals(queueId, queue.queueId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
