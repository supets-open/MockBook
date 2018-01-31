package com.supets.pet.mvvm.example;

import com.supets.pet.mockui.R;
import com.supets.pet.mvvm.ViewModel;

public class DemoView extends ViewModel {

    public DemoViewAdapter adapter = new DemoViewAdapter(this);
    public DemoLogicPrenster prenster = new DemoLogicPrenster(this);

    public interface DemoViewId{
        int  liveid= R.id.live;
    }

}
