package com.github.dev.williamg.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.dev.williamg.greendao.dao.VoiceMailDB;
import com.github.dev.williamg.greendao.interactor.DatabaseInteractor;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity ->";
    DatabaseInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interactor = ((MyApplication)getApplication()).getDatabaseInteractor(this);
        printAllRows();

    }

    private void printAllRows() {
        interactor.read(new DatabaseInteractor.GetRowDatabaseListener() {
            @Override
            public void onResult(List<VoiceMailDB> voiceMailDBList) {
                for (VoiceMailDB voiceMailDB :
                        voiceMailDBList) {
                    Log.d(TAG, "onResult: " + voiceMailDB.getName());
                }
            }
        });

    }
}
