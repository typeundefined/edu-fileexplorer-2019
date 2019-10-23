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

import java.io.File;
import java.io.IOException;
import java.util.List;


public class FileExplorerTest {

    private String folderForTest;
    private FileExplorerService fileExplorer;

    @Before
    public void initTest() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(FileExplorerService.class);

        fileExplorer = context.getBean("fileExplorerService", FileExplorerService.class);
        folderForTest = fileExplorer.getPathToPublish() +
                System.getProperty("file.separator") + "dirForTest";
    }

    @Test
    public void whenCreateFilesAndGetFileList() throws IOException {
        File dirTest = new File(this.folderForTest);
        dirTest.mkdir();

        new File(dirTest, "fReadme.txt").createNewFile();
        new File(dirTest, "mReadme.txt").createNewFile();

        List<FileStore> fileList = fileExplorer.getFileList("dirForTest");

        assertThat(fileList.get(0).getName(), is("fReadme.txt"));
        assertThat(fileList.get(1).getName(), is("mReadme.txt"));
    }

    @Test
    public void whenGetPathFromProperties() {
        String result = fileExplorer.getPathToPublish();
        String expected = "D:\\java";
        assertThat(result, is(expected));
    }

    @After
    public void afterTest() throws IOException {
        FileUtils.deleteDirectory(new File(this.folderForTest));
    }
}
