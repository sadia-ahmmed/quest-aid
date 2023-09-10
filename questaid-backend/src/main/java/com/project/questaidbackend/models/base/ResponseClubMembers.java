package com.project.questaidbackend.models.base;


import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseClubMembers {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String phone;

    @NonNull
    private String clubMemberRoles;

    @NonNull
    private String departmentName;


}
