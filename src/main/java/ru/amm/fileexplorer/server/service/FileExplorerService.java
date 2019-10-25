package ru.amm.fileexplorer.server.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.entity.DirectoryContents;
import ru.amm.fileexplorer.server.entity.FileData;

import java.util.List;

@Service
public class FileExplorerService {
    @Autowired
    private FileSystemProvider provider;

    private final static Logger LOG = LogManager.getLogger(FileExplorerService.class);

    public DirectoryContents getRootContents() {
        DirectoryContents contents = getDirectoryContents( "");
        return contents;
    }

    public DirectoryContents getContents(String relativePath) {
        return getDirectoryContents(relativePath);
    }

    private DirectoryContents getDirectoryContents(String relativePath) {
        List<FileData> list = provider.fillFileList(relativePath);
        String parentDir = provider.getParent(relativePath);

        return new DirectoryContents(relativePath, parentDir, list);
    }
}
