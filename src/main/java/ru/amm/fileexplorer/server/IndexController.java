package ru.amm.fileexplorer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.amm.fileexplorer.server.entity.DirectoryContents;
import ru.amm.fileexplorer.server.entity.FileData;
import ru.amm.fileexplorer.server.entity.FileType;
import ru.amm.fileexplorer.server.entity.NamePartialMatcher;
import ru.amm.fileexplorer.server.service.FileExplorerService;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private FileExplorerService explorerService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(name = "path", required = false) String path) {
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

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(name = "search") String search,
                               @RequestParam(name = "path") String path) {
        DirectoryContents dirContents;
        dirContents = explorerService.getContentsFiltered(path, new NamePartialMatcher(search));
        Map<String, Object> data = new HashMap<>();
        data.put("directory", dirContents);
        return new ModelAndView("filesView", data);
    }

    @RequestMapping(path = "/searchAll", method = RequestMethod.GET)
    public ModelAndView searchAll(@RequestParam(name = "search") String search,
                                  @RequestParam(name = "path") String path) {
        DirectoryContents dirContents;
        if (!search.equals(""))
            dirContents = explorerService.getContentsFilteredAll(path, new NamePartialMatcher(search));
        else dirContents = explorerService.getContents(path);
        Map<String, Object> data = new HashMap<>();
        data.put("directory", dirContents);
        return new ModelAndView("filesView", data);
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@RequestParam(name = "file") String file) {
        FileData f = explorerService.getFile(file);
        InputStreamResource fStream = new InputStreamResource(explorerService.getFileStream(f));
        String fName = f.getName();
        FileType fType = f.getFileType();
        if (f.isDirectory()) {
            fName += ".zip";
            f.getFileType().setExtension("zip");
        }
        ContentDisposition content = ContentDisposition.builder("attachment")
                .filename(fName, StandardCharsets.UTF_8)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, content.toString())
                .contentType(new MediaType(fType.getBase(), fType.getExtension()))
                .body(fStream);

    }

    @RequestMapping(path = "/testcss", method = RequestMethod.GET)
    public ModelAndView testcss() {
        Map<String, String> data = new HashMap<>();
        data.put("WhatTest", "CSS");
        return new ModelAndView("testcss", data);
    }
}
