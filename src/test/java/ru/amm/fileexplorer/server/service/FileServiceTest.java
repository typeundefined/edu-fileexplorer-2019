package ru.amm.fileexplorer.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.amm.fileexplorer.server.service.FileService;

import javax.naming.NoPermissionException;


@SpringBootTest
@RunWith(SpringRunner.class)
public class FileServiceTest {

    @Autowired
    FileService fileService;

    @Test
    public void test1() {
        try {
            fileService.retrieveFilesFrom("D:\\webm");

        } catch (NoPermissionException e) {
            e.printStackTrace();
        }
    }
}