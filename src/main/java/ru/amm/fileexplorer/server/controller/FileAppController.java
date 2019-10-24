package ru.amm.fileexplorer.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.amm.fileexplorer.server.service.FileService;
import ru.amm.fileexplorer.server.service.pojo.MyFile;

import javax.naming.NoPermissionException;
import java.util.List;

@RestController
public class FileAppController {

    private final FileService fileService;

    public FileAppController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping("/fileService")
    public List<MyFile> getFiles(@RequestParam(value = "path",required = false,defaultValue ="")
                                             String path)  {
        System.out.println(path);
        try {
            return fileService.retrieveFilesFrom(path);
        }catch (NoPermissionException e){
            return null;
        }
    }
}
