package com.github.dev.williamg.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.dev.williamg.greendao.dao.DaoMaster;
import com.github.dev.williamg.greendao.dao.DaoSession;
import com.github.dev.williamg.greendao.dao.VoiceMailDB;
import com.github.dev.williamg.greendao.dao.VoiceMailDBDao;
import com.github.dev.williamg.greendao.model.VoiceMail;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "database", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        VoiceMailDBDao voiceMailDBDao = daoSession.getVoiceMailDBDao();

        VoiceMail voiceMail = new VoiceMail("voice_mail");
        voiceMail.setFileSize(20);

        voiceMailDBDao.insert(VoiceMailDB.create(voiceMail));

    }
}
