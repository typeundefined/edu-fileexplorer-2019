package ru.amm.fileexplorer.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.entity.FileData;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class FileSystemProvider {
    @Value("${pathToPublish}")
    private String pathToPublish;

    public List<FileData> fillFileList(String relpath) {
        List<FileData> result = new ArrayList<>();

        Path absPath = Paths.get(pathToPublish, relpath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(absPath)) {
            for (Path file : stream) {
                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                FileData fileData = toFileData(file, attr, relpath);
                result.add(fileData);
            }
        } catch (IOException | DirectoryIteratorException e) {
            throw new DirectoryAccessException(e);
        }
        return result;
    }

    private FileData toFileData(Path file, BasicFileAttributes attr, String relpath) {
        FileData fileData = new FileData();
        String name = file.getFileName().toString();
        fileData.setName(name);
        fileData.setDirectory(attr.isDirectory());
        fileData.setSize(attr.size());
        fileData.setLastModifiedTime(new Date(attr.lastModifiedTime().toMillis()));

        // TODO: verify this is correct
        fileData.setRelativePath(Path.of(relpath, name).toString());
        return fileData;
    }

    public String getParent(String relativePath) {
        Optional<String> path = ofNullable(Path.of(relativePath))
                .map(Path::getParent)
                .map(Path::toString);
        return path.orElse("");
    }

    public String getPathOfFolder(String path,String pathToPublish) {
        if (path == null) {
            path = "";
        }
        Path p = Paths.get(pathToPublish, path);
        return p.toString();
    }
}
