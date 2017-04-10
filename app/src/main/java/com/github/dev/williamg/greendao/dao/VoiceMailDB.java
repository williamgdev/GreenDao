package com.github.dev.williamg.greendao.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VoiceMailDB {
    @Id
    Long id;

    @NotNull
    @Property(nameInDb = "FILENAME")
    String name;
    
    @Transient
    private float fileSize;



    @Generated(hash = 1570594893)
    public VoiceMailDB(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 2059075204)
    public VoiceMailDB() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFileSize() {
        return fileSize;
    }

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }
}
