package ru.amm.fileexplorer.server.entity;

import java.util.List;

public class DirectoryContentsRefactorer {
    public DirectoryContents refactor(List<FileData> fileDataList, DirectoryContents directoryContents){
        return new DirectoryContents( directoryContents.getDirectoryName(),directoryContents.getParentDirectoryName(),fileDataList );
    }
}
