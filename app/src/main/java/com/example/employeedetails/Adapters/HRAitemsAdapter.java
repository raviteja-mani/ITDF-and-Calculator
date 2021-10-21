package com.example.employeedetails.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.employeedetails.Activities.HRADisplayActivity;
import com.example.employeedetails.Fragments.HRADisplayFragment;
import com.example.employeedetails.ModalClasses.HRAItem;
import com.example.employeedetails.R;

import java.util.ArrayList;
import java.util.List;

public class HRAitemsAdapter extends RecyclerView.Adapter<HRAitemsAdapter.RecyclerHolder> {
   private List<HRAItem> list =new ArrayList<>();

   Context context;
    public HRAitemsAdapter(Context context) {
       this.context=context;
    }

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
    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent i = new Intent(view.getContext(), HRADisplayActivity.class);
//            i.putExtra("HRAItem",item);
//            view.getContext().startActivity(i);
            Bundle b=new Bundle();
            b.putSerializable("HRAItem",item);
            Fragment frag=new HRADisplayFragment();
            frag.setArguments(b);
            FragmentManager manager= ((AppCompatActivity)context).getSupportFragmentManager();
            manager.beginTransaction().addToBackStack("incomefromhp").replace(R.id.frameLayoutContainer,frag).commit();
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<HRAItem> HRAlist) {
//        Collections.sort(HRAlist, new Comparator<HRAItem>() {
//            @Override
//            public int compare(HRAItem t1, HRAItem t2) {
//                return t1.getId()-t2.getId();
//            }
//        });
        this.list = HRAlist;
        notifyDataSetChanged();
    }

    protected class RecyclerHolder extends RecyclerView.ViewHolder{
        TextView day;
        TextView amount;
        CardView cardView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.day);
            amount=itemView.findViewById(R.id.amounttext);
            cardView=itemView.findViewById(R.id.HRACard);
        }
    }
}
