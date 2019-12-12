package ru.amm.fileexplorer.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.amm.fileexplorer.server.config.MainConfiguration;
import ru.amm.fileexplorer.server.config.SecurityConfig;
import ru.amm.fileexplorer.server.data.DirectoryContents;
import ru.amm.fileexplorer.server.data.FileData;
import ru.amm.fileexplorer.server.data.FileType;
import ru.amm.fileexplorer.server.service.FileExplorerService;
import ru.amm.fileexplorer.server.service.FileSystemProvider;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileExplorerServiceTest {
    @MockBean
    private FileSystemProvider provider;

    @Autowired
    private FileExplorerService service;


    @Test
    public void getDirectoryReadsDirectories() {
        when(provider.getParent("a/folder")).thenReturn("a");

        doReturn(Arrays.asList(
                file("f1", "a/folder/f1"),
                file("f2", "a/folder/f2")))
                .when(provider).fillFileList("a/folder");

        DirectoryContents contents = service.getContents("a/folder");
        assertEquals("a", contents.getParentDirectoryName());
        assertEquals(2, contents.getFiles().size());
        assertEquals("a/folder", contents.getDirectoryName());
    }

    private FileData file(String name, String relPath) {
        FileData result = new FileData();
        result.setFileType(FileType.TEXT);
        result.setDirectory(false);
        result.setLastModifiedTime(new Date());
        result.setName(name);
        result.setRelativePath(relPath);
        return result;
    }
}
