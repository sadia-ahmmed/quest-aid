package com.project.questaidbackend.services;

import com.project.questaidbackend.services.interfaces.IFileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileStorageServiceImpl implements IFileStorageService {

    private final Path root = Paths.get("uploads");
    private final Path logoRoot = Paths.get("uploads/logos");
    private final Path announcementRoot = Paths.get("uploads/announcements");
    private final Path eventsRoot = Paths.get("uploads/events");

    @Override
    public void init() {

    }

    @Override
    public String save(MultipartFile file, String desiredPath) {
        try {
            Path desiredRoot = getDesiredRoot(desiredPath);
            String filename = Long.toString(System.currentTimeMillis()) + file.getOriginalFilename();
            Files.copy(file.getInputStream(), desiredRoot.resolve(Objects.requireNonNull(filename)));

            return desiredRoot.resolve(filename).toString();
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            System.out.println("here");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource load(String filename, String desiredPath) {
        try {
            return tryToSendFile(desiredPath, filename);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }



    private Resource tryToSendFile(String desiredPath, String filename) throws MalformedURLException {
        Path desiredRoot = getDesiredRoot(desiredPath);
        Path file = desiredRoot.resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }

    private Path getDesiredRoot(String desiredRoot) {
        switch (desiredRoot) {
            case "logo" -> {return logoRoot;}
            case "event" -> {return eventsRoot;}
            case "announcement" -> {return announcementRoot;}
            case "default" -> {throw new RuntimeException("Invalid root");}
        }
        return null;
    }
}
