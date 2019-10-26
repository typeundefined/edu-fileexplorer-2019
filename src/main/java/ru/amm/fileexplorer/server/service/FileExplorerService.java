package ru.amm.fileexplorer.server.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.entity.DirectoryContents;
import ru.amm.fileexplorer.server.entity.FileData;
import ru.amm.fileexplorer.server.entity.FileMatcher;
import ru.amm.fileexplorer.server.entity.NoOpMatcher;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileExplorerService {
    public static final NoOpMatcher NO_OP_MATCHER = new NoOpMatcher();
    @Autowired
    private FileSystemProvider provider;

    private final static Logger LOG = LogManager.getLogger(FileExplorerService.class);

    public DirectoryContents getRootContents() {
        DirectoryContents contents = getDirectoryContents( "", NO_OP_MATCHER);
        return contents;
    }

    public DirectoryContents getContentsFiltered(String relativePath, FileMatcher matcher) {
        return getDirectoryContents(relativePath, matcher);
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
}
