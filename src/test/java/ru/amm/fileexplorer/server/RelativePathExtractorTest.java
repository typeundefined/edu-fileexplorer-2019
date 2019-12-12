package ru.amm.fileexplorer.server;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.amm.fileexplorer.server.util.RelativePathExtractor;

public class RelativePathExtractorTest {
    @Test
    public void prefixIsCut() {
        RelativePathExtractor ex = new RelativePathExtractor("directory");
        String input = "/api/directory/something/else/";
        Assert.assertEquals("something/else/", ex.extract(input));
    }

    @Test
    public void trailingSlashesIgnored() {
        RelativePathExtractor ex = new RelativePathExtractor("directory");
        String input = "/api/directory///something/else/";
        Assert.assertEquals("something/else/", ex.extract(input));
    }

    @Test
    public void noSuffixMeansEmptyString() {
        RelativePathExtractor ex = new RelativePathExtractor("directory");
        String input = "/api/directory/";
        Assert.assertEquals("", ex.extract(input));
    }

    @Test
    public void urlEncodedValuesEncoded() {
        RelativePathExtractor ex = new RelativePathExtractor("directory");
        String input = "/api/directory/%D0%92%D1%81%D0%B5%D0%BC%20%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82/";
        Assert.assertEquals("Всем привет/", ex.extract(input));
    }
}
