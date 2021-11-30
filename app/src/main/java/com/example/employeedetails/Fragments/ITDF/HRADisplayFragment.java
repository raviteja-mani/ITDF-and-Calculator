package com.example.employeedetails.Fragments.ITDF;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.employeedetails.ModalClasses.HRAItem;
import com.example.employeedetails.ViewModels.HRAviewModel;
import com.example.employeedetails.R;

public class HRADisplayFragment extends Fragment {
    Button deletebtn;
    Button Updatebtn;
    EditText month;
    EditText name;
    EditText panncard;

    EditText amount;
    HRAviewModel viewModel;
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_hradisplay, container, false);
        Bundle intent=getArguments();
        HRAItem item=(HRAItem)intent.getSerializable("HRAItem");
//        backbtn=v.findViewById(R.id.back_button);
        Updatebtn=v.findViewById(R.id.update_button);
        deletebtn=v.findViewById(R.id.deletebutton);
        month=v.findViewById(R.id.month);
        month.setFocusable(false);
        name=v.findViewById(R.id.LLname);
        panncard=v.findViewById(R.id.pancardId);
        spinner=v.findViewById(R.id.locationType);
        amount=v.findViewById(R.id.amount);

        //placind data in text fields
        month.setText(item.getMonth());
        name.setText(item.getLandlordname());
        panncard.setText(item.getPancard());
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.metro_nonmetro,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(item.getLocation().equals("Metro"))
            spinner.setSelection(0);
        else spinner.setSelection(1);
        amount.setText(String.valueOf(item.getAmount()));
        viewModel= ViewModelProviders.of(this).get(HRAviewModel.class);
        Updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setMonth(String.valueOf(month.getText()));
                item.setLandlordname(String.valueOf(name.getText()));
                item.setPancard(String.valueOf(panncard.getText()));
                if(spinner.getSelectedItemPosition()==0)
                item.setLocation("Metro");
                else item.setLocation("Non-Metro");
                if(String.valueOf(amount.getText()).equals("")) item.setAmount(0);
                else item.setAmount(Integer.parseInt(String.valueOf(amount.getText())));
                viewModel.update(item);
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        return v;
    }
}