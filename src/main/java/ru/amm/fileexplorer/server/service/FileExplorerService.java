package ru.amm.fileexplorer.server.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.entity.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileExplorerService {
    public static final NoOpMatcher NO_OP_MATCHER = new NoOpMatcher( );
    @Autowired
    private FileSystemProvider provider;

    private final static Logger LOG = LogManager.getLogger( FileExplorerService.class );

    public DirectoryContents getRootContents(SortMethod sortMethod) {
        DirectoryContents contents = getDirectoryContents( "", NO_OP_MATCHER, sortMethod );
        return contents;
    }

    public DirectoryContents getContentsFiltered(String relativePath, FileMatcher matcher, SortMethod sortMethod) {
        return getDirectoryContents( relativePath, matcher, sortMethod );
    }

    public DirectoryContents getContents(String relativePath, SortMethod sortMethod) {
        return getDirectoryContents( relativePath, NO_OP_MATCHER, sortMethod );
    }

    private DirectoryContents getDirectoryContents(String relativePath, FileMatcher matcher, SortMethod sortMethod) {
        List<FileData> list = provider.fillFileList( relativePath );
        List<FileData> filteredList = list.stream( )
                .filter( t -> matcher.matches( t ) )
                .collect( Collectors.toList( ) );
        String parentDir = provider.getParent( relativePath );
        filteredList = getSortedlist( filteredList, sortMethod );
        return new DirectoryContents( relativePath, parentDir, filteredList );
    }

    private List<FileData> getSortedlist(List<FileData> fileList, SortMethod sortMethod) {
        switch (sortMethod) {
            case DATE:
                fileList.sort( Comparator.comparing( FileData::getLastModifiedTime ) );
                break;
            case NAME:
                fileList.sort( Comparator.comparing( FileData::getName ) );
                break;
            case SIZE:
                fileList.sort( Comparator.comparing( FileData::getSize ) );
                break;
            default:
                break;

        }
        return fileList;
    }
}
