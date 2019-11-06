package ru.amm.fileexplorer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.amm.fileexplorer.server.entity.DirectoryContents;
import ru.amm.fileexplorer.server.service.FileExplorerService;
import ru.amm.fileexplorer.server.service.FileSystemProvider;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private FileExplorerService explorerService;
    @Value("${pathToPublish}")
    private String pathToPublish;
    private String fullPath;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(name = "path", required = false) String path, Model model) {
        FileSystemProvider fileSystemProvider = new FileSystemProvider();
        fullPath = fileSystemProvider.getPathOfFolder(path,pathToPublish);
        File file = new File(fullPath);
        model.addAttribute("files", file.listFiles());
        DirectoryContents dirContents;
        if (path == null) {
            dirContents = explorerService.getRootContents();
        } else {
            dirContents = explorerService.getContents(path);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("directory", dirContents);
        return new ModelAndView("index", data);
    }

        @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,@RequestParam(name = "path", required = false)  String path) throws IOException {
        for(MultipartFile uploadedFile : uploadingFiles) {
            File file = new File(fullPath +"/" + uploadedFile.getOriginalFilename());
            uploadedFile.transferTo(file);
        }
            DirectoryContents dirContents;
            if (path == null) {
                dirContents = explorerService.getRootContents();
            } else {
                dirContents = explorerService.getContents(path);
            }
            Map<String, Object> data = new HashMap<>();
            data.put("directory", dirContents);
            ModelAndView model = new ModelAndView("index",data);
        return model;
    }

    @RequestMapping(path = "/testcss", method = RequestMethod.GET)
    public ModelAndView testcss() {
        Map<String, String> data = new HashMap<>();
        data.put("WhatTest", "CSS");
        return new ModelAndView("testcss", data);
    }
}
