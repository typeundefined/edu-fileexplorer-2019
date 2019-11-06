package ru.amm.fileexplorer.server.entity;

import java.util.List;

public class DirectoryContentsFileListReplacer {
    public DirectoryContents replace(List<FileData> fileDataList, DirectoryContents directoryContents) {
        return new DirectoryContents( directoryContents.getDirectoryName( ), directoryContents.getParentDirectoryName( ), fileDataList );
    }
}
