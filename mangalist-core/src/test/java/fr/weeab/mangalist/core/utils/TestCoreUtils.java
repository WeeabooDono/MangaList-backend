package fr.weeab.mangalist.core.utils;

import java.util.StringJoiner;

public class TestCoreUtils {

    public static String joinWithSlash(String... parts) {
        StringJoiner path = new StringJoiner("/");
        for (String part: parts) {
            path.add(part);
        }
        return path.toString();
    }
}
