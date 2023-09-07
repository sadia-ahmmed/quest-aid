package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;

public interface IAdminService {
    public Club createClub(Club club);
    public Admin createAdmin(Admin admin);
    public Admin getAdminById(Long id);
    public Admin getAdminByEmail(String email);
    public void remove(Club club);
    public void modifyClub(Club club);
}
