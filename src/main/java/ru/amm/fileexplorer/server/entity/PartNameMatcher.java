package ru.amm.fileexplorer.server.entity;

public class PartNameMatcher implements FileMatcher{
    private final String pattern;

    public PartNameMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(FileData fileData) {
        return fileData.getName().toLowerCase().contains(pattern.toLowerCase());
    }
}
