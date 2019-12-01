package ru.amm.fileexplorer.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.amm.fileexplorer.server.data.DirectoryContents;
import ru.amm.fileexplorer.server.data.FileData;
import ru.amm.fileexplorer.server.data.api.DirectoryDTO;
import ru.amm.fileexplorer.server.data.api.DirectoryShortDTO;
import ru.amm.fileexplorer.server.data.api.FileDTO;
import ru.amm.fileexplorer.server.service.FileExplorerService;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@ExposesResourceFor(DirectoryDTO.class)
@RequestMapping("/api/directory")
public class DirectoryController {
    @Autowired
    private FileExplorerService service;

    @GetMapping("{path}")
    @ResponseBody
    public DirectoryDTO index(@PathVariable String path) {
        DirectoryDTO dirContents;
        DirectoryContents data = service.getContents(path);
        return toDTO(data);
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
        dto.add(linkTo(methodOn(DirectoryController.class).index(path)).withSelfRel());
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
