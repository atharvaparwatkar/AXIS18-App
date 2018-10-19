package com.developer.kartikraut.axis.Initiatives;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.kartikraut.axis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.developer.kartikraut.axis.R.string.earthHour;

/**
 * A simple {@link Fragment} subclass.
 */
public class Socialragment extends Fragment {

    RecyclerView rv_SI;
    List<SocialClass> socialList;

    public Socialragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_socialragment, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("SOCIAL INITIATIVES");

        initializeData();

        rv_SI = (RecyclerView)view.findViewById(R.id.rv_SI);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv_SI.setLayoutManager(llm);

        SocialAdapter socialAdapter = new SocialAdapter(this.getContext(),socialList);
        rv_SI.setAdapter(socialAdapter);

        return view;
    }

    private void initializeData() {
        socialList = new ArrayList<>();
        socialList.add(new SocialClass(R.drawable.earthhourmin,"EARTH HOUR" ));
        socialList.add(new SocialClass(R.drawable.energy_conservation_daymin,"WORLD ENERGY CONSERVATION DAY"));
        socialList.add(new SocialClass(R.drawable.blood_donationmin,"BLOOD DONATION"));
    }

}
