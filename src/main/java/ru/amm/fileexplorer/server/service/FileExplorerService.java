package ru.amm.fileexplorer.server.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.data.*;
import ru.amm.fileexplorer.server.validator.RelativePath;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.*;
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

    public Path getAbsolutePath(Path relativePath) {
        return provider.getAbsolutePath(relativePath);
    }

    public DirectoryContents getContentsFiltered(String relativePath, FileMatcher matcher) {
        return getDirectoryContents(relativePath, matcher);
    }

    public DirectoryContents getContentsFilteredAll(String relativePath, FileMatcher matcher) {
        ArrayList<FileData> content = new ArrayList<>();
        ArrayDeque<FileData> directories = getContents(relativePath).getFiles().stream()
                .filter(FileData::isDirectory).collect(Collectors.toCollection(ArrayDeque::new));
        while (!directories.isEmpty()) {
            FileData f = directories.poll();
            List<FileData> dirFiles = getContents(f.getRelativePath()).getFiles();
            directories.addAll(dirFiles.stream()
                    .filter(FileData::isDirectory)
                    .collect(Collectors.toList()));
            content.addAll(dirFiles.stream().filter(matcher::matches).collect(Collectors.toList()));
        }
        return getDirectoryContents("", content, "");
    }

    public DirectoryContents getContents(@RelativePath String relativePath) {
        return getDirectoryContents(relativePath, NO_OP_MATCHER);
    }

    private DirectoryContents getDirectoryContents(String relativePath, FileMatcher matcher) {
        List<FileData> list = provider.fillFileList(relativePath);
        List<FileData> filteredList = list.stream()
                .filter(t -> matcher.matches(t))
                .collect(Collectors.toList());
        String parentDir = provider.getParent(relativePath);

        return FileExplorerService.this.getDirectoryContents(relativePath, filteredList, parentDir);
    }

    private DirectoryContents getDirectoryContents(String relativePath, List<FileData> filteredList, String parentDir) {

        DirectoryContents dc = new DirectoryContents(relativePath, parentDir, filteredList);
        dc.getParentFolders().addAll(getParentFolders(relativePath));
        return dc;
    }

    private List<ParentFolder> getParentFolders(String relativePath) {
        LinkedList<ParentFolder> parents = new LinkedList<>();
        Path path = Path.of(relativePath);

        while (path != null && path.getNameCount() > 0) {
            parents.addFirst(new ParentFolder(path.getFileName().toString(),
                    path.toString()));
            path = path.getParent();
        }
        parents.addFirst(new ParentFolder("/", "/"));
        return parents;
    }

    public InputStream getFileStream(FileData f) {
        if (f.isDirectory()) {
            return provider.getDirectoryStream(f.getRelativePath());
        }
        return provider.getFileStream(f.getRelativePath());
    }

    public FileData getFile(@RelativePath  String relativePath) {
        return provider.getFile(relativePath);
    }
}
