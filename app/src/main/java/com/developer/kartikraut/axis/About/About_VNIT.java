package com.developer.kartikraut.axis.About;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.developer.kartikraut.axis.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class About_VNIT extends Fragment {

    public  static About_VNIT newInstance(){
        About_VNIT fragment = new About_VNIT();
        return fragment;
    }

    public About_VNIT() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about__vnit,container,false);
        final ImageView imageView = (ImageView)rootView.findViewById(R.id.abt_vnit_img);
        WebView abt_text = (WebView)rootView.findViewById(R.id.abt_vnit_text);
        String text;
        text = "<html><body><p align=\"justify\" style=\"padding:8px;\">";
        text+="Visvesvaraya National Institute of Technology, Nagpur (abbreviated VNIT Nagpur), also referred to as NIT Nagpur,\n" +
                "        formerly Regional College of Engineering, Nagpur (REC) and Visvesvaraya Regional College of Engineering, Nagpur (VRCE),\n" +
                "        is a premier public engineering and research institution in India. It is located in Nagpur, Maharashtra, in central India.\n" +
                "        The institute has been consistently ranked among the best fifteen engineering colleges in India. It was established in June 1960\n" +
                "        by the Government of India and later named in honor of engineer, planner and statesman, Sir Mokshagundam Visvesvaraya.\n" +
                "        VNIT Nagpur is federally-funded and belongs to the National Institutes of Technology (NIT) system. In 2007, the institute was\n" +
                "        conferred the status of Institute of National Importance (INI) alongside other NITs and IITs by an Act of Parliament of India.\n" +
                "        The institute awards bachelors, masters and doctoral degrees in engineering, technology and architecture.";
        text+= "</p></body></html>";
        text = "<font color='white'>" + text + "</font>";
        abt_text.loadData(text, "text/html", "utf-8");
        abt_text.setBackgroundColor(Color.parseColor("#232c3d"));
        return rootView;
    }

}
