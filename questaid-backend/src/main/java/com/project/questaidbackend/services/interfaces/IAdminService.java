package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Student;

public interface IAdminService {
    public Admin createAdmin(Admin admin);
    public Admin getAdminById(Long id);
    public Admin getAdminByEmail(String email);
}
