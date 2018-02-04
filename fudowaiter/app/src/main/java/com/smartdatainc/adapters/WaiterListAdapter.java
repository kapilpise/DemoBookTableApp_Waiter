package com.smartdatainc.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartdatainc.activities.OrderDetailsActivity;
import com.smartdatainc.dataobject.WaiterModel;
import com.smartdatainc.fudowaiter.R;

import java.util.ArrayList;


/**
 * Created by aniketraut on 24/1/18.
 */

public class WaiterListAdapter extends RecyclerView.Adapter<WaiterListAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<WaiterModel> waiterModels;


    public WaiterListAdapter(Context context, ArrayList<WaiterModel> waiterModels) {
        this.context = context;
        this.waiterModels = waiterModels;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public WaiterListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_order_list, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WaiterListAdapter.MyViewHolder holder, int position) {

        final WaiterModel waiterModel = waiterModels.get(position);

        holder.orderName.setText("Order Id " + waiterModel.getOrderID());
        holder.totalPrice.setText("Total Price $" + waiterModel.getTotalAmount());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Model", waiterModel);
                context.startActivity(new Intent(context, OrderDetailsActivity.class).putExtras(bundle).putExtra("orderId", waiterModel.getOrderID()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return waiterModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tableNo, orderName, totalPrice, accept, reject;
        private LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);
            orderName = (TextView) view.findViewById(R.id.order_name);
            tableNo = (TextView) view.findViewById(R.id.table_no);
            totalPrice = (TextView) view.findViewById(R.id.total_price);
            accept = (TextView) view.findViewById(R.id.accept);
            reject = (TextView) view.findViewById(R.id.reject);
            layout = (LinearLayout) view.findViewById(R.id.layout);

        }
    }
}
