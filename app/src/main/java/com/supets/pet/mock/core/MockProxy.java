package com.supets.pet.mock.core;

import com.supets.lib.supetsrouter.rpc.BaseProxy;
import com.supets.lib.supetsrouter.rpc.IBaseUI;
import com.supets.lib.supetsrouter.rpc.Module;
import com.supets.pet.mock.test.MockModel;

/**
 *  注意：代理类实现
 */
class MockProxy extends BaseProxy<IBaseUI, IMockService> {

    @Override
    public String getModuleClassName() {
        return "com.supets.pet.mock.test.MockModel";
    }

    @Override
    public Module<IBaseUI, IMockService> getDefaultModule() {
        return new MockModel();
    }
}
