package com.supets.pet.mock.db;

import android.database.Cursor;

import com.supets.pet.greendao.MockDataDao;
import com.supets.pet.greendao.SessionFactory;
import com.supets.pet.mock.bean.MockData;

import java.util.ArrayList;
import java.util.List;

public class MockDataDB extends SessionFactory {

    public static List<MockData> queryAllMockData(String url) {
        return getDbSession().getMockDataDao().queryRaw("where url = ?", url);
    }

    public static List<MockData> queryAll() {
        return getDbSession().getMockDataDao().queryBuilder().orderDesc(MockDataDao.Properties.Time).list();
    }

    public static List<MockData> queryMockDataById(String id) {
        return getDbSession().getMockDataDao().queryRaw("where _id = ?  ", id);
    }

    public static void insertMockData(MockData status) {
        getDbSession().getMockDataDao().insert(status);
    }

    public static void updateMockData(MockData status) {
        getDbSession().getMockDataDao().update(status);
    }

    public static void deleteMockData(MockData status) {
        getDbSession().getMockDataDao().delete(status);
    }

    private static final String SQL_DISTINCT_ENAME = "SELECT DISTINCT " + MockDataDao.Properties.Url.columnName + " FROM " + MockDataDao.TABLENAME;

    public static List<String> queryAllUrl() {
        ArrayList<String> result = new ArrayList<String>();
        Cursor c = getDbSession().getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try {
            if (c.moveToFirst()) {
                do {
                    result.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return result;
    }


    public static void deleteAll() {
        getDbSession().getMockDataDao().deleteAll();
    }
}
