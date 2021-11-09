package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.employeedetails.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class OldRegimeFragment extends Fragment {
private TabLayout tabLayout;
private ViewPager viewPager;
   private oldRegimeGiudelinesFragment oldregimeGiudelinesFragment;
//    Toolbar tool;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       oldregimeGiudelinesFragment= new oldRegimeGiudelinesFragment();
        View view=inflater.inflate(R.layout.fragment_old_regime, container, false);
        tabLayout=view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager(),0);
        viewPagerAdapter.addFragment(oldregimeGiudelinesFragment,"Guidelines");
        viewPagerAdapter.addFragment(new HRAExcemptionFragment(),"HRA Excemption");
        viewPagerAdapter.addFragment(new IncomeFragment(),"Income");
        viewPagerAdapter.addFragment(new InvestmentsFragment(),"Investments");
        viewPager.setAdapter(viewPagerAdapter);
        return view;
    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}