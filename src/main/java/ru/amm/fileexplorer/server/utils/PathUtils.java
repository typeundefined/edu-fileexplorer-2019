package ru.amm.fileexplorer.server.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {
    public static String getParentDirectory(String path) {
        Path p = Paths.get(path);
        return p.getParent() == null ? null : p.getParent().toString();
    }
}
