package com.example.employeedetails.Fragments.Calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.employeedetails.ModalClasses.PESclass;
import com.example.employeedetails.R;
import com.example.employeedetails.ViewModels.CalculatorViewModel;

public class  IncomeFromPESFragment extends Fragment {
CalculatorViewModel viewmodel;
EditText PESrow1;
EditText PESrow2;
EditText PESrow3;
EditText PESrow4;
EditText PESrow5;
Button savebtn;
Button cancelbtn;
//int SELECT_PICTURE = 200;
//    ImageView IVPreviewImage;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_income_from_pes.xml);
//        Intent intent=getIntent();
//
//        PESclass item=(PESclass) intent.getSerializableExtra("PESitem");
////        if(item==null)
////            System.out.println("kdjhkdkjdhfksjhfkjsdhfjd");
////        System.out.println(item.getIncTax());
//        IVPreviewImage = findViewById(R.id.IVPreviewImage);
//        PESrow1=findViewById(R.id.PESrow1);
//        PESrow2=findViewById(R.id.PESrow2);
//        PESrow3=findViewById(R.id.PESrow3);
//        PESrow4=findViewById(R.id.PESrow4);
//        PESrow5=findViewById(R.id.PESrow5);
//        savebtn=findViewById(R.id.PESsaveBtn);
//        PESrow1.setText(String.valueOf(item.getGsalary()));
//        PESrow2.setText(String.valueOf(item.getExUSs()));
//        PESrow3.setText(String.valueOf(item.getProfTax()));
//        PESrow4.setText(String.valueOf(item.getProvfund()));
//        PESrow5.setText(String.valueOf(item.getIncTax()));
//        viewmodel= ViewModelProviders.of(this).get(PESviewModel.class);
//        savebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                        item.setGsalary((int)Integer.parseInt(String.valueOf(PESrow1.getText())));
//                        item.setExUSs((int)Integer.parseInt(String.valueOf(PESrow2.getText())));
//                        item.setIncTax((int)Integer.parseInt(String.valueOf(PESrow3.getText())));
//                        item.setProvfund((int)Integer.parseInt(String.valueOf(PESrow4.getText())));
//                        item.setIncTax((int)Integer.parseInt(String.valueOf(PESrow5.getText())));
////                viewmodel.queryRepos();
//                viewmodel.update(item);
//                finish();
////                imageChooser();
//            }
//        });
////        viewmodel.getData().observe(this,data->{
////
////           incomefromprevDis.setText(String.valueOf(data.calculated()));
////        });
////        setTitle("Income Tax Calculator");
//    }
//    void imageChooser() {
//
//        // create an instance of the
//        // intent of the type image
//        Intent i = new Intent();
//        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
//
//        // pass the constant to compare it
//        // with the returned requestCode
//        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
//    }
//
//    // this function is triggered when user
//    // selects the image from the imageChooser
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//
//            // compare the resultCode with the
//            // SELECT_PICTURE constant
//            if (requestCode == SELECT_PICTURE) {
//                // Get the url of the image from data
//                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    IVPreviewImage.setImageURI(selectedImageUri);
//                }
//            }
//        }
//    }
//    @Override
//    public void onBackPressed() {
//
//
//        super.onBackPressed();
//        finish();
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_income_from_pes, container, false);
        Bundle intent=getArguments();

        PESclass item=(PESclass) intent.getSerializable("PESitem");

//        IVPreviewImage = v.findViewById(R.id.IVPreviewImage);
        PESrow1=v.findViewById(R.id.PESrow1);
        PESrow2=v.findViewById(R.id.PESrow2);
        PESrow3=v.findViewById(R.id.PESrow3);
        PESrow4=v.findViewById(R.id.PESrow4);
        PESrow5=v.findViewById(R.id.PESrow5);
        savebtn=v.findViewById(R.id.PESsaveBtn);
        cancelbtn=v.findViewById(R.id.PESCancel);
        PESrow1.setText(String.valueOf(item.getGsalary()));
        PESrow2.setText(String.valueOf(item.getExUSs()));
        PESrow3.setText(String.valueOf(item.getProfTax()));
        PESrow4.setText(String.valueOf(item.getProvfund()));
        PESrow5.setText(String.valueOf(item.getIncTax()));
        viewmodel= new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setGsalary((int)Integer.parseInt(String.valueOf(PESrow1.getText())));
                item.setExUSs((int)Integer.parseInt(String.valueOf(PESrow2.getText())));
                item.setIncTax((int)Integer.parseInt(String.valueOf(PESrow3.getText())));
                item.setProvfund((int)Integer.parseInt(String.valueOf(PESrow4.getText())));
                item.setIncTax((int)Integer.parseInt(String.valueOf(PESrow5.getText())));

                viewmodel.setPesItem(item);
//                finish();
//                imageChooser();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        return v;
    }
}