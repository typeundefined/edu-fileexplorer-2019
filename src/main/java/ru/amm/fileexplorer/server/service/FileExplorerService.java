package ru.amm.fileexplorer.server.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.amm.fileexplorer.server.FileExplorer;
import ru.amm.fileexplorer.server.entity.FileStore;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

@Configuration
@PropertySource("classpath:application.properties")
public class FileExplorerService implements FileExplorer {

    @Value("${pathToPublish}")
    private String pathToPublish;

    private final static Logger LOG = LogManager.getLogger(FileExplorerService.class);

    public String getPathToPublish() {
        return pathToPublish;
    }

    @Override
    public List<FileStore> getFileList(String path) {
        List<FileStore> fileList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(pathToPublish, path))) {
            for (Path file : stream) {
                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                FileStore fileStore = new FileStore();
                fileStore.setName(file.getFileName().toString());
                fileStore.setDirectory(attr.isDirectory());
                fileStore.setSize(attr.size());
                fileStore.setLastModifiedTime(new Date(attr.lastModifiedTime().toMillis()));
                fileList.add(fileStore);
            }
        } catch (IOException | DirectoryIteratorException e) {
            LOG.error(e.getMessage(), e);
        }
        return fileList;
    }
}
