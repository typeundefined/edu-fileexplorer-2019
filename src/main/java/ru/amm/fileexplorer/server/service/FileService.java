package ru.amm.fileexplorer.server.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.service.pojo.MyFile;

import javax.naming.NoPermissionException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@ConfigurationProperties(prefix = "fileservice")
public class FileService {

    private String pathToPublish;

    public String getPathToPublish() {
        return pathToPublish;
    }

    public void setPathToPublish(String pathToPublish) {
        this.pathToPublish = pathToPublish;
    }

    public List<MyFile> retrieveFilesFrom(String s) throws NoPermissionException {
        if (s.equals("")) s = pathToPublish;
        if (s.startsWith(pathToPublish)) {
            File[] children = new File(s).listFiles();
            List<MyFile> res = new ArrayList<>();
            if (children != null)
                for (File child : children) {
                    res.add(new MyFile(child));
                }
            return res;
        } else {
            throw new NoPermissionException("You do not have access to this directory");
        }
    }
}
