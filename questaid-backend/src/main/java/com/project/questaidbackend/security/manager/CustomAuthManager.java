package com.project.questaidbackend.security.manager;

import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.services.interfaces.IAdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthManager implements AuthenticationManager {

    private IAdminService adminService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Admin admin = adminService.getAdminByEmail(authentication.getName());

        if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), admin.getPassword())) {
            throw new BadCredentialsException("Wrong credentials");
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials());
    }
}
