package com.smartdatainc.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartdatainc.activities.OrderDetailsActivity;
import com.smartdatainc.dataobject.OrderItemDetail;
import com.smartdatainc.fudowaiter.R;

import java.util.ArrayList;

/**
 * Created by aniketraut on 24/1/18.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<OrderItemDetail> orderItemDetails;
    private IUpdatePrice iUpdatePrice;

    public OrderListAdapter(Context context, ArrayList<OrderItemDetail> orderItemDetails) {
        this.context = context;
        this.orderItemDetails = orderItemDetails;
        layoutInflater = LayoutInflater.from(context);
        iUpdatePrice = (OrderDetailsActivity) context;
    }

    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_dish, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderListAdapter.MyViewHolder holder, int position) {

        final OrderItemDetail orderItemDetail = orderItemDetails.get(position);


        holder.dishName.setText(orderItemDetail.getDishName());
        holder.dishPrice.setText("$" + orderItemDetail.getDishUnitPrice());

        if (position % 2 == 0) {
            holder.mImageView.setBackgroundResource(R.drawable.restaurant_first);
        } else {
            holder.mImageView.setBackgroundResource(R.drawable.restaurant_second);
        }

        holder.tvQuantity.setText("" + orderItemDetail.getQuantity());
        float totalPrice = 0;
        if (orderItemDetail.getQuantity() > 0) {
            totalPrice = (float) (orderItemDetail.getDishUnitPrice() * orderItemDetail.getQuantity());
            holder.tvTotalOfItem.setVisibility(View.VISIBLE);
        } else {
            holder.tvTotalOfItem.setVisibility(View.GONE);
        }
        holder.tvTotalOfItem.setText("Item total price: $" + totalPrice);


        holder.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderItemDetail.getQuantity() >= 0) {
                    orderItemDetail.setQuantity(orderItemDetail.getQuantity() + 1);
                    notifyDataSetChanged();
                }
                if (iUpdatePrice != null)
                    iUpdatePrice.updateTotalPrice();
            }
        });


        holder.tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderItemDetail.getQuantity() > 1) {
                    orderItemDetail.setQuantity(orderItemDetail.getQuantity() - 1);
                    notifyDataSetChanged();
                    if (iUpdatePrice != null)
                        iUpdatePrice.updateTotalPrice();
                }

                else if (orderItemDetail.getQuantity() == 1) {
                    showRemoveDialog(orderItemDetail);
                }


            }
        });


    }

    private void showRemoveDialog(final OrderItemDetail orderItemDetail) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                context);
        alert.setTitle("Remove!!");
        alert.setMessage("Are you sure to remove this item");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    orderItemDetails.remove(orderItemDetail);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
                if (iUpdatePrice != null)
                    iUpdatePrice.updateTotalPrice();
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

    @Override
    public int getItemCount() {
        return orderItemDetails.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dishName, dishPrice;
        private ImageView mImageView;
        private TextView tvAdd, tvRemove, tvQuantity, tvTotalOfItem;

        public MyViewHolder(View view) {
            super(view);
            dishName = (TextView) view.findViewById(R.id.dish_name);
            dishPrice = (TextView) view.findViewById(R.id.dish_price);
            tvQuantity = (TextView) view.findViewById(R.id.txtQuantity);
            tvAdd = (TextView) view.findViewById(R.id.txtAdd);
            tvRemove = (TextView) view.findViewById(R.id.txtRemove);
            tvTotalOfItem = (TextView) view.findViewById(R.id.tvTotalOfItem);
            mImageView = (ImageView) view.findViewById(R.id.dish_image);

        }
    }

    public interface IUpdatePrice {
        void updateTotalPrice();
    }
}
