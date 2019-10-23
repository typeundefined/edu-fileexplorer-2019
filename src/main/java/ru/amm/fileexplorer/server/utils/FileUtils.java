package ru.amm.fileexplorer.server.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

public class FileUtils {
    public static Iterator<File> fileIterator(String path) {
        return new Iterator<>() {
            Stack<File> stack = new Stack<>();

            {
                File dir = new File(path);
                if (!dir.exists() || !dir.isDirectory())
                    throw new IllegalArgumentException("wrong path to directory");

                //добавляем все файлы из директории в стек
                stack.addAll(Arrays.asList(Objects.requireNonNull(dir.listFiles())));
            }

            @Override
            public boolean hasNext() {
                update();
                return !stack.isEmpty();
            }

            @Override
            public File next() {
                update();
                return stack.pop();
            }

            //обновляем стек так, чтобы на его вершине лежал файл, а не директория
            private void update() {
                while (stack.size() > 0 && stack.peek().isDirectory()) {
                    stack.addAll(Arrays.asList(Objects.requireNonNull(stack.pop().listFiles())));
                }
            }
        };
    }

    public static String absoluteToRelativePath(String path, String base) {
        return new File(base).toURI().relativize(new File(path).toURI()).getPath();
    }
}
