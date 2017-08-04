package com.supets.pet.mock.test;


import com.supets.pet.mock.bean.LocalMockData;
import com.supets.pet.mock.dao.LocalMockDataDB;
import com.supets.pet.mock.core.IMockDataMapper;

import java.util.List;

class MockDataMapper implements IMockDataMapper {


    @Override
    public boolean getMapper(String url) {
        List<LocalMockData> data = LocalMockDataDB.queryAllMockData(url);
        return data != null && data.size() > 0&&data.get(0).getSelected();
    }

    @Override
    public String getData(String url) {
        List<LocalMockData> data = LocalMockDataDB.queryAllMockData(url);
        if (data != null && data.size() > 0) {
            return data.get(0).getData();
        }
        return null;
    }
}
