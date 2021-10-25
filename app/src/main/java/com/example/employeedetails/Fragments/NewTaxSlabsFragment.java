package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.example.employeedetails.R;


public class NewTaxSlabsFragment extends Fragment {

    WebView web;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_new_tax_slabs, container, false);
        web=v.findViewById(R.id.newtaxSlabs);
        button=v.findViewById(R.id.okbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        String s="<html><body>" +
                "   <div  data-collapsed=\"0\">\n" +
                "\t\t    <div class=\"table-responsive\">\n" +
                "\t\t\t\t<table>\n" +
                "\t\t\t\t<thead>\n" +
                "\t\t\t\t<tr><th class=\"table-header\" colspan=\"2\">New Tax Regime</th></tr>\n" +
                "\t\t\t\t</thead>\n" +
                "\t\t\t\t<tbody>\n" +
                "\t\t\t\t<tr><td colspan='2'>The tax liability for Financial Year 2020-21 is calculated after considering the following changes, proposed in Finance Bill 2020</td></tr>\n" +
                "\t\t\t\t<tr><td colspan='2'><strong>New and simplified Personal Income Tax Regime (New Tax Regime)</strong><br />\n" +
                "\t\t\t\tOn satisfaction of certain conditions, an individual will have an option to pay tax at the reduced slab rates (mentioned below) which are applicable without exemptions and deductions:<br /><br />\n" +
                "\t\t\t\t<ol>\n" +
                "\t\t\t\t<li>Income upto 250000 - Nil</li>\n" +
                "\t\t\t\t<li>250001 to 500000 - 5%</li>\n" +
                "\t\t\t\t<li>500001 to 750000 - 10%</li>\n" +
                "\t\t\t\t<li>750001 to 1000000 - 15%</li>\n" +
                "\t\t\t\t<li>1000001 to 1250000 - 20%</li>\n" +
                "\t\t\t\t<li>1250001 to 1500000 - 25%</li>\n" +
                "\t\t\t\t<li>Above 1500000 - 30%</li>\n" +
                "\t\t\t\t</ol>\n" +
                "\t\t\t\t<br />\n" +
                "\t\t\t\tAround 70 exemptions and deductions would be removed under the simplified income tax regime like House Rent Allowance, \n" +
                "\t\t\t\tLeave Travel Concession, Standared deduction on Salary, deduction of professional tax and most of the \n" +
                "\t\t\t\tdeductions available under Chapter VI-A of the Act e.g deduction u/s 80C (life insurance etc.), 80CCD(1B) (your contribution to NPS upto INR 50,000), \n" +
                "\t\t\t\t80D (Medical insurance), 80E (interest on education loan) etc.<br /><br />\n" +
                "\n" +
                "\t\t\t\tFollowing deductions/exemptions (considered in above computation) are not available under the new tax Regime <br /><br />\n" +
                "\t\t\t\t<ol>\n" +
                "\t\t\t\t\t<li>Standared deduction on salary of INR 50,000</li>\n" +
                "\t\t\t\t\t<li>Exemption of House Rent Allowance (HRA)</li>\n" +
                "\t\t\t\t\t<li>Chapter VI-A u/s 80C (life insurance etc.), 80D (Medical insurance), employees contribution to NPS u/s 80CCD(1B) and 80E (interest on housing loan)</li>\n" +
                "\t\t\t\t</ol>\n" +
                "\t\t\t\t</td></tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t    </div>\n" +
                "\t\t  </div> </body></html>";
        web.loadData(s,"text/html", "UTF-8");
        return v;
    }
}