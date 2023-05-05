package com.lib.util;

import java.util.UUID;

public class LibraryUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
