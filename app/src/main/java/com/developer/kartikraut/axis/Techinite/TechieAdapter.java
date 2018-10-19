package com.developer.kartikraut.axis.Techinite;


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

import butterknife.ButterKnife;

public class TechieAdapter extends RecyclerView.Adapter<TechieAdapter.ViewHolder> {

    List<Techie_Class> techie_classes;
    Context context;

    public TechieAdapter(Context context, List<Techie_Class> techie_classes){
        this.techie_classes = techie_classes;
        this.context=context;
    }

    private Context getContext(){
        return context;
    }


    @Override
    public TechieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories,parent, false);
        return new TechieAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TechieAdapter.ViewHolder holder, int position) {

        Techie_Class techie_class = techie_classes.get(position);

        holder.tvCategory.setText(techie_class.getName().toUpperCase());

        Glide.with(getContext()).load(techie_class.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .error(R.mipmap.ic_axis_app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivCategory);
    }

    @Override
    public int getItemCount() {
        return techie_classes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivCategory;

        TextView tvCategory;


        ViewHolder(View view){
            super(view);
            ivCategory = (ImageView)view.findViewById(R.id.ivCategory);
            tvCategory = (TextView)view.findViewById(R.id.tvCategory);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Techie_Class techie_class = techie_classes.get(getAdapterPosition());
            Intent i = new Intent(getContext(),TechiActivity.class);
            i.putExtra("TECHI",techie_class);
            getContext().startActivity(i);
        }
    }
}
