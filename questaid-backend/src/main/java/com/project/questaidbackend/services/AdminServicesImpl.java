package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.repository.AdminRepository;
import com.project.questaidbackend.repository.ClubRepository;
import com.project.questaidbackend.repository.StudentRepository;
import com.project.questaidbackend.services.interfaces.IAdminService;
import com.project.questaidbackend.services.interfaces.IClubDepartmentService;
import com.project.questaidbackend.services.interfaces.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AdminServicesImpl implements IAdminService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // TODO: REFACTOR THIS CODE! DO NOT LINK THIS DIRECTLY TO REPOSITORIES. USE SERVICES INSTEAD
    private AdminRepository adminRepository;


    @Override
    public Admin createAdmin(Admin admin) {
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return unwrapAdmin(admin, id);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        return unwrapAdmin(admin, 404L);
    }


    static Admin unwrapAdmin(Optional<Admin> entity, Long id) {
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Admin.class);
    }

}
