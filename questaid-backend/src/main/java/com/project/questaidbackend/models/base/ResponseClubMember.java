package com.project.questaidbackend.models.base;


import com.project.questaidbackend.models.ClubMember;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ResponseClubMember {

    public ResponseClubMember(ClubMember clubMember) {
        this.firstName = clubMember.getStudent().getFirstName();
        this.lastName = clubMember.getStudent().getLastName();
        this.email = clubMember.getClub().getEmail();
        this.phone = clubMember.getClub().getPhone();
        this.clubMemberRoles = clubMember.getClubMemberRoles().toString();
        this.departmentName = clubMember.getClubDepartment().getDepartmentName();
    }

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
