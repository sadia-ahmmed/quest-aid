package com.project.questaidbackend.models.base;

import com.project.questaidbackend.models.ClubMember;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ResponseClubMember {

    @NonNull
    private Long clubMemberId;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String phone;

    @NonNull
    private String clubMemberRoles;

    @NonNull
    private String departmentName;

    public ResponseClubMember(ClubMember clubMember) {
        this.clubMemberId = clubMember.getId();
        this.name = clubMember.getStudent().getName();
        this.email = clubMember.getStudent().getEmail();
        this.phone = clubMember.getStudent().getPhone();
        this.clubMemberRoles = clubMember.getClubMemberRoles().toString();
        this.departmentName = clubMember.getClubDepartment().getDepartmentName();
    }

}
