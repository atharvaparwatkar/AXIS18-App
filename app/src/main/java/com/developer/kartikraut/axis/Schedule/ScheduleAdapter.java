package com.developer.kartikraut.axis.Schedule;

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
import com.developer.kartikraut.axis.Talks.TalkActivity;
import com.developer.kartikraut.axis.Talks.TalkAdapter;
import com.developer.kartikraut.axis.Talks.TalkClass;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    Context context;
    List<ScheduleClass> scheduleClasses;

    public ScheduleAdapter(Context context, List<ScheduleClass> scheduleClasses) {
        this.context = context;
        this.scheduleClasses = scheduleClasses;
    }

    private Context getContext()
    {
        return context;
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule,parent, false);
        return new ScheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {

        ScheduleClass scheduleClass = scheduleClasses.get(position);

        holder.event_name.setText(scheduleClass.getName());
        holder.event_time.setText(scheduleClass.getTime());
        holder.event_venue.setText(scheduleClass.getVenue());
    }

    @Override
    public int getItemCount() {
        return scheduleClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.event_name)
        TextView event_name;

        @BindView(R.id.event_time)
        TextView event_time;

        @BindView(R.id.event_venue)
        TextView event_venue;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}