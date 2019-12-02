package ru.amm.fileexplorer.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@ExposesResourceFor(FileDTO.class)
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileExplorerService service;

    @GetMapping("{path}")
    @ResponseBody
    public FileDTO download(@PathVariable String path) {
        throw new RuntimeException("Implement me!");
    }
}
