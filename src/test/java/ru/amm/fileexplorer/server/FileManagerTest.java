package ru.amm.fileexplorer.server;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileManagerTest {
    @Autowired
    private FileManager fileManager;

    private File root;
    private File[] files;

    @Before
    public void fillTest() throws IOException {
        files = new File[2];

        root = new File(fileManager.getCurrentPath());
        root.mkdir();

        files[0] = new File(root, "directory");
        files[0].mkdir();

        files[1] = new File(root, "text.txt");
        files[1].createNewFile();

        fileManager.updateChildern();
    }

    @Test
    public void propertyTest() {
        assertThat(fileManager.getCurrentPath(), is("C:\\webApp"));
    }

    @Test
    public void iteratorTest() {
        int i = 0;
        for (FilePath filePath : fileManager) {
            assertThat(filePath.getAbsolutePath(), is(files[i++].getAbsolutePath()));
        }
    }

    @Test
    public void moveTest() {
        down();
        up();
    }

    private void down() {
        for (FilePath filePath : fileManager) {
            if (fileManager.downDirectory(filePath)) {
                assertThat(fileManager.getCurrentPath(), is(files[0].getAbsolutePath()));
                return;
            }
        }
    }

    private void up() {
        String parentPath = getParent();
        while (fileManager.upDirectory()) {
            assertThat(fileManager.getCurrentPath(), is(parentPath));
            parentPath = getParent();
        }
        assertThat(root.getAbsolutePath(), is(fileManager.getCurrentPath()));
    }

    private String getParent() {
        int index = fileManager.getCurrentPath().lastIndexOf("\\");
        return fileManager.getCurrentPath().substring(0, index);
    }

    @After
    public void clearTest() throws IOException {
        FileUtils.deleteDirectory(root);
    }
}