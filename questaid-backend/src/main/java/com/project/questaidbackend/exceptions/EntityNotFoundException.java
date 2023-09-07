package com.project.questaidbackend.exceptions;

import java.util.Locale;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Long id, Class<?> entity) {
        super(entity.getSimpleName().toLowerCase(Locale.ROOT) + " with " + id + " was not found");
    }
}
