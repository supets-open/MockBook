package com.supets.pet.mock.test;

import com.supets.lib.supetsrouter.rpc.IBaseUI;
import com.supets.lib.supetsrouter.rpc.Module;
import com.supets.pet.mock.core.IMockServiceImpl;
import com.supets.pet.mock.core.IMockService;

public class MockModel extends Module<IBaseUI, IMockService> {
    @Override
    public IBaseUI getUiInterface() {
        return null;
    }

    @Override
    public IMockService getServiceInterface() {
        return new IMockServiceImpl()
                .addDataMapper(new MockDataMapper());
    }
}
