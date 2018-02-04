package com.smartdatainc.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smartdatainc.adapters.OrderListAdapter;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.dataobject.CancelOrderRequestModel;
import com.smartdatainc.dataobject.OrderItemDetail;
import com.smartdatainc.dataobject.WaiterModel;
import com.smartdatainc.fudowaiter.R;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.managers.WaiterManager;
import com.smartdatainc.utils.Constants;

import java.util.ArrayList;

public class OrderDetailsActivity extends BaseActivity implements OrderListAdapter.IUpdatePrice, ServiceRedirection {

    private RecyclerView dishlist;
    private TextView accept, reject, tvTotalOrderPrice;
    private ArrayList<OrderItemDetail> orderItemDetails;
    private OrderListAdapter orderListAdapter;
    private String ordereId;

    WaiterManager waiterManager;
    private WaiterModel waiterModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        waiterManager = new WaiterManager(this, this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dishlist = (RecyclerView) findViewById(R.id.hotel_list);
        accept = (TextView) findViewById(R.id.accept);
        tvTotalOrderPrice = (TextView) findViewById(R.id.tvTotalOrderPrice);
        reject = (TextView) findViewById(R.id.reject);
        orderItemDetails = new ArrayList<>();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        ordereId = intent.getStringExtra("orderId");
        waiterModel = (WaiterModel) getIntent().getSerializableExtra("Model");
        orderItemDetails = waiterModel != null ? waiterModel.getOrderItemDetails() : new ArrayList<OrderItemDetail>();
        orderListAdapter = new OrderListAdapter(OrderDetailsActivity.this, orderItemDetails);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(OrderDetailsActivity.this);
        dishlist.setLayoutManager(recyclerViewLayoutManager);
        dishlist.setItemAnimator(new DefaultItemAnimator());
        dishlist.setAdapter(orderListAdapter);


        if (waiterModel != null) {
            if (waiterModel.getIsApproveStatus() == 1) {
                reject.setVisibility(View.GONE);
                accept.setText("Order Accepted ");
            }
        }
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (waiterModel.getIsApproveStatus() == 1)
                    return;
                if (waiterModel != null && waiterModel.getOrderItemDetails() != null && waiterModel.getOrderItemDetails().size() > 0) {
                    WaiterModel requestModel = waiterModel;
                    if (orderItemDetails != null && orderItemDetails.size() > 0) {
                        for (int i = 0; i < orderItemDetails.size(); i++) {
                            orderItemDetails.get(i).setDishTotalAmount((orderItemDetails.get(i).getDishUnitPrice() * orderItemDetails.get(i).getQuantity()));
                            orderItemDetails.get(i).setTableId((waiterModel.getTableId()));
                        }
                    }
                    calculateTotalPrice();
                    requestModel.setTotalAmount(total);
                    requestModel.setUserType("Waiter");
                    requestModel.setCustomerID(orderItemDetails.get(0).getCustomerID());
                    waiterManager.acceptOrder(requestModel);
                }
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRejectDialog();
            }
        });

        calculateTotalPrice();
    }

    private void showRejectDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        alert.setTitle("Reject Order!!");
        alert.setMessage("Are you sure to reject this item");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    rejectOrder();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                dialog.dismiss();

            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    private void rejectOrder() {
        CancelOrderRequestModel cancelOrderRequestModel = new CancelOrderRequestModel();
        cancelOrderRequestModel.setOrderId(waiterModel.getOrderID());
        cancelOrderRequestModel.setUserType("Waiter");
        waiterManager.rejectOrder(cancelOrderRequestModel);
    }

    float total = 0;

    private void calculateTotalPrice() {
        total = 0;
        for (int i = 0; i < orderItemDetails.size(); i++) {
            if (orderItemDetails.get(i).getQuantity() > 0)
                total += (orderItemDetails.get(i).getDishUnitPrice() * orderItemDetails.get(i).getQuantity());
//            else
//                total += (orderItemDetails.get(i).getDishUnitPrice());
        }

        tvTotalOrderPrice.setText("Total Order Price: $ " + total);

    }

    @Override
    public void updateTotalPrice() {
        calculateTotalPrice();
    }

    @Override
    public void onSuccessRedirection(int tasksID) {
        if (Constants.TaskID.ACCEPT_ORDER == tasksID) {
            if (AppInstance.acceptOrderResponse != null && AppInstance.acceptOrderResponse.equalsIgnoreCase("Success")) {
                Toast.makeText(OrderDetailsActivity.this, "Order Accepted", Toast.LENGTH_LONG).show();
                finish();
            }
        } else if (Constants.TaskID.REJECT_ORDER == tasksID) {
            if (AppInstance.rejectResponse != null && AppInstance.rejectResponse.equalsIgnoreCase("Success")) {
                Toast.makeText(OrderDetailsActivity.this, "Order Rejected", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    public void onFailureRedirection(String errorMessage) {

    }
}
