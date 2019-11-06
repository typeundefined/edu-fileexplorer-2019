package ru.amm.fileexplorer.server.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileSystemUploadService implements UploadService {
    private final Path path;

    public FileSystemUploadService(Path path) {
        this.path = path;
    }

    @Override
    public void upload(MultipartFile file) {
        try {
            if ( file.isEmpty( ) ) {
                throw new IOException( "Failed to store empty file " );
            }

            try ( InputStream inputStream = file.getInputStream( ) ) {
                Files.copy( inputStream, this.path.resolve( file.getOriginalFilename( ) ),
                        StandardCopyOption.REPLACE_EXISTING );
            }
        } catch ( IOException e ) {
            e.printStackTrace( );
        }


    }
}
