package ru.amm.fileexplorer.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class FileManager {


    private MyFile root;
    //private String pathToPublish;

    public void setRoot(MyFile root) {
        this.root = root;
    }

//    public String getPathToPublish() {
//        return pathToPublish;
//    }

//    public void setPathToPublish(String pathToPublish) {
//        root = new MyFile(pathToPublish);
//    }

    public FileManager(MyFile root) {
        this.root = root;
    }

    public MyFile getRoot() {
        return root;
    }



}
