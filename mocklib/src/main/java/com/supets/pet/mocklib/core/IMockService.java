package com.supets.pet.mocklib.core;

import com.supets.lib.supetsrouter.rpc.IBaseService;


public interface IMockService extends IBaseService {
    boolean getMapper(String url);
    String  getData(String url);
}
