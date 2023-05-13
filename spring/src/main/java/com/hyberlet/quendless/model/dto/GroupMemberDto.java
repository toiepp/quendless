package com.hyberlet.quendless.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupMemberDto {
    private UUID groupMemberId;
    private GroupDto group;
    private UserDto user;
}
