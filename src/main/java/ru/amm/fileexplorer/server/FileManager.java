package ru.amm.fileexplorer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class FileManager implements Iterable<FilePath> {
    private String pathToPublish;
    private String currentPath;

    private List<FilePath> children;

    @Autowired
    private FileManager(@Value("${filemanager.path-to-publish}") String root) {
        pathToPublish = root;
        currentPath = root;
        updateChildern();
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void updateChildern() {
        updateChildern(new File(pathToPublish));
    }

    private void updateChildern(File file) {
        children = new ArrayList<>();

        File[] list = file.listFiles();
        if (list == null) {
            return;
        }

        for (File child : list) {
            children.add(new FilePath(child.getAbsolutePath(), child.isDirectory()));
        }
    }

    public boolean downDirectory(FilePath fileName) {
        File file = new File(fileName.getAbsolutePath());
        if (file.isDirectory()) {
            currentPath = file.getAbsolutePath();
            updateChildern(file);
            return true;
        }
        return false;
    }

    public boolean upDirectory() {
        if (!pathToPublish.equals(currentPath)) {
            currentPath = currentPath.substring(0, currentPath.lastIndexOf("\\"));
            updateChildern(new File(currentPath));
            return true;
        }
        return false;
    }

    @Override
    public Iterator<FilePath> iterator() {
        return new FileIterator();
    }

    private class FileIterator implements Iterator<FilePath> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < children.size();
        }

        @Override
        public FilePath next() {
            return children.get(index++);
        }
    }
}
