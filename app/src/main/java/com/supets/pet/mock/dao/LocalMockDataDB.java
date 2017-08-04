package com.supets.pet.mock.dao;

import com.supets.pet.greendao.LocalMockDataDao;
import com.supets.pet.greendao.SessionFactory;
import com.supets.pet.mock.bean.LocalMockData;

import java.util.List;

public class LocalMockDataDB extends SessionFactory {


    private  static final LocalMockDataDao dao=getDbSession().getLocalMockDataDao();


    public static List<LocalMockData> queryAllMockData(String url) {
        return dao.queryRaw("where url= ?", url);
    }

    public static List<LocalMockData> queryAll() {
        return dao.loadAll();
    }

    public static void insertMockData(LocalMockData status) {
        dao.insert(status);
    }

    public static void updateMockData(LocalMockData status) {
        dao.update(status);
    }

    public static void deleteMockData(LocalMockData status) {
        dao.delete(status);
    }

    public static void deleteAll() {
        dao.deleteAll();
    }

}
