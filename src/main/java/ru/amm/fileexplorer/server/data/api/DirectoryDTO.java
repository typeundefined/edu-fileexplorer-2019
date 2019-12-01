package ru.amm.fileexplorer.server.data.api;

import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class DirectoryDTO extends RepresentationModel<DirectoryDTO> {
    private final List<FileDTO> files = new ArrayList<>();
    private final List<DirectoryShortDTO> subDirectories = new ArrayList<>();
    private String directoryName;

    public List<FileDTO> getFiles() {
        return files;
    }

    public List<DirectoryShortDTO> getSubDirectories() {
        return subDirectories;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

}
