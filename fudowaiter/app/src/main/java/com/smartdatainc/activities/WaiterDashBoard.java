package com.smartdatainc.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.smartdatainc.adapters.WaiterListAdapter;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.dataobject.WaiterModel;
import com.smartdatainc.fudowaiter.R;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.managers.WaiterManager;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.Utility;

import java.util.ArrayList;

public class WaiterDashBoard extends BaseActivity implements ServiceRedirection {

    private RecyclerView menuList;
    private ArrayList<WaiterModel> waiterModels;
    private WaiterListAdapter waiterListAdapter;
    private Utility mUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        menuList = (RecyclerView) findViewById(R.id.hotel_list);
        waiterModels = new ArrayList<>();
        waiterListAdapter = new WaiterListAdapter(this, waiterModels);
        menuList.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(WaiterDashBoard.this);
        menuList.setLayoutManager(recyclerViewLayoutManager);
        menuList.setItemAnimator(new DefaultItemAnimator());
        menuList.setAdapter(waiterListAdapter);

        mUtility = new Utility(WaiterDashBoard.this);
//        mUtility.startLoader(WaiterDashBoard.this, R.drawable.image_for_rotation);
//        loadOrderData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        waiterModels.clear();
        try {
            mUtility.startLoader(WaiterDashBoard.this, R.drawable.image_for_rotation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadOrderData();
    }

    private void loadOrderData() {
        WaiterManager waiterManager = new WaiterManager(WaiterDashBoard.this, WaiterDashBoard.this);
        waiterManager.getHotelList("1");
    }

    @Override
    public void onSuccessRedirection(int taskID) {
        mUtility.stopLoader();
        if (taskID == Constants.TaskID.WAITER_LIST) {
            mUtility.stopLoader();
            waiterModels.addAll(AppInstance.waiterModels);
            waiterListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailureRedirection(String errorMessage) {
        mUtility.stopLoader();
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
