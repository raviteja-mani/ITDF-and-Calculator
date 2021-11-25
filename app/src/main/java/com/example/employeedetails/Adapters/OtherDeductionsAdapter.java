package com.example.employeedetails.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.ModalClasses.OtherDeductions;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

import java.util.ArrayList;

public class OtherDeductionsAdapter extends RecyclerView.Adapter<OtherDeductionsAdapter.RecyclerHolder> {
    private ArrayList<OtherDeductions> deductions;
    private Context context;
    private Generalfunctions gf;
    CalculatorViewModel viewModel;
    boolean b;
    public OtherDeductionsAdapter(Context context, ArrayList<OtherDeductions> deductions,boolean b) {
    this.context=context;
    this.deductions=deductions;
    gf=new Generalfunctions(context);
    this.b=b;
    viewModel=new ViewModelProvider((ViewModelStoreOwner) context).get(CalculatorViewModel.class);
    }

    @NonNull
    @Override
    public OtherDeductionsAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerdeductionview_item, parent, false);
        return new OtherDeductionsAdapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherDeductionsAdapter.RecyclerHolder holder, @SuppressLint("RecyclerView") int position) {
        OtherDeductions item=deductions.get(position);
        holder.declared.setText(String.valueOf(item.getDeclared()));
//        holder.eligible.setText(String.valueOf(item.getElegible()));
        holder.title.setText(String.valueOf(item.getTitle()));
        holder.declared.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable ss) {
                try {
                    String s;
                    if (String.valueOf(ss).equals("")||!(String.valueOf(ss).matches("[0-9]*"))) {
                        s = "0";
                    } else {
                        s = String.valueOf(ss);
                    }
                    long x = Long.parseLong(s);
                    long l = x;
                    if (position == 0) {
                        if (x > 25000) l = 25000;
                        deductions.get(position).setElegible(l);
                    } else if (position == 1) {
//                    deductions.get(position+1).setElegible(0);
                        if (x > 25000) l = 25000;
                        deductions.get(position).setElegible(l);
                    } else if (position == 2) {
//                    deductions.get(position-1).setElegible(0);
                        if (x > 50000) l = 50000;
                        deductions.get(position).setElegible(l);
                    } else if (position == 3) {
//                    deductions.get(position+1).setElegible(0);
                        if (x > 75000) l = 75000;
                        deductions.get(position).setElegible(l);
                    } else if (position == 4) {
//                    deductions.get(position-1).setElegible(0);
                        if (x > 125000) l = 125000;
                        deductions.get(position).setElegible(l);
                    } else if (position == 5) {
//                    deductions.get(position+1).setElegible(0);
                        if (x > 40000) l = 40000;
                        deductions.get(position).setElegible(l);
                    } else if (position == 6) {
//                    deductions.get(position-1).setElegible(0);
                        if (x > 100000) l = 100000;
                        deductions.get(position).setElegible(l);
                    } else if (position == 7) {
                        deductions.get(position).setElegible(Long.parseLong(s));
                    } else if (position == 8) {
                        deductions.get(position).setElegible(Long.parseLong(s) / 2);
                    } else if (position == 9) {
                        if (x > 75000) l = 75000;
                        deductions.get(position).setElegible(l);
                    } else if (position == 10) {
                        if (x > 50000) l = 50000;
                        deductions.get(position).setElegible(l);
                    }
                    deductions.get(position).setDeclared(Long.parseLong(s));
                    if (b) gf.setITDFOtherDeductionArray(deductions);
//                holder.eligible.setText(String.valueOf(deductions.get(position).getElegible()));
//                viewModel.setOtherDeductions(deductions);
                }
                catch (Exception e){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return deductions.size();
    }
    public ArrayList<OtherDeductions> getDeductionArray(){
        return deductions;
    }
    public class RecyclerHolder extends RecyclerView.ViewHolder{
        TextView title;
        EditText declared;
        TextView eligible;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.deduction_text);
            declared=itemView.findViewById(R.id.deduction_declared);
//            eligible=itemView.findViewById(R.id.deduction_eligible);
        }
    }
}
