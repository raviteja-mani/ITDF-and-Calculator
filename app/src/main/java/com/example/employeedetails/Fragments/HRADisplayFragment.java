package com.example.employeedetails.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.employeedetails.ModalClasses.HRAItem;
import com.example.employeedetails.ViewModels.HRAviewModel;
import com.example.employeedetails.R;

public class HRADisplayFragment extends Fragment {
    Button deletebtn;
//    Button backbtn;
    Button Updatebtn;
    EditText month;
    EditText name;
    EditText panncard;
    EditText location;
    EditText amount;
    HRAviewModel viewModel;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_hradisplay.xml);
//        Intent intent=getIntent();
//        HRAItem item=(HRAItem)intent.getSerializableExtra("HRAItem");
//        backbtn=findViewById(R.id.back_button);
//        Updatebtn=findViewById(R.id.update_button);
//        deletebtn=findViewById(R.id.deletebutton);
//        month=findViewById(R.id.month);
//        name=findViewById(R.id.LLname);
//        panncard=findViewById(R.id.pancardId);
//        location=findViewById(R.id.locationType);
//        amount=findViewById(R.id.amount);
//
//        //placind data in text fields
//        month.setText(item.getMonth());
//        name.setText(item.getLandlordname());
//        panncard.setText(item.getPancard());
//        location.setText(item.getPancard());
//        amount.setText(String.valueOf(item.getAmount()));
//
//
//        viewModel= ViewModelProviders.of(this).get(HRAviewModel.class);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        Updatebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                String monthstr=String.valueOf(month.getText());
////                String lordnamestr=String.valueOf(name.getText());
////                String panstr=String.valueOf(panncard.getText());
////                String locationstr=String.valueOf(location.getText());
////                int amt=Integer.parseInt(String.valueOf(amount.getText()));
////                HRAItem item=new HRAItem();
////                fromItem.getId(),monthstr,lordnamestr,panstr,locationstr,amt
//                item.setMonth(String.valueOf(month.getText()));
//                item.setLandlordname(String.valueOf(name.getText()));
//                item.setPancard(String.valueOf(panncard.getText()));
//                item.setLocation(String.valueOf(location.getText()));
//                item.setAmount(Integer.parseInt(String.valueOf(amount.getText())));
////                viewModel.delete(fromItem);
////                viewModel.insert(item);
//                viewModel.update(item);
//            }
//        });
//        deletebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                viewModel.delete(fromItem);
//
////                finish();
////                month.setText("");
//                month.clearFocus();
//                name.setText("");
//                panncard.setText("");
//                location.setText("");
//                amount.setText("");
//            }
//        });
//    }
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
        name=v.findViewById(R.id.LLname);
        panncard=v.findViewById(R.id.pancardId);
        location=v.findViewById(R.id.locationType);
        amount=v.findViewById(R.id.amount);

        //placind data in text fields
        month.setText(item.getMonth());
        name.setText(item.getLandlordname());
        panncard.setText(item.getPancard());
        location.setText(item.getPancard());
        amount.setText(String.valueOf(item.getAmount()));


        viewModel= ViewModelProviders.of(this).get(HRAviewModel.class);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                finish();
//
//            }
//        });

        Updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String monthstr=String.valueOf(month.getText());
//                String lordnamestr=String.valueOf(name.getText());
//                String panstr=String.valueOf(panncard.getText());
//                String locationstr=String.valueOf(location.getText());
//                int amt=Integer.parseInt(String.valueOf(amount.getText()));
//                HRAItem item=new HRAItem();
//                fromItem.getId(),monthstr,lordnamestr,panstr,locationstr,amt
                item.setMonth(String.valueOf(month.getText()));
                item.setLandlordname(String.valueOf(name.getText()));
                item.setPancard(String.valueOf(panncard.getText()));
                item.setLocation(String.valueOf(location.getText()));
                item.setAmount(Integer.parseInt(String.valueOf(amount.getText())));
//                viewModel.delete(fromItem);
//                viewModel.insert(item);
                viewModel.update(item);
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                viewModel.delete(fromItem);

//                finish();
//                month.setText("");
                month.clearFocus();
                name.setText("");
                panncard.setText("");
                location.setText("");
                amount.setText("");
            }
        });
        return v;
    }
}