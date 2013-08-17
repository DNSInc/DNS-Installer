package com.dnstechpack.installer.util;

/**
 * @Author ShadowChild.
 */
public enum ErrorCodes {

    CLIENT_QUIT(-1),
    INSTALL_ERROR(1, "Installation Failed At A Critical Point");

    private ErrorCodes(int code) {

        this.code = code;
    }

    private ErrorCodes(int code, String description) {

        this.code = code;
        this.description = description;
    }

    public int code;
    public String description;
}
