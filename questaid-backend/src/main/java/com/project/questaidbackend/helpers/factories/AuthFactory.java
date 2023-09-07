package com.project.questaidbackend.helpers.factories;

import com.project.questaidbackend.helpers.enums.LoginEntityType;
import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.base.LoginAttempt;
import com.project.questaidbackend.repository.AdminRepository;
import com.project.questaidbackend.repository.ClubRepository;
import com.project.questaidbackend.repository.StudentRepository;
import com.project.questaidbackend.services.interfaces.IAdminService;
import com.project.questaidbackend.services.interfaces.IClubService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthFactory {

    private IAdminService adminService;
    private IClubService clubService;

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
                return null;
            }
            default -> {
                return null;
            }
        }
    }
}
