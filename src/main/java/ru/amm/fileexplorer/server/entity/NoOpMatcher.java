package ru.amm.fileexplorer.server.entity;

public class NoOpMatcher implements FileMatcher {
    @Override
    public boolean matches(FileData fileData) {
        return true;
    }
}
