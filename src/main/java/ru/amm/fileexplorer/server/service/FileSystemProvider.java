package ru.amm.fileexplorer.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.data.FileData;
import ru.amm.fileexplorer.server.data.FileType;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.util.Optional.ofNullable;

@Service
public class FileSystemProvider {
    @Value("${pathToPublish}")
    private Path pathToPublish;

    public List<FileData> fillFileList(String relpath) {
        List<FileData> result = new ArrayList<>( );

        Path absPath = pathToPublish.resolve( relpath );
        try ( DirectoryStream<Path> stream = Files.newDirectoryStream( absPath ) ) {
            for (Path file : stream) {
                BasicFileAttributes attr = Files.readAttributes( file, BasicFileAttributes.class );
                FileData fileData = toFileData( file, attr, relpath, Files.probeContentType( file ) );
                result.add( fileData );
            }
        } catch ( IOException | DirectoryIteratorException e ) {
            throw new DirectoryAccessException( e );
        }
        return result;
    }

    public Path getPathToPublish() {
        return pathToPublish;
    }

    public Path getAbsolutePath(Path relPath) {
        return pathToPublish.resolve( relPath );
    }

    private FileData toFileData(Path file, BasicFileAttributes attr, String relpath, String mimeType) {
        FileData fileData = new FileData( );
        String name = file.getFileName( ).toString( );
        fileData.setName( name );
        fileData.setDirectory( attr.isDirectory( ) );
        fileData.setSize( attr.size( ) );
        fileData.setLastModifiedTime( new Date( attr.lastModifiedTime( ).toMillis( ) ) );
        if ( mimeType == null ) {
            fileData.setFileType( FileType.get( ) );
        } else {
            String[] m = mimeType.split( "/" );
            fileData.setFileType( FileType.get( m[0], m[1] ) );
        }
        fileData.setRelativePath( Path.of( relpath, name ).toString( ) );
        return fileData;
    }

    public String getParent(String relativePath) {
        Optional<String> path = ofNullable( Path.of( relativePath ) )
                .map( Path::getParent )
                .map( Path::toString );
        return path.orElse( "" );
    }

    public InputStream getFileStream(String relativePath) {
        try {
            Path absPath = pathToPublish.resolve( relativePath );
            return new FileInputStream( absPath.toFile( ) );
        } catch ( FileNotFoundException e ) {
            throw new DirectoryAccessException( e );
        }
    }

    public InputStream getDirectoryStream(String relativePath) {
        try {
            Path f = pathToPublish.resolve( relativePath );
            Path tempZip = Files.createTempFile( "tmpZip", null );
            try ( ZipOutputStream zos = new ZipOutputStream( new FileOutputStream( tempZip.toFile( ) ) ) ) {
                Files.walkFileTree( f, new SimpleFileVisitor<Path>( ) {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        zos.putNextEntry( new ZipEntry( f.relativize( file ).toString( ) ) );
                        Files.copy( file, zos );
                        zos.closeEntry( );
                        return FileVisitResult.CONTINUE;
                    }
                } );
                zos.close( );
                return new FileInputStream( tempZip.toFile( ) );
            } catch ( IOException ex ) {
                throw new DirectoryAccessException( ex );
            }
        } catch ( IOException e ) {
            throw new DirectoryAccessException( e );
        }
    }

    public FileData getFile(String relativePath) {
        try {
            Path f = pathToPublish.resolve( relativePath );
            BasicFileAttributes attr = Files.readAttributes( f, BasicFileAttributes.class );
            return toFileData( f, attr, relativePath.replace( f.getFileName( ).toString( ), "" ), Files.probeContentType( f ) );
        } catch ( IOException e ) {
            throw new DirectoryAccessException( e );
        }
    }

    public String getPathOfFolder(String path, String pathToPublish) {
        if ( path == null ) {
            path = "";
        }
        Path p = Paths.get( pathToPublish, path );
        return p.toString( );
    }
}
