package com.hyberlet.quendless.model.dto;

import lombok.*;

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
    private String eventBegin;
    private String eventEnd;
    private UUID groupId;
    private Boolean membership;
    private Boolean editable;
}
