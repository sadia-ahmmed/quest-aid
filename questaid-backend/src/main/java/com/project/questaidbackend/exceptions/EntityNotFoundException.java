package com.project.questaidbackend.exceptions;

import java.util.Locale;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Long id, Class<?> entity) {
        super(entity.getSimpleName().toLowerCase(Locale.ROOT) + " with ID:" + id + " was not found");
    }

    public EntityNotFoundException(String name, Class<?> entity) {
        super(entity.getSimpleName().toLowerCase(Locale.ROOT) + " with Name:" + name + " was not found");
    }
}
