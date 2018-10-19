package com.developer.kartikraut.axis.Events;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{

    List<EventsClass> eventsClassList;
    Context context;
    CategoryClass category_fragment = null;
    String ps,rules,details;


    public EventsAdapter(Context context, List<EventsClass> eventsClassList){
        this.eventsClassList = eventsClassList;
        this.context=context;
    }

    private Context getContext(){
        return context;
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories,parent, false);
        return new EventsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder holder, int position) {

        EventsClass eventsClass = eventsClassList.get(position);

        holder.tvCategory.setText(eventsClass.getName().toUpperCase());

        Glide.with(getContext()).load(eventsClass.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .error(R.mipmap.ic_axis_app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivCategory);
    }

    @Override
    public int getItemCount() {
        return eventsClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.ivCategory)
        ImageView ivCategory;
        @BindView(R.id.tvCategory)
        TextView tvCategory;
        @BindView(R.id.cvCategory)
        CardView cvCategory;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventsClass eventsClass = eventsClassList.get(getAdapterPosition());
            Intent i = new Intent(getContext(),EventsActivity.class);
            i.putExtra("eventObj",eventsClass);
            getContext().startActivity(i);
        }
    }
}
