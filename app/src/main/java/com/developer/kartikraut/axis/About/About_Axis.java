package com.developer.kartikraut.axis.About;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class About_Axis extends Fragment {

    ImageView inaugimg;

    public  static About_Axis newInstance(){
        About_Axis fragment = new About_Axis();
        return fragment;
    }

    public About_Axis() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about__axis, container, false);
        inaugimg = (ImageView)rootView.findViewById(R.id.inaugimg);
        WebView webView = (WebView)rootView.findViewById(R.id.abt_axis);
        String text;
        text = "<html><body> <p align=\"justify\" style=\"padding:8px;\"body{color: #000; background-color: #000;}>";
        text+= "AXIS is the annual technical festival of the Visvesvaraya National Institute of Technology, Nagpur.\n" +
                "        Traditionally held in the month of September or early October, AXIS has grown to become one of the largest\n" +
                "        technical festivals in India and the largest in Central India. AXIS organises over 40 events, exhibitions and\n" +
                "        workshops, encompassing multiple engineering disciplines, with an aim to provide a national platform to attract\n" +
                "        and nurture talent in the fields of science and technology. AXIS also undertakes various social initiatives which\n" +
                "        strive to bring about a change in the lives of those less fortunate than themselves. Started in 2004 after merging\n" +
                "        two traditionally held festivals - the IEEE Expressions and Odyssey, AXIS has come a long way from its modest beginnings\n" +
                "        and annually attracts over 25,000 school and college students from different parts of the country.";
        text+= "</p></body></html>";
        text = "<font color='white'>" + text + "</font>";
        webView.loadData(text, "text/html", "utf-8");
        webView.setBackgroundColor(Color.parseColor("#232c3d"));


        WebView inaug = (WebView)rootView.findViewById(R.id.inaug);
        String text1;
        text1 = "<html><body><p align=\"justify\" style=\"padding:8px;\">";
        text1+= "The most awaited inaugural addresses of AXIS have been delivered by some of the most distinguished\n" +
                "        personalities in the field of science and technology. Known for being interactive, inspiring and instigating,\n" +
                "        they provide students an opportunity to closely interact with their role models.\n" +
                "        AXIS'14 received the most auspicious inauguration by Dr. APJ Abdul Kalam (late Former President of India and founder\n" +
                "        of India's missile program) Dr. Kalam's motivating oration was aimed at moulding the leaders of tomorrow, and his unforgettable\n" +
                "        words - \"I will fly, I will fly, I will fly\" remain etched in the memories of every person present in the audience on that\n" +
                "        memorable day. AXIS'15 saw Dr. Dinesh Keskar(VNIT Alumnus and Senior Vice President, Boeing Commercial Airplanes) revisit his\n" +
                "        alma mater to inaugurate its technical festival which has grown in leaps and bounds since his time. As an engineer himself,\n" +
                "        Dr. Keskar delivered an extremely relatable depiction of his journey from a VNIT graduate to working under Neil Armstrong,\n" +
                "        and instilled in the audience a sense of purpose and a firm belief that they have it in themselves to scale the pinnacles of success.";
        text1 = "<font color='white'>" + text + "</font>";
        text+= "</p></body></html>";
        inaug.loadData(text1, "text/html", "utf-8");
        inaug.setBackgroundColor(Color.parseColor("#232c3d"));

        //return super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

}
