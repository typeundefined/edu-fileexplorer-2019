package ru.amm.fileexplorer.server.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.entity.DirectoryContents;
import ru.amm.fileexplorer.server.entity.FileData;
import ru.amm.fileexplorer.server.entity.FileMatcher;
import ru.amm.fileexplorer.server.entity.NoOpMatcher;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileExplorerService {
    public static final NoOpMatcher NO_OP_MATCHER = new NoOpMatcher();
    @Autowired
    private FileSystemProvider provider;

    private final static Logger LOG = LogManager.getLogger(FileExplorerService.class);

    public DirectoryContents getRootContents() {
        DirectoryContents contents = getDirectoryContents("", NO_OP_MATCHER);
        return contents;
    }

    public DirectoryContents getContentsFiltered(String relativePath, FileMatcher matcher) {
        return getDirectoryContents(relativePath, matcher);
    }

    public DirectoryContents getContentsFilteredAll(String relativePath, FileMatcher matcher) {
        ArrayList<FileData> content = new ArrayList<>();
        ArrayDeque<FileData> directories = getContents(relativePath).getFiles().stream()
                .filter(FileData::isDirectory).collect(Collectors.toCollection(ArrayDeque::new));
        while (!directories.isEmpty()){
            FileData f = directories.poll();
            List<FileData> dirFiles  = getContents(f.getRelativePath()).getFiles();
            directories.addAll(dirFiles.stream()
                    .filter(FileData::isDirectory)
                    .collect(Collectors.toList()));
            content.addAll(dirFiles.stream().filter(matcher::matches).collect(Collectors.toList()));
        }
        return new DirectoryContents("", "", content);
    }

    public DirectoryContents getContents(String relativePath) {
        return getDirectoryContents(relativePath, NO_OP_MATCHER);
    }

    private DirectoryContents getDirectoryContents(String relativePath, FileMatcher matcher) {
        List<FileData> list = provider.fillFileList(relativePath);
        List<FileData> filteredList = list.stream()
                .filter(t -> matcher.matches(t))
                .collect(Collectors.toList());
        String parentDir = provider.getParent(relativePath);
        return new DirectoryContents(relativePath, parentDir, filteredList);
    }

    public InputStream getFileStream(FileData f) {
        if (f.isDirectory()){
            return provider.getDirectoryStream(f.getRelativePath());
        }
        return provider.getFileStream(f.getRelativePath());
    }

    public FileData getFile(String relativePath){
        return provider.getFile(relativePath);
    }
}
