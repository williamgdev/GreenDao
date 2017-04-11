package com.github.dev.williamg.greendao;

import android.app.Application;
import android.content.Context;

import com.github.dev.williamg.greendao.interactor.DatabaseInteractor;


public class MyApplication extends Application {
    DatabaseInteractor interactor;

    public DatabaseInteractor getDatabaseInteractor(Context context) {
        interactor = new DatabaseInteractor(context);
        return interactor;
    }
}
