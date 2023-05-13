package com.hyberlet.quendless.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class QueueMemberDto {
    private UUID queueMemberId;
    private QueueDto queue;
    private UserDto user;
    private Integer position;
}
