package ru.amm.fileexplorer.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.amm.fileexplorer.server.model.MyFile;
import ru.amm.fileexplorer.server.service.FileService;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/files/get_all")
    public List<MyFile> getAll() {
        return fileService.getAllFiles();
    }
}
