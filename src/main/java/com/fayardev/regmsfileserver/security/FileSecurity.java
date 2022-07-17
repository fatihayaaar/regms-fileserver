package com.fayardev.regmsfileserver.security;

public class FileSecurity {
    private static final String PHOTO_UPLOAD_TOKEN = "token";

    public static boolean uploadAccess(String token) {
        return token.equals(FileSecurity.PHOTO_UPLOAD_TOKEN);
    }
}
