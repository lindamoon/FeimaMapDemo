package com.feima.feimamapdemo;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapsInitializer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.btn_normal)
    Button mBtnNormal;
    @Bind(R.id.btn_map)
    Button mBtnMap;
    @Bind(R.id.rl_content)
    RelativeLayout mRlContent;
    private FragmentManager mFragmentManager;
    private MapFragment mMapFragment;
    private NormalFragment mNormalFragment;
    private AMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            MapsInitializer.initialize(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        mFragmentManager = getSupportFragmentManager();
        mMapFragment = new MapFragment();
        mNormalFragment = new NormalFragment();
        setUpMapIfNeeded();

    }

    @OnClick({R.id.btn_normal, R.id.btn_map})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.rl_content, mNormalFragment);
                transaction.commit();
                break;
            case R.id.btn_map:
                FragmentTransaction transaction1 = mFragmentManager.beginTransaction();
                transaction1.replace(R.id.rl_content, mMapFragment);
                transaction1.commit();
                break;
        }
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = mMapFragment.getMap();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
