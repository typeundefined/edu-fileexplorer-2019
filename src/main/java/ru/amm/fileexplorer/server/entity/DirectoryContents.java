package ru.amm.fileexplorer.server.entity;

import java.util.ArrayList;
import java.util.List;

public class DirectoryContents {
    private final List<FileData> files = new ArrayList<>();
    private String directoryName;
    private String parentDirectoryName;

    public DirectoryContents(String directoryName, String parentDirectoryName, List<FileData> fileList) {
        this.directoryName = directoryName;
        this.parentDirectoryName = parentDirectoryName;
        files.addAll(fileList);
    }

    public List<FileData> getFiles() {
        return files;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getParentDirectoryName() {
        return parentDirectoryName;
    }

    public void setParentDirectoryName(String parentDirectoryName) {
        this.parentDirectoryName = parentDirectoryName;
    }
}
