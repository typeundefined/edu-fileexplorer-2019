package ru.amm.fileexplorer.server;

import ru.amm.fileexplorer.server.entity.FileStore;

import java.util.List;

public interface FileExplorer {

    List<FileStore> getFileList(String path);

}
