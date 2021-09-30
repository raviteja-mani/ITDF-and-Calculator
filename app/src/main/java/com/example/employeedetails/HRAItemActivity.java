package com.example.employeedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HRAItemActivity extends AppCompatActivity {
    Button cancelbtn;
    Button savebtn;
    EditText month;
    EditText name;
    EditText panncard;
    EditText location;
    EditText amount;
    HRAviewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hraitem);
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
                HRAItem item=new HRAItem();
                item.setMonth(String.valueOf(month.getText()));
                item.setLandlordname(String.valueOf(name.getText()));
                item.setPancard(String.valueOf(panncard.getText()));
                item.setLocation(String.valueOf(location.getText()));
                item.setAmount(Integer.parseInt(String.valueOf(amount.getText())));
                viewModel.insert(item);
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