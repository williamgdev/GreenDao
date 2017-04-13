package com.github.dev.williamg.greendao;

import android.app.Application;
import android.content.Context;

import com.github.dev.williamg.greendao.interactor.DatabaseInteractor;
import com.github.dev.williamg.greendao.interactor.HistoryInteractor;


public class MyApplication extends Application {
    DatabaseInteractor databaseInteractor;
    HistoryInteractor historyInteractor;

    public DatabaseInteractor getDatabaseInteractor() {
        databaseInteractor = new DatabaseInteractor(getApplicationContext());
        return databaseInteractor;
    }

    public HistoryInteractor getHistoryInteractor() {
        historyInteractor = new HistoryInteractor(getApplicationContext());
        return historyInteractor;
    }
}
