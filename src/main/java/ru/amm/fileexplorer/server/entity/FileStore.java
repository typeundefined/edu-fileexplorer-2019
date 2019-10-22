package ru.amm.fileexplorer.server.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class FileStore implements Serializable {

    private static final long serialVersionUID = 34332242345L;
    private String name;
    private Date lastModifiedTime;
    private long size;
    private boolean isDirectory;

    public FileStore(String name, Date lastModifiedTime, long size, boolean isDirectory) {
        this.name = name;
        this.lastModifiedTime = lastModifiedTime;
        this.size = size;
        this.isDirectory = isDirectory;
    }

    public FileStore() {
    }

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

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileStore fileStore = (FileStore) o;
        return size == fileStore.size &&
                isDirectory == fileStore.isDirectory &&
                name.equals(fileStore.name) &&
                lastModifiedTime.equals(fileStore.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastModifiedTime, size, isDirectory);
    }
}
