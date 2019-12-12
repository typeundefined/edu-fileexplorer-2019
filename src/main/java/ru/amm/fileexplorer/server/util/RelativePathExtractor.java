package ru.amm.fileexplorer.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelativePathExtractor {
    private final Pattern apiRoot;

    public RelativePathExtractor(String apiResourceName) {
        String patternStr = getPatternString(apiResourceName);
        apiRoot = Pattern.compile(patternStr);
    }

    public String extract(String rawString) {
        String relPath = "";

        Matcher matcher = apiRoot.matcher(rawString);
        if (matcher.matches()) {
            relPath = matcher.group(1);
        }
        return relPath;
    }

    private String getPatternString(String apiResourceName) {
        String sanitized = Pattern.quote(apiResourceName);
        return "^/api/" + sanitized + "/+(.*)$";
    }
}
