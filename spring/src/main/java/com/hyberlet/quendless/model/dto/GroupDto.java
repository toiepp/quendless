package com.hyberlet.quendless.model.dto;

import com.hyberlet.quendless.model.Group;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private UUID groupId;
    private String name;
    private String description;
    private Boolean membership;
    private Boolean editable;
}
