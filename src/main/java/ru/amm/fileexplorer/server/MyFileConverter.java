package ru.amm.fileexplorer.server;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@ConfigurationPropertiesBinding
public class MyFileConverter implements Converter<String, MyFile> {
    @Override
    public MyFile convert(String s) {
        return new MyFile(s);
    }
}
