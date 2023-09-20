package com.project.questaidbackend.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileStorageService {
    public void init();

    public String save(MultipartFile file, String desiredPath);

    public Resource load(String filename, String desiredPath);

    public void deleteAll();

    public Stream<Path> loadAll();
}
