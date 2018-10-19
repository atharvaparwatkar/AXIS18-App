package com.developer.kartikraut.axis.Axis_Home;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.kartikraut.axis.Events.Auto_Robo_Fragment;
import com.developer.kartikraut.axis.Events.Categories_Fragment;
import com.developer.kartikraut.axis.Events.CategoryClass;
import com.developer.kartikraut.axis.Events.Construction_Fragment;
import com.developer.kartikraut.axis.Events.EventsActivity;
import com.developer.kartikraut.axis.Events.EventsAdapter;
import com.developer.kartikraut.axis.Events.EventsClass;
import com.developer.kartikraut.axis.Events.Igniting_Minds_Fragment;
import com.developer.kartikraut.axis.Events.Manage_Other_Fragment;
import com.developer.kartikraut.axis.Events.School_Events_Fragment;
import com.developer.kartikraut.axis.Events.Soft_Elec_Fragment;
import com.developer.kartikraut.axis.Exhibitions.ExhibFragment;
import com.developer.kartikraut.axis.GuestLectures.GuestFragment;
import com.developer.kartikraut.axis.Initiatives.Socialragment;
import com.developer.kartikraut.axis.R;
import com.developer.kartikraut.axis.Talks.TalkFragment;
import com.developer.kartikraut.axis.Workshops.WSFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    List<DataModel> dataModels;
    Context context;

    public HomeAdapter(Context context, List<DataModel> dataModels){
        this.dataModels = dataModels;
        this.context=context;
    }

    private Context getContext(){
        return context;
    }


    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent, false);
        return new HomeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {

        DataModel dataModel = dataModels.get(position);

        holder.tvHome.setText(dataModel.getText().toUpperCase());
        holder.rlHome.setBackgroundColor(Color.parseColor(dataModel.color));

        Glide.with(getContext()).load(dataModel.getDrawable())
                .thumbnail(0.5f)
                .crossFade()
                .error(R.mipmap.ic_axis_app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivHome);
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivHome;

        TextView tvHome;

        RelativeLayout rlHome;


        ViewHolder(View view){
            super(view);
            ivHome = (ImageView)view.findViewById(R.id.ivHome);
            tvHome = (TextView)view.findViewById(R.id.tvHome);
            rlHome = (RelativeLayout)view.findViewById(R.id.rlHome);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DataModel dataModel = dataModels.get(getAdapterPosition());
            fragmentJump(dataModel);
        }
    }

    private void fragmentJump(DataModel dataModel) {
        Fragment mFragment = null;
        String title = dataModel.getText();
        if(title == "EVENTS") {
            mFragment = new Categories_Fragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "WORKSHOPS") {
            mFragment = new WSFragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "EXHIBITIONS"){
            mFragment = new ExhibFragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "TALKS"){
            mFragment = new TalkFragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "LECTURES"){
            mFragment = new GuestFragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "INITIATIVES"){
            mFragment = new Socialragment();
            switchContent(R.id.flContent, mFragment,title);
        }
    }

    public void switchContent(int id, Fragment fragment,String title) {
        if (context == null)
            return;
        if (context instanceof Home) {
            Home home = (Home) context;
            Fragment frag = fragment;
            home.switchContent(id, frag,title);
        }

    }

}
