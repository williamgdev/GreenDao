package com.github.dev.williamg.greendao;

import android.app.Application;
import com.github.dev.williamg.greendao.interactor.DatabaseInteractor;


public class MyApplication extends Application {
    DatabaseInteractor interactor;

    public MyApplication() {
        interactor = new DatabaseInteractor(this);
    }

    public DatabaseInteractor getDatabaseInteractor() {
        return interactor;
    }
}
