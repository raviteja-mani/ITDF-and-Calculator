package com.example.employeedetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HRAitemsAdapter extends RecyclerView.Adapter<HRAitemsAdapter.RecyclerHolder> {
    List<HRAItem> list =new ArrayList<>();
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
    HRAItem item=list.get(position);
    holder.day.setText(item.getMonth());
    holder.amount.setText(String.valueOf(item.getAmount()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<HRAItem> HRAlist) {
        Collections.sort(HRAlist, new Comparator<HRAItem>() {
            @Override
            public int compare(HRAItem t1, HRAItem t2) {
                return t1.getId()-t2.getId();
            }
        });
        this.list = HRAlist;
        notifyDataSetChanged();
    }

    protected class RecyclerHolder extends RecyclerView.ViewHolder{
        TextView day;
        TextView amount;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.day);
            amount=itemView.findViewById(R.id.amounttext);
        }
    }
}
