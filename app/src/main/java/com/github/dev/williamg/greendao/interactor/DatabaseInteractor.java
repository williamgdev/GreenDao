package com.github.dev.williamg.greendao.interactor;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.github.dev.williamg.greendao.dao.DaoMaster;
import com.github.dev.williamg.greendao.dao.DaoSession;
import com.github.dev.williamg.greendao.dao.VoiceMailDB;
import com.github.dev.williamg.greendao.dao.VoiceMailDBDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class DatabaseInteractor {
    private static final String DB_NAME = "database";
    private final Query<VoiceMailDB> voiceMailDBQuery;
    DaoSession daoSession;
    VoiceMailDBDao voiceMailDBDao;

    public DatabaseInteractor(Application app) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        voiceMailDBDao = daoSession.getVoiceMailDBDao();
        voiceMailDBQuery = voiceMailDBDao
                .queryBuilder()
                .orderAsc(VoiceMailDBDao.Properties.Id)
                .build();
    }

    public void create(VoiceMailDB voiceMailDB, CreateDatabaseListener listener) {
        long key = voiceMailDBDao.insert(voiceMailDB);
        listener.onResult(voiceMailDBDao.load(key));
    }

    public void getRow(int rowIndex, ReadDatabaseListener listener){
        VoiceMailDB voiceMailDB = voiceMailDBQuery.list().get(rowIndex);
        listener.onResult(voiceMailDB);
    }

    public void read(GetRowDatabaseListener listener){
        List<VoiceMailDB> voiceMailDB = voiceMailDBQuery.list();
        listener.onResult(voiceMailDB);
    }

    public void update(VoiceMailDB voiceMailDB){
        voiceMailDBDao.update(voiceMailDB);
        /**
         * This method is void...
         */
    }

    public void delete(VoiceMailDB voiceMailDB, DeleteDatabaseListener listener){
        voiceMailDBDao.delete(voiceMailDB);
        listener.onResult(voiceMailDBQuery.list().size());
    }


    public interface CreateDatabaseListener{
        void onResult(VoiceMailDB rowIndex);
    }

    public interface ReadDatabaseListener{
        void onResult(VoiceMailDB voiceMailDB);
    }

    public interface DeleteDatabaseListener{
        void onResult(int rows);
    }

    public interface GetRowDatabaseListener{
        void onResult(List<VoiceMailDB> voiceMailDBList);
    }

}
