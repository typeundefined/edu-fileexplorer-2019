package ru.amm.fileexplorer.server.data;

public class NoOpMatcher implements FileMatcher {
    @Override
    public boolean matches(FileData fileData) {
        return true;
    }
}
