package com.dnstechpack.installer.enums;

/**
 * @Author ShadowChild.
 */
public enum EnumErrorCodes {

    CLIENT_QUIT(-1),
    INSTALL_ERROR(1, "Installation Failed At A Critical Point");

    private EnumErrorCodes(int code) {

        this.code = code;
    }

    private EnumErrorCodes(int code, String description) {

        this.code = code;
        this.description = description;
    }

    public int code;
    public String description;
}
