package ru.amm.fileexplorer.server.data;

public class ParentFolder {
    private String name;
    private String path;

    public ParentFolder(String name, String path) {
        this.name = name;
        this.path = path;
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
        return "ParentFolder{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
