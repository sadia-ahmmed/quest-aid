package com.project.questaidbackend.helpers;

import com.project.questaidbackend.models.enums.LoginEntityType;
import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.models.base.LoginAttempt;
import com.project.questaidbackend.services.interfaces.IAdminService;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthFactory {

    private IAdminService adminService;
    private IClubService clubService;
    private IStudentService studentService;

    public LoginAttempt getAuthEntityDetails(LoginEntityType entityType, String email) {
        switch (entityType) {
            case CLUB -> {
                Club club = clubService.getClubByEmail(email);
                return new LoginAttempt(club.getEmail(), club.getPassword(), LoginEntityType.CLUB);
            }
            case ADMIN -> {
                Admin admin = adminService.getAdminByEmail(email);
                return new LoginAttempt(admin.getEmail(), admin.getPassword(), LoginEntityType.ADMIN);
            }
            case STUDENT -> {
                Student student = studentService.getStudentByEmail(email);
                return new LoginAttempt(student.getEmail(), student.getPassword(), LoginEntityType.STUDENT);
            }
            default -> {
                return null;
            }
        }
    }
}
