package ru.amm.fileexplorer.server;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.amm.fileexplorer.server.entity.FileStore;
import ru.amm.fileexplorer.server.service.FileExplorerService;
import ru.amm.fileexplorer.server.utils.Config;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileExplorerTest {
    Config config = new Config();
    String folderForTest;

    @Before
    public void initTest() throws IOException {
        String pathToPublish = config.get("pathToPublish");
        this.folderForTest = pathToPublish+
                 System.getProperty("file.separator") + "dirForTest";
        File dirTest = new File(this.folderForTest);
        dirTest.mkdir();

        new File(dirTest, "fReadme.txt").createNewFile();
        new File(dirTest, "mReadme.txt").createNewFile();
    }

    @Test
    public void whenCreateFilesAndGetFileList() throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(FileExplorerService.class, Config.class);

        FileExplorer fileExplorer = context.getBean("fileExplorerService", FileExplorerService.class);
        List<FileStore> fileList = fileExplorer.getFileList("dirForTest");

        assertThat(fileList.get(0).getName(), is("fReadme.txt"));
        assertThat(fileList.get(1).getName(), is("mReadme.txt"));
    }

    @After
    public void afterTest() throws IOException {
        FileUtils.deleteDirectory(new File(this.folderForTest));
    }
}
