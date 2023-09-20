package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.repository.ClubRepository;
import com.project.questaidbackend.services.interfaces.IClubDepartmentService;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.IFileStorageService;
import com.project.questaidbackend.services.interfaces.ITreasuryService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClubServicesImpl implements IClubService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ClubRepository clubRepository;

    private IClubDepartmentService clubDepartmentService;
    private ITreasuryService treasuryService;
    private IFileStorageService fileStorageService;

    @Override
    public Club getClubByName(String name) {
        return unwrapClub(clubRepository.findByClubName(name), 404L);
    }

    @Override
    public Club createClub(Club club, MultipartFile file, Admin admin) {
        club.setPassword(bCryptPasswordEncoder.encode(club.getPassword()));
        club.setAssignedAdmin(admin);

        try {
            String filepath = fileStorageService.save(file, "logo");
            club.setClubLogoPath(filepath);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Club savedClub = clubRepository.save(club);

        treasuryService.createTreasuryForClub(savedClub);
        // create a General Department
        clubDepartmentService.addDepartment(new ClubDepartment(club, "General"), savedClub);
        return savedClub;
    }

    @Override
    public void removeClub(Club club) {
        clubRepository.delete(club);
    }

    @Override
    public void modifyClub(Club club) {
//        clubRepository.updateClub(club.getClubName(), club.getClubLogoPath(), club.getId());
    }

    @Override
    public List<Club> getAllClubsUnderAdminId(Long id) {
        return clubRepository.findByAssignedAdminId(id);
    }

    @Override
    public Club getClubById(Long id) {
        Optional<Club> club = clubRepository.findById(id);
        return unwrapClub(club, id);
    }

    @Override
    public Club getClubByEmail(String email) {
        Optional<Club> club = clubRepository.findByEmail(email);
        return unwrapClub(club, 404L);
    }

    @Override
    public ClubDepartment addDepartment(ClubDepartment department, Long clubId) {
        Club club = getClubById(clubId);
        return clubDepartmentService.addDepartment(department, club);
    }

    static Club unwrapClub(Optional<Club> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Club.class);
    }
}
