package ru.amm.fileexplorer.server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FileManagerTest {

    @Autowired
    private FileManager fileManager;

    @Test
    void fileManagerPathToPublishIsCorrect(){
        assertEquals(fileManager.getRoot().getAbsolutePath(),"D:\\webm");
    }

    @Test
    void iteratorTest(){
       Iterator<MyFile> it = fileManager.getRoot().iterator();
       while (it.hasNext()){
           System.out.println(it.next().getAbsolutePath());
       }
    }

}