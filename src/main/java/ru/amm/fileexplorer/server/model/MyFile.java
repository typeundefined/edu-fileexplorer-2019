package ru.amm.fileexplorer.server.model;

import java.io.File;

public class MyFile {
    private String name;
    private String path;

    public MyFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public MyFile(File file) {
        name = file.getName();
        path = file.getPath();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}