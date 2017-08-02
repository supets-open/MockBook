package com.supets.pet.mock.db;

import com.supets.pet.greendao.SessionFactory;
import com.supets.pet.mock.bean.LocalMockData;

import java.util.List;

public class LocalMockDataDB extends SessionFactory {

    public static List<LocalMockData> queryAllMockData(String url) {
        return getDbSession().getLocalMockDataDao().queryRaw("where url= ?", url);
    }

    public static List<LocalMockData> queryAll() {
        return getDbSession().getLocalMockDataDao().loadAll();
    }

    public static void insertMockData(LocalMockData status) {
        getDbSession().getLocalMockDataDao().insert(status);
    }

    public static void updateMockData(LocalMockData status) {
        getDbSession().getLocalMockDataDao().update(status);
    }

    public static void deleteMockData(LocalMockData status) {
        getDbSession().getLocalMockDataDao().delete(status);
    }

    public static void deleteAll() {
        getDbSession().getLocalMockDataDao().deleteAll();
    }

}
