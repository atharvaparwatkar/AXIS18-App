package com.developer.kartikraut.axis.About;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kartikraut.axis.Going_To.Events_GoingTo;
import com.developer.kartikraut.axis.Going_To.GoingTo;
import com.developer.kartikraut.axis.Going_To.WS_GoingTo;
import com.developer.kartikraut.axis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class About_fragment extends Fragment {

    View view;
    Adapter adapter;

    private void setupViewPager(ViewPager viewPager)
    {
        adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new About_Axis(),"AXIS");
        adapter.addFragment(new About_VNIT(),"VNIT");
        viewPager.setAdapter(adapter);
    }

    public About_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_fragment,container,false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this,view);
        final ViewPager viewPager = ButterKnife.findById(view,R.id.about_viewpager);
        setupViewPager(viewPager);
        final TabLayout tabLayout = ButterKnife.findById(view,R.id.about_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00cc00"));
        getActivity().setTitle("ABOUT");
        return view;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
