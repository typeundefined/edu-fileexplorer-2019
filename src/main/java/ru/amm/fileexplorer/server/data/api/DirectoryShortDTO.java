package ru.amm.fileexplorer.server.data.api;

import org.springframework.hateoas.RepresentationModel;

public class DirectoryShortDTO extends RepresentationModel<DirectoryDTO> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
