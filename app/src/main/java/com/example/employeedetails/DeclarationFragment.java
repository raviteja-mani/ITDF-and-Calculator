package com.example.employeedetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class DeclarationFragment extends Fragment {
    private OldRegimeFragment oldRegimeFragment=new OldRegimeFragment();
    private NewRegimeFragment newRegimeFragment=new NewRegimeFragment();
    private Button submit;
    TextView error;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1= inflater.inflate(R.layout.fragment_declaration, container, false);
        RadioGroup group=(RadioGroup) view1.findViewById(R.id.radioGroup);
System.out.println(R.id.radioOldTax);
        submit=view1.findViewById(R.id.regimeSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rbt=(view1.findViewById(group.getCheckedRadioButtonId()));
                String radio=rbt.getText().toString();
                System.out.println(radio+"brodll");
                if(radio.equals("Old Tax Regime")){

                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("declaration").replace(R.id.frameLayoutContainer,oldRegimeFragment).commit();

                }
                else if(radio.equals("New Tax Regime")){
                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("declaration").replace(R.id.frameLayoutContainer,newRegimeFragment).commit();

                }
                else{
                    error=view.findViewById(R.id.declarationError);
                    error.setVisibility(view.VISIBLE);
                }
            }
        });

        return view1;
    }
}