package ru.amm.fileexplorer.server.service.pojo;

import java.io.File;

public class MyFile {
    private boolean directory;
    private String name;
    private String path;

    public MyFile(File f) {
        this.directory = f.isDirectory();
        this.name = f.getName();
        this.path = f.getPath();
    }

    public boolean isDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
