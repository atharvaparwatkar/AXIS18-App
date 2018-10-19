package com.developer.kartikraut.axis.Initiatives;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.kartikraut.axis.R;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;



public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ViewHolder> {

    List<SocialClass> SocialList;
    Context context;

    public SocialAdapter(Context context, List<SocialClass> SocialList) {
        this.context=context;
        this.SocialList=SocialList;
    }

    private Context getContext(){
        return context;
    }

    @Override
    public SocialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social,parent, false);
        return new SocialAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SocialAdapter.ViewHolder holder, int position) {

        SocialClass socialClass = SocialList.get(position);

        holder.tvSocialTitle.setText(socialClass.getTitle());

        holder.ivSocialImage.setImageResource(socialClass.getImageId());

        Glide.with(getContext()).load(socialClass.getImageId())
                .thumbnail(0.5f)
                .crossFade()
                // .placeholder(R.mipmap.ic_axis_app_logo)
                .error(R.mipmap.ic_axis_app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivSocialImage);

    }

    @Override
    public int getItemCount() {
        return SocialList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.ivSocialImage)
        ImageView ivSocialImage;

        @BindView(R.id.tvSocialTitle)
        TextView tvSocialTitle;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent i;
            SocialClass socialClass = SocialList.get(getAdapterPosition());
            String title = socialClass.getTitle();

            if(title=="EARTH HOUR") {
                i = new Intent(getContext(), EarthHour.class);
                getContext().startActivity(i);
            }
            else if(title=="WORLD ENERGY CONSERVATION DAY"){
                i = new Intent(getContext(),Conservation.class);
                getContext().startActivity(i);
            }
            else
            {
                i = new Intent(getContext(),BloodDonation.class);
                getContext().startActivity(i);
            }
        }
    }
}
