package com.supets.pet.mocklib.core.ex;

import com.supets.lib.supetsrouter.rpc.IBaseUI;
import com.supets.lib.supetsrouter.rpc.Module;
import com.supets.pet.mocklib.core.IMockService;
import com.supets.pet.mocklib.core.IMockServiceImpl;

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
