package ru.amm.fileexplorer.server;

public class FilePath {
    private String fileName;
    private String absolutePath;
    private boolean directory;

    public FilePath(String absolutePath, boolean isDirectory) {
        this.absolutePath = absolutePath;
        this.fileName = absolutePath.substring(absolutePath.lastIndexOf("\\") + 1);
        this.directory = isDirectory;
    }


    public String getFileName() {
        return fileName;
    }
    public String getAbsolutePath() {
        return absolutePath;
    }
    public boolean isDirectory() {
        return directory;
    }
}
