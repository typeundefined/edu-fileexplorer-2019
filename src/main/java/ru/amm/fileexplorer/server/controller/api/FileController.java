package ru.amm.fileexplorer.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import ru.amm.fileexplorer.server.data.DirectoryContents;
import ru.amm.fileexplorer.server.data.FileData;
import ru.amm.fileexplorer.server.data.FileType;
import ru.amm.fileexplorer.server.data.api.DirectoryDTO;
import ru.amm.fileexplorer.server.data.api.DirectoryShortDTO;
import ru.amm.fileexplorer.server.data.api.FileDTO;
import ru.amm.fileexplorer.server.service.FileExplorerService;
import ru.amm.fileexplorer.server.validator.RelativePath;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@ExposesResourceFor(FileDTO.class)
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileExplorerService service;

    @GetMapping("/**")
    @ResponseBody
    public ResponseEntity<Resource> download(HttpServletRequest request) {
        String path = (request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString().replace("/api/directory/", ""));
        FileData f = service.getFile(path);
        InputStreamResource fStream = new InputStreamResource(service.getFileStream(f));
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
}
