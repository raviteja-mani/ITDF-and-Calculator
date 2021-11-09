package com.example.employeedetails.Adapters;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.R;

import java.util.ArrayList;

public class DeductionsAdapter extends RecyclerView.Adapter<DeductionsAdapter.RecyclerDeductionHolder> {
    ArrayList<DeductionType> deductions;
//    private int position;
    Generalfunctions generalfunctions;
    Context context;
    public DeductionsAdapter(Context context) {
        this.context=context;
        generalfunctions=new Generalfunctions(context);
        this.deductions=generalfunctions.getDeductionArray();
    }

    @NonNull
    @Override
    public DeductionsAdapter.RecyclerDeductionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerdeductionview_item, parent, false);
        return new RecyclerDeductionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeductionsAdapter.RecyclerDeductionHolder holder, @SuppressLint("RecyclerView") int position) {

        DeductionType d=deductions.get(position);
//    holder.txelegible.setText(String.valueOf(d.getEligible()));
    holder.ed.setText(String.valueOf(d.getDeclared()));
    holder.tv.setText(d.getText());
    holder.ed.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            int i=0;
        if(String.valueOf(s).equals("")){
//            holder.txelegible.setText("0");
            deductions.get(position).setDeclared(0);
//            deductions.get(position).setEligible(0);
        }
        else{
            int i=Integer.parseInt(String.valueOf(s));
//            int max=150000;
//            int total=0;
            deductions.get(position).setDeclared(i);
//            for(DeductionType dd: deductions){
//                if(total==max) {
//                    dd.setEligible(0);
//                }
//                else {
//                    if(total+dd.getDeclared()>max) {
//                        total=total+(max-total);
//                        dd.setEligible(max-total);
//
//                    }
//                    else{
//
//                        total+=dd.getDeclared();
//                        dd.setEligible(i);
////                        holder.txelegible.setText(String.valueOf(dd.getEligible()));
//                    }
//                }
////                Log.d("ravi",String.valueOf(dd.getEligible()));
//            System.out.println(dd.getEligible());
//            }
        }
        generalfunctions.setDeductionArray(deductions);

        }
    });
    }

    @Override
    public int getItemCount() {
        return deductions.size();
    }
    public class RecyclerDeductionHolder extends RecyclerView.ViewHolder{
        TextView tv;
        EditText ed;
        TextView txelegible;
        public RecyclerDeductionHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.deduction_text);
//            txelegible=itemView.findViewById(R.id.deduction_eligible);
            ed=itemView.findViewById(R.id.deduction_declared);
        }
    }
}
