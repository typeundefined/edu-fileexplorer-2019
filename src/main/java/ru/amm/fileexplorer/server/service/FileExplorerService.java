package ru.amm.fileexplorer.server.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.FileExplorer;
import ru.amm.fileexplorer.server.entity.FileStore;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class FileExplorerService implements FileExplorer, Iterable<FileStore> {

    @Value("${pathToPublish}")
    private String pathToPublish;
    private final static Logger LOG = LogManager.getLogger(FileExplorerService.class);
    private List<FileStore> fileList = new ArrayList<>();

    public FileExplorerService() {
        //fillFileList("");
    }

    public String getPathToPublish() {
        return pathToPublish;
    }

    private void fillFileList(String path) {
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
    }

    @Override
    public void changeDir(String path) {
        fillFileList(path);
    }

    @Override
    public List<FileStore> getFileList() {
        return fileList;
    }

    @Override
    public Iterator<FileStore> iterator() {
        return new FileIterator();
    }

    private class FileIterator implements Iterator<FileStore> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < fileList.size();
        }

        @Override
        public FileStore next() {
            if (!hasNext()) {
                LOG.error(new NoSuchElementException());
            }
            return fileList.get(index++);
        }
    }
}
