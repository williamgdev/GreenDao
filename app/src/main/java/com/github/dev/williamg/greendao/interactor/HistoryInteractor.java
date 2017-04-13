package com.github.dev.williamg.greendao.interactor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.dev.williamg.greendao.dao.DaoMaster;
import com.github.dev.williamg.greendao.dao.DaoSession;
import com.github.dev.williamg.greendao.dao.HistoryDB;
import com.github.dev.williamg.greendao.dao.HistoryDBDao;
import com.github.dev.williamg.greendao.dao.VoiceMailDB;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.query.Query;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class HistoryInteractor {
    private final DaoSession daoSession;
    private final HistoryDBDao historyDBDao;
    private final Query<HistoryDB> historyDBQuery;
    HistoryDB historyDB;
    private static final String DB_NAME = "database";

    List<VoiceMailDB> currentHistory = new ArrayList<>();
    String currentHistoryName;
    private boolean currentHistoryFlag;

    public HistoryInteractor(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        historyDBDao = daoSession.getHistoryDBDao();
        historyDBQuery = historyDBDao
                .queryBuilder()
                .build();
        currentHistoryName = "";
    }

    public void getVoiceMails(final String name, final GetHistoryDBListener listener) {
        List<HistoryDB> histories = historyDBQuery.list();
        List<VoiceMailDB> voiceMails = getListVoiceMailsFromString(name, histories);
        listener.onResult(voiceMails);
        setCurrentHistory(name, voiceMails);
    }

    private List<VoiceMailDB> getListVoiceMailsFromString(String name, List<HistoryDB> histories) {
        Type type = new TypeToken<List<VoiceMailDB>>() {
        }.getType();
        //search wich history has the same name and getMessages to convert to List

        return new Gson().fromJson(histories.get(findItem(name, histories)).getMessages(), type);
    }

    private int findItem(String name, List<HistoryDB> histories) {
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getId().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private void setCurrentHistory(String name, List<VoiceMailDB> voiceMails) {
        this.currentHistoryName = name;
        this.currentHistory = voiceMails;
    }

    public boolean currentHistoryFlagOff() {
        return !currentHistoryFlag;
    }

    public void updateHistory(String name, VoiceMailDB voiceMail, AddVoiceMailDBListener listener) {
        if (!currentHistoryFlagOff()) {
            throw new ConcurrentModificationException(); //Improve the temp History concurrence
        }

        currentHistoryFlag = true;
        if (currentHistory == null || !currentHistoryName.equals(name)) {
            List<HistoryDB> histories = historyDBQuery.list();
            List<VoiceMailDB> voiceMails = getListVoiceMailsFromString(name, histories);
            setCurrentHistory(name, voiceMails);
        }

        currentHistory.add(voiceMail);
        /**
         * Todo get limit of message globally. Actual is 5.
         */
        if (currentHistory.size() > 5) {
            currentHistory.remove(0);
            // if limit reached, remove the first one
            // because the first one will be the oldest.
        }

        Type listType = new TypeToken<List<VoiceMailDB>>() {
        }.getType();
        Gson gson = new Gson();
        String json = gson.toJson(currentHistory, listType);


        update(new HistoryDB(name, json), listener);

        currentHistoryFlag = false;

    }

    private void update(HistoryDB historyDB, AddVoiceMailDBListener listener) {
        historyDBDao.update(historyDB);
        listener.onResult(currentHistory.size());
    }

    public void read(GetHistoriesDBListener listener) {
        List<HistoryDB> history = historyDBQuery.list();
        listener.onResult(history);
    }

    public void create(HistoryDB history, CreateHistoryDBListener listener) {
        long key = historyDBDao.insert(history);
        listener.onResult(historyDBDao.load(history.getId()));
    }

    public interface GetHistoriesDBListener {
        void onResult(List<HistoryDB> histories);
    }

    public interface GetHistoryDBListener {
        void onResult(List<VoiceMailDB> histories);
    }

    public interface CreateHistoryDBListener {
        void onResult(HistoryDB history);
    }

    public interface AddVoiceMailDBListener {
        void onResult(int size);
    }
}
