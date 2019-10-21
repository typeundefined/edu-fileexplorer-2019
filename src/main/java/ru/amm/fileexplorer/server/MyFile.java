package ru.amm.fileexplorer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;

import java.io.File;
import java.util.*;


class MyFile implements Iterable<MyFile> {
    private MyFile parentFile;
    private List<MyFile> childFiles;
    private String fileName;

    public MyFile(MyFile parentFile, String fileName) {
        this.parentFile = parentFile;
        this.fileName = fileName;
        parseChildren();
    }

    public MyFile(String absolutePath) {
        int delimiter = absolutePath.lastIndexOf("\\") + 1;
        fileName = absolutePath.substring(delimiter);
        parentFile = new MyFile(null, absolutePath.substring(0, delimiter - 1));
        parseChildren();
    }

    private void parseChildren() {
        //parent of initial file
        if (parentFile == null)
            return;

        String p = parentFile.getAbsolutePath() + "\\" + fileName;
        String[] children = new File(p).list();
        //has no more children
        if (children == null)
            return;

        //has children
        if (childFiles == null) {
            childFiles = new ArrayList<>();
        }
        for (String child : children) {
            childFiles.add(new MyFile(this, child));
        }
    }

    public String getFileName() {
        return fileName;
    }

    public MyFile getParentFile() {
        return parentFile;
    }

    public String getAbsolutePath() {
        if (parentFile == null)
            return fileName;
        return parentFile.getAbsolutePath() + "\\" + fileName;
    }

    @Override
    public Iterator<MyFile> iterator() {
        return new DFTiterator();
    }


    class BFTiterator implements Iterator<MyFile> {

        private Queue<MyFile> toVisit = new ArrayDeque<>(childFiles);

        @Override
        public boolean hasNext() {
            return !toVisit.isEmpty();
        }

        @Override
        public MyFile next() {
            MyFile f = toVisit.peek();
            if (f != null && f.childFiles != null)
                toVisit.addAll(f.childFiles);
            return toVisit.poll();
        }
    }

    class DFTiterator implements Iterator<MyFile> {

        private Deque<MyFile> toVisit = new ArrayDeque<>(childFiles);


        @Override
        public boolean hasNext() {
            return !toVisit.isEmpty();
        }

        @Override
        public MyFile next() {
            MyFile f = toVisit.pop();
            if (f != null && f.childFiles != null)
                f.childFiles.forEach(myFile -> toVisit.push(myFile));
            return f;
        }

    }


}






