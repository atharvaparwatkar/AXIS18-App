package com.developer.kartikraut.axis.Talks;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.kartikraut.axis.R;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {

    Context context;
    List<TalkClass> talkClasses;

    public TalkAdapter(Context context, List<TalkClass> talkClasses) {
        this.context = context;
        this.talkClasses = talkClasses;
    }

    private Context getContext()
    {
        return context;
    }

    @Override
    public TalkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_talks,parent, false);
        return new TalkAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TalkAdapter.ViewHolder holder, int position) {

            TalkClass talkClass = talkClasses.get(position);

            holder.tvTalkTitle.setText(talkClass.getName());

            Glide.with(getContext()).load(talkClass.getImageurl())
                    .error(R.mipmap.ic_axis_app_logo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ivTalkImage);

    }

    @Override
    public int getItemCount() {
        return talkClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.ivTalkImage)
        ImageView ivTalkImage;

        @BindView(R.id.tvTalkTitle)
        TextView tvTalkTitle;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TalkClass object = talkClasses.get(getAdapterPosition());

            Intent i = new Intent(getContext(),TalkActivity.class);
            i.putExtra("talk", object);
            getContext().startActivity(i);

        }
    }
}
