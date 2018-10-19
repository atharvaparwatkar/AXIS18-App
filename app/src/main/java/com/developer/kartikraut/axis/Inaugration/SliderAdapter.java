package com.developer.kartikraut.axis.Inaugration;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.kartikraut.axis.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;
    List<InaugClass> inaugList;

    public SliderAdapter(Context context,List<InaugClass> inaugList) {
        this.context = context;
        this.inaugList = inaugList;
    }


    @Override
    public int getCount() {
        return inaugList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        CircleImageView speaker_image = (CircleImageView)view.findViewById(R.id.speaker_image);
        TextView speaker_name = (TextView) view.findViewById(R.id.speaker_heading);
        TextView speaker_desc = (TextView)view.findViewById(R.id.speaker_desc);

        speaker_name.setText(inaugList.get(position).getSpeaker_name());
        speaker_desc.setText(inaugList.get(position).getSpeaker_desc());

        Glide.with(context).load(inaugList.get(position).getSpeaker_image())
                .into(speaker_image);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
