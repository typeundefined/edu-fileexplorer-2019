package ru.amm.fileexplorer.server;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Iterator;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MyFileTest {
    @Test
    void testChildren() {
        Iterator<MyFile> it = new MyFile("D:\\webm").iterator();
        while (it.hasNext()){
            System.out.println(it.next().getAbsolutePath());
        }
    }

}