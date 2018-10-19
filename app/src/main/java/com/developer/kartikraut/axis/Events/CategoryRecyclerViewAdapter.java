package com.developer.kartikraut.axis.Events;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.kartikraut.axis.Axis_Home.Home;
import com.developer.kartikraut.axis.R;


import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>{

    List<CategoryClass> categoryClassList;
    Context context;
    CategoryClass category_fragment = null;
    String ps,rules,details;

    public CategoryRecyclerViewAdapter(Context context, List<CategoryClass> categoryClassList){
        this.categoryClassList = categoryClassList;
        this.context=context;
    }

    private Context getContext(){
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories1,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CategoryClass categoryClass = categoryClassList.get(position);

     //   holder.tvCategory.setText(categoryClass.getTitle());
     //   holder.tvCategory.setVisibility(View.INVISIBLE);

          Glide.with(getContext()).load(categoryClass.getimageId())
                .thumbnail(0.5f)
                .crossFade()
                .error(R.mipmap.ic_axis_app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivCategory);

    }

    @Override
    public int getItemCount() {
        return categoryClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.ivCategory)
        ImageView ivCategory;
     //   @BindView(R.id.tvCategory)
     //   TextView tvCategory;
        @BindView(R.id.cvCategory)
        CardView cvCategory;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CategoryClass categorySelected = categoryClassList.get(getAdapterPosition());
            fragmentJump(categorySelected);
        }
    }

    private void fragmentJump(CategoryClass categorySelected) {
        Intent intent = null;
        Fragment mFragment = null;
        String s;
        String title = categorySelected.getTitle();
        if(title == "AUTOMATION & ROBOTICS") {
            mFragment = new Auto_Robo_Fragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "SOFTWARE & ELECTRONICS") {
            mFragment = new Soft_Elec_Fragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "CONSTRUCTION & DESIGN"){
            mFragment = new Construction_Fragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "SCHOOL EVENTS"){
            mFragment = new School_Events_Fragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "MANAGEMENT & OTHER"){
            mFragment = new Manage_Other_Fragment();
            switchContent(R.id.flContent, mFragment,title);
        }
        else if(title == "AXIS IGNITING MINDS"){
            mFragment = new Igniting_Minds_Fragment();
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
