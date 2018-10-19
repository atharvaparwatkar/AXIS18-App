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
public class CoreTeamFragment extends Fragment {

    RecyclerView rv_coreTeam;
    FrameLayout fl_coreTeam;

    private ArrayList<ContactClass> coreteams;

    public CoreTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_core_team, container, false);
        ButterKnife.bind(this,view);

        fl_coreTeam = (FrameLayout) view.findViewById(R.id.fl_coreTeam);
        rv_coreTeam = (RecyclerView)view.findViewById(R.id.rv_coreTeam);

        coreteams = new ArrayList<>();

        coreteams.add(new ContactClass("CORE COORDINATOR","Ashlesha Anandikar",R.drawable.c1aamin,"+91 7588741622","ashlesha.anandikar@axisvnit.org"));
        coreteams.add(new ContactClass("CORE COORDINATOR", "Eshant Kubde",R.drawable.kubmin1,"+91 8446305371","eshant.kubde@axisvnit.org"));
        coreteams.add(new ContactClass("CORE COORDINATOR",  "Harshal Charde",R.drawable.chardemin1,"+91 8983973404","harshal.charde@axisvnit.org"));
        coreteams.add(new ContactClass("CORE COORDINATOR", "Prasad Mudde",R.drawable.muddemin1,"+91 9422021422","prasad.mudde@axisvnit.org"));
        coreteams.add(new ContactClass("CORE COORDINATOR", "Ritvik Joshi",R.drawable.c5rjmin,"+91 9823215450","ritvik.joshi@axisvnit.org"));
        coreteams.add(new ContactClass("CORE COORDINATOR", "Siva Kumar Lakkoju",R.drawable.c6sklmin,"+91 7382964696","sivakumar.lakkoju@axisvnit.org"));
        coreteams.add(new ContactClass("CORE COORDINATOR", "Sumedh Tawade",R.drawable.sumedhmin1,"+91 7038919266","sumedh.tawade@axisvnit.org"));
        coreteams.add(new ContactClass("TREASURER","Dhawal Renge",R.drawable.tdrmin,"+91 8087711063","dhawal.renge@axisvnit.org"));
        coreteams.add(new ContactClass("PUBLICITY-IN-CHARGE","Amar Poosarla",R.drawable.pc1apmin,"+91 7028399800","amar.poosarla@axisvnit.org"));
        coreteams.add(new ContactClass("PUBLICITY-IN-CHARGE","Ashwarya Jain",R.drawable.jainmin1,"+91 9828458618","ashwarya.jain@axisvnit.org"));
        coreteams.add(new ContactClass("PUBLICITY-IN-CHARGE","Mirza Ahmed",R.drawable.pc3mamin,"+91 8551992973","ahmed.mirza@axisvnit.org"));
        coreteams.add(new ContactClass("PUBLICITY-IN-CHARGE","Prachi Dewangan",R.drawable.prachimin1,"+91 7697755244","prachi.dewangan@axisvnit.org"));
        coreteams.add(new ContactClass("PUBLICITY-IN-CHARGE","Rugved Gharote",R.drawable.pc5rgmin,"+91 7756988911","rugved.gharote@axisvnit.org"));


        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv_coreTeam.setHasFixedSize(true);
        rv_coreTeam.setLayoutManager(llm);

        Contact_Adapter adapter = new Contact_Adapter(getContext(),coreteams);
        rv_coreTeam.setAdapter(adapter);

        Toast.makeText(getContext(),"Click on person to call or send email",Toast.LENGTH_SHORT).show();


        return view;
    }

}
