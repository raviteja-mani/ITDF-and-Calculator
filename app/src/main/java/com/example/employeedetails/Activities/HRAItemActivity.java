package com.example.employeedetails.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.employeedetails.ViewModels.HRAviewModel;
import com.example.employeedetails.R;

import java.util.HashMap;

//did not used it
public class HRAItemActivity extends AppCompatActivity {
    Button cancelbtn;
    Button savebtn;
    EditText month;
    EditText name;
    EditText panncard;
    EditText location;
    EditText amount;
    HRAviewModel viewModel;
    private HashMap<String,Integer> map =new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hraitem);
        map.put("january",10);
        map.put("february",11);
        map.put("march",12);
        map.put("april",1);
        map.put("may",2);
        map.put("june",3);
        map.put("july",4);
        map.put("august",5);
        map.put("september",6);
        map.put("october",7);
        map.put("november",8);
        map.put("december",9);
        cancelbtn=findViewById(R.id.cancel_button);
        savebtn=findViewById(R.id.Savebutton);
        month=findViewById(R.id.month);
        name=findViewById(R.id.LLname);
        panncard=findViewById(R.id.pancardId);
        location=findViewById(R.id.locationType);
        amount=findViewById(R.id.amount);
        viewModel= ViewModelProviders.of(this).get(HRAviewModel.class);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                HRAItem item=new HRAItem();
//                item.setMonth(String.valueOf(month.getText()));
//                item.setLandlordname(String.valueOf(name.getText()));
//                item.setPancard(String.valueOf(panncard.getText()));
//                item.setLocation(String.valueOf(location.getText()));
//                item.setAmount(Integer.parseInt(String.valueOf(amount.getText())));
//              item.setId((int)map.get(String.valueOf(month.getText()).toLowerCase()));
//                viewModel.insert(item);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}