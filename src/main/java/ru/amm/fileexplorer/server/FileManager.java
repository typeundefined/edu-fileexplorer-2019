package ru.amm.fileexplorer.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "filemanager")
public class FileManager {

    private MyFile pathToPublish;


    public MyFile getPathToPublish() {
        return pathToPublish;
    }

    public void setPathToPublish(MyFile pathToPublish) {
        this.pathToPublish = pathToPublish;
    }
}
