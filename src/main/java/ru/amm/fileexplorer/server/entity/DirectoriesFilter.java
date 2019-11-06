package ru.amm.fileexplorer.server.entity;

import java.util.List;
import java.util.stream.Collectors;

public final class DirectoriesFilter {

    public DirectoryContents filter(String pattern, DirectoryContents dirContents) {
        FileMatcher fileMatcher = new NameMatcher( pattern );
        List<FileData> filteredList = dirContents.getFiles( ).stream( ).filter( file -> fileMatcher.matches( file ) ).collect( Collectors.toList( ) );
        return new DirectoryContentsFileListReplacer( ).replace( filteredList, dirContents );
    }
}