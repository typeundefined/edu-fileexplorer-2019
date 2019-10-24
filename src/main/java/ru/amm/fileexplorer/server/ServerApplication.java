package ru.amm.fileexplorer.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.amm.fileexplorer.server.service.FileService;

@SpringBootApplication
@EnableConfigurationProperties(FileService.class)
public class ServerApplication {
    public static void main(String[] args) {
     SpringApplication.run(ServerApplication.class, args);
    }
}
