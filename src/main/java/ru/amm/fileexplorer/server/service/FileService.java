package ru.amm.fileexplorer.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.model.MyFile;
import ru.amm.fileexplorer.server.utils.FileUtils;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileService {

    @Value("${filemanager.path-to-publish}")
    private String rootDirectory;

    public List<MyFile> getAllFiles() {
        List<MyFile> files = new LinkedList<>();

        System.out.println(rootDirectory);

        Iterator iterator = FileUtils.fileIterator(rootDirectory);
        while (iterator.hasNext()) {
            File current_file = (File) iterator.next();
            String name = current_file.getName();
            String path = FileUtils.absoluteToRelativePath(current_file.getPath(), rootDirectory);
            files.add(new MyFile(name, path));
        }
        return files;
    }
}
