package com.hyberlet.quendless.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class QueueDto {
    private UUID queueId;
    private String name;
    private String description;
    private LocalDateTime eventBegin;
    private LocalDateTime eventEnd;
    private UUID groupId;
    private Boolean membership;
    private Boolean editable;
}
