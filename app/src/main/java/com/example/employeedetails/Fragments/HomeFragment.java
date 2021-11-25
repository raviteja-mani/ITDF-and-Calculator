package com.example.employeedetails.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.employeedetails.Adapters.CustomDashboardGridAdapter;
import com.example.employeedetails.AppSession;
import com.example.employeedetails.ModalClasses.DashboardItem;
import com.example.employeedetails.ModalClasses.User;
import com.example.employeedetails.R;
import com.example.employeedetails.RoundedImageView;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    AppSession session;
    RoundedImageView imageView;
    GridView gridView;
    ArrayList<DashboardItem> gridArray;
    String selectedGridItem;
    TextView txtFullName;
    TextView txtCompany;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Stohrm");
        View rootView=inflater.inflate(R.layout.fragment_home, container, false);
        Context mContext = getActivity().getApplicationContext();
        imageView=rootView.findViewById(R.id.userPhoto);
        txtFullName=rootView.findViewById(R.id.txtFullName);
        txtCompany=rootView.findViewById(R.id.txtCompany);
        session=new AppSession(getActivity().getApplicationContext());
        imageView.setImageBitmap(session.getProfileBitmap());
        User user= session.getUser();
        txtFullName.setText(user.getUserFullName());
        txtCompany.setText(user.getCompanyName());
        LinearLayout ltDashboard = rootView.findViewById(R.id.ltDashboard);
        Drawable[] layers = new Drawable[2];
        layers[0] = ContextCompat.getDrawable(mContext, R.drawable.dashboard_bg);
        layers[1] = ContextCompat.getDrawable(mContext, R.drawable.bg_overlay_blur_image);
        layers[0].setAlpha(255);
        layers[1].setAlpha(50);
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        ltDashboard.setBackground(layerDrawable);
        gridView=rootView.findViewById(R.id.gridDashboard);
        loadGrid(rootView);
        return rootView;
    }
    public void loadGrid(View rootView){
        Bitmap dtcIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_taxcalc);
        Bitmap itdfIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_incometax);
        Bitmap profileIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_profile);
//        Bitmap settings=BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_baseline_settings_24);
        gridArray=new ArrayList<>();
        gridArray.add(new DashboardItem("MyProfile",profileIcon));
        gridArray.add(new DashboardItem("DeclarationForm",itdfIcon));
        gridArray.add(new DashboardItem("taxCalculator",dtcIcon));
        CustomDashboardGridAdapter adapter =new CustomDashboardGridAdapter(gridArray,R.layout.fragment_gridview,getContext());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        try {
            DashboardItem selectedItem = gridArray.get(position);
            selectedGridItem = selectedItem.getTitle();
            ((OnGridviewItemSelectedListener) getActivity()).onGridviewItemPicked(selectedGridItem);
        } catch (ClassCastException cce) {
            //todo
        }
    }

    public interface OnGridviewItemSelectedListener {
        void onGridviewItemPicked(String selectedGridItem);
    }
}