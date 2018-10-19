package com.developer.kartikraut.axis.Events;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.kartikraut.axis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Categories_Fragment extends Fragment {

    @BindView(R.id.rvCategories)
    RecyclerView rvCategories;

    private List<CategoryClass> categories;

    public Categories_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this,view);

        initializeData();

      //  LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        GridLayoutManager glm  = new GridLayoutManager(getContext(),2);
        rvCategories.setHasFixedSize(true);
        rvCategories.setLayoutManager(glm);

        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(getContext(), categories);
        rvCategories.setAdapter(adapter);
        getActivity().setTitle("EVENTS");
        return view;
    }

    private void initializeData() {

        categories = new ArrayList<>();
        categories.add(new CategoryClass("AUTOMATION & ROBOTICS", R.drawable.automin));
        categories.add(new CategoryClass("SOFTWARE & ELECTRONICS", R.drawable.softmin));
        categories.add(new CategoryClass("CONSTRUCTION & DESIGN", R.drawable.constmin));
        categories.add(new CategoryClass("SCHOOL EVENTS", R.drawable.schoolevents2min));
        categories.add(new CategoryClass("MANAGEMENT & OTHER", R.drawable.management2min));
        categories.add(new CategoryClass("AXIS IGNITING MINDS", R.drawable.ignitingmin));
    }

}
