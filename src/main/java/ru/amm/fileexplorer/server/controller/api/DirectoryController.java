package ru.amm.fileexplorer.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.amm.fileexplorer.server.controller.api.exception.BaseAPIException;
import ru.amm.fileexplorer.server.data.DirectoryContents;
import ru.amm.fileexplorer.server.data.FileData;
import ru.amm.fileexplorer.server.data.NamePartialMatcher;
import ru.amm.fileexplorer.server.data.api.DirectoryDTO;
import ru.amm.fileexplorer.server.data.api.DirectoryShortDTO;
import ru.amm.fileexplorer.server.data.api.FileDTO;
import ru.amm.fileexplorer.server.service.FileExplorerService;
import ru.amm.fileexplorer.server.util.RelativePathExtractor;
import ru.amm.fileexplorer.server.validator.RelativePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.servlet.HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE;

@RestController
@ExposesResourceFor(DirectoryDTO.class)
@RequestMapping("/api/directory")
public class DirectoryController {
    @Autowired
    private FileExplorerService service;
    private final RelativePathExtractor pathExtractor = new RelativePathExtractor("directory");

    // XXX Note that "path" path variable is only needed to build the HATEOAS links easily.
    // Physically we parse the path manually since it contains arbitrary amount of slashes.
    @GetMapping({"/{path:.+}", "/**"})
    public DirectoryDTO directory(
            @PathVariable(required = false, name = "path") String fakePath,
            HttpServletRequest request) {
        String path = getRequestPath(request);
        String relPath = pathExtractor.extract(path);
        DirectoryContents data = service.getContents(relPath);
        return toDTO(data);
    }

    private String getRequestPath(HttpServletRequest request) {
        return (String)
                    request.getAttribute(PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    }

    @GetMapping(value = {"/{path:.+}", "/**"}, params = "search")
    public DirectoryDTO directorySearch(@PathVariable(required = false, name = "path") String fakePath,
                                        @RequestParam("search") String name,
                                        HttpServletRequest request) {
        String path = getRequestPath(request);
        String relPath = pathExtractor.extract(path);
        return toDTO(service.getContentsFiltered(relPath, new NamePartialMatcher(name)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,
                       @RelativePath @RequestParam(value = "path", required = false) String path,
                       HttpServletResponse response
    ) {
        Path destPath = service.getAbsolutePath(Paths.get(path));
        for (MultipartFile uploadedFile : uploadingFiles) {
            File file = destPath.resolve(uploadedFile.getOriginalFilename()).toFile();
            try {
                uploadedFile.transferTo(file);
            } catch (IOException e) {
                throw new BaseAPIException("Failed to upload file " + uploadedFile.getOriginalFilename(), e);
            }
        }

    }

    private DirectoryDTO toDTO(DirectoryContents data) {
        DirectoryDTO dto = new DirectoryDTO();
        dto.setDirectoryName(data.getDirectoryName());
        dto.getFiles().addAll(
                data.getFiles().stream()
                        .filter(a -> !a.isDirectory())
                        .map(this::toFileDTO)
                        .collect(Collectors.toList()));
        dto.getSubDirectories().addAll(
                data.getFiles().stream()
                        .filter(a -> a.isDirectory())
                        .map(this::toShortDir)
                        .collect(Collectors.toList()));
        return dto;
    }

    private DirectoryShortDTO toShortDir(FileData fileData) {
        DirectoryShortDTO dto = new DirectoryShortDTO();
        String name = fileData.getName();
        dto.setName(name);
        String path = fileData.getRelativePath();
        dto.add(
                linkTo(methodOn(DirectoryController.class)
                        .directory(path, null))
                        .withSelfRel());
        return dto;
    }

    private FileDTO toFileDTO(FileData fileData) {
        FileDTO dto = new FileDTO();
        dto.setFileType(fileData.getFileType());
        dto.setSize(fileData.getSize());
        dto.setLastModifiedTime(fileData.getLastModifiedTime());
        dto.setName(fileData.getName());

        String path = fileData.getRelativePath();
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(FileController.class).download(path));
        dto.add(linkBuilder.withSelfRel());
        return dto;
    }


}
