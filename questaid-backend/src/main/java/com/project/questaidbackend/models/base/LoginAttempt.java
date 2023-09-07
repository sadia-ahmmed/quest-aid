package com.project.questaidbackend.models.base;

import com.project.questaidbackend.helpers.enums.LoginEntityType;
import jakarta.persistence.Entity;
import lombok.*;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginAttempt {
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private LoginEntityType type;
}
