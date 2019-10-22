package ru.amm.fileexplorer.server;

import ru.amm.fileexplorer.server.entity.FileStore;

import java.io.IOException;
import java.util.List;

public interface FileExplorer {

    public List<FileStore> getFileList(String path) throws IOException;
}
