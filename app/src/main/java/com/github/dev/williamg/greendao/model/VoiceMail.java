package com.github.dev.williamg.greendao.model;

public class VoiceMail {
    String name;

    private float fileSize;

    public float getFileSize() {
        return fileSize;
    }

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }

    public VoiceMail(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return "http://localhost:8080/" + name;
    }

    public boolean isEmpty() {
        return fileSize == 0;
    }
}
