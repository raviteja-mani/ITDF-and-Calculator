package com.example.employeedetails.Fragments.ITDF;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.employeedetails.AppSession;
import com.example.employeedetails.R;


public class DeclarationFragment extends Fragment {
    private OldRegimeFragment oldRegimeFragment=new OldRegimeFragment();
//    private NewRegimeFragment newRegimeFragment=new NewRegimeFragment();
    private Button submit;
    AppSession session;
    TextView error;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1= inflater.inflate(R.layout.fragment_declaration, container, false);
        getActivity().setTitle("Tax Declaration");
        RadioGroup group=(RadioGroup) view1.findViewById(R.id.radioGroup);
        session=new AppSession(getContext());
//System.out.println(R.id.radioOldTax);
        submit=view1.findViewById(R.id.regimeSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rbt=(view1.findViewById(group.getCheckedRadioButtonId()));
                String radio=rbt.getText().toString();
//                System.out.println(radio+"brodll");
                if(radio.equals("Old Tax Regime")){
                    session.setRegimeType("oldRegime");
                    FragmentManager fragmentManager=getFragmentManager();
//                    fragmentManager.beginTransaction().addToBackStack("declaration").replace(R.id.frameLayoutContainer,oldRegimeFragment).commit();
                    fragmentManager.popBackStack();
                }
                else if(radio.equals("New Tax Regime")){
                    session.setRegimeType("newRegime");
                    FragmentManager fragmentManager=getFragmentManager();
//                    fragmentManager.beginTransaction().addToBackStack("declaration").replace(R.id.frameLayoutContainer,new IncomeFragment()).commit();
                    fragmentManager.popBackStack();
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