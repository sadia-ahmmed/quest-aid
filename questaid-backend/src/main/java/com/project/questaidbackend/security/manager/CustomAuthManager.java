package com.project.questaidbackend.security.manager;

import com.project.questaidbackend.helpers.enums.LoginEntityType;
import com.project.questaidbackend.helpers.factories.AuthFactory;
import com.project.questaidbackend.models.base.LoginAttempt;
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

//    private IAdminService adminService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthFactory authFactory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String[] credentialString = authentication.getCredentials().toString().split("//");

        String authPassword = credentialString[0];
        LoginEntityType loginEntityType = LoginEntityType.valueOf(credentialString[1]);


        LoginAttempt authClass = authFactory.getAuthEntityDetails(loginEntityType, authentication.getName());

        if(
                !bCryptPasswordEncoder.matches(
                        authPassword,
                        authClass.getPassword()
                )
        ) {
            throw new BadCredentialsException("Wrong credentials");
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials());
    }
}
