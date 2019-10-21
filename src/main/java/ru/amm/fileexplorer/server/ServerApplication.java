package ru.amm.fileexplorer.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

public class ServerApplication {



	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(ServerApplication.class, args);
		FileManager fileManager = (FileManager) app.getBean("fileManager");
	}




}
