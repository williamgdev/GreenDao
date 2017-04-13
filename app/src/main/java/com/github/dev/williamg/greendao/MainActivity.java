package com.github.dev.williamg.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.dev.williamg.greendao.dao.HistoryDB;
import com.github.dev.williamg.greendao.dao.VoiceMailDB;
import com.github.dev.williamg.greendao.interactor.DatabaseInteractor;
import com.github.dev.williamg.greendao.interactor.HistoryInteractor;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity ->";
    HistoryInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interactor = ((MyApplication)getApplication()).getHistoryInteractor();
        printAllRows();

    }

    private void printAllRows() {
        interactor.read(new HistoryInteractor.GetHistoriesDBListener() {
            @Override
            public void onResult(List<HistoryDB> histories) {
                for (HistoryDB history : histories){
                    Log.d(TAG, "onResult: histories: " + history.getMessages());
                }
            }
        });

    }
}
