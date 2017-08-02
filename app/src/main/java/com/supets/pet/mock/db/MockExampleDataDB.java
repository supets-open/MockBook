package com.supets.pet.mock.db;

import com.supets.pet.greendao.SessionFactory;
import com.supets.pet.mock.bean.MockExampleData;

import java.util.List;

public class MockExampleDataDB extends SessionFactory {

    public static List<MockExampleData> queryAllMockData(String name) {
        return getDbSession().getMockExampleDataDao().queryRaw("where name= ?", name);
    }

    public static List<MockExampleData> queryAllMockDataById(String id) {
        return getDbSession().getMockExampleDataDao().queryRaw("where _id= ?", id);
    }

    public static List<MockExampleData> queryAll() {
        return getDbSession().getMockExampleDataDao().loadAll();
    }

    public static void insertMockData(MockExampleData status) {
        getDbSession().getMockExampleDataDao().insert(status);
    }

    public static void updateMockData(MockExampleData status) {
        getDbSession().getMockExampleDataDao().update(status);
    }

    public static void deleteMockData(MockExampleData status) {
        getDbSession().getMockExampleDataDao().delete(status);
    }

    public static void deleteAll() {
        getDbSession().getMockExampleDataDao().deleteAll();
    }

}
