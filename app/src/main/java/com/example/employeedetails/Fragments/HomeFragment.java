package com.example.employeedetails.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    AppSession session;
    RoundedImageView imageView;
    GridView gridView;
    ArrayList<DashboardItem> gridArray;
    String selectedGridItem;
    TextView txtFullName;
    TextView txtCompany;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Stohrm");
        View rootView=inflater.inflate(R.layout.fragment_home, container, false);
        mAuth=FirebaseAuth.getInstance();
        Context mContext = getActivity().getApplicationContext();

        imageView=rootView.findViewById(R.id.userPhoto);
        txtFullName=rootView.findViewById(R.id.txtFullName);
        txtCompany=rootView.findViewById(R.id.txtCompany);
        session=new AppSession(getActivity().getApplicationContext());
        getUserData();
        imageView.setImageBitmap(session.getProfileBitmap());
//        User user= session.getUser();
//        txtFullName.setText(user.getUsername());
//        txtCompany.setText(user.getCompanyName());
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
    public void getUserData(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        User user1=new User();
        DatabaseReference reference=database.getReference("Users").child(user.getUid());
        System.out.println(user.getUid());
//System.out.println(reference.child("companyname").getValue().toString());
        reference.child("companyname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user1.setCompanyName(snapshot.getValue().toString());
                txtCompany.setText(user1.getCompanyName());
//                error.setVisibility(View.VISIBLE);
//                error.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        bmHgieKAiIOUhpAJaB88mXVup302
        reference.child("emailId").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user1.setEmail(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user1.setUsername(snapshot.getValue(String.class));
                txtFullName.setText(user1.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        System.out.println(user1.getCompanyName());
//Log.d("tag1",user1.getUsername());
//Log.d("tag2",user1.getCompanyName());
        session.setUser(user1);
//        reference.child("password").setValue(password);
    }
}