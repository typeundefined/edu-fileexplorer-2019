package ru.amm.fileexplorer.server;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.amm.fileexplorer.server.entity.*;
import ru.amm.fileexplorer.server.service.FileExplorerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class IndexController {
    @Autowired
    private FileExplorerService explorerService;

    @Value("${pathToPublish}") String publishPath;

    @RequestMapping(path = "/directories", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(name = "path", required = false) String path, @RequestParam(name="sort",required = false) String sortMethod) {
        DirectoryContents dirContents;
        if(sortMethod==null)sortMethod="DEFAULT";
        if (path == null|| path.contains( "../" )) {
            dirContents = explorerService.getRootContents(SortMethod.valueOf( sortMethod ));
        } else {
            dirContents = explorerService.getContents(path,SortMethod.valueOf( sortMethod ));
        }

        Map<String, Object> data = new HashMap<>();
        data.put("directory", dirContents);
        return new ModelAndView("index", data);

    }
    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ModelAndView searchResults(@RequestParam(name = "path") String path, @RequestParam(name="search") String pattern) {
        DirectoryContents dirContents;
       SortMethod sortMethod = SortMethod.DEFAULT;
        if (path == null|| path.contains( "../" )) {
            dirContents = explorerService.getRootContents( sortMethod );
        } else {
            dirContents = explorerService.getContents(path,sortMethod );
        }
        FileMatcher fileMatcher = new NameMatcher(pattern);
       List<FileData> filteredList= dirContents.getFiles().stream().filter( file->fileMatcher.matches(file)).collect( Collectors.toList());
       dirContents=new DirectoryContentsRefactorer().refactor( filteredList,dirContents );
       Map<String, Object> data = new HashMap<>();
        data.put("directory", dirContents);
        return new ModelAndView("index", data);

    }

    @PostMapping(path = "/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@RequestParam("path") String pathToUpload) throws URISyntaxException {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/error";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(publishPath+"\\"+pathToUpload+'\\'+file.getOriginalFilename());
            System.out.println(path );
            Files.write(path, bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/directories/?path="+ pathToUpload.replace("\\" ,"%5C" )+"&sort=DEFAULT";
    }

   @RequestMapping(path = "/", method = RequestMethod.GET)
    public String redirectExample(HttpServletRequest request) {
        //request.getScheme() - if you don't know where was the request sent: http, https, ftp..
        return "redirect:" + request.getScheme() +":/directories/?path=&sort=DEFAULT";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getFile(
            @RequestParam("file") String fileName,

            HttpServletResponse response) {
        try {
            // get your file as InputStream
            InputStream is = new FileInputStream( publishPath+"/"+fileName );
            // copy it to response's OutputStream
            IOUtils.copy( is,response.getOutputStream() );
            response.flushBuffer();
        } catch ( IOException ex) {
           ex.printStackTrace();
        }

    }

    @RequestMapping(path = "/testcss", method = RequestMethod.GET)
    public ModelAndView testcss() {
        Map<String, String> data = new HashMap<>();
        data.put("WhatTest", "CSS");
        return new ModelAndView("testcss", data);
    }

}
