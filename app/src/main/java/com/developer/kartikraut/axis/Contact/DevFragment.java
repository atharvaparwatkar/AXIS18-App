package com.developer.kartikraut.axis.Contact;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.developer.kartikraut.axis.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevFragment extends Fragment {

    RecyclerView rv_dev;
    FrameLayout fl_dev;

    private ArrayList<ContactClass> devs;

    public DevFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dev, container, false);
        ButterKnife.bind(this,view);

        fl_dev = (FrameLayout)view.findViewById(R.id.fl_dev);
        rv_dev = (RecyclerView)view.findViewById(R.id.rv_dev);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv_dev.setHasFixedSize(true);
        rv_dev.setLayoutManager(llm);

        Contact_Adapter adapter = new Contact_Adapter(getContext(),devs);
        rv_dev.setAdapter(adapter);

        Toast.makeText(getContext(),"Click on person to call or send email",Toast.LENGTH_SHORT).show();

        return view;
    }

    private void initializeData() {
        devs = new ArrayList<>();
        devs.add(new ContactClass("APP DEVELOPER","Kartik Raut",R.drawable.dev_rautmin,"+91 8408011312","kartikraut2497@gmail.com"));
        devs.add(new ContactClass("APP/WEB DEVELOPER","Atharva Parwatkar",R.drawable.dev_atharvamin,"+91 9405908448","atharva.parwatkar@gmail.com"));
        devs.add(new ContactClass("WEB DEVELOPER","Sharvil Nagarkar",R.drawable.dev_sharvilmin,"+91 9172323140","sharvilnagarkar@gmail.com"));
        devs.add(new ContactClass("WEB DEVELOPER","Abhishek Gautam",R.drawable.dev_abhishekmin,"+91 8392988541"," gautam.abhisihek46@gmail.com"));
        devs.add(new ContactClass("WEB DEVELOPER","Saurabh Rathi",R.drawable.dev_rathimin,"+91 8793042721","saurabhrathi1234@gmail.com"));
    }

}
