package ru.amm.fileexplorer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.amm.fileexplorer.server.entity.DirectoryContents;
import ru.amm.fileexplorer.server.entity.NameMatcher;
import ru.amm.fileexplorer.server.entity.PartNameMatcher;
import ru.amm.fileexplorer.server.service.FileExplorerService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private FileExplorerService explorerService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(name = "path", required = false, defaultValue = "") String path,
                              @RequestParam(name = "quickSearch", required = false, defaultValue = "") String quickSearchPattern) {
        DirectoryContents dirContents;
        if (quickSearchPattern.isEmpty()) {
            dirContents = explorerService.getContents(path);
        } else {
            dirContents = explorerService.getContentsFiltered(path, new PartNameMatcher(quickSearchPattern));
        }
        Map<String, Object> data = new HashMap<>();
        data.put("directory", dirContents);
        data.put("quickSearchPattern", quickSearchPattern);
        return new ModelAndView("index", data);
    }

    @RequestMapping(path = "/testcss", method = RequestMethod.GET)
    public ModelAndView testcss() {
        Map<String, String> data = new HashMap<>();
        data.put("WhatTest", "CSS");
        return new ModelAndView("testcss", data);
    }
}
