package ru.amm.fileexplorer.server;

import ru.amm.fileexplorer.server.utils.Config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigTest {
    private Config config = new Config();

    @Test
    public void whenGetPathFromProperties() {
        String result = config.get("pathToPublish");
        String expected = "D:\\java";
        assertThat(result, is(expected));
    }
}
