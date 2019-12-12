package ru.amm.fileexplorer.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;
import ru.amm.fileexplorer.server.controller.api.exception.BaseAPIException;
import ru.amm.fileexplorer.server.data.*;
import ru.amm.fileexplorer.server.data.api.DirectoryDTO;
import ru.amm.fileexplorer.server.data.api.DirectoryShortDTO;
import ru.amm.fileexplorer.server.data.api.FileDTO;
import ru.amm.fileexplorer.server.service.FileExplorerService;
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

@RestController
@ExposesResourceFor(DirectoryDTO.class)
@RequestMapping("/api/directory")
public class DirectoryController {
    @Autowired
    private FileExplorerService service;


    @GetMapping(value = "/**")
    public DirectoryDTO directorySearch(HttpServletRequest request,
                                        @RequestParam(value = "search", defaultValue = "", required = false) String name) {
        String relPath = (request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString().replace("/api/directory/", ""));
        FileMatcher matcher = name.equals("") ? new NoOpMatcher() : new NamePartialMatcher(name);
        return toDTO(service.getContentsFiltered(relPath, matcher));
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
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(path);

        dto.add(linkTo(DirectoryController.class).slash(path.replace("\\", "/")).withSelfRel());
        return dto;
    }

    private FileDTO toFileDTO(FileData fileData) {
        FileDTO dto = new FileDTO();
        dto.setFileType(fileData.getFileType());
        dto.setSize(fileData.getSize());
        dto.setLastModifiedTime(fileData.getLastModifiedTime());
        dto.setName(fileData.getName());

        String path = fileData.getRelativePath();
        dto.add(linkTo(FileController.class).slash(path.replace("\\", "/")).withSelfRel());
        return dto;
    }


}
