package ru.amm.fileexplorer.server.entity;

public enum FileType {
    APPLICATION("application"),
    AUDIO("audio"),
    IMAGE("image"),
    MESSAGE("message"),
    MODEL("model"),
    TEXT("text"),
    VIDEO("video");
    private String base;
    private String extension = "";

    FileType(String baseType) {
        this.base = baseType;
    }

    public void setExtension(String s) {
        extension = s;
    }

    public String getBase() {
        return base;
    }

    public String getExtension() {
        return extension;
    }

    public String getMimeType() {
        return base + "/" + extension;
    }

    public static FileType get() {
        FileType f = APPLICATION;
        f.setExtension("octet-stream");
        return f;
    }

    public static FileType get(String base, String ext) {
        for (FileType value : values()) {
            if (value.getBase().equals(base)) {
                value.setExtension(ext);
                return value;
            }
        }
        return get();
    }
}
