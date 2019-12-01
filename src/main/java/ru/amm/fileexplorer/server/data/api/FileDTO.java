package ru.amm.fileexplorer.server.data.api;

import org.springframework.hateoas.RepresentationModel;
import ru.amm.fileexplorer.server.data.FileType;

import java.util.Date;

public class FileDTO extends RepresentationModel<FileDTO> {
    private String name;
    private Date lastModifiedTime;
    private long size;
    private FileType fileType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
