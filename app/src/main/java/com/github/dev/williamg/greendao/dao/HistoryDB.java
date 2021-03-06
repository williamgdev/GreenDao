package com.github.dev.williamg.greendao.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.lang.reflect.Type;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HistoryDB {
    @Id
    String id;
    @NotNull
    String messages;

    @Generated(hash = 2062817921)
    public HistoryDB(String id, @NotNull String messages) {
        this.id = id;
        this.messages = messages;
    }

    @Generated(hash = 1964680625)
    public HistoryDB() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessages() {
        return this.messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
