package com.supets.pet.module.live;


import com.supets.pet.mvvm.example.TestLifeCycle;

public class TestLiveCycleActivity extends TestLifeCycle {

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_live_test);
//
//        final MYLiveData data = new MYLiveData();
//        data.observe(this, new Observer<String>() {
//            public void onChanged(@Nullable String s) {
//                Log.v("livedata", "ss==" + s);
//            }
//        });
//
//
//        findViewById(R.id.live).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                data.updateData("lhj");
//            }
//        });
//
//        model = ViewModelProviders.of(this).get(SharedViewModel.class);
//
//        findViewById(R.id.livemodel).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //test
//                model.select("选择");
//            }
//        });
//
//        getLifecycle().addObserver(new MYLiveCycleObserver());
//
//
//    }
//
//    private SharedViewModel model;

}
