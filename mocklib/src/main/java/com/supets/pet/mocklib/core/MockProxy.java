package com.supets.pet.mocklib.core;

import com.supets.lib.supetsrouter.rpc.BaseProxy;
import com.supets.lib.supetsrouter.rpc.IBaseUI;
import com.supets.lib.supetsrouter.rpc.Module;
import com.supets.pet.mocklib.core.ex.MockModel;

/**
 *  注意：代理类实现
 */
class MockProxy extends BaseProxy<IBaseUI, IMockService> {

    @Override
    public String getModuleClassName() {
        return "com.supets.pet.mocklib.core.ex.MockModel";
    }

    @Override
    public Module<IBaseUI, IMockService> getDefaultModule() {
        return new MockModel();
    }
}
