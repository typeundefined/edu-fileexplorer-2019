package ru.amm.fileexplorer.server.entity;

public interface FileMatcher {
    boolean matches(FileData fileData);
}
