package com.example.farmmarket;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    List<Order> listOrder;
    Locale vnd = new Locale("vi", "VN");
    NumberFormat vietnamdongFormat = NumberFormat.getCurrencyInstance(vnd);

    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");


    int size;
    public OrderAdapter(List<Order> listOrder, int size) {
        this.listOrder = listOrder;
        this.size =size;
    }
    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order,parent, false);
        this.size = listOrder.size();
        return new OrderAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {


        Date date = null;
        try {
            date = inputFormat.parse(listOrder.get(position).getCreate_day());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        holder.txtAmount.setText(vietnamdongFormat.format(listOrder.get(position).getTotal_amount()));
        holder.txtCreateDay.setText(outputFormat.format(date));
    }

    @Override
    public int getItemCount() {
        return this.size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCreateDay;
        TextView txtAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCreateDay=itemView.findViewById(R.id.textCreateDay);
            txtAmount=itemView.findViewById(R.id.txtAmount);

        }
    }
}
