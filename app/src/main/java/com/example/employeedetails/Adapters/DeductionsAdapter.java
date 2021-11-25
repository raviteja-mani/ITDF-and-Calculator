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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetails.ModalClasses.DeductionType;
import com.example.employeedetails.ModalClasses.Generalfunctions;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

import java.util.ArrayList;

public class DeductionsAdapter extends RecyclerView.Adapter<DeductionsAdapter.RecyclerDeductionHolder> {
    ArrayList<DeductionType> deductions;
    Generalfunctions generalfunctions;
    Context context;
    CalculatorViewModel viewModel;
    boolean b;
    public DeductionsAdapter(Context context,ArrayList<DeductionType> deductions,boolean b) {
        this.context=context;
        generalfunctions=new Generalfunctions(context);
        this.deductions=deductions;
        this.b=b;
//        viewModel=new ViewModelProvider(context).get(CalculatorViewModel.class);
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
                try {
                    if (String.valueOf(s).equals("") || !(String.valueOf(s).matches("[0-9]*"))) {
                        holder.ed.setText("0");
                        deductions.get(position).setDeclared(0);

                    } else {
                        int i = Integer.parseInt(String.valueOf(s));

                        deductions.get(position).setDeclared(i);
                    }
                    if (b) generalfunctions.setITDFDeductionArray(deductions);
                }
                catch(Exception e){
                        //todo
                    }
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
    public ArrayList<DeductionType> getDeductionArray(){
        return deductions;
    }
}
