package com.developer.kartikraut.axis.Axis_Home;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developer.kartikraut.axis.Constants;
import com.developer.kartikraut.axis.Contact.ContactUs;
import com.developer.kartikraut.axis.Events.Categories_Fragment;
import com.developer.kartikraut.axis.Initiatives.Socialragment;
import com.developer.kartikraut.axis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import com.developer.kartikraut.axis.Workshops.WSFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    FloatingActionButton fab_share,fab_fb,fab_twitter,fab_youtube,fab_insta,fab_linkedin;
    Animation fab_open,fab_close,fab_clock,fab_anticlock;
    Boolean isOpened;
    RecyclerView rv_home;
    Handler setDelay;
    Runnable startDelay;
    List<DataModel> dataModelList;

    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_, container,false);
        ButterKnife.bind(this,v);
        getActivity().setTitle("HOME");

        fab_share = (FloatingActionButton)v.findViewById(R.id.fab_share);
        fab_fb = (FloatingActionButton)v.findViewById(R.id.fab_fb);
        fab_insta = (FloatingActionButton)v.findViewById(R.id.fab_insta);
        fab_linkedin = (FloatingActionButton)v.findViewById(R.id.fab_linkedin);
        fab_twitter = (FloatingActionButton)v.findViewById(R.id.fab_twitter);
        fab_youtube = (FloatingActionButton)v.findViewById(R.id.fab_youtube);

        fab_open = AnimationUtils.loadAnimation(getContext(),R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        fab_clock = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_clockwise);
        fab_anticlock = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_anticlockwise);
        rv_home = (RecyclerView)v.findViewById(R.id.rv_Home) ;
        isOpened = false;

        Constants.instance(getContext());

        initializeHome();

        fab_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpened){
                    fab_fb.startAnimation(fab_close);
                    fab_fb.setClickable(false);
                    fab_insta.startAnimation(fab_close);
                    fab_insta.setClickable(false);
                    fab_linkedin.startAnimation(fab_close);
                    fab_linkedin.setClickable(false);
                    fab_twitter.startAnimation(fab_close);
                    fab_twitter.setClickable(false);
                    fab_youtube.startAnimation(fab_close);
                    fab_youtube.setClickable(false);
                    fab_share.startAnimation(fab_anticlock);
                    isOpened=false;
                }
                else
                {
                    fab_fb.startAnimation(fab_open);
                    fab_fb.setClickable(true);
                    fab_insta.startAnimation(fab_open);
                    fab_insta.setClickable(true);
                    fab_linkedin.startAnimation(fab_open);
                    fab_linkedin.setClickable(true);
                    fab_twitter.startAnimation(fab_open);
                    fab_twitter.setClickable(true);
                    fab_youtube.startAnimation(fab_open);
                    fab_youtube.setClickable(true);
                    fab_share.startAnimation(fab_clock);
                    isOpened=true;

                    fab_fb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.facebook.com/axisvnit";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });

                    fab_insta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.instagram.com/axis_vnit/";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });

                    fab_twitter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://twitter.com/axisvnit";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });

                    fab_linkedin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.linkedin.com/company/axis-vnit-nagpur";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });

                    fab_youtube.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.youtube.com/user/AXISVNIT";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    });
                }
            }
        });

        GridLayoutManager glm = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        rv_home.setLayoutManager(glm);
        rv_home.setHasFixedSize(true);

        HomeAdapter adapter = new HomeAdapter(getContext(),dataModelList);
        rv_home.setAdapter(adapter);

        return v;
    }

    private void initializeHome() {
        dataModelList = new ArrayList<>();
        dataModelList.add(new DataModel("EVENTS",R.drawable.ic_home_event,"#333333"));
        dataModelList.add(new DataModel("WORKSHOPS",R.drawable.ic_home_ws,"#7395ae"));
        dataModelList.add(new DataModel("EXHIBITIONS",R.drawable.ic_home_exhib,"#557a95"));
        dataModelList.add(new DataModel("TALKS",R.drawable.ic_home_talks_white,"#837d84"));
        dataModelList.add(new DataModel("LECTURES",R.drawable.ic_home_lec,"#9c949d"));
        dataModelList.add(new DataModel("INITIATIVES",R.drawable.ic_home_social,"#395872"));

    }

    public void switchHomeContent(int id, Fragment fragment, String fragment_title) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
        getActivity().setTitle(fragment_title);
    }

}
