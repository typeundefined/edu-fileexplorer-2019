package ru.amm.fileexplorer.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.amm.fileexplorer.server.data.DirectoryContents;
import ru.amm.fileexplorer.server.data.FileData;
import ru.amm.fileexplorer.server.data.FileType;
import ru.amm.fileexplorer.server.data.NamePartialMatcher;
import ru.amm.fileexplorer.server.service.FileExplorerService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class IndexController {
    @Autowired
    private FileExplorerService explorerService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(name = "path", required = false) String path) {
        DirectoryContents dirContents;
        System.out.println( path );
        if ( path == null || "/".equals( path ) ) {
            dirContents = explorerService.getRootContents( );
        } else {
            dirContents = explorerService.getContents( path );
        }
        Map<String, Object> data = new HashMap<>( );
        data.put( "directory", dirContents );
        return new ModelAndView( "index", data );
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(name = "search") String search,
                               @RequestParam(name = "path") String path) {
        DirectoryContents dirContents;
        dirContents = explorerService.getContentsFiltered( path, new NamePartialMatcher( search ) );
        Map<String, Object> data = new HashMap<>( );
        data.put( "directory", dirContents );
        return new ModelAndView( "filesView", data );
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }


    @RequestMapping(path = "/searchAll", method = RequestMethod.GET)
    public ModelAndView searchAll(@RequestParam(name = "search") String search,
                                  @RequestParam(name = "path") String path) {
        DirectoryContents dirContents;
        if ( !search.equals( "" ) )
            dirContents = explorerService.getContentsFilteredAll( path, new NamePartialMatcher( search ) );
        else dirContents = explorerService.getContents( path );
        Map<String, Object> data = new HashMap<>( );
        data.put( "directory", dirContents );
        return new ModelAndView( "filesView", data );
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@RequestParam(name = "file") String file) {
        FileData f = explorerService.getFile( file );
        InputStreamResource fStream = new InputStreamResource( explorerService.getFileStream( f ) );
        String fName = f.getName( );
        FileType fType = f.getFileType( );
        if ( f.isDirectory( ) ) {
            fName += ".zip";
            f.getFileType( ).setExtension( "zip" );
        }
        ContentDisposition content = ContentDisposition.builder( "attachment" )
                .filename( fName, StandardCharsets.UTF_8 )
                .build( );
        return ResponseEntity.ok( )
                .header( HttpHeaders.CONTENT_DISPOSITION, content.toString( ) )
                .contentType( new MediaType( fType.getBase( ), fType.getExtension( ) ) )
                .body( fStream );

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String uploadingPost(
            @RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,
            @RequestParam(name = "path", required = false) Path path,
            RedirectAttributes attributes)
            throws IOException {
        Path destPath;
        if ( path == null )
            destPath = explorerService.getRootPath( );
        else
             destPath = explorerService.getAbsolutePath(path);

        for (MultipartFile uploadedFile : uploadingFiles) {
            File file = destPath.resolve( uploadedFile.getOriginalFilename( ) ).toFile( );
            uploadedFile.transferTo( file );
        }


        attributes.addAttribute( "path", String.valueOf( path ));
        return "redirect:/";
    }

    @RequestMapping(value = "/createdir", method = RequestMethod.POST)
    public String createDir(
            @RequestParam("directory") String directory,
            @RequestParam(name = "path", required = false) Optional<String> oPath,
            RedirectAttributes attributes)
            throws IOException {
        String directoryPath;
        String path = oPath.orElse( "" );
        if ( path == null ) directoryPath = explorerService.getRootPath( ).resolve( directory ).toString( );
        else directoryPath = explorerService.getAbsolutePath( Paths.get( path ) ).resolve( directory ).toString( );
        new File( directoryPath ).mkdir( );
        attributes.addAttribute( "path", path );
        return "redirect:/";
    }

    @RequestMapping(path = "/testcss", method = RequestMethod.GET)
    public ModelAndView testcss() {
        Map<String, String> data = new HashMap<>( );
        data.put( "WhatTest", "CSS" );
        return new ModelAndView( "testcss", data );
    }
}
