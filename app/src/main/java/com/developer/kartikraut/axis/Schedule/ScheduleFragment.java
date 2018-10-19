package com.developer.kartikraut.axis.Schedule;


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
import android.widget.Adapter;

import com.developer.kartikraut.axis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    View view;
    Adapter adapter;

    private void setupViewPager(ViewPager viewPager)
    {
        adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FirstDayFragment(),"23rd FEB");
        adapter.addFragment(new SecondDayFragment(),"24th FEB");
        adapter.addFragment(new ThirdDayFragment(),"25th FEB");
        viewPager.setAdapter(adapter);
    }

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_schedule,container,false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this,view);
        final ViewPager viewPager = ButterKnife.findById(view,R.id.schedule_viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = ButterKnife.findById(view,R.id.schedule_tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00cc00"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setupWithViewPager(viewPager);
        getActivity().setTitle("SCHEDULE");
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
