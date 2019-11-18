package ru.amm.fileexplorer.server.data;

public class NameMatcher implements FileMatcher {
    private final String pattern;

    public NameMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(FileData fileData) {
        return fileData.getName().equalsIgnoreCase(pattern);
    }
}
